/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package studio_music;


import Exception.InstrumentException;
import java.io.File;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.sound.midi.*;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
 
    
     
public class MonStudioController implements Initializable {
   
    @FXML
    private Rectangle rec1,rec2,rec3,rec4,rec5,rec6,rec7,rec8,rec9,rec10,rec11,rec12,rec13,rec14,rec15,rec16,rec17,rec18,rec19;
    
    @FXML
    private ProgressBar progressionBar;
    @FXML
    private ProgressBar progressionBar1;
    @FXML
  private VBox tracksContainer;
    
    private MidiChannel channel;
    MidiChannel[] channels;
   
    @FXML
    private ChoiceBox<String> instrumentChoiceBox;
    private List<Note> notes = Arrays.asList(
        new Note("C", KeyCode.Q, 60),
        new Note("C#",KeyCode.Z,61),    
        new Note("D", KeyCode.S, 62 ),
        new Note("D#", KeyCode.E, 63 ),
        new Note("E", KeyCode.D, 64),
        new Note("F", KeyCode.F, 65),
        new Note("F#", KeyCode.T, 66 ),
        new Note("G", KeyCode.G, 67),
        new Note("G#", KeyCode.Y, 68),
        new Note("A", KeyCode.H, 69),
        new Note("A#", KeyCode.U, 70),
        new Note("B", KeyCode.J, 71),
        new Note("C", KeyCode.K, 72),
        new Note("C#", KeyCode.O, 73),
        new Note("D", KeyCode.L, 74),
        new Note("D#", KeyCode.P, 75),
        new Note("E", KeyCode.M, 76),
        new Note("F", KeyCode.W, 77)
    );
    
    private InstrumentsMIDI instru;
    private String nomInstru;
    private ArrayList<Rectangle> listRectangles= new ArrayList<>();
    private Synthetiseur synth;
    private Enregistreur enregistreur; 
     private boolean estEnTrainEnregistrer = false;
     private Voix enregistreurVoix;
     private Sequencer sequenceur;
     
     ArrayList<Note> notesJouees = new ArrayList<>();
    private double recordedProgress = 0.0;
    private Track[] tracks; 
    private int currentTrackIndex ; 
    @FXML
    private Rectangle rec20;
    @FXML
    private Rectangle rec22;
    @FXML
    private Rectangle rec25;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         
        enregistreurVoix=new Voix();
        synth = new Synthetiseur();
        channels = synth.getChannels();
        if (channels != null && channels.length > 0) {
            channel = channels[0];
        }
        enregistreur = new Enregistreur();
        sequenceur=enregistreur.getSequencer();
        instrumentChoiceBox.getItems().addAll("Piano", "Guitar","Electric Guitar ", "Percussion","Chromatic Percussion","Organ","Bass","Strings","Ensemble","Sound Effects","Drums","Brass","Synth Lead","Synth Pad","Synth Effects","Ethnic");
        instru=new InstrumentsMIDI();
        listRectangles.add(rec1);
        listRectangles.add(rec2);
        listRectangles.add(rec3);
        listRectangles.add(rec4);
        listRectangles.add(rec5);
        listRectangles.add(rec6);
        listRectangles.add(rec7);
        listRectangles.add(rec8);
        listRectangles.add(rec9);
        listRectangles.add(rec10);
        listRectangles.add(rec11);
        listRectangles.add(rec12);
        listRectangles.add(rec13);
        listRectangles.add(rec14);
        listRectangles.add(rec15);
        listRectangles.add(rec16);
        listRectangles.add(rec17);
        listRectangles.add(rec18);
         
   
        
       
    }
@FXML
public void PlayNote(KeyEvent ev) throws MidiUnavailableException, InterruptedException, InvalidMidiDataException, ExecutionException {
    nomInstru = instrumentChoiceBox.getValue();
    
    synth.Open();
    channel.programChange(instru.getInstrumentNumber(nomInstru));
  
    KeyCode keyCode = ev.getCode();
     
    
   notes.stream()
    .filter(note -> note.getKey() == keyCode)
    .forEach(note -> {
        
        int index = notes.indexOf(note);
        FillTransition ft = new FillTransition(
                Duration.seconds(0.5),
                listRectangles.get(index),
                (Color) listRectangles.get(index).getFill(),
                Color.GRAY
        );
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
        channel.noteOn(note.getNumber(), 100);
        channel.noteOff(note.getNumber());
        notesJouees.add(note);
        
        System.out.println(note);
         
        long noteDuration = 500;
        System.out.println("Note duration: " + noteDuration + "ms");
        if (estEnTrainEnregistrer) {
            int noteNumber = note.getNumber();
            enregistreur.recordNote(nomInstru, noteNumber, 100, noteDuration);
            System.out.println("Recording Note");
        }
        
    });
   
}



 @FXML
   public void toggleEnregistrement(MouseEvent event) throws InvalidMidiDataException, InstrumentException {
    if (event.getButton() == MouseButton.PRIMARY) { 
        enregistreur.addInstrumentToTrack(instrumentChoiceBox.getValue(), instru.getInstrumentNumber(instrumentChoiceBox.getValue()), enregistreur.getTrack());
        enregistreur.démarrerEnregistrement(instrumentChoiceBox.getValue());
        
        estEnTrainEnregistrer = true;
        simulateRecordingProgress(progressionBar);
    }
}
    
    
    
    @FXML
   public void toggleArretEnregistrement(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) { 
        if (estEnTrainEnregistrer) {
            enregistreur.arrêterEnregistrement();
            estEnTrainEnregistrer = false;
            recordedProgress = progressionBar.getProgress();
        }
    }
}
     private void simulateRecordingProgress(ProgressBar p) {
        Thread recordingThread = new Thread(() -> {
            while (estEnTrainEnregistrer) {
                
                for (double progress = 0; progress <= 1; progress += 0.01) {
                    if (!estEnTrainEnregistrer) {
                        break; 
                    }
                    
                    double finalProgress = progress;
                    Platform.runLater(() -> p.setProgress(finalProgress));

                    try {
                        Thread.sleep(50); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        recordingThread.setDaemon(true);
        recordingThread.start();
    }
  @FXML
    public void sauvegarderEnregistrement(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder fichier MIDI");
        
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers MIDI (*.mid)", "*.mid");
        fileChooser.getExtensionFilters().add(extFilter);
        
       
        Stage stage = (Stage) instrumentChoiceBox.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            
            enregistreur.getSequence();
            
           
            try {
                enregistreur.sauvegarderEnregistrement(file.getAbsolutePath());
                System.out.println("Fichier MIDI enregistré avec succès à : " + file.getAbsolutePath());
                 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void handleRecordingProgress(double progress) {
        progressionBar.setProgress(progress);
    }
    
     
      private double calculateProgressForTrack(int index) {
        double progress = Math.random(); 
        return progress;
    }
      @FXML
  public void handleAddTrackButtonClick(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) { 
        ProgressBar progressBar = new ProgressBar();
        double height = progressionBar.getHeight();
        progressBar.setPrefHeight(height);
        progressBar.prefWidthProperty().bind(tracksContainer.widthProperty());
        progressBar.setProgress(0.0);
        
        tracksContainer.getChildren().add(progressBar);
    }
}
@FXML
 public void toggleEnregistrementVoix(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) { 
        enregistreurVoix.demarrerEnregistrementVoix();
        estEnTrainEnregistrer = true;
        simulateRecordingProgress(progressionBar1);
    }
}

@FXML
public void arreterEnregistrementVoix(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) { 
        enregistreurVoix.arreterEnregistrementVoix();
        estEnTrainEnregistrer = false;
        recordedProgress = progressionBar1.getProgress();
    }
}

@FXML
public void handlePlayButtonClick(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) { 
        File audioFile = new File(System.getProperty("user.home") + "/Desktop/enregistrement.wav");

        if (enregistreurVoix != null) {
            enregistreurVoix.playAudio(audioFile);
            simulateRecordingProgress(progressionBar1);
        } else {
            System.out.println("Voix non initialisée.");
        }
    }
}
    

   
}











   






           
        
                

   


