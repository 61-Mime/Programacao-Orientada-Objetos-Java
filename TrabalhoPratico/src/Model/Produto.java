package Model;

import java.util.Objects;

public class Produto {
    private String prodCode;
    private String name;
    private double weight;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Produto() {
        this.prodCode = "";
        this.name = "";
        this.weight = 0d;
    }

    public Produto(String prodCode, String name, double weight) {
        this.prodCode = prodCode;
        this.name = name;
        this.weight = weight;
    }

    public Produto(Produto p) {
        this.prodCode = p.getProdCode();
        this.name = p.getName();
        this.weight = p.getWeight();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Produto{");
        sb.append("prodCode='").append(prodCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.weight, weight) == 0 &&
                Objects.equals(prodCode, produto.prodCode) &&
                Objects.equals(name, produto.name);
    }

    public Produto clone() {
        return new Produto(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

}
