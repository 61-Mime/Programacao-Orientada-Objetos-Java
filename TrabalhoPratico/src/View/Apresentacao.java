package View;

import Model.Encomenda;

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

    public void printMainMenuLogOut(String type) {
        printMenus((new String[]{"Logout","Menu " + type, "Consultas", "Gravar para um Ficheiro", "Carregar de um ficheiro"}),"MENU PRINCIPAL",0);
    }

    public void printMenuConsultas() {
        printMenus((new String[]{"Top Utilizadores do Sistema", "Top Transportadoras do Sistema"}),"MENU CONSULTAS",1);
    }

    public void printMenuUtilizador() {
        printMenus((new String[]{"Solicitar entrega de uma encomenda", "Aceder às encomendas","Fazer encomenda"}),"MENU UTILIZADOR",1);
    }

    public void printMenuVoluntario() {
        printMenus(new String[]{"Sinalizar como disponivel/indisponivel para entregar encomendas", " ","Aceder às encomendas", "Classificação"},"MENU VOLUNTÁRIO",1);
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
        System.out.println("Introduza o tipo de conta (Voluntario / Transportadora / Utilizador / Loja): ");
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

    public void printEncomendaEntregue(String code, String tipo, String nome, double preco, double tempo) {
        System.out.println("A sua encomenda foi entregue pelo(a) " + tipo + " " + code + ": " + nome);
        if(tipo.equals("Transportadora"))
            System.out.println("Preço: " + preco + " €");
        System.out.println("Tempo de entrega: " + tempo + " min");
    }

    public void printEncomendaAceite() {
        System.out.println("A sua encomenda foi aceite pela loja e precisa de ser solicitada");
    }
}
