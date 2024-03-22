package org.proiect.proiectjavafxbd;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;


import static org.proiect.proiectjavafxbd.GradebookStudents.getId;

public class GradebookTeachers {
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
    private Button downloadButton;
    @FXML
    private  Button Procentaje;
    private static int procentCurs;
    private static int procentSeminar;
    private static int procentLab;

    @FXML
    private Button Activities;

    @FXML
    private void handleActivitiesButton() {
        switchScene("Activities.fxml", 1200, 700, Calendar);
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

    @FXML
    private void handleProcentajButton() {
        try {
            System.out.println(getClass().getResource("Procentaj.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Procentaj.fxml"));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 300, 120));
            URL iconUrl = new URL("file:/C:/Users/Robert/Documents/facultate/anu2/bd/ProiectJavaFxBd/src/main/java/img/utcn-logo.png");
            Image icon = new Image(iconUrl.toString());
            newStage.getIcons().add(icon);

            newStage.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(30));
            delay.setOnFinished(event -> {
                Stage currentStage = (Stage) Procentaje.getScene().getWindow();
                currentStage.close();
            });
            delay.play();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
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

    public ArrayList<NotaPentruProfesor> array_ = new ArrayList<>();


    @FXML
    public TableView<NotaPentruProfesor> tabel;

    @FXML
    private TableColumn<NotaPentruProfesor, String> numeStudent;

    @FXML
    private TableColumn<NotaPentruProfesor, String> Materie;

    @FXML
    private TableColumn<NotaPentruProfesor, Float> notaCurs;

    @FXML
    private TableColumn<NotaPentruProfesor, Float> notaSeminar;

    @FXML
    private TableColumn<NotaPentruProfesor, Float> notaLaborator;

    @FXML
    private TableColumn<NotaPentruProfesor, Float> notaFinala;
    public void initialize() {


        numeStudent.setCellValueFactory(new PropertyValueFactory<>("numeStudent"));
        Materie.setCellValueFactory(new PropertyValueFactory<>("Materie"));
        notaCurs.setCellValueFactory(new PropertyValueFactory<>("notaCurs"));
        notaSeminar.setCellValueFactory(new PropertyValueFactory<>("notaSeminar"));
        notaLaborator.setCellValueFactory(new PropertyValueFactory<>("notaLaborator"));
        notaFinala.setCellValueFactory(new PropertyValueFactory<>("notaFinala"));

        getNote();
        re_update();
        // Assuming you have a method like getNote in your code
        tabel.setItems(getNote());
        tabel.setEditable(true);


        notaCurs.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        notaSeminar.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        notaLaborator.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        notaFinala.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        tabel.setEditable(true);

        // Set columns to be editable
        notaCurs.setEditable(true);
        notaSeminar.setEditable(true);
        notaLaborator.setEditable(true);
        notaFinala.setEditable(true);

        // Assuming you have a method like getNote in your code
        tabel.setItems(getNote());

        // Commit the changes when editing is finished
        setCellValueFactories();

    }

    @FXML
    private void handleDownloadButton() {
        // Show a file chooser dialog to get the download location
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Gradebook");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(downloadButton.getScene().getWindow());

        if (file != null) {
            // Save the content of the TableView to the chosen file
            saveGradebookToFile(file);
        }
    }

    private void saveGradebookToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            // Write the header
            writer.println("numeStudent,materie,notaCurs,notaLaborator,notaSeminar,notaFinala");

            // Write the data
            for (NotaPentruProfesor nota : tabel.getItems()) {
                writer.println(String.format("%s,%s,%f,%f,%f,%f",
                        nota.getNumeStudent(),nota.getMaterie(), nota.getNotaCurs(),
                        nota.getNotaLaborator(), nota.getNotaSeminar(), nota.getNotaFinala()));
            }

            System.out.println("Gradebook saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void setCellValueFactories() {
        notaCurs.setOnEditCommit(event -> {
            NotaPentruProfesor nota = event.getRowValue();
            nota.setNotaCurs(event.getNewValue());
            updateDatabase(nota);
            System.out.println(event.getNewValue());
        });

        notaSeminar.setOnEditCommit(event -> {
            NotaPentruProfesor nota = event.getRowValue();
            nota.setNotaSeminar(event.getNewValue());
            updateDatabase(nota);
        });

        notaLaborator.setOnEditCommit(event -> {
            NotaPentruProfesor nota = event.getRowValue();
            nota.setNotaLaborator(event.getNewValue());
            updateDatabase(nota);
        });

        notaFinala.setOnEditCommit(event -> {
            NotaPentruProfesor nota = event.getRowValue();
            nota.setNotaFinala(event.getNewValue());
            updateDatabase(nota);
        });
    }

    private void callUpdateFinalGradesProcedure(Connection connection, int subjectId) {
        try {
            // Call the stored procedure
            CallableStatement cstmt = connection.prepareCall("{call UpdateFinalGrades(?)}");
            cstmt.setInt(1, subjectId);
            cstmt.execute();

            System.out.println("Final grades updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error calling stored procedure:");
            e.printStackTrace();
        }
    }


    private int getStudentIdByName(String studentName) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sqlQuery = "SELECT id FROM facultate.student WHERE nume=?";
            try (PreparedStatement prep = connection.prepareStatement(sqlQuery)) {
                prep.setString(1, studentName);

                try (ResultSet resultSet = prep.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query:");
            e.printStackTrace();
        }

        // Return a default value or handle the case when the student ID is not found
        return -1;
    }

    private static int getSubjectIdByName(String subjectName) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sqlQuery = "SELECT id FROM facultate.materie WHERE nume like ?";
            try (PreparedStatement prep = connection.prepareStatement(sqlQuery)) {
                prep.setString(1, subjectName);

                try (ResultSet resultSet = prep.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query:");
            e.printStackTrace();
        }

        // Return a default value or handle the case when the subject ID is not found
        return -1;
    }


    public int get_prof_id(){

        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<NotaPentruProfesor> note = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            PreparedStatement prep = connection.prepareStatement("SELECT id from facultate.profesor where email = ? and cnp = ?");
            prep.setString(1, UserCredentials.getUsername());
            prep.setString(2, UserCredentials.getPassword());
            ResultSet rs = prep.executeQuery();

            if (rs.next()){
                return rs.getInt("id");
            }
            return -1;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getMaterieIdByProfId(int profId) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sqlQuery = "SELECT facultate.activitate_didactica.idMaterie FROM facultate.activitate_didactica " +
                    "WHERE facultate.activitate_didactica.idProfesor = ?";

            try (PreparedStatement prep = connection.prepareStatement(sqlQuery)) {
                prep.setInt(1, profId);

                try (ResultSet resultSet = prep.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("idMaterie");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query:");
            e.printStackTrace();
        }

        // Return a default value or handle the case when the materie ID is not found
        return -1;
    }

    public ObservableList<NotaPentruProfesor> getNote() {


        array_ = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<NotaPentruProfesor> note = FXCollections.observableArrayList();

        try {
            int prof_id = get_prof_id();
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println(getId(UserCredentials.getPassword()));
            System.out.println(UserCredentials.getUsername());


            PreparedStatement prep = connection.prepareStatement("SELECT facultate.catalog.notaCurs, facultate.catalog.notaSeminar, facultate.catalog.notaFinala, facultate.catalog.notaLaborator, facultate.materie.nume, student.nume as nume_student from facultate.catalog join facultate.activitate_didactica on facultate.activitate_didactica.idProfesor = ? join facultate.materie on facultate.materie.id = facultate.catalog.id_materie join facultate.student on facultate.student.id = facultate.catalog.id_student where facultate.catalog.id_materie = facultate.activitate_didactica.idMaterie ");
            prep.setInt(1, prof_id);
            ResultSet resultSet = prep.executeQuery();

            if (true) {

                while (resultSet.next()) {
                    NotaPentruProfesor nota = new NotaPentruProfesor(resultSet.getString("nume"),
                            resultSet.getString("nume_student"),
                            resultSet.getInt("notaCurs"),
                            resultSet.getInt("notaSeminar"),
                            resultSet.getInt("notaLaborator"), resultSet.getInt("notaFinala"));
                    note.add(nota);
                    array_.add(nota);


                }
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
        return note;

    }

    public static void updatePercentageValues(int curs, int seminar, int lab, String materie) {
        procentCurs = curs;
        procentSeminar = seminar;
        procentLab = lab;

        // Perform any additional actions if needed
        System.out.println("Updated percentages: Curs=" + procentCurs + ", Seminar=" + procentSeminar + ", Lab=" + procentLab);

        // Update the database with the new percentage values
        updateDatabaseWithPercentages(procentCurs, procentSeminar, procentLab, materie);


        // Call getNote() to refresh the table data with updated values
        GradebookTeachers gradebookTeachers = new GradebookTeachers();
        gradebookTeachers.getNote();
    }



    private static void updateDatabaseWithPercentages(int curs, int seminar, int lab, String materie) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        System.out.println("Apelez aici");
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sqlQuery = "UPDATE facultate.materie SET procentCurs=?, procentSeminar=?, procentLaborator=? WHERE id=?";
            try (PreparedStatement prep = connection.prepareStatement(sqlQuery)) {
                prep.setInt(1, curs);
                prep.setInt(2, seminar);
                prep.setInt(3, lab);
                // Set the appropriate subject ID (modify this part based on your logic)
                prep.setInt(4, getSubjectIdByName(materie));

                prep.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query:");
            e.printStackTrace();
        }
    }
    private void updateDatabase(NotaPentruProfesor nota) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sqlQuery = "UPDATE facultate.catalog SET notaCurs=?, notaSeminar=?, notaLaborator=? WHERE id_student=? AND id_materie=?";
            try (PreparedStatement prep = connection.prepareStatement(sqlQuery)) {
                prep.setFloat(1, nota.getNotaCurs());
                prep.setFloat(2, nota.getNotaSeminar());
                prep.setFloat(3, nota.getNotaLaborator());

                // Assuming you have methods to get student and subject IDs
                int studentId = getStudentIdByName(nota.getNumeStudent());
                int subjectId = getSubjectIdByName(nota.getMaterie());

                prep.setInt(4, studentId);
                prep.setInt(5, subjectId);

                prep.executeUpdate();

                // Call the stored procedure to calculate and update the final grade
                callUpdateFinalGradesProcedure(connection, subjectId);

                System.out.println("Update DataBase");

            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query:");
            e.printStackTrace();
        }
    }

    public void re_update(){

        for (NotaPentruProfesor nota : array_){
            updateDatabase(nota);
        }
    }

}