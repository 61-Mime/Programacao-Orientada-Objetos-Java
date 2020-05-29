package View;

import Model.Encomenda;
import Model.LinhaEncomenda;

import java.io.Serializable;
import java.util.List;

public class Apresentacao implements IApresentacao, Serializable {

    public void welcome() {
        System.out.println(" _____                 _               _");
        System.out.println("|_   _| __ __ _ ____  / \\   __ _ _   _(_)");
        System.out.println("  | || '__/ _` |_  / / _ \\ / _` | | | | |");
        System.out.println("  | || | | (_| |/ / / ___ \\ (_| | |_| | |");
        System.out.println("  |_||_|  \\__,_/___/_/   \\_\\__, |\\__,_|_|");
        System.out.println("                              |_|");
        System.out.println("Bem vindo à aplicação traz aqui.");
        System.out.println("Pressione qualquer tecla para continuar.");
    }

    public void clear() {
        for(int i = 0; i<5; i++)
            System.out.println("");
    }

    private void printLine(int size) {
        for(int i=0; i<size; i++)
            System.out.print("-");

        System.out.println("");
    }

    private void printMenus(String []menu, String message, int type){

        int size, length=message.length();

        for(String linha: menu)
            if(linha.length() + 4 > length)
                length = linha.length() + 4;

        printLine(length);
        System.out.println(message);
        printLine(length);

        size = menu.length;
        for(int i = 0;i < size;i++)
            System.out.println(i+1+" | "+menu[i]);
        if(type == 0)
            System.out.println("0 | Sair");
        else
            System.out.println("0 | Voltar atrás");
        printLine(length);
    }

    public void printMenuLogin() {
        printMenus((new String[]{"Login", "Registar"}),"MENU LOGIN",1);
    }

    public void printMainMenuLogIn() {
        printMenus((new String[]{"Login/Registar", "Gravar para um Ficheiro", "Carregar de um ficheiro"}),"MENU PRINCIPAL",0);
    }

    public void printMainMenuLogOut(String type, int numN) {
        printMenus((new String[]{"Logout","Menu " + type, "Consultas", "Gravar para um Ficheiro", "Carregar de um ficheiro", "Notificações (" + numN + ")"}),"MENU PRINCIPAL",0);
    }

    public void printMenuConsultas() {
        printMenus((new String[]{"Top Utilizadores do Sistema", "Top Transportadoras do Sistema"}),"MENU CONSULTAS",1);
    }

    public void printMenuUtilizador() {
        printMenus((new String[]{"Solicitar entrega de uma encomenda", "Aceder às encomendas","Fazer encomenda"}),"MENU UTILIZADOR",1);
    }

    public void printMenuVoluntario() {
        printMenus(new String[]{"Sinalizar como disponivel/indisponivel para entregar encomendas", "Aceitar Encomenda","Aceder às encomendas", "Classificação"},"MENU VOLUNTÁRIO",1);
    }

    public void printMenuTransportadora() {
        printMenus(new String[]{"Sinalizar como disponivel/indisponivel para entregar encomendas", "Preço de transporte de uma encomenda", "Aceder às encomendas", "Total faturado", "Classificação"},"MENU TRANSPORTADORA",1);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printArray(String message, List<String> arr) {
        System.out.println("\n" + message);

        for(String line : arr)
            System.out.println(line);

        System.out.print("\n");
    }

    public void printTable(String message, List<String> arr) {
        System.out.println("\n" + message);

        for(int i=0; i<arr.size(); i++)
            System.out.println((i+1) + ") " + arr.get(i));

        System.out.print("\n");
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

    public void printErroEntrega() {
        System.out.println("Sem mais estafetas disponiveis!");
    }

    public void printErroComandoInvalido(){
        System.out.println("Comando Inválido");
    }

    public void printErroEncomendaInvalida(){
        System.out.println("Encomenda Inválida");
    }

    public void printFicheiroCarregado(String file){
        System.out.println("Ficheiro " + file + " carregado");
    }

    public void printFicheiroGuardado(String file){
        System.out.println("Ficheiro " + file + " guardado");
    }

    public void printSair() {
        System.out.println("A Sair do Programa");
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

    public void printPedirClassificar() {
        System.out.println("Pretende classificar o serviço?(S/N)");
    }

    public void printPedirEncomenda() {
        System.out.println("Escolha uma encomenda: ");
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

    public void printEncomendaStandBy(String code){
        System.out.println("A sua encomenda está à espera de ser aceite pelo voluntário " + code);
    }

    public void printSemEncomendas(){
        System.out.println("Não existem encomendas para entrega!");
    }

    public void printEncRecusada(){
        System.out.println("Encomenda recusada com sucesso!");
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
        System.out.println("A encomenda foi cancelada");
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

    public String pedirMusica() {
        return "Deseja ouvir música de fundo? (S/N)";
    }
}
