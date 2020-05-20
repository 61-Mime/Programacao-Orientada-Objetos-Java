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

    public void printMenu() {
        printLine(22);
        System.out.println("1 | Login");
        System.out.println("2 | Registar");
        System.out.println("Q | Sair");
        printLine(22);
        System.out.print("Escolha a sua opção: ");
    }

    public void printEscolheVolintariTransportadora() {
        printLine(22);
        System.out.println("1 | Voluntario");
        System.out.println("2 | Transportadora");
        System.out.println("3 | Ambos");
        System.out.println("B | Voltar atrás");
        printLine(22);
        System.out.print("Escolha a sua opção: ");
    }

    public void printMenuUtilizador() {
        printLine(35);
        System.out.println("1 | Fazer uma encomenda");
        System.out.println("2 | Aceder às encomendas anteriores");
        System.out.println("Q | Sair");
        printLine(35);
        System.out.print("Escolha a sua opção: ");
    }

    public void printMenuVoluntario() {
        printLine(22);
        System.out.println("1 | ");
        System.out.println("Q | Sair");
        printLine(22);
        System.out.print("Escolha a sua opção: ");
    }

    public void printMenuTransportadora() {
        printLine(22);
        System.out.println("1 | ");
        System.out.println("Q | Sair");
        printLine(22);
        System.out.print("Escolha a sua opção: ");
    }

    public void printMenuLoja() {
        printLine(22);
        System.out.println("1 | ");
        System.out.println("Q | Sair");
        printLine(22);
        System.out.print("Escolha a sua opção: ");
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printMessageLn(String message) {
        System.out.println("\n" + message);
    }


}
