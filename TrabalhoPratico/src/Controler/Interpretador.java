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

    private void notificacoes(GestTrazAqui c, Login l, String type) {
        if(type.equals("Utilizador")) {
            a.printTable("Notificações", c.getUserNotificacoes(l.getCode()));
            c.limpaUserNotificacoes(l.getCode());
        }
        else {
            a.printTable("Notificações", c.getEstafetaNotificacoes(l.getCode()));
            c.limpaEstafetaNotificacoes(l.getCode());
        }
    }

    public void interpretador(GestTrazAqui c) throws ClassNotFoundException, IOException {
        boolean r=true;
        InterpretadorLogin intL = new InterpretadorLogin();
        Scanner s = new Scanner(System.in);
        int command, numN;
        Login l = null;
        String type;

        if(in.lerSN(a.pedirMusica())) {
            a.welcome();
            Audio music = new Audio();
            music.play("music1.wav");
        }

        else {
            a.welcome();
            s.nextLine();
        }

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
                type = l.getTipoConta();

                if(l.getTipoConta().equals("Utilizador"))
                    numN = c.getUserNumNotificacoes(l.getCode());
                else
                    numN = c.getEstafetaNumNotificacoes(l.getCode());

                a.printMainMenuLogOut(l.getTipoConta(),numN);
                command = (int) in.lerDouble("Escolha a sua opção:", 0, 6);
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
                    case 6:
                        notificacoes(c, l, type);
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