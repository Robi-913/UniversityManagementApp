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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class AdministratorSuggestion {

    @FXML
    private Button back;
    @FXML
    private TableView<AdministratorSuggestionH1> tabel;

    @FXML
    private TableColumn<AdministratorSuggestionH1, String> sugestii;
    private String CNP;
    @FXML
    private Button Remove;
    @FXML
    private Button Add;




    @FXML
    public void initialize() {
        sugestii.setCellValueFactory(new PropertyValueFactory<AdministratorSuggestionH1, String>("sugestii")); // setează cum dorești să fie afișate datele în coloană

        int id_student=Integer.parseInt(getStudentIdByCNP(AdministratorSuggestionH.getCNP()));

        tabel.setItems(getActivitiesForGroup(id_student));

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




    @FXML
    private void handleBack() {
        switchScene("studentAdministrator.fxml", 1200, 700, back);
    }

    public String getStudentIdByCNP(String cnp) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";


        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT id FROM facultate.student WHERE CNP = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, cnp);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("id");
                    } else {
                        // Student not found in the database
                        System.out.println("Student not found in the database for CNP: " + cnp);
                        return null; // Or handle it according to your requirements
                    }
                }
            }
        } catch (SQLException e) {
            showErrorAlert("Error getting student ID from the database.");
            e.printStackTrace();
            return null; // Or handle it according to your requirements
        }
    }

    public ObservableList<AdministratorSuggestionH1> getActivitiesForGroup(int studentId) {
        ObservableList<AdministratorSuggestionH1> activities = FXCollections.observableArrayList();

        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "{CALL GetActivitiesForGroup(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(query)) {
                callableStatement.setInt(1, studentId);

                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        AdministratorSuggestionH1 activ=new AdministratorSuggestionH1(resultSet.getString("nume"));
                    activities.add(activ);
                    }
                }
            }
        } catch (SQLException e) {
            showErrorAlert("Error getting activities for the group.");
            e.printStackTrace();
        }

        return activities;
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

    @FXML
    private void handleAdd() {
        String studentCNP = AdministratorSuggestionH.getCNP();
        int studentId = Integer.parseInt(getStudentIdByCNP(studentCNP));

        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            connection.setAutoCommit(false);

            // Assuming you have a table named 'student_activitate' with columns 'id_student' and 'id_activitate'
            String checkQuery = "SELECT 1 FROM inscris_activitate_grup WHERE id_student = ? AND id_activitate = ?";
            String insertQuery = "INSERT INTO inscris_activitate_grup (id_student, id_activitate) VALUES (?, ?)";

            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                 PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

                ObservableList<AdministratorSuggestionH1> selectedItems = tabel.getSelectionModel().getSelectedItems();

                for (AdministratorSuggestionH1 selectedItem : selectedItems) {
                    int activityId = getActivityIdByName(selectedItem.getSugestii(), connection);

                    // Check if the student is already enrolled in the selected activity
                    checkStatement.setInt(1, studentId);
                    checkStatement.setInt(2, activityId);
                    try (ResultSet resultSet = checkStatement.executeQuery()) {
                        if (!resultSet.next()) {
                            // Student is not enrolled in the activity, proceed with addition
                            insertStatement.setInt(1, studentId);
                            insertStatement.setInt(2, activityId);
                            insertStatement.addBatch();
                            showErrorAlert("Student was added!");
                        } else {
                            // Student is already enrolled in the activity, show an alert or handle appropriately
                            showErrorAlert("Student is already enrolled in the selected activity: " + selectedItem.getSugestii());
                            connection.rollback();
                            return;
                        }
                    }
                }

                insertStatement.executeBatch();
                connection.commit();

                // Refresh the table after adding the student to activities
                tabel.setItems(getActivitiesForGroupFromDatabase(studentId, connection));
            }
        } catch (SQLException e) {
            showErrorAlert("Error adding student to activities.");
            e.printStackTrace();
        }
    }


    @FXML
    private void handleRemove() {
        String studentCNP = AdministratorSuggestionH.getCNP();
        int studentId = Integer.parseInt(getStudentIdByCNP(studentCNP));

        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            connection.setAutoCommit(false);

            // Assuming you have a table named 'student_activitate' with columns 'id_student' and 'id_activitate'
            String checkQuery = "SELECT 1 FROM inscris_activitate_grup WHERE id_student = ? AND id_activitate = ?";
            String deleteQuery = "DELETE FROM inscris_activitate_grup WHERE id_student = ? AND id_activitate = ?";

            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                 PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {

                ObservableList<AdministratorSuggestionH1> selectedItems = tabel.getSelectionModel().getSelectedItems();

                for (AdministratorSuggestionH1 selectedItem : selectedItems) {
                    int activityId = getActivityIdByName(selectedItem.getSugestii(), connection);

                    // Check if the student is enrolled in the selected activity
                    checkStatement.setInt(1, studentId);
                    checkStatement.setInt(2, activityId);
                    try (ResultSet resultSet = checkStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Student is enrolled in the activity, proceed with removal
                            deleteStatement.setInt(1, studentId);
                            deleteStatement.setInt(2, activityId);
                            deleteStatement.addBatch();
                            showErrorAlert("Student was deleted!");
                        } else {
                            // Student is not enrolled in the activity, show an alert or handle appropriately
                            showErrorAlert("Student is not enrolled in the selected activity: " + selectedItem.getSugestii());
                            connection.rollback();
                            return;
                        }
                    }
                }

                deleteStatement.executeBatch();
                connection.commit();

                // Refresh the table after removing the student from activities
                tabel.setItems(getActivitiesForGroupFromDatabase(studentId, connection));
            }
        } catch (SQLException e) {
            showErrorAlert("Error removing student from activities.");
            e.printStackTrace();
        }
    }


    // Method to get the activity ID by name
    private int getActivityIdByName(String activityName, Connection connection) throws SQLException {
        String selectQuery = "SELECT id FROM activitate_grup WHERE nume = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, activityName);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        return -1; // Return a default value or handle appropriately
    }

    // Method to get activities for a specific student from the database
    private ObservableList<AdministratorSuggestionH1> getActivitiesForGroupFromDatabase(int studentId, Connection connection) throws SQLException {
        ObservableList<AdministratorSuggestionH1> activitiesList = FXCollections.observableArrayList();

        // Query to retrieve activities for a specific student
        String selectQuery = "SELECT a.nume FROM activitate_grup a " +
                "JOIN inscris_activitate_grup i ON a.id = i.id_activitate " +
                "WHERE i.id_student = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, studentId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    String activityName = resultSet.getString("nume");
                    activitiesList.add(new AdministratorSuggestionH1(activityName));
                }
            }
        }

        return activitiesList;
    }




}


