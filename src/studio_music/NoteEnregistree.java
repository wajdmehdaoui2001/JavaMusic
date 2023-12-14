/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studio_music;

/**
 *
 * @author LENOVO
 */
public class NoteEnregistree {
     private Note note;
    private long duree; // Durée de la note en millisecondes

    public NoteEnregistree(Note note, long duree) {
        this.note = note;
        this.duree = duree;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public long getDuree() {
        return duree;
    }

    public void setDuree(long duree) {
        this.duree = duree;
    }
    
}
