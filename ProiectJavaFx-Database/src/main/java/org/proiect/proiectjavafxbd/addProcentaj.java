package org.proiect.proiectjavafxbd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class addProcentaj {

    @FXML
    private TextField procentCurs;

    @FXML
    private TextField procentSeminar;

    @FXML
    private TextField procentLab;

    @FXML
    private TextField Materie;

    @FXML
    private Button saveButton;

    private void switchScene(String fxmlFileName, int width, int height, Button buton) {
        try {
            System.out.println(getClass().getResource(fxmlFileName));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, width, height));
            URL iconUrl = new URL("file:/C:/Users/Robert/Documents/facultate/anu2/bd/ProiectJavaFxBd/src/main/java/img/utcn-logo.png");
            Image icon = new Image(iconUrl.toString());
            newStage.getIcons().add(icon);

            newStage.show();

            // Close the current stage if needed
            Stage currentStage = (Stage) buton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private int getProcentCursValue() {
        return Integer.parseInt(procentCurs.getText());
    }

    private int getProcentSeminarValue() {
        return Integer.parseInt(procentSeminar.getText());
    }

    private int getProcentLabValue() {
        return Integer.parseInt(procentLab.getText());
    }

    public String getMaterie() {
        return Materie.getText();
    }

    @FXML
    private void handleSaveButton() {
        int procentCursValue = getProcentCursValue();
        int procentSeminarValue = getProcentSeminarValue();
        int procentLabValue = getProcentLabValue();
        String materie = getMaterie();

        // Call a method in GradebookTeachers to update the percentages
        GradebookTeachers.updatePercentageValues(procentCursValue, procentSeminarValue, procentLabValue, materie);

        // Re-populate the table with updated data
        GradebookTeachers gradebookTeachers = new GradebookTeachers();

        // Re-populate the table with updated data
        gradebookTeachers.getNote();

        // Close the window
        switchScene("GradebookProfesor.fxml",1200,700,saveButton);


    }



}

