package lk.ijse.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static AnchorPane pane;

    public static void navigate(Role role,AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (role){
            case ADMIN:
                iniUI("AdminWindowForm.fxml");
                break;
            case LOGOUT:
                iniUI("LoginForm.fxml");
                break;
            case ROOM:
                iniUI("RoomForm.fxml");
                break;
                case REGISTRATION:
                iniUI("StudentForm.fxml");
                break;
            case RESERVATION:
                iniUI("ReservationRoomForm.fxml");
                break;
            case USER:
                iniUI("ChangeUserForm.fxml");
                break;
                case LESS:
                iniUI("LessPaidForm.fxml");
                break;

        }
    }
    public static void iniUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hostel/view/"+location)));
    }
}
