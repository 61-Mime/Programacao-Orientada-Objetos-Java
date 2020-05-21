package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estafeta {

    private String code;
    private String name;
    private Coordenadas gps;
    private double raio;
    private double velocidade;
    private boolean isFree;
    private boolean isMedic;
    private double classificacao;
    private List<String> registo;
    private String type;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Estafeta() {
        this.code = "";
        this.name = "";
        this.type = "";
        this.gps = new Coordenadas();
        this.raio = 0d;
        this.velocidade = 0;
        this.isFree = false;
        this.isMedic = false;
        this.classificacao = 0;
        this.registo = new ArrayList<>();
    }

    public Estafeta(String voluntaryCode, String name, Coordenadas gps, double raio, double velocidade, boolean isFree, boolean isMedic, double classificacao, String type) {
        this.code = voluntaryCode;
        this.name = name;
        this.type = type;
        this.gps = gps.clone();
        this.raio = raio;
        this.velocidade = velocidade;
        this.classificacao = classificacao;
        this.isFree = isFree;
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
        this.isFree = v.isFree();
        this.isMedic = v.isMedic();
        this.classificacao = v.getClassificacao();
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

    public void atualizaClassificacao(double classificacao) {
        this.setClassificacao((this.getClassificacao() * this.registo.size() + classificacao) / (this.registo.size() + 1));
    }
}
