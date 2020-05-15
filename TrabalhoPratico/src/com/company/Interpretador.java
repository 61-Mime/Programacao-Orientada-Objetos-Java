package com.company;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Interpretador {

    public Login login(Controlador c) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        System.out.println("Introduza o usercode");
        user = s.nextLine();

        if(c.containsUser(user)) {
            System.out.println("Introduza a password");
            pass = s.nextLine();

            if(c.containsPassword(user, pass))
                return c.getLogin(user);
        }

        return null;
    }

    public Utilizador registarUtilizador(String code, String nome){
        Scanner s = new Scanner(System.in);
        double lat, lon, price;

        System.out.print("Introduza a sua Latitude: ");
        lat = s.nextDouble();

        System.out.print("Introduza a sua Latitude: ");
        lon = s.nextDouble();

        System.out.print("Introduza o Preco Máximo: ");
        price = s.nextDouble();

        return new Utilizador(code, nome, new Coordenadas(lat, lon), price, new ArrayList<>());
    }

    public boolean registar(Controlador c) {
        Scanner s = new Scanner(System.in);
        Login l = new Login();
        String code, nome, pass, tipo;

        System.out.print("Introduza o nome completo: ");
        nome = s.nextLine();

        do {
            System.out.print("Introduza o tipo de conta (Voluntario / Transportadora / Utilizador / Loja): ");
            tipo = s.nextLine();
        } while(!(tipo.equals("Voluntario") || tipo.equals("Transportadora") || tipo.equals("Utilizador") || tipo.equals("Loja")));

        if(!c.containsNameAndType(nome, tipo)) {
            l.setNome(nome);
            l.setTipoConta(tipo);

            System.out.print("Introduza a password: ");
            pass = s.nextLine();

            l.setPassword(pass);

            do { code = l.generateCode(tipo); } while(c.containsUser(code));

            l.setCode(code);

            switch(tipo) {
                case "Voluntario":
                    break;
                case "Transportadora":
                    break;
                case "Utilizador":
                    c.addUser(registarUtilizador(code, nome));
                    break;
                case "Loja":
                    break;
            }

            System.out.println("Código de acesso: " + l);

            c.addLogin(l);

            return true;
        }

        return false;
    }

    public int escolheVoluntarioTransportadora() {
        boolean r=true;
        int res = 1;
        String tipo;
        Scanner s = new Scanner(System.in);

        while(r){
            System.out.println("Voluntario / Transportadora / Ambos / Q");
            tipo = s.nextLine();
            switch(tipo) {
                case "Voluntario":
                    res = 1;
                    r = false;
                    break;

                case "Transportadora":
                    res = 2;
                    r = false;
                    break;

                case "Ambos":
                    res = 3;
                    r = false;
                    break;

                case "Q":
                    res = 0;
                    r = false;
                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }

        return res;
    }

    public void menuUtilizador(Controlador c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            System.out.println("1 | Fazer uma encomenda");
            System.out.println("2 | Aceder às encomendas anteriores");
            System.out.println("Q | Sair");
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "2":
                    int res = escolheVoluntarioTransportadora();

                    switch(res) {
                        case 1:
                            System.out.println(c.getUser(l.getCode()).getEntregas().stream().filter(Encomenda::isVoluntario).collect(Collectors.toList()));
                            break;
                        case 2:
                            System.out.println(c.getUser(l.getCode()).getEntregas().stream().filter(Encomenda::isTransportadora).collect(Collectors.toList()));
                            break;
                        case 3:
                            System.out.println(c.getUser(l.getCode()).getEntregas());
                            break;
                        default:
                            break;
                    }

                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }
    }

    public void menuVoluntario(Controlador c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            System.out.println("1 | ");
            System.out.println("Q | Sair");
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }
    }

    public void menuTransportadora(Controlador c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            System.out.println("1 | ");
            System.out.println("Q | Sair");
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }
    }

    public void menuLoja(Controlador c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            System.out.println("1 | ");
            System.out.println("Q | Sair");
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }
    }

    public void menu(Controlador c, Login l) {
        switch (l.getTipoConta()) {
            case "Utilizador":
                menuUtilizador(c, l);
                break;
            case "Voluntario":
                menuVoluntario(c, l);
                break;
            case "Transportadora":
                menuTransportadora(c, l);
                break;
            case "Loja":
                menuLoja(c, l);
                break;
        }
    }

    public void interpretador(Controlador c) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;
        Login l = null;

        while(r) {
            System.out.println("Login ou Registar");
            line = s.nextLine();

            if(line.equals("Login") || line.equals("login")) {
                l = login(c);

                if(l != null) {
                    r=false;
                    System.out.println("Login efetuado com sucesso");
                }

                else
                    System.out.println("Dados inválidos");
            }

            else if(line.equals("Registar") || line.equals("registar")) {
                if(registar(c)) {
                    System.out.println("Registo efetuado com sucesso");
                    System.out.println("Efetue Login para continuar");
                }

                else
                    System.out.println("Dados inválidos");
            }

            else {
                System.out.println("Comando Inválido");
            }
        }

        menu(c, l);

        s.close();
    }
}
