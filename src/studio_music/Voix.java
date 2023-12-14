/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
import javax.sound.sampled.*;
import java.io.*;

public class Voix {

    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private AudioInputStream audioInputStream;
    private File wavFile;
    private boolean recording;
    private Clip clip;

    public Voix() {
        audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("La ligne de capture audio n'est pas prise en charge.");
            return;
        }

        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void demarrerEnregistrementVoix() {
        if (targetDataLine != null) {
           wavFile = new File(System.getProperty("user.home") + "/Desktop/enregistrement.wav");
            Thread recordingThread = new Thread(() -> {
                try {
                    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
                    targetDataLine.start();

                    recording = true;

                    audioInputStream = new AudioInputStream(targetDataLine);

                    AudioSystem.write(audioInputStream, fileType, wavFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            recordingThread.start();
        }
    }

    public void arreterEnregistrementVoix() {
        if (targetDataLine != null && recording) {
            targetDataLine.stop();
            targetDataLine.close();
            recording = false;
        }
    }

    public boolean estEnTrainEnregistrer() {
        return recording;
    }
     public void playAudio(File audioFile) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start(); 

            
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close(); 
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
