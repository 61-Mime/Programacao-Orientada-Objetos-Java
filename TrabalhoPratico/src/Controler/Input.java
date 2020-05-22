package Controler;

import Model.Coordenadas;
import Model.GestTrazAqui;
import View.Apresentacao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input implements Serializable {
    Apresentacao a = new Apresentacao();

    public double lerDouble(String message,int min,int max){
        Scanner s = new Scanner(System.in);
        double n = -1;

        do{
            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }

    public LocalDateTime lerData(String message){
        Scanner s = new Scanner(System.in);
        boolean val = true;
        LocalDateTime data = null;

        do{
            a.printMessage(message);
            try {
                data = data.parse(s.nextLine());
                val = false;
            } catch (DateTimeParseException dtpe) {
                a.printMessage("Data inválida");
            }
        } while (val);

        return data;
    }

    public String lerString(String message, GestTrazAqui c) {
        Scanner s = new Scanner(System.in);
        String line;

        do{
            a.printMessage(message);
            line = s.nextLine();
        } while (!c.containsLoja(line));

        return line;
    }

    public Coordenadas lerCoordenada(){
        Scanner s = new Scanner(System.in);
        String[] line;
        double lat,lon = 0;

        do{
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

        return new Coordenadas(lat,lon);
    }
}
