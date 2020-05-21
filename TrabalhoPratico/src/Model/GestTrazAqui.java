package Model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GestTrazAqui implements IGestTrazAqui{

    private Map<String, Utilizador> users;
    private Map<String, Loja> lojas;
    private Map<String, Estafeta> estafetas;
    private Map<String, Encomenda> encomendas;
    private Map<String, Produto> produtos;
    private Map<String, Login> loginMap;

    public GestTrazAqui() {
        this.users = new HashMap<>();
        this.lojas = new HashMap<>();
        this.estafetas = new HashMap<>();
        this.encomendas = new HashMap<>();
        this.produtos = new HashMap<>();
        this.loginMap = new HashMap<>();
    }


    //--------------------------------------------------------------Métodos Utilizador--------------------------------------------------------------------------\\

    public Utilizador getUser(String userCode) {
        return users.get(userCode).clone();
    }

    public void setUser(Utilizador user) {
        users.replace(user.getCodigoUtilizador(), user);
    }

    public void addUser(Utilizador user) {
        users.put(user.getCodigoUtilizador(), user);
    }

    public List<Encomenda> getUserEncbyData(String code,int type, LocalDateTime min,LocalDateTime max) {
        List<Encomenda> list = new ArrayList<>();

        for (String c : users.get(code).getEntregas()) {
            list.add(encomendas.get(c).clone());
        }
        if(type == 1)
            list = list.stream().filter(e -> e.isEntregue() && e.isVoluntario() && e.encData(min,max)).collect(Collectors.toList());
        else if(type == 2)
            list = list.stream().filter(e -> e.isEntregue() && e.isTransportadora() && e.encData(min,max)).collect(Collectors.toList());
        else
            list = list.stream().filter(e -> e.isEntregue() && e.encData(min,max)).collect(Collectors.toList());

        return list;
    }

    public List<String> getEncReady(String userCode){
        return users.get(userCode).getEntregas().stream().filter(c -> encomendas.get(c).isAceite() && !encomendas.get(c).isEntregue()).collect(Collectors.toList());
    }

    public List<String> getTopUsers() {
        return users.values().stream().sorted().limit(10).map(c -> c.getCodigoUtilizador() + ": " + c.getName()).collect(Collectors.toList());
    }

    //---------------------------------------------------------------Métodos Estafeta--------------------------------------------------------------------------\\

    public Estafeta getEstafeta(String code) {
        return estafetas.get(code).clone();
    }

    public void setEstafeta(Estafeta estafeta) {
        estafetas.replace(estafeta.getCode(), estafeta);
    }

    public void addEstafeta(Estafeta estafeta) {
        estafetas.put(estafeta.getCode(), estafeta);
    }

    public void addEncomendaEstafeta(String code, String encCode) {
        estafetas.get(code).addEncomenda(encCode);
    }

    public String getEstafetaType(String estCode){
        return estafetas.get(estCode).getType();
    }

    public String getEstafetaName(String estCode){
        return estafetas.get(estCode).getName();
    }

    public List<Estafeta> possiveisEstafetas(String enc) {
        List<Estafeta> estafetaList;
        Coordenadas cr = lojas.get(encomendas.get(enc).getStoreCode()).getGps();
        boolean isMedic = encomendas.get(enc).isMedic();
        //se for transportadora filtrar por preço
        estafetaList = estafetas.values().stream().filter(e -> ((!isMedic || e.isMedic()) && e.isFree() && e.getGps().distancia(cr) < e.getRaio()))
                .map(Estafeta::clone).collect(Collectors.toList());

        return estafetaList;
    }

    public String escolheEstafeta(String encCode) {
        Encomenda encomenda = encomendas.get(encCode).clone();
        List<Estafeta> estafetaList = possiveisEstafetas(encCode);
        Iterator<Estafeta> it = estafetaList.iterator();
        boolean escolhido = false;
        String code = "";
        Estafeta curr;
        double maxprice = users.get(encomenda.getUserCode()).getPrecoMax();

        while (it.hasNext() && !escolhido) {
            curr = it.next();
            if(curr.getType().equals("Voluntario") || precoEncomenda(encCode,curr.getCode()) <= maxprice) {
                escolhido = true;
                code = curr.getCode();
            }
        }
        return code;
    }

    public void classificarEstafeta(double pontuacao,String code){
        estafetas.get(code).atualizaClassificacao(pontuacao);
    }

    public void setEstafetaFree(String code) {
        estafetas.get(code).setFree(true);
    }

    public List<String> getTopTrans() {
        return this.estafetas.values().stream().filter(c -> c.getType().equals("Transportadora")).sorted().limit(10).map(c -> c.getCode() + ": " + c.getName()).collect(Collectors.toList());
    }

    //-----------------------------------------------------------------Métodos Lojas--------------------------------------------------------------------------\\

    public List<String> getLojas() {
        List<String> lojas = new ArrayList<>();

        for (Loja l: this.lojas.values())
            lojas.add(l.getStoreCode() + ": " + l.getStoreName());

        return lojas;
    }

    public Loja getLoja(String storeCode) {
        return lojas.get(storeCode).clone();
    }

    public void setLoja(Loja loja) {
        lojas.replace(loja.getStoreCode(), loja);
    }

    public void addLoja(Loja loja) {
        lojas.put(loja.getStoreCode(), loja);
    }

    public void addProdLoja(String storeCode, List<String> produtos) {
        lojas.get(storeCode).addProdList(produtos);
    }

    public List<String> getProdutosLoja(String storeCode) {
        List<String> produtos = new ArrayList<>();

        for (String prodCode: getLoja(storeCode).getProds())
            produtos.add(prodCode + ": " + getProdName(prodCode));

        return produtos;
    }

    public boolean containsLoja(String storeCode) {
        return lojas.containsKey(storeCode);
    }

    public boolean containsProdutoLoja(String storeCode, String prodCode) {
        return lojas.get(storeCode).containsProd(prodCode);
    }


    //--------------------------------------------------------------Métodos Encomenda--------------------------------------------------------------------------\\

    public Encomenda getEncomenda(String encCode) {
        return encomendas.get(encCode).clone();
    }

    public void setEncomenda(Encomenda enc) {
        encomendas.replace(enc.getEncCode(), enc);
    }

    public void addEncomenda(Encomenda encomenda) {
        users.get(encomenda.getUserCode()).addEncomenda(encomenda.getEncCode());
        encomendas.put(encomenda.getEncCode(), encomenda);
    }

    public boolean containsEncomenda(String encCode) {
        return encomendas.containsKey(encCode);
    }

    public void aceitarEncomenda(String encCode) {
        Encomenda enc = encomendas.get(encCode);
        enc.setAceite(true);
    }

    //guardar tempo de entrega
    public void entregarEncomenda(String encCode,String estafetaCode) {
        Coordenadas cr = lojas.get(encomendas.get(encCode).getStoreCode()).getGps();

        encomendas.get(encCode).setTranspCode(estafetaCode);
        encomendas.get(encCode).setEntregue(true);
        estafetas.get(estafetaCode).setEnc(encCode);
        estafetas.get(estafetaCode).addNumKm(estafetas.get(estafetaCode).getGps().distancia(cr));
    }

    public Set<String> encomendasAceites() {
        Set<String> res = new HashSet<>();

        encomendas.values().stream().map(Encomenda::clone).filter(Encomenda::isAceite).map(Encomenda::getEncCode).forEach(res::add);
        return res;
    }

    public boolean isEncomendaAceite(String encCode) {
        return encomendasAceites().contains(encCode);
    }

    public double precoEncomenda(String encCode,String transpCode) {
        Encomenda enc = getEncomenda(encCode);
        double dist = estafetas.get(transpCode).getGps().distancia(lojas.get(enc.getStoreCode()).getGps())
                + lojas.get(enc.getStoreCode()).getGps().distancia(users.get(enc.getUserCode()).getGps());
        return  ((Transportadora)estafetas.get(transpCode)).getTaxaKm() * dist + enc.getWeight() * ((Transportadora)estafetas.get(transpCode)).getTaxaPeso();
    }


    //----------------------------------------------------------------Métodos Produto--------------------------------------------------------------------------\\

    public Produto getProduto(String prodCode) {
        return produtos.get(prodCode).clone();
    }

    public void setProduto(Produto prod) {
        produtos.replace(prod.getProdCode(), prod);
    }

    public void addProduto(Produto prod) {
        produtos.put(prod.getProdCode(), prod);
    }

    public String getProdName(String prodCode) {
        return produtos.get(prodCode).getName();
    }

    public double getProdWeight(String prodCode) {
        return produtos.get(prodCode).getWeight();
    }


    //----------------------------------------------------------------Métodos Login--------------------------------------------------------------------------\\

    public Login getLogin(String code) {
        return loginMap.get(code).clone();
    }

    public void setLogin(Login login) {
        loginMap.replace(login.getCode(), login);
    }

    public void addLogin(Login login) { loginMap.put(login.getCode(), login);}

    public boolean containsUser(String code) {
        return loginMap.containsKey(code);
    }

    public boolean containsPassword(String code, String password) {
        return loginMap.get(code).getPassword().equals(password);
    }

    public boolean containsNameAndType(String name, String type) {
        return loginMap.values().stream().filter(c -> c.getNome().equals(name)).filter(c -> c.getTipoConta().equals(type)).collect(Collectors.toList()).size() != 0;
    }


    //----------------------------------------------------------------Outros Métodos--------------------------------------------------------------------------\\

    public double calculaTempo(Coordenadas cr1,Coordenadas cr2,Coordenadas cr3,double tempoFilaEspera,double velocidade) {
        Random rand = new Random();
        int condicoesAtmosfericas = rand.nextInt(3);
        if(tempoFilaEspera == -1)
            tempoFilaEspera = rand.nextDouble() * 50;

        double dist = cr1.distancia(cr2) + cr2.distancia(cr3);
        double tempo = (velocidade * dist)/60 + tempoFilaEspera;

        if(condicoesAtmosfericas == 2)
            tempo *= 1.2;
        else if(condicoesAtmosfericas == 3)
            tempo *= 1.5;

        return tempo;
    }

    public String generateCode(String tipoConta) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        char c = ' ';

        switch (tipoConta) {
            case "Utilizador":
                c = 'u';
                break;
            case "Voluntario":
                c = 'v';
                break;
            case "Transportadora":
                c = 't';
                break;
            case "Loja":
                c = 'l';
                break;
            case "Encomenda":
                c = 'e';
                break;
        }

        int randInt;

        if (c != 'e')
            randInt = rand.nextInt(100);

        else
            randInt = rand.nextInt(10000);

        sb.append(c).append(randInt);

        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Controlador{").append("users=").append(users).append("\n");
        sb.append(", lojas=").append(lojas).append("\n");
        sb.append(", voluntarios=").append(estafetas).append("\n");
        sb.append(", encomendas=").append(encomendas).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
