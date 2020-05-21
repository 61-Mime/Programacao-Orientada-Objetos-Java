package View;

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
        printMenus((new String[]{"Login", "Registar"}),"MENU LOGIN",0);
    }

    public void printMainMenu(String type) {
        printMenus((new String[]{"Menu " + type, "Consultas", "Gravar para um Ficheiro", "Carregar de um ficheiro"}),"MENU PRINCIPAL",0);
    }

    public void printMenuConsultas() {
        printMenus((new String[]{"Top Utilizadores do Sistema", "Top Transportadoras do Sistema"}),"MENU CONSULTAS",1);
    }

    public void printMenuUtilizador() {
        printMenus((new String[]{"Solicitar entrega de uma encomenda", "Aceder às encomendas","Fazer encomenda"}),"MENU UTILIZADOR",1);
    }

    public void printMenuEstafeta() {
        printMenus(new String[]{"Sinalizar como disponivel para entregar encomendas"},"MENU ESTAFETA",1);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageLn(String message) {
        System.out.println("\n" + message);

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
}
