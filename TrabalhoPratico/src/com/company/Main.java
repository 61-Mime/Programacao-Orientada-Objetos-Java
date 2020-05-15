package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Encomenda> encList = new ArrayList<>();
        Coordenadas c = new Coordenadas(-81.554855,84.01756);
        Utilizador user = new Utilizador( "u38" ,"Ricardo Manuel Almeida Vieira de Castro", c, encList);

        LinhaEncomenda l = new LinhaEncomenda("p70","Queijo Mussarela",6.373054,38.18086);
        LinhaEncomenda l1 = new LinhaEncomenda("p53","Batata",1.4474236,10.431012);
        LinhaEncomenda l2 = new LinhaEncomenda("p79","Carne seca",9.521179,45.27606);
        List<LinhaEncomenda> encLine = new ArrayList<>();
        encLine.add(l);
        encLine.add(l1);

        Encomenda enc = new Encomenda("u38", "e8727", "l49","18", 41.09162, false, LocalDateTime.now(),true, encLine);
        enc.addLinhaEncomenda(l2);

        user.addEncomenda(enc);
        //System.out.println(user);

        Controlador controlador = new Controlador();
        Parse parse = new Parse();
        parse.parse(controlador);

        System.out.println(controlador);

    }
}
