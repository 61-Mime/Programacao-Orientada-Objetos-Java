package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InterpretadorVoluntario implements Serializable {
    Apresentacao a = new Apresentacao();
    Input in = new Input();

    public void interpretador(GestTrazAqui c, Login l) {
        boolean r = true;
        int command;

        while (r) {
            a.printMenuVoluntario();
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 3);

            switch (command) {
                case 1:
                    c.setEstafetaFree(l.getCode());
                    a.printMessage("Está disponivel para entregar encomendas");
                    break;

                case 2:
                    LocalDateTime min = in.lerData("Intruza a 1º data de tipo(2018-12-02T10:15)");
                    LocalDateTime max = in.lerData("Intruza a 2º data de tipo(2018-12-02T10:15)");
                    System.out.println(c.getEncomendasEstafeta(l.getCode(),min,max));
                    break;

                case 3:

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
