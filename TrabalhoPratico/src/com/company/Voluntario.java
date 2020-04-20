package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntario {

    private String voluntaryCode;
    private String name;
    private Coordenadas gps;
    private double raio;
    private boolean isFree;
    private List<Encomenda> registerV;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Voluntario() {
        this.voluntaryCode = "";
        this.name = "";
        this.gps = new Coordenadas();
        this.raio = 0d;
        this.isFree = false;
        this.registerV = new ArrayList<>();
    }

    public Voluntario(String voluntaryCode, String name, Coordenadas gps, double raio, boolean isFree, List<Encomenda> registerV) {
        this.voluntaryCode = voluntaryCode;
        this.name = name;
        this.gps = gps.clone();
        this.raio = raio;
        this.isFree = isFree;
        setRegisterV(registerV);
    }

    public Voluntario(Voluntario v) {
        this.voluntaryCode = v.getVoluntaryCode();
        this.name = v.getName();
        this.gps = v.getGps();
        this.raio = v.getRaio();
        this.isFree = v.isIsFree();
        setRegisterV(v.getRegisterV());
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getVoluntaryCode() {
        return voluntaryCode;
    }

    public void setVoluntaryCode(String voluntaryCode) {
        this.voluntaryCode = voluntaryCode;
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
        this.gps.setLatitude(gps.getLatitude());
        this.gps.setLongitude(gps.getLongitude());
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public boolean isIsFree() {
        return isFree;
    }

    public void setHasAccepted(boolean isFree) {
        this.isFree = isFree;
    }

    public List<Encomenda> getRegisterV() {
        List<Encomenda> res = new ArrayList<>();

        for(Encomenda e: this.registerV)
            res.add(e.clone());

        return res;
    }

    public void setRegisterV(List<Encomenda> enc) {
        this.registerV = new ArrayList<>();

        for (Encomenda e: enc)
            this.registerV.add(e.clone());
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\

    public String toString() {
        final StringBuilder sb = new StringBuilder("Voluntario{");
        sb.append("voluntaryCode='").append(voluntaryCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", gps=").append(gps);
        sb.append(", raio=").append(raio);
        sb.append(", isFree=").append(isFree);
        sb.append(", registerV=").append(registerV);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario that = (Voluntario) o;
        return Double.compare(that.raio, raio) == 0 &&
                isFree == that.isFree &&
                Objects.equals(voluntaryCode, that.voluntaryCode) &&
                Objects.equals(name, that.name) &&
                Objects.equals(gps, that.gps) &&
                Objects.equals(registerV, that.registerV);
    }

    public Voluntario clone() {
        return new Voluntario(this);
    }
}
