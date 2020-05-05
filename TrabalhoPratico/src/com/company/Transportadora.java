package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Transportadora extends Voluntario{

    private int nif;
    private double taxaKm;
    private double taxaPeso;
    private int numEncomendas;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\


    public Transportadora() {
        super();
        this.nif = 0;
        this.taxaKm = 0;
        this.taxaPeso = 0;
        this.numEncomendas = 0;
    }

    public Transportadora(String voluntaryCode, String name, Coordenadas gps, double raio, double velocidade, boolean isFree, boolean isMedic,
                          double classificacao, List<Encomenda> registerV, int nif, double taxaKm, double taxaPeso, int numEncomendas) {
        super(voluntaryCode, name, gps, raio, velocidade, isFree, isMedic, classificacao, registerV);
        this.nif = nif;
        this.taxaKm = taxaKm;
        this.taxaPeso = taxaPeso;
        this.numEncomendas = numEncomendas;
    }

    public Transportadora(Transportadora t) {
        super(t);
        this.nif = t.getNif();
        this.taxaKm = t.getTaxaKm();
        this.taxaPeso = t.getTaxaPeso();
        this.numEncomendas = t.getNumEncomendas();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public double getTaxaKm() {
        return taxaKm;
    }

    public void setTaxaKm(double taxaKm) {
        this.taxaKm = taxaKm;
    }

    public double getTaxaPeso() {
        return taxaPeso;
    }

    public void setTaxaPeso(double taxaPeso) {
        this.taxaPeso = taxaPeso;
    }

    public int getNumEncomendas() {
        return numEncomendas;
    }

    public void setNumEncomendas(int numEncomendas) {
        this.numEncomendas = numEncomendas;
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    public String toString1() {
        final StringBuilder sb = new StringBuilder("Transportadora{");
        sb.append("nif=").append(nif);
        sb.append(", taxaKm=").append(taxaKm);
        sb.append(", taxaPeso=").append(taxaPeso);
        sb.append(", numEncomendas=").append(numEncomendas);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toString() {
        return super.toString() + toString1();
    }

    public boolean equals1(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Transportadora that = (Transportadora) o;
        return nif == that.nif &&
                Double.compare(that.taxaKm, taxaKm) == 0 &&
                Double.compare(that.taxaPeso, taxaPeso) == 0 &&
                numEncomendas == that.numEncomendas;
    }

        @Override
    public boolean equals(Object o) {
        return super.equals(o) && equals1(o);
    }

//    public Transportadora clone() {
//        return new Transportadora(this);
//    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

}
