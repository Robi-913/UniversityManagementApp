package org.proiect.proiectjavafxbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class SuperAdmin {

    @FXML
    private Button Student;
    @FXML
    private Button Profesor;
    @FXML
    private Button SuperAdmin;
    @FXML
    private Button LogOut;

    @FXML
    private TableView<SuperAdminH> tabel;

    @FXML
    private TableColumn<SuperAdminH, String> nume;

    @FXML
    private TableColumn<SuperAdminH, String> prenume;
    @FXML
    private TableColumn<SuperAdminH, String> CNP;
    @FXML
    private TableColumn<SuperAdminH, String> IBAN;
    @FXML
    private TableColumn<SuperAdminH, String> adresa;
    @FXML
    private TableColumn<SuperAdminH, String> email;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;

    @FXML
    private Button cauta;

    @FXML
    private TextField searchBar;

    @FXML
    public void handleCauta() {
        tabel.setItems(getAdministrator(searchBar.getText()));
    }

    @FXML
    public void initialize() {
        nume.setCellValueFactory(new PropertyValueFactory<SuperAdminH, String>("nume"));
        prenume.setCellValueFactory(new PropertyValueFactory<SuperAdminH, String>("prenume"));
        CNP.setCellValueFactory(new PropertyValueFactory<SuperAdminH, String>("CNP"));
        IBAN.setCellValueFactory(new PropertyValueFactory<SuperAdminH, String>("IBAN"));
        adresa.setCellValueFactory(new PropertyValueFactory<SuperAdminH, String>("adresa"));
        email.setCellValueFactory(new PropertyValueFactory<SuperAdminH, String>("email"));


        nume.setCellFactory(TextFieldTableCell.forTableColumn());
        prenume.setCellFactory(TextFieldTableCell.forTableColumn());
        CNP.setCellFactory(TextFieldTableCell.forTableColumn());
        IBAN.setCellFactory(TextFieldTableCell.forTableColumn());
        adresa.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        tabel.setItems(getAdministrator(""));
        tabel.setEditable(true);

        setCellEditHandler();

    }

    private void setCellEditHandler() {
        nume.setOnEditCommit(this::handleEditCommit);
        prenume.setOnEditCommit(this::handleEditCommit);
        CNP.setOnEditCommit(this::handleEditCommit);
        IBAN.setOnEditCommit(this::handleEditCommit);
        adresa.setOnEditCommit(this::handleEditCommit);
        email.setOnEditCommit(this::handleEditCommit);

    }

    private void handleEditCommit(TableColumn.CellEditEvent<SuperAdminH, String> event) {
        SuperAdminH editedAdmin = event.getRowValue();
        String columnName = event.getTableColumn().getText(); // Get the column name

        // Update the corresponding property of the StudentTeacher object
        switch (columnName) {
            case "Nume":
                editedAdmin.setNume(event.getNewValue());
                break;
            case "Prenume":
                editedAdmin.setPrenume(event.getNewValue());
                break;
            case "CNP":
                editedAdmin.setCNP(event.getNewValue());
                break;
            case "Adresa":
                editedAdmin.setAdresa(event.getNewValue());
                break;
            case "Email":
                editedAdmin.setEmail(event.getNewValue());
                break;
            case "IBAN":
                editedAdmin.setIBAN(event.getNewValue());
                break;
            // Add cases for other columns

            default:
                // Handle unknown column
                System.out.println("Unknown column: " + columnName);
                break;
        }

        // Update the database with the edited values
        updateStudentInDatabase(editedAdmin);
    }



    private void updateStudentInDatabase(SuperAdminH student) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "UPDATE facultate.administrator SET nume = ?, prenume = ?, CNP = ?, adresa = ?, email = ?, IBAN = ? WHERE CNP = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, student.getNume());
                preparedStatement.setString(2, student.getPrenume());
                preparedStatement.setString(3, student.getCNP());
                preparedStatement.setString(4, student.getAdresa());
                preparedStatement.setString(5, student.getEmail());
                preparedStatement.setString(6, student.getIBAN());
                preparedStatement.setString(7, student.getCNP());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Student updated successfully in the database.");
                } else {
                    System.out.println("No rows were updated. Student with CNP: " + student.getCNP() + " not found.");
                }
            }
        } catch (SQLException e) {
            showErrorAlert("Error updating student in the database.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAdd() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addAdministrator.fxml"));
            Parent root = fxmlLoader.load();

            // Pass the main controller to the child controller
            addAdministrator addColleagueController = fxmlLoader.getController();

            // Create a new Stage for the "Add Colleague" view
            Stage addColleagueStage = new Stage();
            addColleagueStage.setScene(new Scene(root));
            addColleagueStage.setTitle("Add Administrator");

            // Set the modality to APPLICATION_MODAL to make it a pop-up window
            addColleagueStage.initModality(Modality.APPLICATION_MODAL);

            // Show the "Add Colleague" window and wait for it to be closed before proceeding
            addColleagueStage.showAndWait();

            // Reload the table after adding a teacher
            tabel.setItems(getAdministrator(""));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    private void handleDelete() {
        SuperAdminH selectedColleague = tabel.getSelectionModel().getSelectedItem();

        if (selectedColleague != null) {
            if (showConfirmationAlert("Are you sure you want to delete this colleague?")) {
                deleteColleague(selectedColleague);
                tabel.getItems().remove(selectedColleague);
            }
        } else {
            showErrorAlert("Please select a colleague to delete.");
        }
    }




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

    public SuperAdmin() {

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

    public ObservableList<SuperAdminH> getAdministrator(String CNP) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<SuperAdminH> colegi = FXCollections.observableArrayList();

        try {

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println(UserCredentials.getUsername());
            PreparedStatement prep = connection.prepareStatement("select nume, prenume, CNP, IBAN, adresa, email from facultate.administrator where CNP like ? and email!=? ");
            prep.setString(1, '%' + CNP + '%');
            prep.setString(2, UserCredentials.getUsername());
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                SuperAdminH coleg = new SuperAdminH(rs.getString("nume"), rs.getString("prenume"), rs.getString("CNP"), rs.getString("IBAN"), rs.getString("adresa"), rs.getString("email"));
                colegi.add(coleg);
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }

        return colegi;

    }

    private boolean showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void deleteColleague(SuperAdminH colleague) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "DELETE FROM facultate.administrator WHERE CNP = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, colleague.getCNP());

                // Debugging print statements
                System.out.println("SQL Query: " + preparedStatement.toString());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Colleague deleted successfully from the database.");
                } else {
                    System.out.println("No rows were deleted. Colleague with CNP: " + colleague.getCNP() + " not found.");
                }
            }
        } catch (SQLException e) {
            showErrorAlert("Error deleting colleague from the database.");
            e.printStackTrace();
        }
    }



}
