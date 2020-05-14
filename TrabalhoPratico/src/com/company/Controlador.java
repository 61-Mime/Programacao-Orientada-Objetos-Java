package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Controlador {

    private Map<String, Utilizador> users;
    private Map<String, Loja> lojas;
    private Map<String, Estafeta> estafetas;
    private Map<String, Encomenda> encomendas;

    public Controlador() {
        this.users = new HashMap<>();
        this.lojas = new HashMap<>();
        this.estafetas = new HashMap<>();
        this.encomendas = new HashMap<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Controlador{");
        sb.append("users=").append(users).append("\n");
        sb.append(", lojas=").append(lojas).append("\n");
        sb.append(", voluntarios=").append(estafetas).append("\n");
        sb.append(", encomendas=").append(encomendas).append("\n");
        sb.append('}');
        return sb.toString();
    }

    public Utilizador getUser(String userCode) {
        return users.get(userCode).clone();
    }

    public void setUser(Utilizador user) {
        users.replace(user.getCodigoUtilizador(), user);
    }

    public void addUser(Utilizador user) {
        users.put(user.getCodigoUtilizador(), user);
    }

    public Estafeta getEstafeta(String code) {
        return estafetas.get(code).clone();
    }

    public void setEstafeta(Estafeta estafeta) {
        estafetas.replace(estafeta.getCode(), estafeta);
    }

    public void addEstafeta(Estafeta estafeta) {
        estafetas.put(estafeta.getCode(), estafeta);
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

    public Encomenda getEncomenda(String encCode) {
        return encomendas.get(encCode).clone();
    }

    public void setEncomenda(Encomenda enc) {
        encomendas.replace(enc.getEncCode(), enc);
    }

    public void addEncomenda(Encomenda encomenda) {
        encomendas.put(encomenda.getEncCode(), encomenda);
    }

    public void aceitarEncomenda(String encCode){
        Encomenda enc = encomendas.get(encCode);
        enc.setAceite(true);
        String estafetaCode = escolheEstafeta(enc);
        estafetas.get(estafetaCode).setEnc(enc);
        users.get(enc.getUserCode()).setEntrega(enc);
        enc.setTranspCode(estafetaCode);
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
        double precoPeso = ((Transportadora)estafetas.get(transpCode)).getTaxaKm() * dist + enc.getWeight() * ((Transportadora)estafetas.get(transpCode)).getTaxaKm();
        return precoPeso;
    }

    public List<Estafeta> possiveisEstafetas(Encomenda enc) {
        List<Estafeta> estafetaList;
        Coordenadas cr = lojas.get(enc.getStoreCode()).getGps();
        boolean isMedic = enc.isMedic();
        //se for transportadora filtrar por preço
        estafetaList = estafetas.values().stream().filter(e -> (((isMedic && e.isMedic()) || isMedic == false) && e.isFree() && e.getGps().distancia(cr) < e.getRaio()))
                                                  .map(Estafeta::clone).collect(Collectors.toList());

        return estafetaList;
    }

    public String escolheEstafeta(Encomenda enc) {
        List<Estafeta> estafetaList = possiveisEstafetas(enc);
        Iterator<Estafeta> it = estafetaList.iterator();
        boolean escolhido = false;
        String code = "";
        Estafeta curr;
        double maxprice = users.get(enc.getUserCode()).getPrecoMax();

        while (it.hasNext() && !escolhido) {
            curr = it.next();
            if(curr.getType() == "Voluntário" || precoEncomenda(enc.getEncCode(),curr.getCode()) <= maxprice) {
                escolhido = true;
                code = curr.getCode();
            }
        }

        return code;
    }
}
