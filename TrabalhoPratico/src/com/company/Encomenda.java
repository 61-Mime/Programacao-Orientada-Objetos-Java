package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Encomenda {
    private String userCode;
    private String sellerCode;
    private String storeCode;
    private Double weight;
    private boolean isMedic;
    private List<LinhaEncomenda> linha;


    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Encomenda() {
        this.userCode = "";
        this.sellerCode = "";
        this.weight = 0d;
        this.storeCode = "";
        this.isMedic = false;
        this.linha = new ArrayList<>();
    }

    public Encomenda(String userCode, String sellerCode, String storeCode, Double weight, boolean isMedic, List<LinhaEncomenda> linha) {
        this.userCode = userCode;
        this.sellerCode = sellerCode;
        this.storeCode = storeCode;
        this.weight = weight;
        this.isMedic = isMedic;
        setLinha(linha);
    }

    public Encomenda(Encomenda enc) {
        this.userCode = enc.userCode;
        this.sellerCode = enc.sellerCode;
        this.weight = enc.weight;
        this.storeCode = enc.storeCode;
        this.isMedic = enc.isMedic;
        setLinha(enc.getLinha());
    }


     //--------------------------------------------------------------Getters/Setters--------------------------------------------------------------------------\\

    public String getUser() {
        return userCode;
    }

    public void setUser(String userCode) {
        this.userCode = userCode;
    }

    public String getSeller() {
        return sellerCode;
    }

    public void setSeller(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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

    public String toString() {
        final StringBuilder sb = new StringBuilder("Encomenda{");
        sb.append("userCode='").append(userCode).append('\'');
        sb.append(", sellerCode='").append(sellerCode).append('\'');
        sb.append(", storeCode='").append(storeCode).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", isMedic=").append(isMedic);
        sb.append(", linha=").append(linha);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return isMedic == encomenda.isMedic &&
                Objects.equals(userCode, encomenda.userCode) &&
                Objects.equals(sellerCode, encomenda.sellerCode) &&
                Objects.equals(storeCode, encomenda.storeCode) &&
                Objects.equals(weight, encomenda.weight) &&
                Objects.equals(linha, encomenda.linha);
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public void addLinhaEncomenda(LinhaEncomenda l) {
        this.linha.add(l.clone());
    }
}
