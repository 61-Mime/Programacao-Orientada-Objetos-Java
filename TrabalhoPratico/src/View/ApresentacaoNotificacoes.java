package View;

public class ApresentacaoNotificacoes {

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

    public String notificacaoVoluntarioAceite(String code) {
        return "O voluntário " + code + " aceitou a sua encomenda";
    }

    public String notificacaoVoluntarioRecusado(String code) {
        return "O voluntário " + code + " recusou a sua encomenda\nVolte a solicitar a encomenda";
    }

    public String notificacaoNovaEntregaPendente(String userCode) {
        return "Tem uma entrega pendente do utilizador " + userCode;
    }

    public String notificacaoEntregaTransportadora(String transCode, String encCode) {
        return "Entrega da encomenda " + encCode + " realizada com sucesso pela transportadora " + transCode;
    }

    public String notificacaoEntregaVoluntario(String transCode, String encCode) {
        return "Entrega da encomenda " + encCode + " realizada com sucesso pelo voluntario " + transCode;
    }

    public String notificacaoEntregaAoUtilizador(String userCode, String encCode) {
        return "Entrega da encomenda " + encCode + " realizada com sucesso ao utilizador " + userCode;
    }

    public String notificacaoCompraRealizada(String encCode, String storeCode) {
        return "Compra realizada (" + encCode + ") na loja: " + storeCode + "\nSolicite a entrega da encomenda";
    }
}
