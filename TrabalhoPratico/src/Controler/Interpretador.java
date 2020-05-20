package Controler;

import Model.*;
import View.Apresentacao;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Interpretador {
    Apresentacao a = new Apresentacao();

    public Login login(GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        a.printMessage("Introduza o usercode: ");
        user = s.nextLine();

        if(c.containsUser(user)) {
            a.printMessage("Introduza a password: ");
            pass = s.nextLine();

            if(c.containsPassword(user, pass))
                return c.getLogin(user);
        }

        return null;
    }

    public Utilizador registarUtilizador(String code, String nome){
        Scanner s = new Scanner(System.in);
        double lat, lon, price;

        a.printMessage("Introduza a sua Latitude: ");
        lat = s.nextDouble();

        a.printMessage("Introduza a sua Longitude: ");
        lon = s.nextDouble();

        a.printMessage("Introduza o Preco Máximo: ");
        price = s.nextDouble();

        return new Utilizador(code, nome, new Coordenadas(lat, lon), price, new ArrayList<>());
    }

    public Estafeta registarEstafeta(String code, String nome, String type) {
        Scanner s = new Scanner((System.in));
        double lat, lon, raio, velocidade;
        String medic;
        boolean isMedic;

        a.printMessage("Introduza a sua Latitude: ");
        lat = s.nextDouble();

        a.printMessage("Introduza a sua Longitude: ");
        lon = s.nextDouble();

        a.printMessage("Introduza o seu raio da ação: ");
        raio = s.nextDouble();

        a.printMessage("Introduza a sua velocidade média: ");
        velocidade = s.nextDouble();

        a.printMessage("Pode transportar encomendas médicas? (S/N): ");
        medic = s.nextLine();

        isMedic = medic.equals("S");

        return new Estafeta(code, nome, new Coordenadas(lat, lon), raio, velocidade, true, isMedic, 0, type);
    }

    public Transportadora registarTransportadora(Estafeta e) {
        Scanner s = new Scanner(System.in);
        int nif;
        double taxaKm, taxaPeso;

        a.printMessage("Introduza o seu NIF: ");
        nif = s.nextInt();

        a.printMessage("Introduza a sua taxa por Km: ");
        taxaKm = s.nextDouble();

        a.printMessage("Introduza a sua taxa por Kg: ");
        taxaPeso = s.nextDouble();

        return new Transportadora(e.getCode(), e.getName(), e.getGps(), e.getRaio(), e.getVelocidade(), e.isFree(), e.isMedic(), e.getClassificacao(), nif, taxaKm, taxaPeso, 0);
    }

    public Loja registarLoja(String code, String nome) {
        Scanner s = new Scanner(System.in);
        double lat, lon;
        String queue;
        boolean hasQueue;

        a.printMessage("Introduza a sua Latitude: ");
        lat = s.nextDouble();

        a.printMessage("Introduza a sua Longitude: ");
        lon = s.nextDouble();

        a.printMessage("A loja tem informação sobre a fila de espera? (S/N): ");
        queue = s.nextLine();

        hasQueue = queue.equals("S");

        if (hasQueue) {
            a.printMessage("Qual é o tempo médio de espera em fila?: ");
            double queueTime = s.nextDouble();
            return new Loja(code, nome, new Coordenadas(lat, lon), hasQueue, queueTime);
        }

        return new Loja(code, nome, new Coordenadas(lat, lon), hasQueue, -1);
    }

    public boolean registar(GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        Login l = new Login();
        String code, nome, pass, tipo;

        a.printMessage("Introduza o nome completo: ");
        nome = s.nextLine();

        do {
            a.printMessage("Introduza o tipo de conta (Voluntario / Transportadora / Utilizador / Loja): ");
            tipo = s.nextLine();
        } while(!(tipo.equals("Voluntario") || tipo.equals("Transportadora") || tipo.equals("Utilizador") || tipo.equals("Loja")));

        if(!c.containsNameAndType(nome, tipo)) {
            l.setNome(nome);
            l.setTipoConta(tipo);

            a.printMessage("Introduza a password: ");
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

            a.printMessageLn("Código de acesso: " + l);

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
            a.printEscolheVolintariTransportadora();
            tipo = s.nextLine();
            switch(tipo) {
                case "1":
                    res = 1;
                    r = false;
                    break;

                case "2":
                    res = 2;
                    r = false;
                    break;

                case "3":
                    res = 3;
                    r = false;
                    break;

                case "B":
                    res = 0;
                    r = false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }

        return res;
    }

    public void menuUtilizador(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuUtilizador();
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
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menuVoluntario(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuVoluntario();
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menuTransportadora(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuTransportadora();
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menuLoja(GestTrazAqui c, Login l) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            a.printMenuLoja();
            line = s.nextLine();

            switch(line) {
                case "1":
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    a.printMessageLn("Comando inválido");
            }
        }
    }

    public void menu(GestTrazAqui c, Login l) {
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

    public void interpretador(GestTrazAqui c) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;
        Login l = null;

        a.welcome();
        s.nextLine();

        while(r) {
            a.printMenu();
            line = s.nextLine();

            switch(line){
                case "1":
                    l = login(c);

                    if(l != null) {
                        r = false;
                        a.printMessageLn("Login efetuado com sucesso");
                    }

                    else
                        a.printMessageLn("Dados inválidos");
                    break;

                case "2":
                    if(registar(c)) {
                        a.printMessageLn("Registo efetuado com sucesso");
                        a.printMessageLn("Efetue Login para continuar");
                    }

                    else
                        a.printMessageLn("Dados inválidos");
                    break;

                case "Q":
                    return;

                default:
                    a.printMessageLn("Comando Inválido");
                    break;
            }
        }

        menu(c, l);

        s.close();
    }
}
