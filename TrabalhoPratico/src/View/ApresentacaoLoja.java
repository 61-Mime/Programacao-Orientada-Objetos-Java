package View;

import java.io.Serializable;

public class ApresentacaoLoja implements Serializable {
    private final Output out;

    public ApresentacaoLoja() {
        out = new Output();
    }

    public void printMenuLoja() {
        out.printMenus(new String[]{"Atualizar Tempo de Fila de Espera"},"MENU LOJA",1);
    }

    public void printMenuLojaIndisponivel() {
        System.out.println("-------------------------------------------------");
        System.out.println("Menu de loja indisponível para este tipo de lojas");
        System.out.println("0 | Voltar Atrás");
        System.out.println("-------------------------------------------------");
    }
}
