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
public class MusicPlayer {

     private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> playTones("do", "mi", "sol", "si", "do-octave"));
        Thread thread2 = new Thread(() -> playTones("re", "fa", "la", "do-octave"));

        thread1.start();
        thread2.start();
    }

    private static void playTones(String... notes) {
        synchronized (lock) {
        for (String note : notes) {
            playSound(note, 500);
            try {
                // Release the lock during sleep
                lock.wait(500); // Sleep duration is 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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




