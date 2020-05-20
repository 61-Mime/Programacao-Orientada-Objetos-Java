package Model;

public class Coordenadas {

    private double latitude;
    private double longitude;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Coordenadas() {
        this(0, 0);
    }

    public Coordenadas(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordenadas(Coordenadas c) {
        this.longitude = c.getLongitude();
        this.latitude = c.getLatitude();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    //--------------------------------------------------------------Equals, toString e clone--------------------------------------------------------------------------\\

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenadas that = (Coordenadas) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Coordenadas{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }

    public Coordenadas clone() {
        return new Coordenadas(this);
    }

    //--------------------------------------------------------------Outros métodos--------------------------------------------------------------------------\\

    public double distancia(Coordenadas c) {
        return Math.sqrt((this.latitude - c.getLatitude()) * (this.latitude - c.getLatitude()) +
                        (this.longitude - c.getLongitude()) * (this.longitude - c.getLongitude()));
    }
}

