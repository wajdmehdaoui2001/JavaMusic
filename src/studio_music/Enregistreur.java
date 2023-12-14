/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
import Exception.InstrumentException;
import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enregistreur {
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
   
    
    private Synthetiseur synth;
    private int tickPosition;
    private Map<Integer, Long> lastNoteTimes = new HashMap<>();
    private long minNoteInterval = 200;
    private Map<String, Track> tracksByInstrument;
   
    public Enregistreur() {
        try {
            
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 120);
             track = sequence.createTrack();
             
            
             
            sequencer.setSequence(sequence);
           
            sequencer.setTickPosition(0);
            setDefaultTempo(120);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
         tracksByInstrument = new HashMap<>();
    }
    
private void setDefaultTempo(int bpm) {
        if (sequencer != null) {
            try {
                MetaMessage tempoMessage = new MetaMessage();
                int msPerQuarterNote = Math.round(60000000 / bpm); // Calcul du temps en millisecondes par noire

                // Définir le message tempo pour le fichier MIDI
                tempoMessage.setMessage(0x51, new byte[] {
                        (byte) ((msPerQuarterNote >>> 16) & 0xFF),
                        (byte) ((msPerQuarterNote >>> 8) & 0xFF),
                        (byte) (msPerQuarterNote & 0xFF)
                }, 3);

                
                track.add(new MidiEvent(tempoMessage, 0));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }
    }
public void addInstrumentToTrack(String instrumentName, int instrumentNumber, Track track) {
    
    ShortMessage programChange = new ShortMessage();
    try {
        programChange.setMessage(ShortMessage.PROGRAM_CHANGE, 0, instrumentNumber, 0);
    } catch (InvalidMidiDataException e) {
        e.printStackTrace();
    }

    
    track.add(new MidiEvent(programChange, 0));
}
    public void démarrerEnregistrement(String instrumentName) throws InstrumentException {
         if (instrumentName == null) {
            throw new InstrumentException("Veuillez choisir un instrument avant de démarrer l'enregistrement.");
        }
        sequencer.recordEnable(track, 0);
        
         tracksByInstrument.put(instrumentName, track);
        sequencer.startRecording();
        tickPosition = 0;
    }

    public void arrêterEnregistrement() {
        sequencer.stopRecording();
        sequencer.stop();
    }

    public void sauvegarderEnregistrement(String fichier) {
        try {
            MidiSystem.write(sequence, 1, new java.io.File(fichier));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sequencer getSequencer() {
        return sequencer;
    }
    

    public Sequence getSequence() {
        return sequence;
    }
   

    public Track getTrack() {
        return track;
    }
   
    

public void recordNote(String instrumentName,int note, int velocity, long durationInMilliseconds) {
    
    Track track = tracksByInstrument.get(instrumentName);
     if (track != null) {
    try {
        
        long tickDuration = millisecondsToTicks(durationInMilliseconds);

        
        

        ShortMessage noteOnMessage = new ShortMessage();
        noteOnMessage.setMessage(ShortMessage.NOTE_ON, 0, note, velocity);

        ShortMessage noteOffMessage = new ShortMessage();
        noteOffMessage.setMessage(ShortMessage.NOTE_OFF, 0, note, 0);

        track.add(new MidiEvent(noteOnMessage, tickPosition));
        track.add(new MidiEvent(noteOffMessage, tickPosition + tickDuration));

        tickPosition += tickDuration;
    
    } catch (InvalidMidiDataException e) {
        e.printStackTrace();
    }
     }
}

    private long millisecondsToTicks(long milliseconds) {
        double ticksPerMillisecond = sequence.getResolution() / 1000.0;
        return (long) (milliseconds * ticksPerMillisecond);
    }
  
     
}
