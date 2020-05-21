package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.util.Scanner;

public class InterpretadorEstafeta {
    Apresentacao a = new Apresentacao();
    Input in = new Input();

    public void interpretador(GestTrazAqui c, Login l) {
        boolean r = true;
        Scanner s = new Scanner(System.in);
        String line;

        while (r) {
            a.printMenuTransportadora();
            line = s.nextLine();

            switch (line) {
                case "1":
                    c.setEstafetaFree(l.getCode());
                    a.printMessage("Está disponivel para entregar encomendas");
                    break;

                case "Q":
                    r = false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }
}
