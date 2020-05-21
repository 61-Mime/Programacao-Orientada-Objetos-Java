package Controler;

import Model.*;
import View.Apresentacao;

import java.util.Scanner;

public class Interpretador {
    Apresentacao a = new Apresentacao();

    public void interpretador(GestTrazAqui c) {
        InterpretadorLogin intL = new InterpretadorLogin();
        Scanner s = new Scanner(System.in);

        a.welcome();
        s.nextLine();

        Login l = intL.interpretador(c);

        if(l==null)
            return;

        if (l.getTipoConta().equals("Utilizador")) {
            InterpretadorUtilizador intU = new InterpretadorUtilizador();
            intU.interpretador(c, l);
        }

        if (l.getTipoConta().equals("Voluntario") || l.getTipoConta().equals("Transportadora")) {
            InterpretadorEstafeta intE = new InterpretadorEstafeta();
            intE.interpretador(c, l);
        }

        s.close();
    }
}