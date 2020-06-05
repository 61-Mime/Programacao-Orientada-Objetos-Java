package Model;

public class Notificacao {
    String not;
    int type; // 1 - Default, 2 - Entrega

    //--------------------------------------------------------------Construtores--------------------------------------------------------------------------\\

    public Notificacao() {
        this.not = "";
        this.type = 0;
    }

    public Notificacao(String not, int type) {
        this.not = not;
        this.type = type;
    }

    public Notificacao(Notificacao aux) {
        this.not = aux.getNot();
        this.type = aux.getType();
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
}
