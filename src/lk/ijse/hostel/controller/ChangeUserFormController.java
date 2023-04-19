package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.UserService;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Role;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ChangeUserFormController {

    public JFXComboBox cmbUserID;
    public Label lblOldUserName;
    @FXML
    private AnchorPane userManageContext;

    @FXML
    private JFXTextField txtOldUsername;

    @FXML
    private JFXPasswordField txtOldPassword;

    @FXML
    private JFXTextField txtNewUsername;

    private Pattern passwordPattern;

    @FXML
    private JFXPasswordField txtNewPassword;
    private final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    public void initialize() throws SQLException, ClassNotFoundException {
       // getAllUsers();
        UserDTO userDTO=LoginFormController.logUserDTO;
        lblOldUserName.setText(userDTO.getUserName());
        passwordPattern=Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    }

    private void getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUser = userService.getAllUser();
        ObservableList<String> idList = FXCollections.observableArrayList();
        for (UserDTO u:allUser) {
            idList.add(u.getUser_id());
        }
        cmbUserID.setItems(idList);
    }

    @FXML
    void addNewUserOnAction(ActionEvent event) {

    }

    @FXML
    void updateUserOnAction(ActionEvent event) {
        boolean isIdOld=passwordPattern.matcher(txtOldPassword.getText()).matches();
        boolean isIdNew=passwordPattern.matcher(txtNewPassword.getText()).matches();
        if (isIdOld){
            if (isIdNew){
                try {
                    UserDTO userDTO = userService.searchUser(lblOldUserName.getText());
                    if (userDTO.getPassword().equals(txtOldPassword.getText())) {
                        String newUsername=txtNewUsername.getText();
                        boolean isUpdate = userService.updateUserName(userDTO.getUser_id(), txtNewUsername.getText(), txtNewPassword.getText());
                        if (isUpdate){
                            lblOldUserName.setText(newUsername);
                            LoginFormController.logUserDTO.setUserName(newUsername);

                            new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                        }

                    }else {
                        new Alert(Alert.AlertType.ERROR, "wrong password").show();
                        txtOldPassword.setFocusColor(Paint.valueOf("Red"));
                        txtOldPassword.requestFocus();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else {
                txtNewPassword.setFocusColor(Paint.valueOf("Red"));
                txtNewPassword.requestFocus();
            }

        }else {
            txtOldPassword.setFocusColor(Paint.valueOf("Red"));
            txtOldPassword.requestFocus();
        }

    }

}
