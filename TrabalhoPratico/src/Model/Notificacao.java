package Model;

public class Notificacao {
    private String not;
    private int type; // 1 - Default, 2 - Entrega
    private String estCode;

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Notificacao() {
        this.not = "";
        this.type = 0;
    }

    public Notificacao(String not, int type, String estCode) {
        this.not = not;
        this.type = type;
        this.estCode = estCode;
    }

    public Notificacao(Notificacao aux) {
        this.not = aux.getNot();
        this.type = aux.getType();
        this.estCode = aux.getEstCode();
    }

    //--------------------------------------------------------------Getters e Setters--------------------------------------------------------------------------\\

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEstCode() {
        return estCode;
    }

    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }
}
