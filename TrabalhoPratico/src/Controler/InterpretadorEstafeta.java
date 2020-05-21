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
        int command;

        while (r) {
            a.printMenuEstafeta();
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 1);

            switch (command) {
                case 1:
                    c.setEstafetaFree(l.getCode());
                    a.printMessage("Está disponivel para entregar encomendas");
                    break;

                case 0:
                    r = false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }
}
