package org.proiect.proiectjavafxbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CalendarControllerTeacher implements Initializable {

    private final Map<ZonedDateTime, List<CalendarActivityTeacher>> calendarActivitiesMap = new HashMap<>();
    private ZonedDateTime dateFocus;
    private ZonedDateTime today;

    public int dayClick = -1;
    private HashMap<String, String[]> activties_db = new HashMap<>();


    public HashMap<Integer, String[]> activitati = new HashMap<>();

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private GridPane calendar;

    private Stage eventPopupStage;
    private EventPopupControllerTeacher eventPopupController;

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

    private void updateCalendar() {
        activties_db = showTeacherActivities(getProfesorId(UserCredentials.getUsername(), UserCredentials.getPassword()));
        System.out.println(activties_db);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        updateCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        updateCalendar();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activties_db = showTeacherActivities(getProfesorId(UserCredentials.getUsername(), UserCredentials.getPassword()));
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();


        drawCalendar();
    }
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

    private HashMap<String,String[]> showTeacherActivities(int profesorId) {
        String url = "jdbc:mysql://localhost:3306/facultate";
        String dbUsername = "root";
        String dbPassword = "20072003Robert";
        ObservableList<ActivitiesH> date = FXCollections.observableArrayList();
        HashMap<String, String[]> data=new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {

            // Setarea parametrilor pentru procedură
            try (CallableStatement statement = connection.prepareCall("{CALL facultate.ActivitatiProfesor(?)}")) {
                statement.setInt(1, profesorId);

                // Executarea procedurii
                ResultSet resultSet = statement.executeQuery();

                // Afișarea rezultatelor în tabel
                //
                while (resultSet.next()) {
                    String[] date_parts = resultSet.getString("inceputActivitate").split(" ");
                    String[] date_parts_final = resultSet.getString("sfarsitActivitate").split(" ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate localDate = LocalDate.parse(date_parts[0], formatter);

                    String[] value = new String[]{date_parts[0], date_parts[1], resultSet.getString("nume_activitate"), date_parts_final[1]};
                    System.out.println("Month value is "+localDate.getMonthValue());
                    data.put(localDate.getMonthValue() + "-" + localDate.getDayOfMonth(), value);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratează excepția după nevoie
        }

        return data;
    }

    public void saveEvent(String eventName, String eventTime) {
        closeEventPopup();

        if (dayClick == -1) {
            System.err.println("No Click");
            return;
        }

        int hour;
        try {
            hour = Integer.parseInt(eventTime);
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer format for event time");
            System.err.println(eventTime);
            return;
        }

        ZonedDateTime time = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), dayClick, 0, 0, 0, 0, dateFocus.getZone());
        ZonedDateTime time2 = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), dayClick, hour, 0, 0, 0, dateFocus.getZone());
        CalendarActivityTeacher newEvent = new CalendarActivityTeacher(time2, eventName, 0);

        List<CalendarActivityTeacher> eventsForDay = calendarActivitiesMap.get(time);
        if (eventsForDay == null) {
            eventsForDay = new ArrayList<>();
            eventsForDay.add(newEvent);
            calendarActivitiesMap.put(time, eventsForDay);
        } else {
            eventsForDay.add(newEvent);
            calendarActivitiesMap.put(time, eventsForDay);
        }


        dateFocus = time; // Update dateFocus to the selected day
        updateCalendar();
        closeEventPopup();
    }

    public void showEventPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventPopupTeacher.fxml"));
            Parent root = loader.load();

            eventPopupStage = new Stage();
            eventPopupStage.initModality(Modality.APPLICATION_MODAL);
            eventPopupStage.setTitle("Event Details");
            eventPopupStage.setScene(new Scene(root));

            eventPopupController = loader.getController();
            eventPopupController.setCalendarController(this);

            eventPopupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeEventPopup() {
        if (eventPopupStage != null) {
            eventPopupStage.close();
        }
    }

    private void drawCalendar() {
        calendar.getChildren().clear();
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        ZonedDateTime startOfMonth = dateFocus.withDayOfMonth(1);
        startOfMonth.withHour(0).withMinute(0).withSecond(0).withNano(0);
        int dateOffset = startOfMonth.getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);

                stackPane.setOnMouseClicked(event -> {
                    dayClick = calculatedDate - dateOffset;
                    showEventPopup();
                });
                int currentDate=-1;
                if (calculatedDate > dateOffset) {
                    currentDate = calculatedDate - dateOffset;
                    int lastDayOfMonth = startOfMonth.plusMonths(1).minusDays(1).getDayOfMonth();

                    if (currentDate <= lastDayOfMonth) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        ZonedDateTime currentDay = startOfMonth.plusDays(currentDate - 1);
                        List<CalendarActivityTeacher> calendarActivities = calendarActivitiesMap.get(currentDay);

                        System.err.println(currentDay);

                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }

                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                System.out.println("Luna pentru care desenam este "+dateFocus.getMonthValue());
                draw_from_db(stackPane, dateFocus.getMonthValue(),currentDate);
                calendar.add(stackPane, j, i);
            }
        }
    }
    public void draw_from_db(StackPane pane, int month, int day){
        System.out.println("Ziua este: "+day + ", iar luna este "+month);

        if (activties_db.get(month + "-" + day) != null){
            System.out.println("Avem activtate in ziua de "+day);
            System.out.println(activties_db.get(month + "-" + day)[2]);

            Label label = new Label(" "+activties_db.get(month + "-" + day)[2] + " " + activties_db.get(month + "-" + day)[1]);
            label.setTextFill(Color.RED);
            label.setStyle("-fx-font-size: 8");

            pane.getChildren().add(label);

        }

    }
    private void createCalendarActivity(List<CalendarActivityTeacher> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }
}