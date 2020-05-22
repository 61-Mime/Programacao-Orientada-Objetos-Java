package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GestTrazAqui implements IGestTrazAqui, Serializable {

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

    public List<String> getUserNotificacoes(String code) {
        return users.get(code).getNotificacoes();
    }

    public int getUserNumNotificacoes(String code) {
        return users.get(code).getNumNotificacoes();
    }

    public void addUserNotificacao(String code, String not) {
        users.get(code).addNotificacao(not);
    }

    public void removeUserNotificacao(String code, String not) {
        users.get(code).removeNotificacao(not);
    }

    public void limpaUserNotificacoes(String code) {
        users.get(code).limpaNotificacoes();
    }

    public Coordenadas getUserCoord(String userCode) {
        return users.get(userCode).getGps();
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
        return users.get(userCode).getEntregas().stream().filter(c -> encomendas.get(c).isAceiteLoja() && !encomendas.get(c).isEntregue()).collect(Collectors.toList());
    }

    public List<String> getTopUsers() {
        return users.values().stream().sorted().limit(10).map(c -> c.getCodigoUtilizador() + ": " + c.getName() + " " + c.getEntregasSize()).collect(Collectors.toList());
    }

    public void addUserStandBy(String userCode, String encCode) {
        users.get(userCode).addStandBy(encCode);
        users.get(userCode).removeEncomenda(encCode);
    }

    public void removeUserStandBy(String userCode, String encCode) {
        users.get(userCode).removeStandBy(encCode);
        users.get(userCode).addEncomenda(encCode);
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

    public Coordenadas getEstafetaCoord(String code) {
        return estafetas.get(code).getGps();
    }

    public double getEstafetaVelocidade(String code) {
        return estafetas.get(code).getVelocidade();
    }

    public double getEstafetaClassificacao(String code) {
        return estafetas.get(code).getClassificacao();
    }

    public List<String> getEstafetaNotificacoes(String code) {
        return estafetas.get(code).getNotificacoes();
    }

    public int getEstafetaNumNotificacoes(String code) {
        return estafetas.get(code).getNumNotificacoes();
    }

    public void addEstafetaNotificacao(String code, String not) {
        estafetas.get(code).addNotificacao(not);
    }

    public void removeEstafetaNotificacao(String code, String not) {
        estafetas.get(code).removeNotificacao(not);
    }

    public void limpaEstafetaNotificacoes(String code) {
        users.get(code).limpaNotificacoes();
    }

    public List<String> possiveisEstafetas(String enc) {
        List<String> estafetaList;
        Coordenadas cr = lojas.get(encomendas.get(enc).getStoreCode()).getGps();
        boolean isMedic = encomendas.get(enc).isMedic();
        double maxprice = users.get(encomendas.get(enc).getUserCode()).getPrecoMax();

        estafetaList = estafetas.values().stream().filter(e -> ((!isMedic || e.isMedic()) && e.isFree() && !e.isOccup() && e.getGps().distancia(cr) < e.getRaio() && (precoEncomenda(enc,e.getCode()) <= maxprice)))
                .map(Estafeta::getCode).collect(Collectors.toList());

        return estafetaList;
    }

    public String escolheEstafeta(List<String> listEst,String encCode) {
        String best = "";
        Coordenadas coord = getStoreCoordFromEnc(encCode);
        double distmin = Double.MAX_VALUE,curr;

        for(String code:listEst){
            if((curr = getEstafetaCoord(code).distancia(coord)) <= distmin) {
                best = code;
                distmin = curr;
            }
        }

        return best;
    }

    public void classificarEstafeta(double pontuacao,String code){
        estafetas.get(code).atualizaClassificacao(pontuacao);
    }

    public boolean isEstafetaFree(String code) {
        return estafetas.get(code).isFree();
    }

    public void setEstafetaFree(String code, boolean free) {
        estafetas.get(code).setFree(free);
    }

    public List<String> getTopTrans() {
        return this.estafetas.values().stream().filter(c -> c.getType().equals("Transportadora")).sorted().limit(10).map(c -> c.getCode() + ": " + c.getName() + " " + c.getNumKm()).collect(Collectors.toList());
    }

    public List<Encomenda> getEncomendasEstafeta(String code, LocalDateTime min, LocalDateTime max) {
        List<Encomenda> list = new ArrayList<>();

        for (String c : estafetas.get(code).getRegisto()) {
            list.add(encomendas.get(c).clone());
        }

        return list.stream().filter(e -> e.encData(min,max)).collect(Collectors.toList());
    }

    public double calcularFaturacao(String code, LocalDateTime min, LocalDateTime max) {
        return encomendas.values().stream().filter(e -> e.encData(min,max) && e.getTranspCode().equals(code))
                .map(e -> precoEncomenda(e.getEncCode(), code)).reduce(0d, Double::sum);
    }

    public boolean containsEncomendaEstafeta(String encCode, String code) {
        return estafetas.get(code).containsEncomenda(encCode);
    }

    public String encomendaStandBy(String estCode){
        return estafetas.get(estCode).getRegisto().stream().filter(enc -> !encomendas.get(enc).isEntregue()).findFirst().orElse("");
    }

    public void removerEnc(String code,String encCode){
        estafetas.get(code).removeEnc(encCode);
    }

    public void setEstafetaOccup(String code,boolean occup){
        estafetas.get(code).setOccup(occup);
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

    private Coordenadas getStoreCoord(String storeCode) {
        return lojas.get(storeCode).getGps();
    }

    public Coordenadas getStoreCoordFromEnc(String encCode) {
        return getStoreCoord(encomendas.get(encCode).getStoreCode());
    }

    private double getStoreQueueTime(String storeCode) {
        return lojas.get(storeCode).getQueueTime();
    }

    public double getStoreQueueTimeFromEnc(String encCode) {
        return getStoreQueueTime(encomendas.get(encCode).getStoreCode());
    }

    public List<String> getProdutosLoja(String storeCode) {
        List<String> produtos = new ArrayList<>();

        for (String prodCode: getLoja(storeCode).getProds())
            produtos.add(prodCode + ": " + getProdName(prodCode) + " " + getProdWeight(prodCode) + "Kg " + getProdPrice(prodCode) + "€ ");

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
        enc.setAceiteLoja(true);
    }

    //guardar tempo de entrega
    public void entregarEncomenda(String encCode,String estafetaCode) {
        Encomenda enc = encomendas.get(encCode);
        Estafeta e = estafetas.get(estafetaCode);
        Loja l = lojas.get(enc.getStoreCode());
        Coordenadas cr = lojas.get(enc.getStoreCode()).getGps();

        enc.setTranspCode(estafetaCode);
        enc.setEntregue(true);
        enc.setTempoEntrega(calculaTempo(e.getGps(),l.getGps(),users.get(enc.getUserCode()).getGps(),l.getQueueTime(),e.getVelocidade()));
        e.setEnc(encCode);
        e.addNumKm(e.getGps().distancia(cr));
    }

    public Set<String> encomendasAceites() {
        Set<String> res = new HashSet<>();

        encomendas.values().stream().map(Encomenda::clone).filter(Encomenda::isAceiteLoja).map(Encomenda::getEncCode).forEach(res::add);
        return res;
    }

    public boolean isEncomendaAceite(String encCode) {
        return encomendasAceites().contains(encCode);
    }

    public double precoEncomenda(String encCode,String transpCode) {
        if(estafetas.get(transpCode).getType().equals("Voluntario"))
            return 0;

        Encomenda enc = getEncomenda(encCode);
        double dist = estafetas.get(transpCode).getGps().distancia(lojas.get(enc.getStoreCode()).getGps())
                + lojas.get(enc.getStoreCode()).getGps().distancia(users.get(enc.getUserCode()).getGps());
        return  ((Transportadora)estafetas.get(transpCode)).getTaxaKm() * dist + enc.getWeight() * ((Transportadora)estafetas.get(transpCode)).getTaxaPeso();
    }

    public String getEncUser(String encCode){
        return encomendas.get(encCode).getUserCode();
    }

    public double getEncTime(String encCode){
        return encomendas.get(encCode).getTempoEntrega();
    }

    public String getEncUserName(String encCode){
        return users.get(encomendas.get(encCode).getUserCode()).getName();
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

    public double getProdPrice(String prodCode) {
        return produtos.get(prodCode).getPrice();
    }

    public boolean getProdisMedic(String prodCode) {
        return produtos.get(prodCode).isMedic();
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

    public double calculaTempo(Coordenadas crE,Coordenadas crL,Coordenadas crU,double tempoFilaEspera,double velocidade) {
        Random rand = new Random();
        int condicoesAtmosfericas = rand.nextInt(3);
        int queueSize = rand.nextInt(8);
        if(tempoFilaEspera == -1)
            tempoFilaEspera = rand.nextDouble() * 10 * queueSize;

        double dist = crE.distancia(crL) + crL.distancia(crU);
        double tempo = (dist/velocidade)*60 + tempoFilaEspera;

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
        int randInt;

        c = tipoConta.toLowerCase().charAt(0);

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
