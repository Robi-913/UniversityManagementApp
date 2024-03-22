package org.proiect.proiectjavafxbd;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class StudyGroupSuggestions {


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
    private Button Leave;
    @FXML
    private Button Enroll;


    @FXML
    private TableView<StudyGroupH> tabel;

    @FXML
    private TableColumn<StudyGroupH, String> nume;

    @FXML
    private TableColumn<StudyGroupH, String> prenume;
    @FXML
    private TableColumn<StudyGroupH, String> activitate;
    @FXML
    private TableView<StudyGroupH> tabel1;
    @FXML
    private TableColumn<StudyGroupH, String> activitate1;




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
    private Button downloadButton;

    @FXML
    private void handleDownloadButton() {
        // Show a file chooser dialog to get the download location
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Activities");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(downloadButton.getScene().getWindow());

        if (file != null) {
            // Save the content of the TableView to the chosen file
            saveActvitiesToFile(file);
        }
    }

    private void saveActvitiesToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            // Write the header
            writer.println("activitate,Nume Coleg,Prenume Coleg");

            // Write the data
            for (StudyGroupH activ : tabel.getItems()) {
                writer.println(String.format("%s, %s, %s", activ.getActivitate(),activ.getNume(),activ.getPrenume()));

            }

            System.out.println("activities saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    public void initialize() {
        nume.setCellValueFactory(new PropertyValueFactory<StudyGroupH, String>("nume"));
        prenume.setCellValueFactory(new PropertyValueFactory<StudyGroupH, String>("prenume"));
        activitate.setCellValueFactory(new PropertyValueFactory<StudyGroupH, String>("activitate"));

        activitate1.setCellValueFactory(new PropertyValueFactory<>("activitate"));

        tabel1.setItems(showAllActivities());



        tabel.setItems(showStudentActivities(getStudentId(UserCredentials.getUsername(),UserCredentials.getPassword())));


    }

    @FXML
    private void handleEnroll() {
        StudyGroupH selectedActivity = tabel1.getSelectionModel().getSelectedItem();

        if (selectedActivity != null) {
            String activityName = selectedActivity.getActivitate();

            int studentId = getStudentId(UserCredentials.getUsername(), UserCredentials.getPassword());

            if (checkEnrollment(studentId, activityName) == -1) {
                // Student is not enrolled, enroll in the activity
                enrollInActivity(studentId, activityName);
                // Refresh the tables
                tabel.setItems(showStudentActivities(studentId));
                tabel1.setItems(showAllActivities());
            } else {
                showErrorAlert("You are already enrolled in this activity.");
            }
        } else {
            showErrorAlert("Please select an activity to enroll.");
        }
    }

    @FXML
    private void handleLeave() {
        StudyGroupH selectedActivity = tabel1.getSelectionModel().getSelectedItem();

        if (selectedActivity != null) {
            String activityName = selectedActivity.getActivitate();

            int studentId = getStudentId(UserCredentials.getUsername(), UserCredentials.getPassword());

            if (checkEnrollment(studentId, activityName) == 1) {
                // Student is enrolled, leave the activity
                leaveActivity(studentId, activityName);
                // Refresh the tables
                tabel.setItems(showStudentActivities(studentId));
                tabel1.setItems(showAllActivities());
            } else {
                showErrorAlert("You are not enrolled in this activity.");
            }
        } else {
            showErrorAlert("Please select an activity to leave.");
        }
    }

    private void enrollInActivity(int studentId, String activityName) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "INSERT INTO inscris_activitate_grup (id_student, id_activitate) " +
                    "SELECT ?, id FROM activitate_grup WHERE nume = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, activityName);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    showErrorAlert("Enrolled successfully in " + activityName);
                } else {
                    showErrorAlert("Failed to enroll in " + activityName + ". Maybe you are already enrolled.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error enrolling in " + activityName + ". Please try again later.");
        }
    }

    private void leaveActivity(int studentId, String activityName) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "DELETE FROM inscris_activitate_grup " +
                    "WHERE id_student = ? AND id_activitate = (SELECT id FROM activitate_grup WHERE nume = ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, activityName);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    showErrorAlert("Left " + activityName + " successfully");
                } else {
                    showErrorAlert("Failed to leave " + activityName + ". Maybe you are not enrolled.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error leaving " + activityName + ". Please try again later.");
        }
    }



    private int getStudentId(String email, String cnp) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT id FROM facultate.student WHERE cnp = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, cnp);
                preparedStatement.setString(2, email);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        // Return -1 if an error occurs
        return -1;
    }


    private ObservableList<StudyGroupH> showStudentActivities(int studentId) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<StudyGroupH> date = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {

            // Setarea parametrilor pentru procedură
            try (CallableStatement statement = connection.prepareCall("{CALL GetStudentActivities(?)}")) {
                statement.setInt(1, studentId);

                // Executarea procedurii
                ResultSet resultSet = statement.executeQuery();

                // Afișarea rezultatelor în tabel
                //
                while (resultSet.next()) {
                    StudyGroupH data=new StudyGroupH(resultSet.getString("nume_student"),
                            resultSet.getString("prenume_student"),
                            resultSet.getString( "nume_activitate"));

                    date.add(data);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratează excepția după nevoie
        }

        return date;
    }

    private ObservableList<StudyGroupH> showAllActivities() {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<StudyGroupH> date = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT ag.nume FROM facultate.activitate_grup ag";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String activityName = resultSet.getString("nume");
                    StudyGroupH data = new StudyGroupH(activityName);
                    date.add(data);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }

        return date;
    }

    private int checkEnrollment(int studentId, String activityName) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT 1 FROM inscris_activitate_grup iag " +
                    "JOIN activitate_grup ag ON iag.id_activitate = ag.id " +
                    "WHERE iag.id_student = ? AND ag.nume = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, activityName);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return 1; // Studentul este înscris în activitate
                } else {
                    return -1; // Studentul nu este înscris în activitate
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratează excepția după nevoie
        }

        // Return -1 dacă apare o eroare
        return -1;
    }



    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Apply custom styles from the CSS file
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/org/proiect/proiectjavafxbd/custom-alert.css").toExternalForm());
        dialogPane.getStyleClass().add("error-alert");

        alert.showAndWait();
    }




}