package Controler;

import Model.*;
import View.Apresentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpretadorLogin implements Serializable {
    private final Input in;

    public InterpretadorLogin() {
        in = new Input();
    }

    private Login login(GestTrazAqui c, Apresentacao a) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        a.printPedirUsername();
        user = s.nextLine();

        if(c.containsUser(user)) {
            a.play("sound/ok.wav");
            a.printPedirPassword();
            pass = s.nextLine();

            if(c.containsPassword(user, pass)) {
                a.play("sound/ok.wav");
                return c.getLogin(user);
            }
        }

        a.play("sound/error.wav");
        return null;
    }

    private Utilizador registarUtilizador(Apresentacao a, String code, String nome){
        Scanner s = new Scanner(System.in);
        double price;

        Coordenadas cr = in.lerCoordenada(a);
        price = in.lerDouble(a,"Introduza o Preco Máximo: ",0,1000000);

        return new Utilizador(code, nome, cr, price, new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }

    private Estafeta registarEstafeta(Apresentacao a, String code, String nome, String type) {
        Scanner s = new Scanner((System.in));
        double raio, velocidade;
        String medic;
        boolean isMedic;

        Coordenadas cr = in.lerCoordenada(a);
        raio = in.lerDouble(a,"Introduza o seu raio da ação: ",0,100000);
        velocidade = in.lerDouble(a,"Introduza a sua velocidade média: ",0,100000);

        a.printPedirEncomendasMedicas();
        medic = s.nextLine();

        isMedic = medic.equals("S");

        return new Estafeta(code, nome, cr, raio, velocidade, 0, true, isMedic, 0, 0, type,false,new ArrayList<>());
    }

    private Transportadora registarTransportadora(Apresentacao a, Estafeta e) {
        Scanner s = new Scanner(System.in);
        int nif;
        double taxaKm, taxaPeso;

        nif = (int) in.lerDouble(a,"Introduza o seu NIF: ",0,1000000);
        taxaKm = in.lerDouble(a,"Introduza a sua taxa por Km: ",0,1000000);
        taxaPeso = in.lerDouble(a,"Introduza a sua taxa por Kg: ",0,1000000);

        return new Transportadora(e.getCode(), e.getName(), e.getGps(), e.getRaio(), e.getVelocidade(), e.getNumKm(), e.isFree(), e.isMedic(), e.getClassificacao(),e.getNumCla(), nif, taxaKm, taxaPeso, 0,false, new ArrayList<>());
    }

    private Loja registarLoja(Apresentacao a, String code, String nome) {
        Scanner s = new Scanner(System.in);
        String queue;
        boolean hasQueue;

        Coordenadas cr = in.lerCoordenada(a);

        a.printPedirFilaEspera();
        queue = s.nextLine();

        hasQueue = queue.equals("S");

        if (hasQueue) {
            double queueTime = in.lerDouble(a,"Qual é o tempo médio de espera em fila?: ",0,1000);
            return new Loja(code, nome, cr, hasQueue, queueTime, new ArrayList<>(), new ArrayList<>());
        }

        return new Loja(code, nome, cr, hasQueue, -1, new ArrayList<>(), new ArrayList<>());
    }

    private boolean registar(GestTrazAqui c, Apresentacao a) {
        Scanner s = new Scanner(System.in);
        Login l = new Login();
        String code, nome, pass, tipo;

        a.printPedirNomeCompleto();
        nome = s.nextLine();

        do {
            a.printPedirTipoConta();
            tipo = s.nextLine();
        } while(!(tipo.equals("Voluntario") || tipo.equals("Transportadora") || tipo.equals("Utilizador") || tipo.equals("Loja")));

        if(!c.containsNameAndType(nome, tipo)) {
            l.setNome(nome);
            l.setTipoConta(tipo);

            a.printPedirPassword();
            pass = s.nextLine();

            l.setPassword(pass);

            do { code = c.generateCode(tipo); } while(c.containsUser(code));

            l.setCode(code);

            switch(tipo) {
                case "Voluntario":
                    c.addEstafeta(registarEstafeta(a, code, nome, "Voluntario"));
                    break;
                case "Transportadora":
                    Estafeta e = registarEstafeta(a, code, nome, "Transportadora");
                    c.addEstafeta(registarTransportadora(a, e));
                    break;
                case "Utilizador":
                    c.addUser(registarUtilizador(a, code, nome));
                    break;
                case "Loja":
                    c.addLoja(registarLoja(a, code, nome));
                    break;
            }

            a.printCodigoAcesso(code);

            c.addLogin(l);

            return true;
        }

        return false;
    }

    public Login interpretador(GestTrazAqui c, Apresentacao a) {
        boolean r=true;
        int command;
        Login l = null;

        while(r) {
            a.printMenuLogin();
            command = (int) in.lerDouble(a,"Escolha a sua opção:", 0, 2);

            switch(command){
                case 1:
                    if((l = login(c, a))!= null) {
                        a.printLoginSucesso();
                        r = false;
                    }
                    else {
                        a.printErroDadosInvalidos();
                    }
                    break;

                case 2:
                    if(registar(c, a))
                        a.printRegistoSucesso();
                    else
                        a.printErroDadosInvalidos();
                    break;

                case 0:
                    l = null;
                    r = false;
                    break;

                default:
                    a.printErroComandoInvalido();
                    break;
            }
        }
        return l;
    }
}
