package Controler;

import Model.GestTrazAqui;
import Model.Login;
import View.Apresentacao;

import java.time.LocalDateTime;
import java.util.Scanner;

public class InterpretadorTransportadora {
    Apresentacao a = new Apresentacao();
    Input in = new Input();

    private String lerStringEncomenda(String message, GestTrazAqui c, String code) {
        Scanner s = new Scanner(System.in);
        String line;

        do{
            a.printMessage(message);
            line = s.nextLine();
        } while (!c.containsEncomendaEstafeta(line, code));

        return line;
    }

    public void interpretador(GestTrazAqui c, Login l) {
        boolean r = true;
        int command;
        LocalDateTime min, max;

        while (r) {
            a.printMenuTransportadora();
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 4);

            switch (command) {
                case 1:
                    c.setEstafetaFree(l.getCode());
                    a.printMessage("Está disponivel para entregar encomendas");
                    break;

                case 2:
                    String encCode = lerStringEncomenda("Introduza o código de encomenda: ", c, l.getCode());
                    System.out.println(c.precoEncomenda(encCode, l.getCode()));
                    break;

                case 3:
                    min = in.lerData("Intruza a 1º data de tipo(2018-12-02T10:15)");
                    max = in.lerData("Intruza a 2º data de tipo(2018-12-02T10:15)");
                    System.out.println(c.getEncomendasEstafeta(l.getCode(),min,max));
                    break;

                case 4:
                    min = in.lerData("Intruza a 1º data de tipo(2018-12-02T10:15)");
                    max = in.lerData("Intruza a 2º data de tipo(2018-12-02T10:15)");
                    System.out.println(c.calcularFaturacao(l.getCode(), min, max));
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

