/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import javax.sound.midi.*;

public class Tracks {
    private ArrayList<Track> trackList;
    private Sequence sequence;
    private Track[] tracks;
    private int trackCount;

    public Tracks(int numTracks) throws InvalidMidiDataException {
        this.trackList=new ArrayList<>();
        this.sequence = new Sequence(Sequence.PPQ, 24, numTracks);
        this.tracks = sequence.getTracks();
        this.trackCount = 0;
    }

    public void addTrack() {
        if (trackCount < tracks.length) {
            tracks[trackCount++] = sequence.createTrack();
        } else {
            System.out.println("Nombre maximum de pistes atteint.");
        }
    }
    public void deleteTrack(int index) {
        if (index >= 0 && index < trackCount) {
            for (int i = index; i < trackCount - 1; i++) {
                tracks[i] = tracks[i + 1];
            }
            trackCount--;
        } else {
            System.out.println("Index de piste invalide.");
        }
    }

    public Sequence getSequence() {
        return sequence;
    }

   public int getCount() {
        return trackList.size();
    }
   public Track getTrack(){
   return trackList.get(trackCount);}
   public void addTrack1(Track track){
       trackList.add(track);
}
     public void addTrack2(Track track, int index) {
        if (index >= 0 && index <= trackList.size()) {
            trackList.add(index, track);
        } else {
            throw new IllegalArgumentException("Invalid track index");
        }
    }
      public Track getTrack2(int index) {
        if (index >= 0 && index < trackList.size()) {
            return trackList.get(index);
        } else {
            throw new IllegalArgumentException("Invalid track index");
        }
    }
}


