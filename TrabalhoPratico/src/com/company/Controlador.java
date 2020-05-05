package com.company;

import java.util.*;

public class Controlador {

    private Map<String, Utilizador> users;
//    private Map<String, Transportadora> transportadoras;
    private Map<String, Loja> lojas;
    private Map<String, Voluntario> voluntarios;
    private Map<String, Encomenda> encomendas;
//    private Map<String, Estafeta> estafetas;

    public Controlador() {
        this.users = new HashMap<>();
//        this.transportadoras = new HashMap<>();
        this.lojas = new HashMap<>();
        this.voluntarios = new HashMap<>();
        this.encomendas = new HashMap<>();
//        this.estafetas = new HashMap<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Controlador{");
        sb.append("users=").append(users);
//        sb.append(", transportadoras=").append(transportadoras);
        sb.append(", lojas=").append(lojas);
        sb.append(", voluntarios=").append(voluntarios);
        sb.append(", encomendas=").append(encomendas);
//        sb.append(", estafetas=").append(estafetas);
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
//
//    public Transportadora getTransportadora(String transpCode) {
//        return transportadoras.get(transpCode).clone();
//    }
//
//    public void setTransportadora(Transportadora transportadora) {
//        transportadoras.replace(transportadora.getCompanyCode(), transportadora);
//    }
//
//    public void addTransportadora(Transportadora transportadora) {
//        transportadoras.put(transportadora.getCompanyCode(), transportadora);
//    }
//
    public Voluntario getVoluntario(String volCode) {
        return voluntarios.get(volCode).clone();
    }

    public void setVoluntario(Voluntario voluntario) {
        voluntarios.replace(voluntario.getCode(), voluntario);
    }

    public void addVoluntario(Voluntario voluntario) {
        voluntarios.put(voluntario.getCode(), voluntario);
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
        encomendas.get(encCode).setAceite(true);
    }

//    public Estafeta getEstafeta(String code) {
//        return estafetas.get(code).clone();
//    }
//
//    public void setEstafeta(Estafeta estafeta) {
//        estafetas.replace(estafeta.getTranspCode(), estafeta);
//    }
//
//    public void addEstafeta(Estafeta estafeta) {
//        estafetas.put(estafeta.getTranspCode(), estafeta);
//    }

    public Set<String> encomendasAceites() {
        Set<String> res = new HashSet<>();

        encomendas.values().stream().map(Encomenda::clone).filter(Encomenda::isAceite).map(Encomenda::getEncCode).forEach(res::add);
        return res;
    }

    public boolean isEncomendaAceite(String encCode) {
        return encomendasAceites().contains(encCode);
    }

//    public double precoEncomenda(String encCode) {
//        if (isEncomendaAceite(encCode)) {
//            Encomenda enc = getEncomenda(encCode);
//
//            double dist = voluntarios.get(enc.getTranspCode()).getGps().distancia(lojas.get(enc.getStoreCode()).getGps())
//                    + lojas.get(enc.getStoreCode()).getGps().distancia(users.get(enc.getUserCode()).getGps());
//            double precoPeso = voluntarios.get(enc.getTranspCode()).get
//        }
//    }
}
