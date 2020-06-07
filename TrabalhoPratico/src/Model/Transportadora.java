package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transportadora extends Estafeta implements Serializable {

    private int nif;
    private double taxaKm;
    private double taxaPeso;
    private int numEncomendas;
    private List<String> rota;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\


    public Transportadora() {
        super();
        this.nif = 0;
        this.taxaKm = 0;
        this.taxaPeso = 0;
        this.numEncomendas = 0;
    }

    public Transportadora(String voluntaryCode, String name, Coordenadas gps, double raio, double velocidade, double numKm, boolean isFree, boolean isMedic, double classificacao,
                          int numCla, int nif, double taxaKm, double taxaPeso, int numEncomendas, boolean occup, List<Notificacao> notificacoes) {
        super(voluntaryCode, name, gps, raio, velocidade, numKm, isFree,isMedic, classificacao,numCla,"Transportadora",occup,notificacoes);
        this.nif = nif;
        this.taxaKm = taxaKm;
        this.taxaPeso = taxaPeso;
        this.numEncomendas = numEncomendas;
        this.rota = new ArrayList<>();
    }

    public Transportadora(Transportadora t) {
        super(t);
        this.nif = t.getNif();
        this.taxaKm = t.getTaxaKm();
        this.taxaPeso = t.getTaxaPeso();
        this.numEncomendas = t.getNumEncomendas();
        setRota(t.getRota());
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

    public void setNumEncomendas() {
        this.numEncomendas ++;
    }

    public List<String> getRota(){
        return new ArrayList<>(rota);
    }

    public int getRotaSize(){
        return rota.size();
    }

    public void setRota(List<String> rota){
        rota = new ArrayList<>(rota);
    }

    public void remEncRota(String enc){
        rota.remove(enc);
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    public String toString1() {
        final StringBuilder sb = new StringBuilder("Transportadora{");
        sb.append("nif=").append(nif);
        sb.append(", taxaKm=").append(taxaKm);
        sb.append(", taxaPeso=").append(taxaPeso);
        sb.append(", numEncomendas=").append(numEncomendas);
        sb.append('}').append("\n");
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

    public Transportadora clone() {
        return new Transportadora(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

}
