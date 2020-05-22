package View;

public interface IApresentacao {
    void welcome();
    void clear();
    void printMenuLogin();
    void printMainMenuLogIn();
    void printMainMenuLogOut(String type, int numN);
    void printMenuUtilizador();
    void printMenuVoluntario();
    void printMessage(String message);
}
