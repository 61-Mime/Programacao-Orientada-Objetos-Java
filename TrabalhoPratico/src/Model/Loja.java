package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Loja {

    private String storeCode;
    private String storeName;
    private Coordenadas gps;
    private boolean hasQueueInfo;
    private double queueTime;
    private List<String> prods;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Loja() {
        this("", "", new Coordenadas(), false, -1, new ArrayList<>());
    }

    public Loja(String storeCode, String storeName, Coordenadas gps, boolean hasQueueInfo, double queueTime, List<String> prods) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.gps = gps.clone();
        this.hasQueueInfo = hasQueueInfo;
        this.queueTime = queueTime;
        this.prods = new ArrayList<>(prods);
    }

    public Loja(Loja l) {
        this.storeCode = l.getStoreCode();
        this.storeName = l.getStoreName();
        this.gps = l.getGps();
        this.hasQueueInfo = l.isHasQueueInfo();
        this.queueTime = l.getQueueTime();
        this.prods = l.getProds();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

     public Coordenadas getGps() {
        return gps.clone();
    }

    public void setGps(Coordenadas gps) {
        this.gps.setLongitude(gps.getLongitude());
        this.gps.setLatitude(gps.getLatitude());
    }

    public boolean isHasQueueInfo() {
        return hasQueueInfo;
    }

    public void setHasQueueInfo(boolean hasQueueInfo) {
        this.hasQueueInfo = hasQueueInfo;
    }

    public double getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(double queueTime) {
        this.queueTime = queueTime;
    }

    public List<String> getProds() {
        return new ArrayList<>(prods);
    }

    public void setProds(List<String> prods) {
        this.prods = new ArrayList<>(prods);
    }

    //--------------------------------------------------------------toString, equals e clone--------------------------------------------------------------------------\\


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loja{");
        sb.append("storeCode='").append(storeCode).append('\'');
        sb.append(", storeName='").append(storeName).append('\'');
        sb.append(", gps=").append(gps);
        sb.append(", hasQueueInfo=").append(hasQueueInfo);
        sb.append(", queueTime=").append(queueTime);
        sb.append('}').append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Loja loja = (Loja) o;
        return hasQueueInfo == loja.hasQueueInfo &&
                Double.compare(loja.queueTime, queueTime) == 0 &&
                Objects.equals(storeCode, loja.storeCode) &&
                Objects.equals(storeName, loja.storeName) &&
                Objects.equals(gps, loja.gps);
    }

    public Loja clone() {
        return new Loja(this);
    }

    //---------------------------------------------------------------------------------outros metodos----------------------------------------------------------------------------\\

    public void addProd(String prodCode) {
        this.prods.add(prodCode);
    }

    public void addProdList(List<String> produtos) {
        for (String s: produtos)
            addProd(s);
    }

    public boolean containsProd(String prodCode) {
        return prods.contains(prodCode);
    }
}
