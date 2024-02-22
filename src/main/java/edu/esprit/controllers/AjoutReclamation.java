package edu.esprit.controllers;

import edu.esprit.entities.EndUser;
import edu.esprit.entities.Muni;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceReclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class AjoutReclamation implements Initializable {
    @FXML
    private TextArea TAdescription_reclamation;

    @FXML
    private ComboBox<String> typeReclamationComboBox;

    @FXML
    private String value1;

    @FXML
    private TextField TFsujet_reclamation;

    @FXML
    private TextField TFadresse_reclamation;

    @FXML
    private ImageView imgView_reclamation;

    @FXML
    private Button uploadbutton;
    @FXML
    private Label label;

    private final ServiceReclamation sr = new ServiceReclamation();
    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
    Muni muni = new Muni(5);
    EndUser user = new EndUser(13,muni);
    private String imagePath;
    private boolean type_reclamation;

    public boolean getType_reclamation() {
        return type_reclamation;
    }

    public void setTypesReclamation(boolean isUrgent) {
        ObservableList<String> typesReclamation = FXCollections.observableArrayList();
        if (isUrgent) {
            // Ajouter des éléments à la liste pour les cas urgents
            typesReclamation.addAll("Urgences médicales", "Incendies", "Fuites de gaz", "Inondations", "Sécurité publique", "Défaillances des infrastructures critiques");
        } else {
            // Ajouter des éléments à la liste pour les cas non urgents
            typesReclamation.addAll("Réparations de voirie", "Collecte des déchets", "Environnement", "Aménagement paysager", "Problèmes de logement", "Services municipaux");
        }
        // Définir les éléments du ComboBox en utilisant la liste observable
        typeReclamationComboBox.setItems(typesReclamation);
    }

    @FXML
    void ajouterReclamationAction(ActionEvent event) {
        try {
            // Utilisez imagePath pour enregistrer le chemin absolu de l'image dans la base de données
            sr.ajouter(new Reclamation(user, muni, TFsujet_reclamation.getText(), sqlDate, typeReclamationComboBox.getValue(), TAdescription_reclamation.getText(), imagePath, TFadresse_reclamation.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation a été ajoutée");
            alert.setContentText("GG");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void uploadimg(ActionEvent event) {
        uploadbutton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png"));
            Stage stage = (Stage) uploadbutton.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                // Affiche le nom du fichier sélectionné
                label.setText(selectedFile.getName());

                // Récupère le chemin absolu du fichier
                String absolutePath = selectedFile.getAbsolutePath();
                // Stocke le chemin absolu dans la variable de classe
                imagePath = absolutePath;

                // Crée une URL à partir du chemin absolu du fichier
                String fileUrl = new File(absolutePath).toURI().toString();

                // Crée une image à partir de l'URL du fichier
                Image image = new Image(fileUrl);

                // Affiche l'image dans l'ImageView
                imgView_reclamation.setImage(image);
            }
        });
    }
    @FXML
    void navigatetoAfficherReclamationAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamation.fxml"));
            TFsujet_reclamation.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}