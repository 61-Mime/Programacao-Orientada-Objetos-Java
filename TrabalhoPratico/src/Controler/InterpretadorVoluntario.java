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
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 4);

            switch (command) {
                case 1:
                    if(c.isEstafetaFree(l.getCode())) {
                        c.setEstafetaFree(l.getCode(), false);
                        a.printEstafetaIndisponivel();
                    }
                    else {
                        c.setEstafetaFree(l.getCode(), true);
                        a.printEstafetaDisponivel();
                    }
                    break;

                case 2:
                    LocalDateTime min = in.lerData("Intruza a 1º data de tipo(2018-12-02T10:15)");
                    LocalDateTime max = in.lerData("Intruza a 2º data de tipo(2018-12-02T10:15)");
                    a.printEncomendas("Lista de Entregas da Transportadora", c.getEncomendasEstafeta(l.getCode(),min,max));
                    break;

                case 3:

                    break;

                case 4:
                    a.printEstafetaClassicacao(c.getEstafetaClassificação(l.getCode()));
                    break;

                case 0:
                    r = false;
                    break;

                default:
                    a.printErroComandoInvalido();
            }
        }
    }
}
