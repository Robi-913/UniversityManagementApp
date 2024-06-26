package org.proiect.proiectjavafxbd;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Colleagues {

    @FXML
    private Button Calendar;

    @FXML
    private Button Students;

    @FXML
    private Button Colleagues;

    @FXML
    private Button Gradebook;

    @FXML
    private Button AboutYou;

    @FXML
    private Button LogOut;

    @FXML
    private TableView<Colleague> tabel;

    @FXML
    private TableColumn<Colleague, String> nume;

    @FXML
    private TableColumn<Colleague, String> prenume;

    @FXML
    private TableColumn<Colleague, String> Departament;

    @FXML
    private Button cauta;

    @FXML
    private TextField searchBar;

    @FXML
    private Button Activities;

    @FXML
    private void handleActivitiesButton() {
        switchScene("Activities.fxml", 1200, 700, Calendar);
    }

    @FXML
    public void handleCauta() {
        tabel.setItems(getCollegues(searchBar.getText()));
    }


    @FXML
    private void handleCalendarButton() {
        switchScene("CalendarTeacher.fxml", 1200, 700, Calendar);
    }

    @FXML
    private void handleStudentsButton() {
        switchScene("Students_profesor.fxml", 1200, 700, Students);
    }

    @FXML
    private void handleColleaguesButton() {
        switchScene("Colleagues.fxml", 1200, 700, Colleagues);
    }

    @FXML
    private void handleGradebookButton() {
        switchScene("GradebookProfesor.fxml", 1200, 700, Gradebook);
    }

    @FXML
    private void handleAboutYouButton() {
        switchScene("AboutYouProfesor.fxml", 1200, 700, AboutYou);
    }

    @FXML
    private void handleLogOut() {
        switchScene("hello-view.fxml", 500, 400, LogOut);
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
    public void initialize() {
        nume.setCellValueFactory(new PropertyValueFactory<Colleague, String>("nume"));
        prenume.setCellValueFactory(new PropertyValueFactory<Colleague, String>("prenume"));
        Departament.setCellValueFactory(new PropertyValueFactory<Colleague, String>("Departament"));

        tabel.setItems(getCollegues(""));

    }

    public ObservableList<Colleague> getCollegues(String Departament) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<Colleague> colegi = FXCollections.observableArrayList();

        try {

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println(UserCredentials.getUsername());
            PreparedStatement prep = connection.prepareStatement("select nume, prenume, departament from facultate.profesor where departament like ? and email!=? ");
            prep.setString(1, '%' + Departament + '%');
            prep.setString(2, UserCredentials.getUsername());
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Colleague coleg = new Colleague(rs.getString("nume"), rs.getString("prenume"), rs.getString("Departament"));
                colegi.add(coleg);
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }

        return colegi;

    }





}