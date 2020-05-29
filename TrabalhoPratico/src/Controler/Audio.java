package Controler;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class Audio {

    public void play(String filePath){
        try {
            File musicPath = new File(filePath);
            Scanner s = new Scanner(System.in);

            if(musicPath.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                s.nextLine();
            }
            else
                System.out.println("Não existe");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
