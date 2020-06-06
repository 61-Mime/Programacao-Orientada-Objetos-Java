package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.io.Serializable;

public class InterpretadorLoja implements Serializable {
    private final Input in;

    public InterpretadorLoja() {
        in = new Input();
    }

    public void interpretador(Apresentacao a, GestTrazAqui c, Login l) {
        boolean r = true;
        int command;
        String encCode;

        while (r) {
            if(c.hasQueueInfoLoja(l.getCode())) {
                a.printMenuLoja();
                command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 4);

                switch (command) {
                    case 1:
                        double time = in.lerDouble(a, "Introduza o tempo de espera na fila", 0, 30);
                        c.setStoreQueueTime(l.getCode(), time);
                        break;

                    case 0:
                        r = false;
                        break;

                    default:
                        a.printErroComandoInvalido();
                }
            }
            else {
                a.printMenuLojaIndisponivel();
                in.lerNum(a,"Escolha a sua opção:", 0);
                r = false;
            }
        }
    }
}
