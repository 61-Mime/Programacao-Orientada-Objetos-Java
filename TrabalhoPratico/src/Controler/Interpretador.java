package Controler;

import Model.*;
import View.Apresentacao;
import View.ApresentacaoNotificacoes;
import View.Audio;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Interpretador implements Serializable{
    Input in = new Input();

    private void interpretadorConsultas(GestTrazAqui c, Apresentacao a) {
        boolean r=true;
        int command;

        while(r) {
            a.printMenuConsultas();
            command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 2);

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

    private void notificacoes(GestTrazAqui c, Apresentacao a, Login l, String type) {
        List<Notificacao> notificacoes;

        if(type.equals("Utilizador"))
            notificacoes = c.getUserNotificacoes(l.getCode());
        else
            notificacoes = c.getUserNotificacoes(l.getCode());

        int size = notificacoes.size();

        if(size == 0)
            a.printEmptyNot();
        else {
            boolean r=true;
            int page = 0, command = 0;

            while(r) {
                a.notifTable(notificacoes.get(page).getNot(), notificacoes.get(page).getType(), page, size);
                command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 3);

                if(command == 1 && page<(size-1))
                    page ++;

                else if(command==2 && page>=0)
                    page --;

                else if(command==3 && notificacoes.get(page).getType() == 2)
                    System.out.println("CLASSIFICAR");

                else if(command==0)
                    r=false;

                else
                    a.printErroComandoInvalido();

            }
        }

        if(type.equals("Utilizador"))
            c.limpaUserNotificacoes(l.getCode());
        else
            c.limpaEstafetaNotificacoes(l.getCode());
    }

    public void interpretador(GestTrazAqui c, Apresentacao a) throws ClassNotFoundException, IOException {
        int command, numN;
        boolean r=true;
        String type;

        InterpretadorLogin intL = new InterpretadorLogin();
        Scanner s = new Scanner(System.in);
        Login l = null;
        GuardarCarregarEstado g = new GuardarCarregarEstado();

        a.welcome();
        a.play("sound/on.wav");
        s.nextLine();

        while(r) {
            if(l==null) {
                a.printMainMenuLogIn();
                command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 3);

                switch (command) {
                    case 1:
                        l = intL.interpretador(c, a);
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
                command = (int) in.lerDouble(a, "Escolha a sua opção:", 0, 6);
                switch (command) {
                    case 1:
                        l = null;
                        a.printLogoutSucesso();
                        break;
                    case 2:
                        switch (l.getTipoConta()) {
                            case "Utilizador":
                                InterpretadorUtilizador intU = new InterpretadorUtilizador();
                                intU.interpretador(c, a, l);
                                break;
                            case "Voluntario":
                                InterpretadorVoluntario intE = new InterpretadorVoluntario();
                                intE.interpretador(a, c, l);
                                break;
                            case "Transportadora":
                                InterpretadorTransportadora intT = new InterpretadorTransportadora();
                                intT.interpretador(c, a, l);
                                break;
                        }
                        break;
                    case 3:
                        interpretadorConsultas(c, a);
                        break;
                    case 4:
                        g.guardaDados("files/GestTrazAqui.dat", c);
                        a.printFicheiroGuardado("files/GestTrazAqui.dat");
                        break;
                    case 5:
                        c = g.carregaDados("files/GestTrazAqui.dat");
                        a.printFicheiroCarregado("GestTrazAqui.dat");
                        break;
                    case 6:
                        notificacoes(c, a, l, type);
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