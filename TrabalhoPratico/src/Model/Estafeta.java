package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estafeta implements Comparable<Estafeta>, Serializable {

    private String code;
    private String name;
    private Coordenadas gps;
    private double raio;
    private double velocidade;
    private double numKm;
    private boolean isFree;
    private boolean isMedic;
    private boolean occup;
    private double classificacao;
    private int numCla;
    private List<String> registo;
    private String type;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Estafeta() {
        this.code = "";
        this.name = "";
        this.type = "";
        this.gps = new Coordenadas();
        this.raio = 0d;
        this.velocidade = 0d;
        this.numKm = 0d;
        this.isFree = false;
        this.isMedic = false;
        this.occup = false;
        this.classificacao = 0d;
        this.numCla = 0;
        this.registo = new ArrayList<>();
    }

    public Estafeta(String voluntaryCode, String name, Coordenadas gps, double raio, double velocidade, double numKm, boolean isFree, boolean isMedic, double classificacao, int numCla, String type,boolean occup) {
        this.code = voluntaryCode;
        this.name = name;
        this.type = type;
        this.gps = gps.clone();
        this.raio = raio;
        this.velocidade = velocidade;
        this.numKm = numKm;
        this.classificacao = classificacao;
        this.numCla = numCla;
        this.isFree = isFree;
        this.occup = occup;
        this.isMedic = isMedic;
        this.registo = new ArrayList<>();
    }

    public Estafeta(Estafeta v) {
        this.code = v.getCode();
        this.name = v.getName();
        this.type = v.getType();
        this.gps = v.getGps();
        this.raio = v.getRaio();
        this.velocidade = v.getVelocidade();
        this.numKm = v.getNumKm();
        this.isFree = v.isFree();
        this.isMedic = v.isMedic();
        this.classificacao = v.getClassificacao();
        this.numCla = v.getNumCla();
        setRegisto(v.getRegisto());
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public void setCode(String code) {
        this.code = code;
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

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getNumKm() {
        return numKm;
    }

    public void setNumKm(double numKm) {
        this.numKm = numKm;
    }

    public void addNumKm(double numKm) {
        this.numKm = numKm;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isMedic() {
        return isMedic;
    }

    public void setMedic(boolean medic) {
        isMedic = medic;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public int getNumCla() {
        return numCla;
    }

    public void setNumCla(int numCla) {
        this.numCla = numCla;
    }

    public void incNumCla() {
        this.numCla ++;
    }

    public List<String> getRegisto() {
        return new ArrayList<>(registo);
    }

    public void setRegisto(List<String> enc) {
        this.registo = new ArrayList<>();
        this.registo.addAll(enc);
    }

    public void setEnc(String enc) {
        this.registo.add(enc);
        if(this.getType() == "Transportadora")
            ((Transportadora)this).setNumEncomendas();
    }

    public void removeEnc(String encCode){
        registo.remove(encCode);
    }

    public boolean isOccup() {
        return occup;
    }

    public void setOccup(boolean occup) {
        this.occup = occup;
    }

    public void setType(String type) {
        this.type = type;
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(type);
        sb.append("{estafetaCode='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", gps=").append(gps);
        sb.append(", raio=").append(raio);
        sb.append(", velocidade=").append(velocidade);
        sb.append(", isFree=").append(isFree);
        sb.append(", isMedic=").append(isMedic);
        sb.append(", classificacao=").append(classificacao);
        sb.append(", \nregisterV=").append(registo);
        sb.append('}').append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Estafeta that = (Estafeta) o;
        return Double.compare(that.raio, raio) == 0 &&
                Double.compare(that.velocidade, velocidade) == 0 &&
                isFree == that.isFree &&
                isMedic == that.isMedic &&
                Double.compare(that.classificacao, classificacao) == 0 &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(gps, that.gps) &&
                Objects.equals(registo, that.registo);
    }

    public Estafeta clone() {
        return new Estafeta(this);
    }

    public int compareTo(Estafeta e) {
        if(this.numKm > e.getNumKm())
            return -1;
        else if(this.numKm < e.getNumKm())
            return 1;
        else
            return 0;
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public void atualizaClassificacao(double classificacao) {
        setClassificacao((getClassificacao() * getNumCla() + classificacao) / (getNumCla() + 1));
        incNumCla();
    }

    public void addEncomenda(String encCode) {
        registo.add(encCode);
    }

    public boolean containsEncomenda(String encCode) {
        return registo.contains(encCode);
    }
}
