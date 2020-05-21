package View;

public interface IApresentacao {
    void welcome();
    void clear();
    void printMenuLogin();
    void printMainMenu(String type);
    void printMenuUtilizador();
    void printMenuEstafeta();
    void printMessage(String message);
    void printMessageLn(String message);
}
