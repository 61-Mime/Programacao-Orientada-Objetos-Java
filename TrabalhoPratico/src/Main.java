import Controler.Interpretador;
import Files.Parse;
import Model.*;
import View.Apresentacao;

import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GestTrazAqui controlador = new GestTrazAqui();
        Interpretador i = new Interpretador();
        Apresentacao a = new Apresentacao();
        Parse parse = new Parse();
        Login l = null;
        parse.parse(controlador);

        i.interpretador(controlador, a, l);
    }
}
