package com.company;

import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.Scanner;
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

    public Estafeta registarEstafeta(String code, String nome, String type) {
        Scanner s = new Scanner((System.in));
        double lat, lon, raio, velocidade;
        String medic;
        boolean isMedic;

        System.out.print("Introduza a sua Latitude: ");
        lat = s.nextDouble();

        System.out.print("Introduza a sua Latitude: ");
        lon = s.nextDouble();

        System.out.print("Introduza o seu raio da ação: ");
        raio = s.nextDouble();

        System.out.print("Introduza a sua velocidade média: ");
        velocidade = s.nextDouble();

        System.out.println("Pode transportar encomendas médicas? (S/N): ");
        medic = s.nextLine();

        isMedic = medic.equals("S");

        return new Estafeta(code, nome, new Coordenadas(lat, lon), raio, velocidade, true, isMedic, 0, type);
    }

    public Transportadora registarTransportadora(Estafeta e) {
        Scanner s = new Scanner(System.in);
        int nif;
        double taxaKm, taxaPeso;

        System.out.print("Introduza o seu NIF: ");
        nif = s.nextInt();

        System.out.print("Introduza a sua taxa por Km: ");
        taxaKm = s.nextDouble();

        System.out.print("Introduza a sua taxa por Kg: ");
        taxaPeso = s.nextDouble();

        return new Transportadora(e.getCode(), e.getName(), e.getGps(), e.getRaio(), e.getVelocidade(), e.isFree(), e.isMedic(), e.getClassificacao(), nif, taxaKm, taxaPeso, 0);
    }

    public Loja registarLoja(String code, String nome) {
        Scanner s = new Scanner(System.in);
        double lat, lon;
        String queue;
        boolean hasQueue;

        System.out.print("Introduza a sua Latitude: ");
        lat = s.nextDouble();

        System.out.print("Introduza a sua Longitude: ");
        lon = s.nextDouble();

        System.out.print("A loja tem informação sobre a fila de espera? (S/N): ");
        queue = s.nextLine();

        hasQueue = queue.equals("S");

        if (hasQueue) {
            System.out.print("Qual é o tempo médio de espera em fila?: ");
            double queueTime = s.nextDouble();
            return new Loja(code, nome, new Coordenadas(lat, lon), hasQueue, queueTime);
        }

        return new Loja(code, nome, new Coordenadas(lat, lon), hasQueue, -1);
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
