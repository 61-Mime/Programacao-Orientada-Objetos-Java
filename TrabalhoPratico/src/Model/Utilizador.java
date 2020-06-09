/**
 * classe que representa um utilizador
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utilizador implements Comparable<Utilizador>, Serializable {
    private String codigoUtilizador;
    private String nome;
    private Coordenadas gps;
    private double precoMax;
    private List<String> entregas;
    private List<String> standBy;
    private List<Notificacao> notificacoes;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Utilizador() {
        this.codigoUtilizador = "";
        this.nome = "";
        this.gps = new Coordenadas();
        this.precoMax = 0;
        this.entregas = new ArrayList<>();
        this.standBy = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
    }

    public Utilizador(String codigoUtilizador, String nome, Coordenadas gps, double precoMax, List<String> entregas, List<String> standBy, List<Notificacao> notificacoes) {
        this.codigoUtilizador = codigoUtilizador;
        this.nome = nome;
        this.precoMax = precoMax;
        this.gps = gps.clone();
        setEntregas(entregas);
        setStandBy(standBy);
        setNotificacoes(notificacoes);
    }

    public Utilizador(Utilizador user) {
        this.codigoUtilizador = user.getCodigoUtilizador();
        this.nome = user.getName();
        this.precoMax = user.getPrecoMax();
        this.gps = user.getGps();
        setEntregas(user.getEntregas());
        setStandBy(user.getStandBy());
        setNotificacoes(user.getNotificacoes());
    }

     //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\
    public String getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public String getName() {
        return nome;
    }

    public Coordenadas getGps() {
        return gps.clone();
    }

    public double getPrecoMax() {
        return precoMax;
    }

    public void setEntregas(List<String> enc) {
        this.entregas = new ArrayList<>();
        this.entregas.addAll(enc);
    }

    public List<String> getEntregas(){
        return new ArrayList<>(entregas);
    }

    public int getEntregasSize(){
        return entregas.size();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<String> getStandBy() {
        return standBy;
    }

    public void setStandBy(List<String> standBy) {
        this.standBy = new ArrayList<>();
        this.standBy.addAll(standBy);
    }

    public void addStandBy(String encCode) {
        standBy.add(encCode);
    }

    public void removeStandBy(String encCode) {
        standBy.remove(encCode);
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = new ArrayList<>();
        this.notificacoes.addAll(notificacoes);
    }

    public int getNumNotificacoes() {
        return notificacoes.size();
    }

    public void addNotificacao(String not, int type, String estCode) {
        notificacoes.add(new Notificacao(not, type, estCode));
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Utilizador{");
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

    public int compareTo(Utilizador u) {
        return u.getEntregas().size() - this.entregas.size();
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public void addEncomenda(String enc) {
        this.entregas.add(enc);
    }

    public void removeEncomenda(String enc) {
        this.entregas.remove(enc);
    }

    public void limpaNotificacoes() {
        notificacoes = new ArrayList<>();
    }

    public boolean isEncStandBy(String enc){
        return standBy.contains(enc);
    }
}
