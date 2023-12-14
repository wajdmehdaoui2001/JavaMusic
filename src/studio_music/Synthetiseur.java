/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.scene.input.KeyCode;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.*;

/**
 *
 * @author LENOVO
 */
public class Synthetiseur {
   
    private Synthesizer synth;
    private MidiChannel[] channels;

    public Synthetiseur() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public MidiChannel[] getChannels() {
        return channels;
    }
    public void Open() throws MidiUnavailableException{
    synth.open();}
    
    
    

    
    
    
}
