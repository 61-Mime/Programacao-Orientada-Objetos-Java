package Model;


import java.io.*;

public class GuardarCarregarEstado {

    public int guardaDados(String fileName, GestTrazAqui c) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(c);
            oos.flush();
            oos.close();
        }
        catch (FileNotFoundException e) {
            return 1;
        }
        catch (IOException e) {
            return 2;
        }

        return 0;
    }

    public GestTrazAqui carregaDados(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(file);
        GestTrazAqui c = (GestTrazAqui) ois.readObject();
        ois.close();
        return c;
    }
}
