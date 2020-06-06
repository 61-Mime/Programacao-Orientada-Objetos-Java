import Controler.Interpretador;
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
        parse.parse(controlador);

        i.interpretador(controlador, a);
    }
}
