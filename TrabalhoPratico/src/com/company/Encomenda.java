package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Encomenda {
    private String encCode;
    private String userCode;
    private String transpCode;
    private String storeCode;
    private double weight;
    private boolean isMedic;
    private LocalDateTime data;
    private boolean aceite;
    private List<LinhaEncomenda> linha;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Encomenda() {
        this.encCode = "";
        this.userCode = "";
        this.transpCode = "";
        this.weight = 0d;
        this.storeCode = "";
        this.isMedic = false;
        this.data = LocalDateTime.now();
        this.aceite = false;
        this.linha = new ArrayList<>();
    }

    public Encomenda(String encCode, String userCode, String sellerCode, String storeCode, double weight, boolean isMedic, LocalDateTime data, boolean aceite,List<LinhaEncomenda> linha) {
        this.encCode = encCode;
        this.userCode = userCode;
        this.transpCode = sellerCode;
        this.storeCode = storeCode;
        this.weight = weight;
        this.isMedic = isMedic;
        this.aceite = aceite;
        this.data = data;
        setLinha(linha);
    }

    public Encomenda(Encomenda enc) {
        this.encCode = enc.getEncCode();
        this.userCode = enc.userCode;
        this.transpCode = enc.transpCode;
        this.weight = enc.weight;
        this.storeCode = enc.storeCode;
        this.isMedic = enc.isMedic;
        this.data = enc.getData();
        this.aceite = isAceite();
        setLinha(enc.getLinha());
    }

     //--------------------------------------------------------------Getters/Setters--------------------------------------------------------------------------\\


    public String getEncCode() {
        return encCode;
    }

    public void setEncCode(String encCode) {
        this.encCode = encCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTranspCode() {
        return transpCode;
    }

    public void setTranspCode(String transpCode) {
        this.transpCode = transpCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getProduct() {
        return storeCode;
    }

    public void setProduct(String storeCode) {
        this.storeCode = storeCode;
    }

    public boolean isMedic() {
        return isMedic;
    }

    public void setMedic(boolean medic) {
        isMedic = medic;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public boolean isVoluntario() {
        return transpCode.charAt(0) == 'v';
    }

    public boolean isTransportadora() {
        return transpCode.charAt(0) == 't';
    }

    public List<LinhaEncomenda> getLinha() {
        List<LinhaEncomenda> line = new ArrayList<>();

        for(LinhaEncomenda l: this.linha)
            line.add(l.clone());

        return line;
    }

    public void setLinha(List<LinhaEncomenda> line) {
        this.linha = new ArrayList<>();

        for (LinhaEncomenda l: line)
            this.linha.add(l.clone());

    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Encomenda{");
        sb.append("encCode='").append(encCode).append('\'');
        sb.append(", userCode='").append(userCode).append('\'');
        sb.append(", transpCode='").append(transpCode).append('\'');
        sb.append(", storeCode='").append(storeCode).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", isMedic=").append(isMedic);
        sb.append(", data=").append(data);
        sb.append(", aceite=").append(aceite);
        sb.append(", linha=").append(linha);
        sb.append('}').append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Encomenda encomenda = (Encomenda) o;
        return Double.compare(encomenda.weight, weight) == 0 &&
                isMedic == encomenda.isMedic &&
                aceite == encomenda.aceite &&
                Objects.equals(encCode, encomenda.encCode) &&
                Objects.equals(userCode, encomenda.userCode) &&
                Objects.equals(transpCode, encomenda.transpCode) &&
                Objects.equals(storeCode, encomenda.storeCode) &&
                Objects.equals(data, encomenda.data) &&
                Objects.equals(linha, encomenda.linha);
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public void addLinhaEncomenda(String productCode, String description, double quantity, double unitPrice) {
        this.linha.add(new LinhaEncomenda(productCode, description, quantity, unitPrice));
    }

    public void addLinhaEncomenda(LinhaEncomenda l) {
        this.linha.add(l.clone());
    }
}
