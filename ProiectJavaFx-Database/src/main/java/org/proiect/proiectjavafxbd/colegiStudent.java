package org.proiect.proiectjavafxbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class colegiStudent {

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
        switchScene("AboutYouStudent.fxml", 1200, 700, AboutYou);
    }


    @FXML
    private void handleChatButton() {
        switchScene("Chat_students.fxml", 1200, 700, Chat);
    }


    @FXML
    private void handleLogOut() {
        switchScene("hello-view.fxml", 500, 400, Logout);
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

    @FXML
    private TableView<colegStudent> tabel;

    @FXML
    private TableColumn<colegStudent, String> nume;

    @FXML
    private TableColumn<colegStudent, String> prenume;

    @FXML
    private TableColumn<colegStudent, Integer> anDeStudiu;
    @FXML
    private TableColumn<colegStudent, String> grupa;

    @FXML
    private Button cauta;

    @FXML
    private TextField searchBar;
    @FXML
    public void handleCauta() {
        tabel.setItems(getCollegues(Integer.valueOf(searchBar.getText())));
    }

    @FXML
    public void initialize() {
        nume.setCellValueFactory(new PropertyValueFactory<colegStudent, String>("nume"));
        prenume.setCellValueFactory(new PropertyValueFactory<colegStudent, String>("prenume"));
        anDeStudiu.setCellValueFactory(new PropertyValueFactory<colegStudent, Integer>("anDeStudiu"));
        grupa.setCellValueFactory(new PropertyValueFactory<colegStudent, String>("grupa"));
        tabel.setItems(getCollegues(-1));

    }

    public ObservableList<colegStudent> getCollegues(Integer anDeStudiu) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<colegStudent> colegi = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println(UserCredentials.getUsername());

            // Corrected query to include the group information
            String query = "SELECT s.nume, s.prenume, s.anDeStudiu, g.nume AS nume_grupa " +
                    "FROM student s " +
                    "JOIN inscris_grup ig ON s.id = ig.id_student " +
                    "JOIN grup_studenti g ON ig.id_grup = g.id " +
                    "WHERE (s.anDeStudiu = ? OR ? = -1) AND s.email != ?";


            try (PreparedStatement prep = connection.prepareStatement(query)) {
                prep.setInt(1, anDeStudiu);
                prep.setInt(2, anDeStudiu);
                prep.setString(3, UserCredentials.getUsername());

                ResultSet rs = prep.executeQuery();
                while (rs.next()) {
                    colegStudent coleg = new colegStudent(
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getString("anDeStudiu"),
                            rs.getString("nume_grupa")
                    );
                    colegi.add(coleg);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }

        return colegi;
    }




}