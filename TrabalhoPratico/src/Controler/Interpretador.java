package Controler;

import Model.*;
import View.Apresentacao;

import java.util.Scanner;

public class Interpretador {
    Apresentacao a = new Apresentacao();
    Input in = new Input();

    private void interpretadorConsultas(GestTrazAqui c) {
        boolean r=true;
        int command;

        while(r) {
            a.printMenuConsultas();
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 2);

            switch(command) {
                case 1:
                    a.printTable("Top Utilizadores", c.getTopUsers());
                    break;
                case 2:
                    a.printTable("Top Transportadoras", c.getTopTrans());
                    break;
                case 0:
                    r = false;
                    break;
                default:
                    a.printMessageLn("Comando inválido");
                    break;
            }
        }
    }

    public void interpretador(GestTrazAqui c) {
        boolean r=true;
        InterpretadorLogin intL = new InterpretadorLogin();
        Scanner s = new Scanner(System.in);
        int command;

        a.welcome();
        s.nextLine();

        Login l = intL.interpretador(c);

        if(l==null)
            return;

        while(r) {
            a.printMainMenu(l.getTipoConta());
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 2);
            switch (command) {
                case 1:
                    if (l.getTipoConta().equals("Utilizador")) {
                        InterpretadorUtilizador intU = new InterpretadorUtilizador();
                        intU.interpretador(c, l);
                    }

                    else if (l.getTipoConta().equals("Voluntario") || l.getTipoConta().equals("Transportadora")) {
                        InterpretadorEstafeta intE = new InterpretadorEstafeta();
                        intE.interpretador(c, l);
                    }
                    break;
                case 2:
                    interpretadorConsultas(c);
                    break;
                case 3:
                    //Gravar num ficheiro
                    break;
                case 0:
                    r = false;
                    break;
                default:
                    a.printMessageLn("Comando inválido");
                    break;
            }
        }

        a.printMessageLn("A sair do programa");

        s.close();
    }
}