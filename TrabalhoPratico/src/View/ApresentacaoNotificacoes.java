package View;

import java.io.Serializable;

public class ApresentacaoNotificacoes implements Serializable {

    public void notifTable(String not, int type, int page, int max) {
        System.out.println("-------------------------------------------------------");
        System.out.println("                  Notificação ("+(page+1)+"/"+max+")");
        System.out.println("-------------------------------------------------------");
        System.out.println(not);
        System.out.println("-------------------------------------------------------");
        if(type == 2)
            System.out.println("[1] Próxima | [2] Anterior | [3] Classificar | [0] Sair");
        else
            System.out.println("         [1] Próxima | [2] Anterior | [0] Sair");
        System.out.println("-------------------------------------------------------");
    }

    public void printEmptyNot() {
        System.out.println("O utilizador não tem notificações");
    }

    public String notificacaoUtilizadorLojaAceite(String storeCode) {
        return "A loja " + storeCode + " aceitou a sua compra.\nSolicite o levantamento da encomenda";
    }

    public String notificacaoUtilizadorLojaRecusado(String storeCode) {
        return "A loja " + storeCode + " recusou a sua compra.\nVolte a realizar o pedido";
    }

    public String notificacaoUtilizadorVoluntarioRecusado(String code) {
        return "O voluntário " + code + " recusou a sua encomenda.\nVolte a solicitar a encomenda";
    }

    public String notificacaoUtilizadorEntregaTransportadora(String transCode, String encCode) {
        return "Entrega da encomenda " + encCode + " realizada com sucesso pela transportadora " + transCode;
    }

    public String notificacaoUtilizadorEntregaVoluntario(String transCode, String encCode) {
        return "Entrega da encomenda " + encCode + " realizada com sucesso pelo voluntario " + transCode;
    }

    public String notificacaoVoluntarioNovaEntrega(String encCode, String userCode) {
        return "Tem uma entrega pendente (" + encCode + ") do utilizador " + userCode + ".";
    }

    public String notificacaoLojaNovaCompra(String encCode, String userCode) {
        return "Tem uma compra pendente (" + encCode + ") do utilizador " + userCode + ".";
    }

    public String notificacaoUserNovaTransportadora(String encCode, String transpCode) {
        return "Tem um entrega sugerida (" + encCode + ") da transportadora " + transpCode + ".";
    }
}
