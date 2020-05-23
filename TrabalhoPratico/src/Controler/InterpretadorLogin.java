package Controler;

import Model.*;
import View.Apresentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpretadorLogin implements Serializable {
    Apresentacao a = new Apresentacao();
    Input in = new Input();

    private Login login(GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        a.printPedirUsername();
        user = s.nextLine();

        if(c.containsUser(user)) {
            a.printPedirPassword();
            pass = s.nextLine();

            if(c.containsPassword(user, pass))
                return c.getLogin(user);
        }

        return null;
    }

    private Utilizador registarUtilizador(String code, String nome){
        Scanner s = new Scanner(System.in);
        double price;

        Coordenadas cr = in.lerCoordenada();
        price = in.lerDouble("Introduza o Preco Máximo: ",0,1000000);

        return new Utilizador(code, nome, cr, price, new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }

    private Estafeta registarEstafeta(String code, String nome, String type) {
        Scanner s = new Scanner((System.in));
        double raio, velocidade;
        String medic;
        boolean isMedic;

        Coordenadas cr = in.lerCoordenada();
        raio = in.lerDouble("Introduza o seu raio da ação: ",0,100000);
        velocidade = in.lerDouble("Introduza a sua velocidade média: ",0,100000);

        a.printPedirEncomendasMedicas();
        medic = s.nextLine();

        isMedic = medic.equals("S");

        return new Estafeta(code, nome, cr, raio, velocidade, 0, true, isMedic, 0, 0, type,false,new ArrayList<>());
    }

    private Transportadora registarTransportadora(Estafeta e) {
        Scanner s = new Scanner(System.in);
        int nif;
        double taxaKm, taxaPeso;

        nif = (int) in.lerDouble("Introduza o seu NIF: ",0,1000000);
        taxaKm = in.lerDouble("Introduza a sua taxa por Km: ",0,1000000);
        taxaPeso = in.lerDouble("Introduza a sua taxa por Kg: ",0,1000000);

        return new Transportadora(e.getCode(), e.getName(), e.getGps(), e.getRaio(), e.getVelocidade(), e.getNumKm(), e.isFree(), e.isMedic(), e.getClassificacao(),e.getNumCla(), nif, taxaKm, taxaPeso, 0,false, new ArrayList<>());
    }

    private Loja registarLoja(String code, String nome) {
        Scanner s = new Scanner(System.in);
        String queue;
        boolean hasQueue;

        Coordenadas cr = in.lerCoordenada();

        a.printPedirFilaEspera();
        queue = s.nextLine();

        hasQueue = queue.equals("S");

        if (hasQueue) {
            double queueTime = in.lerDouble("Qual é o tempo médio de espera em fila?: ",0,1000);
            return new Loja(code, nome, cr, hasQueue, queueTime, new ArrayList<>());
        }

        return new Loja(code, nome, cr, hasQueue, -1, new ArrayList<>());
    }

    private boolean registar(GestTrazAqui c) {
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
                    c.addEstafeta(registarEstafeta(code, nome, "Voluntario"));
                    break;
                case "Transportadora":
                    Estafeta e = registarEstafeta(code, nome, "Transportadora");
                    c.addEstafeta(registarTransportadora(e));
                    break;
                case "Utilizador":
                    c.addUser(registarUtilizador(code, nome));
                    break;
                case "Loja":
                    c.addLoja(registarLoja(code, nome));
                    break;
            }

            a.printCodigoAcesso(code);

            c.addLogin(l);

            return true;
        }

        return false;
    }

    public Login interpretador(GestTrazAqui c) {
        boolean r=true;
        int command;
        Login l = null;

        while(r) {
            a.printMenuLogin();
            command = (int) in.lerDouble("Escolha a sua opção:", 0, 2);

            switch(command){
                case 1:
                    if((l = login(c))!= null) {
                        a.printLoginSucesso();
                        r = false;
                    }
                    else
                        a.printErroDadosInvalidos();
                    break;

                case 2:
                    if(registar(c))
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