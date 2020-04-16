package com.company;

import java.util.Objects;

public class Transportadora {

    private String companyCode;
    private String companyName;
    private Coordenadas gps;
    private int nif;
    private double raio;
    private double precoPorKm;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Transportadora() {
        this.companyCode = "";
        this.companyName = "";
        this.gps = new Coordenadas();
        this.nif = 0;
        this.raio = 0d;
        this.precoPorKm = 0d;
    }

    public Transportadora(String companyCode, String companyName, Coordenadas gps, int nif, double raio, double precoPorKm) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.gps = gps.clone();
        this.nif = nif;
        this.raio = raio;
        this.precoPorKm = precoPorKm;
    }

    public Transportadora(Transportadora t) {
        this.companyName = t.getCompanyName();
        this.companyCode = t.getCompanyCode();
        this.gps = t.getGps();
        this.nif = t.getNif();
        this.raio = t.getRaio();
        this.precoPorKm = t.getPrecoPorKm();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Coordenadas getGps() {
        return gps.clone();
    }

    public void setGps(Coordenadas gps) {
        this.gps.setLongitude(gps.getLongitude());
        this.gps.setLatitude(gps.getLatitude());
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public double getPrecoPorKm() {
        return precoPorKm;
    }

    public void setPrecoPorKm(double precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\

    public String toString() {
        final StringBuilder sb = new StringBuilder("Transportadora{");
        sb.append("companyCode='").append(companyCode).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", gps=").append(gps);
        sb.append(", nif=").append(nif);
        sb.append(", raio=").append(raio);
        sb.append(", precoPorKm=").append(precoPorKm);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportadora that = (Transportadora) o;
        return nif == that.nif &&
                Double.compare(that.raio, raio) == 0 &&
                Double.compare(that.precoPorKm, precoPorKm) == 0 &&
                Objects.equals(companyCode, that.companyCode) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(gps, that.gps);
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }
}
