package View;

import java.io.Serializable;

public class ApresentacaoLoja implements Serializable {
    private final Output out;

    public ApresentacaoLoja() {
        out = new Output();
    }

    public void printMenuLoja() {
        out.printMenus(new String[]{"Atualizar Tempo de Fila de Espera", "Aceitar compra"},"MENU LOJA",1);
    }

    public void printMenuLojaIndisponivel() {
        out.printMenus(new String[]{"Aceitar compra"},"MENU LOJA",1);
    }

    public void printCompraAceite(String encCode) {
        System.out.println("A compra " + encCode + "foi aceite.");
    }

    public void printCompraRecusada(String encCode) {
        System.out.println("A compra " + encCode + "foi recusada.");
    }
}
