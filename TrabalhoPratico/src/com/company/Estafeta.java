package com.company;

import java.util.ArrayList;
import java.util.List;

public class Estafeta {

    private String transpCode;
    private String name;
    private Coordenadas gps;
    private double raio;
    private double velocidade;
    private double classificacao;
    private boolean isMedic;
    private boolean isFree;
    private List<Encomenda> encomendasEntregues;

    public Estafeta() {
        this("", "", new Coordenadas(), 0, 0, 0, false, false, new ArrayList<>());
    }

    public Estafeta(String transpCode, String name, Coordenadas gps, double raio, double velocidade, double classificacao, boolean isMedic, boolean isFree, List<Encomenda> encomendasEntregues) {
        this.transpCode = transpCode;
        this.name = name;
        this.gps = gps;
        this.raio = raio;
        this.velocidade = velocidade;
        this.classificacao = classificacao;
        this.isMedic = isMedic;
        this.isFree = isFree;
        setEncomendasEntregues(encomendasEntregues);
    }

    public Estafeta(Estafeta estafeta) {
        this.transpCode = estafeta.getTranspCode();
        this.name = estafeta.getName();
        this.gps = estafeta.getGps();
        this.raio = estafeta.getRaio();
        this.velocidade = estafeta.getVelocidade();
        this.classificacao = estafeta.getClassificacao();
        this.isMedic = estafeta.isMedic();
        this.isFree = estafeta.isFree();
        setEncomendasEntregues(estafeta.getEncomendasEntregues());
    }

    public String getTranspCode() {
        return transpCode;
    }

    public void setTranspCode(String transpCode) {
        this.transpCode = transpCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordenadas getGps() {
        return gps.clone();
    }

    public void setGps(Coordenadas gps) {
        this.gps.setLongitude(gps.getLongitude());
        this.gps.setLatitude(gps.getLatitude());
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isMedic() {
        return isMedic;
    }

    public void setMedic(boolean medic) {
        isMedic = medic;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public List<Encomenda> getEncomendasEntregues() {
        List<Encomenda> encomendas = new ArrayList<>();

        for (Encomenda enc: this.encomendasEntregues)
            encomendas.add(enc.clone());

        return encomendas;
    }

    public void setEncomendasEntregues(List<Encomenda> list) {
        this.encomendasEntregues = new ArrayList<>();

        for (Encomenda enc: list)
            this.encomendasEntregues.add(enc.clone());
    }

    public Estafeta clone() {
        return new Estafeta(this);
    }
}
