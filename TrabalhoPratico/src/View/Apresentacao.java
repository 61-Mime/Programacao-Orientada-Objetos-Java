package View;

public class Apresentacao implements IApresentacao {

    public void welcome() {
        System.out.println(" _____                 _               _");
        System.out.println("|_   _| __ __ _ ____  / \\   __ _ _   _(_)");
        System.out.println("  | || '__/ _` |_  / / _ \\ / _` | | | | |");
        System.out.println("  | || | | (_| |/ / / ___ \\ (_| | |_| | |");
        System.out.println("  |_||_|  \\__,_/___/_/   \\_\\__, |\\__,_|_|");
        System.out.println("                              |_|");
        System.out.println("Bem vindo à aplicação traz aqui.");
        System.out.println("Precione qualquer tecla para continuar.");
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

    public void printMenus(String []menu,int size2,int type){
        printLine(size2);
        int size;
        if(menu == null)
            size = 0;
        else
            size = menu.length;
        for(int i = 0;i < size;i++)
            System.out.println(i+1+" | "+menu[i]);
        if(type == 0)
            System.out.println("Q | Sair");
        else
            System.out.println("B | Voltar atrás");
        printLine(size2);
    }

    public void printMenu() {
        printMenus((new String[]{"Login", "Registar"}),22,0);

    }

    public void printEscolheVolintariTransportadora() {
        printMenus((new String[]{"Voluntário", "Transportadora","Ambos"}),22,1);
    }

    public void printMenuUtilizador() {
        printMenus((new String[]{"Solicitar entrega de uma encomenda", "Aceder às encomendas"}),36,1);
    }

    public void printMenuVoluntario() {
        printMenus(null,22,1);
    }

    public void printMenuTransportadora() {
        printMenus(null,22,1);
    }

    public void printMenuLoja() {
        printMenus(null,22,1);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageLn(String message) {
        System.out.println("\n" + message);

    }
}
