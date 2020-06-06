package View;

public class ApresentacaoLogin {
    Output out = new Output();

    public void printMenuLogin() {
        out.printMenus((new String[]{"Login", "Registar"}),"MENU LOGIN",1);
    }

    public void printCodigoAcesso(String code) {
        System.out.println("Códido de Acesso: " + code);
    }

    public void printLoginSucesso() {
        System.out.println("Login efetuado com sucesso");
    }

    public void printLogoutSucesso() {
        System.out.println("Logout efetuado com sucesso");
    }

    public void printRegistoSucesso() {
        System.out.println("Registo efetuado com sucesso");
    }

    public void printErroDadosInvalidos() {
        System.out.println("Dados inválidos");
    }

    public void printPedirUsername() {
        System.out.println("Introduza o username: ");
    }

    public void printPedirPassword() {
        System.out.println("Introduza a password: ");
    }

    public void printPedirEncomendasMedicas() {
        System.out.println("Pode transportar encomendas médicas? (S/N): ");
    }

    public void printPedirFilaEspera() {
        System.out.println("A loja tem informação sobre a fila de espera? (S/N): ");
    }

    public void printPedirNomeCompleto() {
        System.out.println("Introduza o nome completo: ");
    }

    public void printPedirTipoConta() {
        System.out.println("Introduza o tipo de conta (Voluntario / Transportadora / Utilizador / Loja): ");
    }
}
