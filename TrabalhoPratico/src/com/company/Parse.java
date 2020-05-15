package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parse {
    public void parse(Controlador c){
        List<String> linhas = lerFicheiro("logs.txt");
        String [] linhaPartida;
        for(String linha: linhas) {
            linhaPartida = linha.split(":",2);
            switch (linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]);
                    c.addUser(u);
                    break;
                case "Loja":
                    Loja l = parseLoja(linhaPartida[1]);
                    c.addLoja(l);
                    break;
                case "Voluntario":
                    Estafeta v = parseVoluntario(linhaPartida[1]);
                    c.addEstafeta(v);
                    break;
                case "Transportadora":
                    Estafeta t = parseTransportadora(linhaPartida[1]);
                    c.addEstafeta(t);
                    break;
                case "Encomenda":
                    Encomenda e = parseEncomenda(linhaPartida[1]);
                    c.addEncomenda(e);
                    break;
                case "Aceite":
                    c.aceitarEncomenda(linhaPartida[1]);
                    break;
            }
        }
    }

    public List<String> lerFicheiro(String filename){
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage() + "Erro a ler ficheiro");
        }
        return lines;
    }

    public Utilizador parseUtilizador(String input){
        String [] campos = input.split(",");
        String nome = campos[1];
        String codUtilizador = campos[0];
//        double cr = Double.parseDouble(campos[0]);
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        List<Encomenda> entregas = new ArrayList<>();

        return new Utilizador(codUtilizador,nome,gps,entregas);
    }

    public Loja parseLoja(String input){
        String [] campos = input.split(",");
        String storeCode = campos[0];
        String storeName = campos[1];
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));

        return new Loja(storeCode,storeName,gps,false,0);
    }

    public Estafeta parseVoluntario(String input){
        String [] campos = input.split(",");
        String voluntaryCode = campos[0];
        String name = campos[1];
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        double raio = Double.parseDouble(campos[4]);

        return new Estafeta(voluntaryCode,name,gps,raio,50,false,"Voluntário");
    }

    public Estafeta parseTransportadora(String input){
        String [] campos = input.split(",");
        String companyCode = campos[0];
        String companyName = campos[1];
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        int nif = Integer.parseInt(campos[4]);
        double raio = Double.parseDouble(campos[5]);
        double precoPorKm = Double.parseDouble(campos[6]);

        return new Transportadora(companyCode,companyName,gps,raio,60,false,nif,precoPorKm,0.05);
    }

    public Encomenda parseEncomenda(String input){
        String [] campos = input.split(",");
        String encCode = campos[0];
        String userCode = campos[1];
        String storeCode = campos[2];
        double weight = Double.parseDouble(campos[3]);
        List<LinhaEncomenda> linha = new ArrayList<>();
        int size = campos.length;
        for(int i = 4;i < size - 1;i+=4)
            linha.add(parseLinhaEncomenda(campos[i],campos[i+1],campos[i+2],campos[i+3]));

        return new Encomenda(encCode,userCode,"",storeCode,weight,false, LocalDateTime.now(),false,linha);
    }
    public LinhaEncomenda parseLinhaEncomenda(String code,String description,String qt,String price){
        return new LinhaEncomenda(code,description,Double.parseDouble(qt),Double.parseDouble(price));
    }

}
