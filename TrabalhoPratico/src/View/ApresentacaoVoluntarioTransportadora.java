package View;

import java.io.Serializable;

public class ApresentacaoVoluntarioTransportadora implements Serializable {
    private final Output out;

    public ApresentacaoVoluntarioTransportadora() {
        out = new Output();
    }

    public void printMenuVoluntario() {
        out.printMenus(new String[]{"Sinalizar como disponivel/indisponivel para entregar encomendas", "Aceitar Encomenda","Aceder às encomendas", "Classificação"},"MENU VOLUNTÁRIO",1);
    }

    public void printMenuTransportadora() {
        out.printMenus(new String[]{"Sinalizar como disponivel/indisponivel para entregar encomendas", "Preço de transporte de uma encomenda", "Aceder às encomendas", "Total faturado", "Classificação"},"MENU TRANSPORTADORA",1);
    }

    public void printEstafetaDisponivel() {
        System.out.println("Está disponivel para entregar encomendas");
    }

    public void printEstafetaIndisponivel() {
        System.out.println("Está indisponivel para entregar encomendas");
    }

    public void printEstafetaPreco(double preco) {
        System.out.println("Preço: " + preco + " €");
    }

    public void printEstafetaFaturacao(double faturacao) {
        System.out.println("Faturação: " + faturacao + " €");
    }

    public void printEstafetaClassicacao(double classificacao) {
        System.out.println("Classificação: " + classificacao);
    }

    public void printSemEncomendas(){
        System.out.println("Não existem encomendas para entrega!");
    }

    public void printEncRecusada(){
        System.out.println("Encomenda recusada com sucesso!");
    }

}
