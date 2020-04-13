package com.company;

import java.util.Objects;

public class Encomenda {
    private String user;
    private String seller;
    private Double weight;
    private String product;
    private String medic;


    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Encomenda() {
        this.user = new String();
        this.seller = new String();
        this.weight = 0d;
        this.product = new String();
        this.medic = new String();
    }

    public Encomenda(String user, String seller, Double weight, String product, String medic) {
        this.user = user;
        this.seller = seller;
        this.weight = weight;
        this.product = product;
        this.medic = medic;
    }

    public Encomenda(Encomenda enc) {
        this.user = enc.user;
        this.seller = enc.seller;
        this.weight = enc.weight;
        this.product = enc.product;
        this.medic = enc.medic;
    }


     //--------------------------------------------------------------Getters/Setters--------------------------------------------------------------------------\\

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

     //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Encomenda{");
        sb.append("user='").append(user).append('\'');
        sb.append(", seller='").append(seller).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", product='").append(product).append('\'');
        sb.append(", medic='").append(medic).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return Objects.equals(user, encomenda.user) &&
                Objects.equals(seller, encomenda.seller) &&
                Objects.equals(weight, encomenda.weight) &&
                Objects.equals(product, encomenda.product) &&
                Objects.equals(medic, encomenda.medic);
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }
}
