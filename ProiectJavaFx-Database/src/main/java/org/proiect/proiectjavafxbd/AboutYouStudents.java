package org.proiect.proiectjavafxbd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AboutYouStudents {

    @FXML
    private Label nume;

    @FXML
    private Label prenume;

    @FXML
    private Label adresa;

    @FXML
    private Label nrTelefon;

    @FXML
    private Label IBAN;

    @FXML
    private Label CNP;

    @FXML
    private Label Email;


    @FXML
    private Label anDeStudiu;

    @FXML
    private Button Calendar;
    @FXML
    private Button Students;
    @FXML
    private Button Teachers;
    @FXML
    private Button Gradebook;
    @FXML
    private Button AboutYou;
    @FXML
    private Button Chat;

    @FXML
    private Button Logout;

    @FXML
    private Button Suggestions;

    @FXML
    public void initialize() {
        try {
            showInfo();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    private void  handleSuggestionsButton(){switchScene("StudentSuggestions.fxml",1200,700,Suggestions);}



    @FXML
    private void handleCalendarButton() {
        switchScene("CalendarStudents.fxml", 1200, 700, Calendar);
    }

    @FXML
    private void handleStudentsButton() {
        switchScene("Students_students.fxml", 1200, 700, Students);
    }

    @FXML
    private void handleTeachersButton() {
        switchScene("Students_profesor_pt_student.fxml", 1200, 700, Teachers);
    }

    @FXML
    private void handleGradebookButton() {
        switchScene("Gradebook_students.fxml", 1200, 700, Gradebook);
    }

    @FXML
    private void handleAboutYouButton() {
        switchScene("AboutYou_students.fxml", 1200, 700, AboutYou);
    }


    @FXML
    private void handleChatButton() {
        switchScene("Chat_students.fxml", 1200, 700, Chat);
    }


    @FXML
    private void handleLogOut() {
        switchScene("hello-view.fxml", 500, 400, Logout);
    }

    private void showInfo() throws IOException, SQLException {
        String email = UserCredentials.getUsername();
        String cnp = UserCredentials.getPassword();  // Assuming CNP is stored in the password field

        // Assuming you have a method to retrieve user data from the database based on email and CNP
        UserData userData = getUserData(email, cnp);

        // Set the data to corresponding labels
        nume.setText(userData.getNume());
        prenume.setText(userData.getPrenume());
        adresa.setText(userData.getAdresa());
        nrTelefon.setText(userData.getNrTelefon());
        IBAN.setText(userData.getIBAN());
        anDeStudiu.setText(userData.getAnDeStudiu());
        CNP.setText(userData.getCnp());
        Email.setText(userData.getEmail());
    }

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

    private UserData getUserData(String email, String cnp) throws SQLException {
        // Replace these with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        // Replace this query with your actual query to retrieve user data
        String query = "SELECT nume, prenume, adresa, nrDeTelefon, iban, anDeStudiu, CNP, email FROM facultate.student WHERE email = ? AND cnp = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, cnp);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserData(
                            resultSet.getString("nume"),
                            resultSet.getString("prenume"),
                            resultSet.getString("adresa"),
                            resultSet.getString("nrDeTelefon"),
                            resultSet.getString("IBAN"),
                            resultSet.getString("anDeStudiu"),
                            resultSet.getString("CNP"),
                            resultSet.getString("email")
                    );
                }
            }
        }

        // Return null if no user data is found
        return null;
    }

    // UserData class with appropriate getters and setters
    private static class UserData {
        private final String nume;
        private final String prenume;
        private final String adresa;
        private final String nrTelefon;
        private final String IBAN;
        private final String anDeStudiu;
        private final String CNP;
        private final String email;

        public UserData(String nume, String prenume, String adresa, String nrTelefon, String IBAN, String anDeStudiu, String cnp, String email) {
            this.nume = nume;
            this.prenume = prenume;
            this.adresa = adresa;
            this.nrTelefon = nrTelefon;
            this.IBAN = IBAN;
            this.anDeStudiu = anDeStudiu;
            this.CNP = cnp;
            this.email = email;
        }

        // Add getters and setters
        public String getNume() {
            return nume;
        }

        public String getPrenume() {
            return prenume;
        }

        public String getAdresa() {
            return adresa;
        }

        public String getNrTelefon() {
            return nrTelefon;
        }

        public String getIBAN() {
            return IBAN;
        }

        public String getAnDeStudiu() {
            return anDeStudiu;
        }

        // Add getters and setters for email and cnp
        public String getEmail() {
            return email;
        }

        public String getCnp() {
            return CNP;
        }


    }
}