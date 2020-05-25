package Effects;

import Var.Var;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.io.File;

public class Sounds {
        //Funktion um Audio abspielen zu lassen
    public static void playSound(String fileName, double startLoop, double endLoop, double volume) {
        int startLoopFrame = getSampleFrame(fileName, (int)startLoop*1000000);
        int endLoopFrame = getSampleFrame(fileName, (int)endLoop*1000000);
        System.out.println(startLoopFrame + "||" + endLoopFrame);
        try {
            File audioFile = new File("rsc/Sounds/" + fileName);
            if(audioFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.setLoopPoints(startLoopFrame, endLoopFrame);
                Thread handler = new Thread(new SoundHandler(clip));
                handler.start();
                //System.out.println("started");
            }else{
                System.out.println("Datei existiert nicht!");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static void playSound(String fileName, double volume) {
        try {
            File audioFile = new File("rsc/Sounds/" + fileName); // Erstelle File Objekt
            if(audioFile.exists()) {
                //Erstelle und öffne Audio-Clip Objekt mithilfe eines audioInputStreams
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);

                // Lautstärke einstellen
                FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                double gain = volume;
                float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                Thread handler = new Thread(new SoundHandler(clip));
                handler.start();
            }else{
                System.out.println("Datei existiert nicht!");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static int getSampleFrame(String fileName, int microsec) {
        int framePos = 0;
        try {
            File audioFile = new File("rsc/Sounds/" + fileName);
            if(audioFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.setMicrosecondPosition(microsec);
                framePos = clip.getFramePosition();
            }else{
                System.out.println("Datei existiert nicht!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return framePos;
    }
    public static void stopMusic() {

    }
}
