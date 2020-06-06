package View;

import java.io.Serializable;

public class ApresentacaoMain implements Serializable {
    private final Output out;

    public ApresentacaoMain() {
        out = new Output();
    }

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

    public void printMainMenuLogIn() {
        out.printMenus((new String[]{"Login/Registar", "Gravar para um Ficheiro", "Carregar de um ficheiro"}),"MENU PRINCIPAL",0);
    }

    public void printMainMenuLogOut(String type, int numN) {
        out.printMenus((new String[]{"Logout","Menu " + type, "Consultas", "Gravar para um Ficheiro", "Carregar de um ficheiro", "Notificações (" + numN + ")"}),"MENU PRINCIPAL",0);
    }

    public void printMenuConsultas() {
        out.printMenus((new String[]{"Top Utilizadores do Sistema", "Top Transportadoras do Sistema"}),"MENU CONSULTAS",1);
    }

    public void printErroComandoInvalido(){
        System.out.println("Comando Inválido");
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

}
