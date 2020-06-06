package View;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class Audio implements Serializable{

    public void play(String filePath){
        try {
            File musicPath = new File(filePath);
            Scanner s = new Scanner(System.in);

            if(musicPath.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            }
            else
                System.out.println("Não existe");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
