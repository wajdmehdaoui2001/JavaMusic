/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
import javax.sound.midi.*;

public class EnregistrementMusical {

    public static void main(String[] args) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();
            
            // Fonction pour jouer une note avec une durée spécifique
            playNote(track, 60, 100, 4); // Note C (60) pour 4 temps
            
            // Ajoutez d'autres notes avec leurs durées ici
            
            sequencer.setSequence(sequence);
            
            // Enregistrement du morceau dans un fichier MIDI
            MidiSystem.write(sequence, 1, new java.io.File("morceau.mid"));
            
            sequencer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playNote(Track track, int note, int velocity, int duration) {
        int noteOn = 144;
        int noteOff = 128;
        
        try {
            ShortMessage noteOnMessage = new ShortMessage();
            noteOnMessage.setMessage(noteOn, 0, note, velocity);
            track.add(new MidiEvent(noteOnMessage, 0));
            
            ShortMessage noteOffMessage = new ShortMessage();
            noteOffMessage.setMessage(noteOff, 0, note, velocity);
            track.add(new MidiEvent(noteOffMessage, duration * 16)); // Multiplication pour définir la durée en temps
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
