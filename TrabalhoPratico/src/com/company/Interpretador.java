package com.company;

import java.util.Scanner;

public class Interpretador {

    public void menu() {
        System.out.println("Login");
        System.out.println("Registar");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public boolean login(Controlador c) {
        Scanner s = new Scanner(System.in);
        String user, pass;

        System.out.println("Introduza o usercode");
        user = s.nextLine();

        if(c.containsUser(user)) {
            System.out.println("Introduza a password");
            pass = s.nextLine();

            return c.containsPassword(user, pass);
        }

        return false;
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

            l.setPassword(pass);

            do { code = l.generateCode(tipo); } while(c.containsUser(code));

            l.setCode(code);

            System.out.println("Código de acesso: " + l);

            c.addLogin(l);

            return true;
        }

        return false;
    }

    public void interpretador(Controlador c) {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            System.out.println("Login ou Registar");
            line = s.nextLine();

            if(line.equals("Login") || line.equals("login")) {
                if(login(c)) {
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
    }
}
