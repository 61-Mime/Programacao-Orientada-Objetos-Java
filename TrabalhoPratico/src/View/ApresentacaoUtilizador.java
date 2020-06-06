package View;

import Model.Encomenda;
import Model.LinhaEncomenda;

import java.io.Serializable;
import java.util.List;

public class ApresentacaoUtilizador implements Serializable {
    private final Output out;

    public ApresentacaoUtilizador() {
        out = new Output();
    }

    public void printMenuUtilizador() {
        out.printMenus((new String[]{"Solicitar entrega de uma encomenda", "Aceder às encomendas","Fazer encomenda"}),"MENU UTILIZADOR",1);
    }

    public void printEncomendas(String message, List<Encomenda> arr)  {
        System.out.println("\n" + message);

        for(Encomenda line : arr)
            System.out.println(line);

        System.out.print("\n");
    }

    public void printFatura(Encomenda enc)  {
        System.out.print("\n");

        for(LinhaEncomenda line : enc.getLinha())
            System.out.println(line);

        System.out.println("Preço: " + enc.getPrice() +"€\n");
    }

    public void printErroEntrega() {
        System.out.println("Sem mais estafetas disponiveis!");
    }

    public void printErroSemEncomenda(){
        System.out.println("Não existem encomendas");
    }

    public void printEncomendaEntregueVol(String code, String nome, double tempo) {
        System.out.println("A encomenda foi entregue ao utilizador " + code + ": " + nome);
        System.out.println("Tempo de entrega: " + tempo + " min");
    }

    public void printEncomendaEntregue(String code, String tipo, String nome, double preco, double tempo) {
        System.out.println("A sua encomenda foi entregue pelo(a) " + tipo + " " + code + ": " + nome);
        if(tipo.equals("Transportadora"))
            System.out.println("Preço: " + preco + " €");
        System.out.println("Tempo de entrega: " + tempo + " min");
    }

    public void printEncomendaAceite() {
        System.out.println("A sua encomenda foi aceite pela loja e precisa de ser solicitada");
    }

    public void printCompraCancelada() {
        System.out.println("A compra foi cancelada");
    }

    public void printCompraEspera() {
        System.out.println("A compra está à espera de aceitação da loja");
    }

    public void printPedirClassificar() {
        System.out.println("Pretende classificar o serviço?(S/N)");
    }

    public void printEncomendaStandBy(String code){
        System.out.println("A sua encomenda está à espera de ser aceite pelo voluntário " + code);
    }

    public String pedirEncomenda() {
        return "Escolha uma encomenda: ";
    }
}
