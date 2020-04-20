package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Transportadora {

    private String companyCode;
    private String companyName;
    private Coordenadas gps;
    private int nif;
    private double raio;
    private double precoPorKm;
    private double precoPorKg;
    private int numEncomendas;
    private List<Encomenda> encomendasEmpresa;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Transportadora() {
        this.companyCode = "";
        this.companyName = "";
        this.gps = new Coordenadas();
        this.nif = 0;
        this.raio = 0d;
        this.precoPorKm = 0d;
        this.precoPorKg = 0d;
        this.numEncomendas = 0;
        this.encomendasEmpresa = new ArrayList<>();
    }

    public Transportadora(String companyCode, String companyName, Coordenadas gps, int nif, double raio,
                          double precoPorKm, double precoPorKg, int numEncomendas, List<Encomenda> encomendasEmpresa) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.gps = gps.clone();
        this.nif = nif;
        this.raio = raio;
        this.precoPorKm = precoPorKm;
        this.precoPorKg = precoPorKg;
        this.numEncomendas = numEncomendas;
        setEncomendasEmpresa(encomendasEmpresa);
    }

    public Transportadora(Transportadora t) {
        this.companyName = t.getCompanyName();
        this.companyCode = t.getCompanyCode();
        this.gps = t.getGps();
        this.nif = t.getNif();
        this.raio = t.getRaio();
        this.precoPorKm = t.getPrecoPorKm();
        this.precoPorKg = t.getPrecoPorKg();
        this.numEncomendas = t.getNumEncomendas();
        setEncomendasEmpresa(t.getEncomendasEmpresa());
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

    public double getPrecoPorKg() {
        return precoPorKg;
    }

    public void setPrecoPorKg(double precoPorKg) {
        this.precoPorKg = precoPorKg;
    }

    public int getNumEncomendas() {
        return numEncomendas;
    }

    public void setNumEncomendas(int numEncomendas) {
        this.numEncomendas = numEncomendas;
    }

    public List<Encomenda> getEncomendasEmpresa() {
        List<Encomenda> ent = new ArrayList<>();

        for(Encomenda enc: this.encomendasEmpresa)
            ent.add(enc.clone());

        return ent;
    }

    public void setEncomendasEmpresa(List<Encomenda> ent) {
        this.encomendasEmpresa = new ArrayList<>();

        for(Encomenda enc: ent)
            this.encomendasEmpresa.add(enc.clone());
    }
    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transportadora{");
        sb.append("companyCode='").append(companyCode).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", gps=").append(gps);
        sb.append(", nif=").append(nif);
        sb.append(", raio=").append(raio);
        sb.append(", precoPorKm=").append(precoPorKm);
        sb.append(", precoPorKg=").append(precoPorKg);
        sb.append(", numEncomendas=").append(numEncomendas);
        sb.append(", encomendasEmpresa=").append(encomendasEmpresa);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportadora that = (Transportadora) o;
        return nif == that.nif &&
                Double.compare(that.raio, raio) == 0 &&
                Double.compare(that.precoPorKm, precoPorKm) == 0 &&
                Double.compare(that.precoPorKg, precoPorKg) == 0 &&
                numEncomendas == that.numEncomendas &&
                Objects.equals(companyCode, that.companyCode) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(gps, that.gps) &&
                Objects.equals(encomendasEmpresa, that.encomendasEmpresa);
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public Encomenda getEncomenda(String user, String loja) {
//        if (!encomendasEmpresa.contains(user))
//            System.out.println("Utilizador inválido");
//
//        else if (!encomendasEmpresa.contains(loja))
//            System.out.println("Loja inválida");
//        else {

            List<Encomenda> lista = encomendasEmpresa.stream().filter(e -> e.getUserCode().equals(user)).
                    filter(e -> e.getSellerCode().equals(loja)).collect(Collectors.toList());

            return lista.get(0);
        }
    }

//    public int precoEncomenda(String user, String loja) {
//
//        double pesoEncomenda = getEncomenda(user, loja).getWeight();
//        double distEncomenda =
//    }
//}
