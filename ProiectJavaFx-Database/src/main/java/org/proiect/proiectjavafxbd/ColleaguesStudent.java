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
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class ColleaguesStudent {
    @FXML
    private Button Calendar;

    @FXML
    private Button Students;


    @FXML
    private Button Gradebook;

    @FXML
    private Button AboutYou;

    @FXML
    private Button LogOut;


    @FXML
    private Button Teachers;
    @FXML
    private Button Chat;

    @FXML
    private Button Suggestions;
    @FXML
    private Button Leave;
    @FXML
    private Button Enroll;



    @FXML
    private void  handleSuggestionsButton(){switchScene("StudentSuggestions.fxml",1200,700,Suggestions);}

    @FXML
    private TableView<ColleagueStudent> tabel;

    @FXML
    private TableColumn<ColleagueStudent, String> nume;

    @FXML
    private TableColumn<ColleagueStudent, String> prenume;

    @FXML
    private TableColumn<ColleagueStudent, String> Departament;

    @FXML
    private Button cauta;

    @FXML
    private TextField searchBar;
    @FXML
    public void handleCauta() {
        tabel.setItems(getCollegues(searchBar.getText()));
        System.out.println((searchBar.getText()));
    }




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
        nume.setCellValueFactory(new PropertyValueFactory<ColleagueStudent, String>("nume"));
        prenume.setCellValueFactory(new PropertyValueFactory<ColleagueStudent, String>("prenume"));
        Departament.setCellValueFactory(new PropertyValueFactory<ColleagueStudent, String>("Departament"));
        tabel.setItems(getCollegues(""));

    }



    public ObservableList<ColleagueStudent> getCollegues(String Departament) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<ColleagueStudent> colegi = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println(UserCredentials.getUsername());
            PreparedStatement prep;

            prep = connection.prepareStatement("select nume, prenume, Departament from facultate.profesor where profesor.Departament like ? and email != ?");
            prep.setString(1, '%' + Departament + '%');
            prep.setString(2, UserCredentials.getUsername());

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                ColleagueStudent coleg = new ColleagueStudent(rs.getString("nume"), rs.getString("prenume"), rs.getString("Departament"));
                colegi.add(coleg);
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }

        return colegi;
    }

    @FXML
    private void handleEnroll() {
        ColleagueStudent colleague = getColleague();

        if (colleague != null) {
            Pair<Integer, Integer> professorAndSubjectIds = getProfessorAndSubjectIds(colleague);

            if (professorAndSubjectIds != null) {
                int studentId = getStudentId(UserCredentials.getUsername(), UserCredentials.getPassword());
                int professorId = professorAndSubjectIds.getKey();
                int subjectId = professorAndSubjectIds.getValue();

                // Check if the student is already enrolled
                int enrollmentStatus = isStudentEnrolled(studentId, professorId, subjectId);

                if (enrollmentStatus == 1) {
                    showErrorAlert("Student is already enrolled in the course.");
                } else if (enrollmentStatus == 0) {
                    enrollInCourse(studentId, professorId, subjectId);
                    // Perform any additional actions if needed
                } else {
                    showErrorAlert("Error checking enrollment status.");
                }
            } else {
                showErrorAlert("Professor or subject not found");
            }
        } else {
            showErrorAlert("Please select a colleague.");
        }
    }

    @FXML
    private void handleLeave() {
        ColleagueStudent colleague = getColleague();

        if (colleague != null) {
            Pair<Integer, Integer> professorAndSubjectIds = getProfessorAndSubjectIds(colleague);

            if (professorAndSubjectIds != null) {
                int studentId = getStudentId(UserCredentials.getUsername(), UserCredentials.getPassword());
                int professorId = professorAndSubjectIds.getKey();
                int subjectId = professorAndSubjectIds.getValue();

                // Verifică dacă studentul este înscris în curs
                int enrollmentStatus = isStudentEnrolled(studentId, professorId, subjectId);

                if (enrollmentStatus == 1) {
                    // Studentul este înscris, poate părăsi cursul
                    leaveCourse(studentId, professorId, subjectId);
                    // Perform any additional actions if needed
                } else if (enrollmentStatus == 0) {
                    // Studentul nu este înscris, afișează un mesaj de eroare
                    showErrorAlert("Student is not enrolled in the course.");
                } else {
                    showErrorAlert("Error checking enrollment status.");
                }
            } else {
                showErrorAlert("Professor or subject not found");
            }
        } else {
            showErrorAlert("Please select a colleague.");
        }
    }


    private ColleagueStudent getColleague() {
        return tabel.getSelectionModel().getSelectedItem();
    }

    private void enrollInCourse(int studentId, int professorId, int subjectId) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Call the InroleazaStudent stored procedure
            try (CallableStatement callableStatement = connection.prepareCall("{call InroleazaStudent(?, ?, ?)}")) {
                callableStatement.setInt(1, subjectId);
                callableStatement.setInt(2, professorId);
                callableStatement.setInt(3, studentId);

                // Execute the stored procedure
                callableStatement.execute();

                System.out.println("Student enrolled successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void leaveCourse(int studentId, int professorId, int subjectId) {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Call the StergeStudentDinCurs stored procedure
            try (CallableStatement callableStatement = connection.prepareCall("{call StergeStudentDinCurs(?, ?, ?)}")) {
                callableStatement.setInt(1, subjectId);
                callableStatement.setInt(2, professorId);
                callableStatement.setInt(3, studentId);

                // Execute the stored procedure
                callableStatement.execute();

                System.out.println("Student removed from the course successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
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


    private Pair<Integer, Integer> getProfessorAndSubjectIds(ColleagueStudent colleague) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT p.id AS idProfesor, ad.idMaterie FROM profesor p JOIN activitate_didactica ad ON p.id = ad.idProfesor WHERE p.nume = ? AND p.prenume = ? AND p.Departament = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, colleague.getNume());
                preparedStatement.setString(2, colleague.getPrenume());
                preparedStatement.setString(3, colleague.getDepartament());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int idProfesor = resultSet.getInt("idProfesor");
                    int idMaterie = resultSet.getInt("idMaterie");
                    return new Pair<>(idProfesor, idMaterie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        // Return null if an error occurs or if the professor is not found
        return null;
    }

    // Modify this method to check if the student is already enrolled
    private int isStudentEnrolled(int studentId, int professorId, int subjectId) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT COUNT(*) AS count FROM facultate.inscris_activitate_didactica WHERE idStudent = ? AND idActivitateDidactica IN " +
                    "(SELECT id FROM facultate.activitate_didactica WHERE idProfesor = ? AND idMaterie = ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, professorId);
                preparedStatement.setInt(3, subjectId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0 ? 1 : 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        // Return -1 if an error occurs
        return -1;
    }



}