package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
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
    public Label lblAdmin;
    public JFXTextField txtFName;
    public JFXTextField txtLName;
    public JFXTextField txtUserName;
    public JFXTextField txtPhone;
    public JFXComboBox<String> cmbPhone;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtAdminPass;
    public Label lblUserID;
    public Label lblAdminUserName;
    public JFXButton btnAddNewUser;
    public JFXButton btnChPassword;
    public JFXButton btnAdminPassword;
    public JFXButton btnNewPassword;
    public JFXTextField txtnewPass;
    public JFXButton btnOldPassword;
    public JFXTextField txtOldPass;
    public JFXTextField txtAdminPassShow;
    public JFXTextField txtNewShowPass;
    @FXML
    private AnchorPane userManageContext;

    @FXML
    private JFXTextField txtOldUsername;

    @FXML
    private JFXPasswordField txtOldPassword;

    @FXML
    private JFXTextField txtNewUsername;

    private Pattern passwordPattern;
    private Pattern stringPattern;
    private Pattern phonePattern;



    @FXML
    private JFXPasswordField txtNewPassword;
    private final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    public void initialize() throws SQLException, ClassNotFoundException, IOException {
       // getAllUsers();
        cmbPhone.getItems().addAll("Work","Home");
        lblUserID.setText(userService.getNewId());
        UserDTO userDTO=LoginFormController.logUserDTO;
        lblOldUserName.setText(userDTO.getUserName());
        passwordPattern=Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
        stringPattern=Pattern.compile("[(A-Z,a-z)]{1,}$");
        phonePattern=Pattern.compile("[(0-9)]{10}$");

        btnAddNewUser.setDisable(true);
        txtnewPass.setVisible(false);
        txtOldPass.setVisible(false);
        txtAdminPassShow.setVisible(false);
        txtNewShowPass.setVisible(false);

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

                            new Alert(Alert.AlertType.CONFIRMATION, "User Updated!").show();
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
    private void clear() {
        txtAdminPass.clear();
        txtPassword.clear();
        txtFName.clear();
        txtLName.clear();
        txtPhone.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        boolean isAdmin=passwordPattern.matcher(txtAdminPass.getText()).matches();
        boolean isnewPass=passwordPattern.matcher(txtPassword.getText()).matches();
        boolean isFName=stringPattern.matcher(txtFName.getText()).matches();
        boolean isLName=stringPattern.matcher(txtLName.getText()).matches();
        //boolean isUserName=stringPattern.matcher(txtLName.getText()).matches();
        boolean isPhone=phonePattern.matcher(txtPhone.getText()).matches();

            if (isAdmin){
                if (isnewPass){
                    if (isFName){
                        if (isLName){
                            if (isPhone){

                                try {
                                    UserDTO userDTO = userService.searchUser("Admin");
                                    System.out.println(userDTO);
                                    if (userDTO.getPassword().equals(txtAdminPass.getText())) {

                                        boolean isAdded = userService.addUser(new UserDTO(lblUserID.getText(),txtUserName.getText(),new Name_Format(txtFName.getText(),txtLName.getText()),txtPassword.getText(),new Phone_Number_Format(txtPhone.getText(),cmbPhone.getSelectionModel().getSelectedItem().toString())));
                                        if (isAdded){
                                            clear();
                                            lblUserID.setText(userService.getNewId());
                                            new Alert(Alert.AlertType.CONFIRMATION, "User Added!").show();
                                        }

                                    }else {
                                        new Alert(Alert.AlertType.ERROR, "wrong password").show();
                                        txtAdminPass.setFocusColor(Paint.valueOf("Red"));
                                        txtAdminPass.requestFocus();
                                    }
                                } catch (SQLException | ClassNotFoundException | IOException e) {
                                    e.printStackTrace();
                                }

                            }else {
                                txtPhone.setFocusColor(Paint.valueOf("Red"));
                                txtPhone.requestFocus();
                            }

                        }else {
                            txtLName.setFocusColor(Paint.valueOf("Red"));
                            txtLName.requestFocus();
                        }

                    }else {
                        txtFName.setFocusColor(Paint.valueOf("Red"));
                        txtFName.requestFocus();
                    }

                }else {
                    txtPassword.setFocusColor(Paint.valueOf("Red"));
                    txtPassword.requestFocus();
                }

            }else {
                txtAdminPass.setFocusColor(Paint.valueOf("Red"));
                txtAdminPass.requestFocus();
            }
        }

    public void cmbClickOnAction(ActionEvent actionEvent) {
        btnAddNewUser.setDisable(false);
    }

    public void changePasswordOnAction(ActionEvent actionEvent) {
        if (btnChPassword.getText().equals("Show")){
            txtNewPassword.setVisible(false);
            txtnewPass.setVisible(true);
            txtnewPass.setText(txtNewPassword.getText());
            btnChPassword.setText("Hide");
        }else {
            txtNewPassword.setVisible(true);
            txtnewPass.setVisible(false);
            txtNewPassword.setText(txtnewPass.getText());
            btnChPassword.setText("Show");
        }
    }

    public void adminPasswordOnAction(ActionEvent actionEvent) {
        if (btnAdminPassword.getText().equals("Show")){
            txtAdminPass.setVisible(false);
            txtAdminPassShow.setVisible(true);
            txtAdminPassShow.setText(txtAdminPass.getText());
            btnAdminPassword.setText("Hide");
        }else {
            txtAdminPass.setVisible(true);
            txtAdminPassShow.setVisible(false);
            txtAdminPass.setText(txtAdminPassShow.getText());
            btnAdminPassword.setText("Show");
        }
    }

    public void newPasswordOnAction(ActionEvent actionEvent) {
        if (btnNewPassword.getText().equals("Show")){
            txtPassword.setVisible(false);
            txtNewShowPass.setVisible(true);
            txtNewShowPass.setText(txtPassword.getText());
            btnNewPassword.setText("Hide");
        }else {
            txtPassword.setVisible(true);
            txtNewShowPass.setVisible(false);
            txtPassword.setText(txtNewShowPass.getText());
            btnNewPassword.setText("Show");
        }
    }

    public void oldPasswordOnAction(ActionEvent actionEvent) {
        if (btnOldPassword.getText().equals("Show")){
            txtOldPassword.setVisible(false);
            txtOldPass.setVisible(true);
            txtOldPass.setText(txtOldPassword.getText());
            btnOldPassword.setText("Hide");
        }else {
            txtOldPassword.setVisible(true);
            txtOldPass.setVisible(false);
            txtOldPassword.setText(txtOldPass.getText());
            btnOldPassword.setText("Show");
        }
    }
}
