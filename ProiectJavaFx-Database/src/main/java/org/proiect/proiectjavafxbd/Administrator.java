package org.proiect.proiectjavafxbd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class Administrator {


    @FXML
    private Button Student;
    @FXML
    private Button Profesor;
    @FXML
    private Button SuperAdmin;
    @FXML
    private Button LogOut;




    @FXML
    private void handleLogOutAdministrator() {
        switchScene("hello-view.fxml",500,400,LogOut);
    }

    @FXML
    private void handleStudentAdministrator() {
        switchScene("studentAdministrator.fxml",1200,700,Student);
    }

    @FXML
    private void handleProfesorAdministrator() {
        switchScene("profesorAdministartor.fxml",1200,700,Profesor);
    }

    @FXML
    private void handleSuperAdminAdministrator() {
        if(isSuperAdmin()){
            switchScene("superAdmin.fxml",1200,700,SuperAdmin);
        }else {
            showErrorAlert("Not SuperAdmin");
        }

    }

    public Administrator() {

    }


    private void switchScene(String fxmlFileName, int width, int height, Button button) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, width, height));
            URL iconUrl = new URL("file:/C:/Users/Robert/Documents/facultate/anu2/bd/ProiectJavaFxBd/src/main/java/img/utcn-logo.png");
            Image icon = new Image(iconUrl.toString());
            newStage.getIcons().add(icon);
            newStage.show();

            // Close the current stage if needed
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    boolean isSuperAdmin() {
        String email = UserCredentials.getUsername();
        String cnp = UserCredentials.getPassword();

        try {
            String url = "jdbc:mysql://localhost:3306/facultate";
            String dbUsername = "root";
            String dbPassword = "20072003Robert";

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);


            // Query to retrieve superadministrator_bool for the given email and cnp
            String query = "SELECT superadministrator_bool FROM administrator WHERE email = ? AND cnp = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, cnp);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBoolean("superadministrator_bool");
                    } else {
                        // User not found in the database
                        System.out.println("User not found in the database.");
                        return false; // Or handle it according to your requirements
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception as needed
                return false; // Or handle it according to your requirements
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions as needed
            return false; // Or handle it according to your requirements
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Apply custom styles from the CSS file
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/org/proiect/proiectjavafxbd/custom-alert.css").toExternalForm());
        dialogPane.getStyleClass().add("error-alert");

        alert.showAndWait();
    }

}
