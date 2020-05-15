package com.company;

import java.util.Scanner;

public class Interpretador {

    public Login login(Controlador c) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        System.out.println("Introduza o usercode");
        user = s.nextLine();

        if(c.containsUser(user)) {
            System.out.println("Introduza a password");
            pass = s.nextLine();

            s.close();

            if(c.containsPassword(user, pass))
                return c.getLogin(user);
        }

        s.close();

        return null;
    }

    public boolean registar(Controlador c) {
        Scanner s = new Scanner(System.in);
        Login l = new Login();
        String code, nome, pass, tipo;

        System.out.println("Introduza o nome completo");
        nome = s.nextLine();

        System.out.println("Introduza o tipo de conta");
        tipo = s.nextLine();

        if(!c.containsNameAndType(nome, tipo)) {
            l.setNome(nome);
            l.setTipoConta(tipo);

            System.out.println("Introduza a password");
            pass = s.nextLine();

            s.close();

            l.setPassword(pass);

            do { code = l.generateCode(tipo); } while(c.containsUser(code));

            l.setCode(code);

            System.out.println("Código de acesso: " + l);

            c.addLogin(l);

            return true;
        }

        s.close();

        return false;
    }

    public void menuUtilizador(Login l) {
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
                    break;

                case "Q":
                    r=false;
                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }
    }

    public void menuVoluntario(Login l) {
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

    public void menuTransportadora(Login l) {
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

    public void menuLoja(Login l) {
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

    public void menu(Login l) {
        switch (l.getTipoConta()) {
            case "Utilizador":
                menuUtilizador(l);
                break;
            case "Voluntario":
                menuVoluntario(l);
                break;
            case "Transportadora":
                menuTransportadora(l);
                break;
            case "Loja":
                menuLoja(l);
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

        s.close();

        menu(l);
    }
}
