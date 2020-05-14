package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilizador {
    private String codigoUtilizador;
    private String nome;
    private Coordenadas gps;
    private double precoMax;
    private List<Encomenda> entregas;


     //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Utilizador() {
        this.codigoUtilizador = "";
        this.nome = "";
        this.gps = new Coordenadas();
        this.precoMax = 0;
        this.entregas = new ArrayList<>();
    }

    public Utilizador(String codigoUtilizador, String nome, Coordenadas gps, List<Encomenda> entregas) {
        this.codigoUtilizador = codigoUtilizador;
        this.nome = nome;
        this.precoMax = 100;
        this.gps = gps.clone();
        setEntregas(entregas);
    }

    public Utilizador(Utilizador user) {
        this.codigoUtilizador = user.getCodigoUtilizador();
        this.nome = user.getName();
        this.gps = user.getGps();
        setEntregas(user.getEntregas());
    }

     //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\
    public String getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(String codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public Coordenadas getGps() {
        return gps.clone();
    }

    public void setGps(Coordenadas gps) {
        this.gps.setLongitude(gps.getLongitude());
        this.gps.setLatitude(gps.getLatitude());
    }

    public double getPrecoMax() {
        return precoMax;
    }

    public List<Encomenda> getEntregas() {
        List<Encomenda> ent = new ArrayList<>();

        for(Encomenda enc: this.entregas)
            ent.add(enc.clone());

        return ent;
    }

    public void setEntregas(List<Encomenda> ent) {
        this.entregas = new ArrayList<>();

        for(Encomenda enc: ent)
            this.entregas.add(enc.clone());
    }

    public void setEntrega(Encomenda ent) {
        this.entregas.add(ent.clone());
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utilizador{");
        sb.append("codigoUtilizador='").append(codigoUtilizador).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", gps='").append(gps).append('\'');
        sb.append(", entregas=").append(entregas);
        sb.append('}').append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(codigoUtilizador, that.codigoUtilizador) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(gps, that.gps) &&
                Objects.equals(entregas, that.entregas);
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public void addEncomenda(Encomenda enc) {
        this.entregas.add(enc.clone());
    }
}
