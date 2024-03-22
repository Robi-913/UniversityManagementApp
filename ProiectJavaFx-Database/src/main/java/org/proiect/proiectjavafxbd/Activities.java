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

public class Activities {

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
    private void switchScene(String fxmlFileName, int width, int height, Button buton) {
        try {
            System.out.println(getClass().getResource(fxmlFileName));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, width, height));
            URL iconUrl = new URL("file:/C:/Users/Alexia/Desktop/UTCN/bd/ProiectBdFX1/src/main/java/com/example/proiectbdfx1/img/utcn-logo (1).png");
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
    private Button Join;

    @FXML
    private Button Leave;

    @FXML
    private TableView<ActivitiesH> tabel1;
    @FXML
    private TableColumn<ActivitiesH,String> activitate1;

    @FXML
    private TableView<ActivitiesH> tabel2;
    @FXML
    private TableColumn<ActivitiesH, String> activitate2;

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
            writer.println("activitate");

            // Write the data
            for (ActivitiesH activ : tabel2.getItems()) {
                writer.println(String.format("%s", activ.getActivitate()));

            }

            System.out.println("activities saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    public void initialize(){
        activitate1.setCellValueFactory(new PropertyValueFactory<ActivitiesH,String>("activitate"));
        activitate2.setCellValueFactory(new PropertyValueFactory<ActivitiesH,String>("activitate"));
        tabel1.setItems(showAllActivities());
        tabel2.setItems(showTeacherActivities(getProfesorId(UserCredentials.getUsername(),UserCredentials.getPassword())));

    }

    //    private void updateTeacherActivitiesTable() {
//        int profesorId = getProfesorId(UserCredentials.getUsername(), UserCredentials.getPassword());
//        tabel2.setItems(showTeacherActivities(profesorId));
//    }
    private int getProfesorId(String email, String cnp) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT id FROM facultate.profesor WHERE cnp = ? AND email = ?";
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

    private int getActivityId(String activity) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT id FROM facultate.activitate_grup WHERE nume like ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, activity);


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

    @FXML
    private void handleEnroll() {
        ActivitiesH selectedActivity = tabel1.getSelectionModel().getSelectedItem();
        System.out.println(selectedActivity);
        if (selectedActivity != null) {
            String activityName = selectedActivity.getActivitate();

            int profesorId = getProfesorId(UserCredentials.getUsername(),UserCredentials.getPassword());
            int activitateID=getActivityId(activityName);


            enrollInActivity(profesorId, activitateID);
            // Refresh the tables
            tabel2.setItems(showTeacherActivities(profesorId));
            tabel1.setItems(showAllActivities());

        }
    }

    private void enrollInActivity(int profesorID, int idActivity) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Check if the professor is already associated with the activity
            if (!isProfesorInActivity(connection, profesorID, idActivity)) {
                String procedureCall = "{CALL AdaugaProfesorLaActivitate(?, ?)}";
                try (CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
                    callableStatement.setInt(1, idActivity);
                    callableStatement.setInt(2, profesorID);

                    callableStatement.execute();

                    // If the procedure executes without errors, display a success message
                    showErrorAlert("Profesorul a fost adăugat la activitate cu succes.");
                } catch (SQLException e) {
                    // If an error occurs during the procedure call, display an error message
                    showErrorAlert("Eroare la adăugarea profesorului la activitate: " + e.getMessage());
                }
            } else {
                // If the professor is already associated with the activity, display an error message
                showErrorAlert("Profesorul este deja asociat acestei activități.");
            }
        } catch (SQLException e) {
            // Handle database connection errors
            showErrorAlert("Eroare la conectarea la baza de date: " + e.getMessage());
        }
    }



    private boolean isProfesorInActivity(Connection connection, int profesorID, int idActivity) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM facultate.profesor_activitate_grup " +
                "WHERE id_profesor = ? AND id_activitate_grup = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, profesorID);
            preparedStatement.setInt(2, idActivity);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }

            return false;
        }
    }

    private ObservableList<ActivitiesH> showTeacherActivities(int profesorId) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<ActivitiesH> date = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {

            // Setarea parametrilor pentru procedură
            try (CallableStatement statement = connection.prepareCall("{CALL facultate.ActivitatiProfesor(?)}")) {
                statement.setInt(1, profesorId);

                // Executarea procedurii
                ResultSet resultSet = statement.executeQuery();

                // Afișarea rezultatelor în tabel
                //
                while (resultSet.next()) {
                    ActivitiesH data=new ActivitiesH(resultSet.getString("nume_activitate"));


                    date.add(data);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratează excepția după nevoie
        }

        return date;
    }


    private ObservableList<ActivitiesH> showAllActivities() {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<ActivitiesH> date = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT ag.nume FROM facultate.activitate_grup ag";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String activityName = resultSet.getString("nume");
                    ActivitiesH data = new ActivitiesH(activityName);
                    date.add(data);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }

        return date;
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

    private void leaveActivity(int profesorId, String activityName) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // First, retrieve the ID of the specified activity
            int activityId = getActivityId(activityName);

            if (activityId != -1) {
                // Call the stored procedure to remove the professor from the activity
                String procedureCall = "{CALL StergeProfesorDinActivitate(?, ?)}";
                try (CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
                    callableStatement.setInt(1, activityId);
                    callableStatement.setInt(2, profesorId);

                    callableStatement.execute();

                    // If the procedure executes without errors, display a success message
                    showErrorAlert("Profesorul a fost șters din activitate cu succes.");
                } catch (SQLException e) {
                    // If an error occurs during the procedure call, display an error message
                    showErrorAlert("Eroare la ștergerea profesorului din activitate: " + e.getMessage());
                }
            } else {
                showErrorAlert("Activitatea specificată nu există.");
            }
        } catch (SQLException e) {
            // Handle database connection errors
            showErrorAlert("Eroare la conectarea la baza de date: " + e.getMessage());
        }
    }

    @FXML
    private void handleLeave() {
        ActivitiesH selectedActivity = tabel1.getSelectionModel().getSelectedItem();
        System.out.println(selectedActivity);
        if (selectedActivity != null) {
            String activityName = selectedActivity.getActivitate();

            int profesorId = getProfesorId(UserCredentials.getUsername(), UserCredentials.getPassword());
            leaveActivity(profesorId, activityName);

            tabel1.setItems(showAllActivities());
            tabel2.setItems(showTeacherActivities(getProfesorId(UserCredentials.getUsername(),UserCredentials.getPassword())));
        }
    }
}