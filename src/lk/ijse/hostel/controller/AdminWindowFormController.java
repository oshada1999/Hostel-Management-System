package lk.ijse.hostel.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Role;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AdminWindowFormController {

    @FXML
    private AnchorPane adminContext;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane dashboardContext;
    public void initialize(){
        setDateAndTime();
    }
    public void setDateAndTime(){
        Timeline time=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  "+"|"+"  HH:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }),new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    @FXML
    void logoutOnAction(MouseEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Logout?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                Navigation.navigate(Role.LOGOUT, adminContext);
            }

    }

    @FXML
    void manageRoomOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Role.ROOM,dashboardContext);
    }

    @FXML
    void onDashbordAction(ActionEvent event) throws IOException {
        Navigation.navigate(Role.ADMIN,adminContext);
    }

    @FXML
    void registrationOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Role.REGISTRATION,dashboardContext);
    }

    public void reserveRoomOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.RESERVATION,dashboardContext);
    }

    public void changeUserOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.USER,dashboardContext);
    }

    public void lessPaidStudentOnAction(ActionEvent actionEvent) {

    }
}
