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

    public void login() {

    }

    public void registar() {

    }

    public void interpretador() {
        boolean r=true;
        Scanner s = new Scanner(System.in);
        String line;

        while(r) {
            System.out.println("Login ou Registar");
            line = s.nextLine();

            if(line.equals("Login") || line.equals("login")) {
                login();
            }

            else if(line.equals("Registar") || line.equals("registar")) {
                registar();
            }

            else {
                System.out.println("Comando Inválido");
            }
        }
    }
}
