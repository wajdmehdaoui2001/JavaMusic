/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package authentificaton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginController  {

    @FXML 
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
  private Musicien musicien = new Musicien("wajd mehdaoui", "12345678", 1, "Wajd", "wajd@gmail.com");
    
     @FXML
    public void VerifLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if(username.isEmpty() || password.isBlank()){
            
            showAlert("veuiller remplir tous les champs");
             }
        else if(password.length()<8){
         showAlert("le mot de passe doit contenir au moins 8 caractères!");}
        else{
        
        boolean isAuthenticated = musicien.authentifier(username, password);
        
        if (isAuthenticated) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/studio_music/MonStudio.fxml"));
                Parent root = loader.load();
                
                // Créer une nouvelle scène
                Scene scene = new Scene(root);
                
                // Obtenir la fenêtre actuelle
                Stage stage = (Stage) usernameField.getScene().getWindow();
                
                // Mettre la nouvelle scène dans la fenêtre
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer toute exception qui pourrait survenir lors du chargement du fichier MonStudio.fxml
            }
           
        } else {
            // Authentication failed, show error message (e.g., display an alert)
           showAlert("adresse ou mot de passe incorrect");
        }
        }
    }
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
   
}
}
