import Controler.Interpretador;
import Model.*;

import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GestTrazAqui controlador = new GestTrazAqui();
        Parse parse = new Parse();
        parse.parse(controlador);

        Interpretador i = new Interpretador();
        i.interpretador(controlador);
    }
}
