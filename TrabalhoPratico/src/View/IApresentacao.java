package View;

public interface IApresentacao {
    void welcome();
    void clear();
    void printMenuLogin();
    void printMainMenuLogIn();
    void printMainMenuLogOut(String type);
    void printMenuUtilizador();
    void printMenuVoluntario();
    void printMessage(String message);
}
