package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.UserService;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Role;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane loginContext;
    public static UserDTO logUserDTO;

    private final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    public JFXButton btnPassword;
    public JFXTextField txtShowPass;

    public void initialize(){
        txtShowPass.setVisible(false);
    }

    public void loginOnAction(ActionEvent actionEvent) {
        String userName=txtUsername.getText();
        String password=txtPassword.getText();

        try {
            UserDTO userDTO = userService.searchUser(userName);
            logUserDTO=userDTO;
            if (userDTO.getPassword().equals(password)) {
                Navigation.navigate(Role.ADMIN,loginContext);
            }else {
                new Alert(Alert.AlertType.ERROR, "wrong password").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        if (btnPassword.getText().equals("Show")){
            txtPassword.setVisible(false);
            txtShowPass.setVisible(true);
            txtShowPass.setText(txtPassword.getText());
            btnPassword.setText("Hide");
        }else {
            txtPassword.setVisible(true);
            txtShowPass.setVisible(false);
            txtPassword.setText(txtShowPass.getText());
            btnPassword.setText("Show");
        }

    }
}
