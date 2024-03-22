package org.proiect.proiectjavafxbd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class LogIn {


    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password; // Use PasswordField for password

    @FXML
    private Button button;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnOpenFile; // Adăugați un câmp pentru buton

    @FXML
    public void openTxtFile(ActionEvent event) {
        try {
            // Specificați calea către fișierul TXT
            String filePath = "C:/Users/Robert/Documents/facultate/anu2/bd/ProiectJavaFxBd/src/main/java/img/README.txt";

            // Verificați dacă funcționalitatea Desktop este suportată
            if (Desktop.isDesktopSupported()) {
                // Verificați dacă fișierul există
                File file = new File(filePath);
                if (file.exists()) {
                    // Deschideți fișierul cu aplicația implicită asociată
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Fișierul nu există: " + filePath);
                }
            } else {
                System.out.println("Funcționalitatea Desktop nu este suportată pe această platformă.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        // Add items to the ComboBox
        comboBox.getItems().addAll("Administrator", "Student", "Profesor");

        // Set default selection
        comboBox.getSelectionModel().selectFirst();
    }

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }

    public String getUsername() {
        return this.username.getText();
    }

    public String getPassword() {
        return this.password.getText();
    }

    private void switchScene(String fxmlFileName, int width, int height) {
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

    private void checkLogin() throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        String selection = this.comboBox.getValue();

        if (!validateUser(username, selection, password)) {
            wrongLogIn.setText("Invalid data");
        } else {
            UserCredentials.setUsername(username);
            UserCredentials.setPassword(password);
            wrongLogIn.setText("Connected");
            if ("administrator".equalsIgnoreCase(selection)) {
                System.out.println("Switching to administrator.fxml");
                switchScene("administrator.fxml", 1200, 700);
            } else if ("student".equalsIgnoreCase(selection)) {
                System.out.println("Switching to student.fxml");
                switchScene("student.fxml", 1200, 700);
            } else if ("profesor".equalsIgnoreCase(selection)) {
                System.out.println("Switching to profesor.fxml");
                switchScene("profesor.fxml", 1200, 700);
            } else {
                System.out.println("Invalid selection");
            }
        }
    }

    // Validate email, category, and password
// Validate email and password
    private boolean validateUser(String email, String selectedCategory, String password) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        // Check if the email exists in the respective table for the specified category
        String query = "SELECT COUNT(*) FROM ";

        // Choose the appropriate table based on the selected category
        switch (selectedCategory.toLowerCase()) {
            case "student":
                query += "student WHERE email = ? AND cnp = ?";
                break;
            case "profesor":
                query += "profesor WHERE email = ? AND cnp = ?";
                break;
            case "administrator":
                query += "administrator WHERE email = ? AND cnp = ?";
                break;
            default:
                return false;
        }

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);  // Assuming CNP is stored in the 'cnp' column

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Email and password are valid for the specified category
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }

        return false;
    }






}
