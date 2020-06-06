package Controler;

import Model.Coordenadas;
import Model.GestTrazAqui;
import View.Apresentacao;
import View.Audio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input implements Serializable {

    public boolean lerSN(Apresentacao a, String message){
        Scanner s = new Scanner(System.in);
        String line;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");
            a.printMessage(message);
            line = s.nextLine();
        } while (!line.toUpperCase().equals("S") && !line.toUpperCase().equals("N"));

        a.play("sound/ok.wav");

        return line.toUpperCase().equals("S");
    }

    public double lerDouble(Apresentacao a, String message,int min,int max){
        Scanner s = new Scanner(System.in);
        double n = -1;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");

            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        a.play("sound/ok.wav");

        return n;
    }

    public LocalDateTime lerData(Apresentacao a, String message){
        Scanner s = new Scanner(System.in);
        boolean val = true;
        LocalDateTime data = null;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");

            a.printMessage(message);
            try {
                data = data.parse(s.nextLine());
                val = false;
            } catch (DateTimeParseException dtpe) {
                a.printMessage("Data inválida");
            }
        } while (val);

        a.play("sound/ok.wav");

        return data;
    }

    public String lerString(Apresentacao a, String message, GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        String line;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");

            a.printMessage(message);
            line = s.nextLine();
        } while (!c.containsLoja(line));

        a.play("sound/ok.wav");

        return line;
    }

    public Coordenadas lerCoordenada(Apresentacao a){
        Scanner s = new Scanner(System.in);
        String[] line;
        double lat,lon = 0;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");

            a.printMessage("Introduza a latitude ([-90,90]) e a longitude ([-180,180]), por exemplo: 30 20");
            line = s.nextLine().split(" ",2);
            try{
                lat = Double.parseDouble(line[0]);
                if(line.length == 2)
                    lon = Double.parseDouble(line[1]);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                lat = 100;
            }
        } while (line.length != 2 || lat < -90 || lat > 90 || lon < -180 || lon > 180);

        a.play("sound/ok.wav");

        return new Coordenadas(lat,lon);
    }

    public String lerStringEncomenda(Apresentacao a, String message, GestTrazAqui c, String code) {
        Scanner s = new Scanner(System.in);
        String line;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");

            a.printMessage(message);
            line = s.nextLine();
        } while (!c.containsEncomendaEstafeta(line, code));

        a.play("sound/ok.wav");

        return line;
    }

    private boolean linhaEncomendaValida(String[] line, String storeCode, GestTrazAqui c) {
        String[] tmp;

        for (String s: line) {
            tmp = s.split(" ");

            if (!c.containsProdutoLoja(storeCode, tmp[0]))
                return false;
        }
        return true;
    }

    public String[] lerLinhaEncomenda(Apresentacao a, String message, GestTrazAqui c, String storeCode){
        Scanner s = new Scanner(System.in);
        String line;
        String[] linhaPartida;
        int i = 0;

        do{
            if(i++ != 0)
                a.play("sound/error.wav");

            a.printMessage(message);
            line = s.nextLine();
            linhaPartida = line.split(" \\| ");
        } while (!linhaEncomendaValida(linhaPartida, storeCode, c));

        a.play("sound/ok.wav");

        return linhaPartida;
    }
}
