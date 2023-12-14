/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
import java.util.HashMap;
import java.util.Map;

public class InstrumentsMIDI {
    private static final Map<String, Integer> instruments = new HashMap<>();

    static {
        instruments.put("Piano", 0);
        instruments.put("Chromatic Percussion", 8);
        instruments.put("Organ", 16);
        instruments.put("Guitar", 24);
        instruments.put("Electric Guitar ", 27);
        instruments.put("Bass", 32);
        instruments.put("Strings", 40);
        instruments.put("Ensemble", 48);
        instruments.put("Brass", 56);
        instruments.put("Reed", 64);
        instruments.put("Synth Lead", 72);
        instruments.put("Synth Pad", 80);
        instruments.put("Synth Effects", 88);
        instruments.put("Ethnic", 96);
        instruments.put("Percussion", 104);
        instruments.put("Sound Effects", 112);
        instruments.put("Drums", 128);
    }

    public  Integer getInstrumentNumber(String instrumentName) {
        return instruments.getOrDefault(instrumentName, -1);
    }
}

