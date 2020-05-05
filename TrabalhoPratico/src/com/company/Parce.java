package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parce {
    public void parce(Controlador c){
        List<String> linhas = lerFicheiro("xxx");
        String [] linhaPartida;
        for(String linha: linhas) {
            linhaPartida = linha.split(":",2);
            switch (linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parceUtilizador(linhaPartida[1]);
                    c.addUser(u);
                    break;
                case "Loja":
                    Loja l = parceLoja(linhaPartida[1]);
                    c.addLoja(l);
                    break;
                case "Voluntario":
                    Voluntario v = parceVoluntario(linhaPartida[1]);
                    c.addVoluntario(v);
                    break;
                case "Transportadora":
                    Transportadora t = parceTransportadora(linhaPartida[1]);
                    c.addVoluntario(t);
                    break;
                case "Encomenda":
                    Encomenda e = parceEncomenda(linhaPartida[1]);
                    c.addEncomenda(e);
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

    public Utilizador parceUtilizador(String input){
        String [] campos = input.split(",");
        String nome = campos[0];
        String codUtilizador = campos[1];
        double cr = Double.parseDouble(campos[0]);
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        List<Encomenda> entregas = new ArrayList<>();

        return new Utilizador(codUtilizador,nome,gps,entregas);
    }

    public Loja parceLoja(String input){
        String [] campos = input.split(",");
        String storeCode = campos[0];
        String storeName = campos[1];
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));

        return new Loja(storeCode,storeName,gps,false,0);
    }

    public Voluntario parceVoluntario(String input){
        String [] campos = input.split(",");
        String voluntaryCode = campos[0];
        String name = campos[1];
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        double raio = Double.parseDouble(campos[4]);
        List<Encomenda> registerV = new ArrayList<>();

        return new Voluntario(voluntaryCode,name,gps,raio,0,true,false,0,registerV);
    }

    public Transportadora parceTransportadora(String input){
        String [] campos = input.split(",");
        String companyCode = campos[0];
        String companyName = campos[1];
        Coordenadas gps = new Coordenadas(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        int nif = Integer.parseInt(campos[4]);
        double raio = Double.parseDouble(campos[5]);
        double precoPorKm = Double.parseDouble(campos[6]);
        List<Encomenda> registerV = new ArrayList<>();

        return new Transportadora(companyCode,companyName,gps,raio,0,true,false,0,registerV,nif,precoPorKm,0,0);
    }

    public Encomenda parceEncomenda(String input){
        String [] campos = input.split(",");
        String encCode = campos[0];
        String userCode = campos[1];
        String storeCode = campos[2];
        double weight = Double.parseDouble(campos[3]);
        List<LinhaEncomenda> linha = new ArrayList<>();
        int size = campos.length;
        for(int i = 4;i < size - 1;i+=4)
            linha.add(parceLinhaEncomenda(campos[i],campos[i+1],campos[i+2],campos[i+3]));

        return new Encomenda(encCode,userCode,"",storeCode,weight,false, LocalDateTime.now(),false,linha);
    }
    public LinhaEncomenda parceLinhaEncomenda(String code,String description,String qt,String price){
        return new LinhaEncomenda(code,description,Double.parseDouble(qt),Double.parseDouble(price));
    }

}
