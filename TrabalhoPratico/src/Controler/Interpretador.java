package Controler;

import Model.*;
import View.Apresentacao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Interpretador implements Serializable{
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
                    a.printErroComandoInvalido();
                    break;
            }
        }
    }

    public void interpretador(GestTrazAqui c) throws ClassNotFoundException, IOException {
        boolean r=true;
        InterpretadorLogin intL = new InterpretadorLogin();
        Scanner s = new Scanner(System.in);
        int command;
        Login l = null;

        a.welcome();
        s.nextLine();

        GuardarCarregarEstado g = new GuardarCarregarEstado();

        while(r) {
            if(l==null) {
                a.printMainMenuLogIn();
                command = (int) in.lerDouble("Escolha a sua opção:", 0, 3);

                switch (command) {
                    case 1:
                        l = intL.interpretador(c);
                        break;
                    case 2:
                        g.guardaDados("GestTrazAqui.dat", c);
                        a.printFicheiroGuardado("GestTrazAqui.dat");
                        break;
                    case 3:
                        c = g.carregaDados("GestTrazAqui.dat");
                        a.printFicheiroCarregado("GestTrazAqui.dat");
                        break;
                    case 0:
                        r = false;
                        break;
                    default:
                        a.printErroComandoInvalido();
                        break;

                }
            }

            else {
                a.printMainMenuLogOut(l.getTipoConta());
                command = (int) in.lerDouble("Escolha a sua opção:", 0, 5);
                switch (command) {
                    case 1:
                        l = null;
                        a.printLogoutSucesso();
                        break;
                    case 2:
                        switch (l.getTipoConta()) {
                            case "Utilizador":
                                InterpretadorUtilizador intU = new InterpretadorUtilizador();
                                intU.interpretador(c, l);
                                break;
                            case "Voluntario":
                                InterpretadorVoluntario intE = new InterpretadorVoluntario();
                                intE.interpretador(c, l);
                                break;
                            case "Transportadora":
                                InterpretadorTransportadora intT = new InterpretadorTransportadora();
                                intT.interpretador(c, l);
                                break;
                        }
                        break;
                    case 3:
                        interpretadorConsultas(c);
                        break;
                    case 4:
                        g.guardaDados("GestTrazAqui.dat", c);
                        a.printFicheiroGuardado("GestTrazAqui.dat");
                        break;
                    case 5:
                        c = g.carregaDados("GestTrazAqui.dat");
                        a.printFicheiroCarregado("GestTrazAqui.dat");
                        break;
                    case 0:
                        r = false;
                        break;
                    default:
                        a.printErroComandoInvalido();
                        break;
                }
            }
        }

        a.printSair();

        s.close();
    }
}