/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sounds;
import javax.sound.sampled.*;
import java.io.File;


/**
 *
 * @author gauravvraii
 */
public class TwinkleTwinkle {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> playTwinkleTwinkle());
        Thread thread2 = new Thread(() -> playTwinkleTwinkle());

        thread1.start();
        thread2.start();
    }

    private static void playTwinkleTwinkle() {
        String[] twinkleTone = {"do", "do", "sol", "sol", "la", "la", "sol", "fa", "fa", "mi", "mi", "re", "re", "do",
                "sol", "sol", "fa", "fa", "mi", "mi", "re", "sol", "sol", "fa", "fa", "mi", "mi", "re", "do", "do", "sol",
                "sol", "la", "la", "sol", "fa", "fa", "mi", "mi", "re", "re", "do"};

        synchronized (lock) {
            for (String note : twinkleTone) {
                playSound(note, 500);
            }
        }
    }

    private static void playSound(String note, int duration) {
        try {
            String filePath = "/Users/gauravvraii/Downloads/Multithreading  Resources/Sounds/" + note + ".wav";
            File audioFile = new File(filePath);

            if (audioFile.exists()) {
                System.out.println("Playing sound: " + note);
                Clip clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(duration);
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

