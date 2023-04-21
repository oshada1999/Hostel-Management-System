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
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Role;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AdminWindowFormController {

    public Label lblNonAcFood;
    public Label lblNonAc;
    public Label lblAcFood;
    public Label lblAcRoom;
    public Label lblAcRoomAmount;
    public Label lblNonAcFoodAmount;
    public Label lblAcFoodAmount;
    public Label lblNonAcAmount;
    @FXML
    private AnchorPane adminContext;

    @FXML
    private Label lblTime;


    @FXML
    private AnchorPane dashboardContext;

    private final RoomService roomService = (RoomService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ROOM);

    public void initialize() throws SQLException, ClassNotFoundException {
        setDateAndTime();
        setAcRoom();
        setAcFoodRoom();
        setNonAcRoom();
        setNonAcFood();
    }

    private void setNonAcFood() throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomService.searchRoom("R004");
        lblNonAcFood.setText(String.valueOf(roomDTO.getQty()));
        lblNonAcFoodAmount.setText(String.valueOf(roomDTO.getKey_money()));
    }

    private void setNonAcRoom() throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomService.searchRoom("R003");
        lblNonAc.setText(String.valueOf(roomDTO.getQty()));
        lblNonAcAmount.setText(String.valueOf(roomDTO.getKey_money()));
    }

    private void setAcFoodRoom() throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomService.searchRoom("R002");
        lblAcFood.setText(String.valueOf(roomDTO.getQty()));
        lblAcFoodAmount.setText(String.valueOf(roomDTO.getKey_money()));
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

    private void setAcRoom() throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomService.searchRoom("R001");
        lblAcRoom.setText(String.valueOf(roomDTO.getQty()));
        lblAcRoomAmount.setText(String.valueOf(roomDTO.getKey_money()));
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

    public void lessPaidStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.LESS,dashboardContext);
    }

    public void allStudentReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ALLRESERVATION,dashboardContext);
    }
}
