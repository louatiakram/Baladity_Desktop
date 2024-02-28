package edu.esprit.controllers;

import edu.esprit.entities.CommentaireTache;
import edu.esprit.entities.EndUser;
import edu.esprit.entities.Tache;
import edu.esprit.services.ServiceCommentaireTache;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Date;

public class AjouterCommentaireTacheController {

    @FXML
    private TextArea commentField;

    private ServiceCommentaireTache serviceCommentaireTache;
    private Tache tache;
    private EndUser user;

    public void setUserAndTaskIds(Tache tache, EndUser user) {
        this.tache = tache;
        this.user = user;
    }

    public void setServiceCommentaireTache(ServiceCommentaireTache serviceCommentaireTache) {
        this.serviceCommentaireTache = serviceCommentaireTache;
    }

    @FXML
    void ajouterCommentaire(javafx.event.ActionEvent event) {
        String commentaireText = commentField.getText();

        // Check if a comment already exists for the task
        CommentaireTache existingComment = serviceCommentaireTache.getCommentaireForTask(tache.getId_T());
        if (existingComment != null) {
            // Show an error alert if a comment already exists
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Un commentaire existe déjà pour cette tâche.");
            errorAlert.showAndWait();
            return; // Exit the method
        }

        // Creating the comment object
        CommentaireTache commentaireTache = new CommentaireTache();
        commentaireTache.setText_C(commentaireText);
        commentaireTache.setId_T(tache.getId_T()); // Set the task ID
        commentaireTache.setUser(user); // Set the user

        commentaireTache.setDate_C(new Date()); // Set the current date

        // Call the service to add the comment
        serviceCommentaireTache.ajouter(commentaireTache);

        // Show alert if added successfully
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Ajout réussi");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Le commentaire a été ajouté avec succès.");
        successAlert.showAndWait();

        // Close the window
        Stage stage = (Stage) commentField.getScene().getWindow();
        stage.close();
    }

}