import Controler.Interpretador;
import Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GestTrazAqui controlador = new GestTrazAqui();
        Parse parse = new Parse();
        parse.parse(controlador);

        Interpretador i = new Interpretador();
        i.interpretador(controlador);
    }
}
