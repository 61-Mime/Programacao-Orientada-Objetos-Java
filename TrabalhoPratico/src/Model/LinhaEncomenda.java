package Model;

import java.io.Serializable;
import java.util.Objects;

public class LinhaEncomenda implements Serializable {

    private String productCode;
    private String description;
    private double quantity;
    private double unitPrice;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public LinhaEncomenda() {
        this("", "", 0, 0);
    }

    public LinhaEncomenda(String productCode, String description, double quantity, double unitPrice) {
        this.productCode = productCode;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public LinhaEncomenda(LinhaEncomenda l) {
        this.productCode = l.getProductCode();
        this.description = l.getDescription();
        this.quantity = l.getQuantity();
        this.unitPrice = l.getUnitPrice();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    //--------------------------------------------------------------equals, toString e clone--------------------------------------------------------------------------\\

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinhaEncomenda that = (LinhaEncomenda) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                Double.compare(that.unitPrice, unitPrice) == 0 &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(description, that.description);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("LinhaEncomenda{");
        sb.append("productCode='").append(productCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append('}');
        return sb.toString();
    }

    public LinhaEncomenda clone() {
        return new LinhaEncomenda(this);
    }
}
