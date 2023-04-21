package lk.ijse.hostel.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.tm.RoomTM;
import lk.ijse.hostel.tm.StudentTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentFormController {

    public TableColumn clmLName;
    public TableColumn clmFName;
    public JFXButton btnAdd;
    @FXML
    private AnchorPane studentManageContext;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmContactStatus;

    @FXML
    private TableColumn<?, ?> clmDOB;

    @FXML
    private TableColumn<?, ?> clmGender;

    @FXML
    private TableColumn<?, ?> clmOption;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXRadioButton rbtnMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton rbtnFemale;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXDatePicker txtDob;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXComboBox cmbcontact;

    private String searchText="";
    private Pattern idPattern;
    private Pattern phonePattern;
    private Pattern stringPattern;

    private final StudentService studentService = (StudentService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        cmbcontact.getItems().addAll("Work","Home");
        loadStudent(searchText);
        setCellValue();
        SearchListener();
        setListener();
        idPattern= Pattern.compile("[(S)(0-9)]{4,}$");
        phonePattern=Pattern.compile("[(0-9)]{10}$");
        stringPattern=Pattern.compile("[(A-Z,a-z)]{1,}$");
        btnAdd.setDisable(true);
    }
    private void setListener() {
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }
    private void setData(StudentTM newValue) {
        txtID.setText(newValue.getStudent_id());
        txtFName.setText(newValue.getFName());
        txtLName.setText(newValue.getLName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact());
        rbtnFemale.setSelected(newValue.getGender().equals("female"));
        rbtnMale.setSelected(newValue.getGender().equals("male"));
        txtDob.setValue(newValue.getDob());
        cmbcontact.setValue(newValue.getContact_status());
    }
    void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadStudent(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCellValue() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        clmFName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        clmLName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmContactStatus.setCellValueFactory(new PropertyValueFactory<>("contact_status"));
        clmDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void clear(){
        txtID.clear();
        txtAddress.clear();
        txtFName.clear();
        txtLName.clear();
        txtContact.clear();


    }

    public void loadStudent(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<StudentTM> tmList= FXCollections.observableArrayList();

        for (StudentDTO s:studentService.getAllStudent() ) {
            if (s.getStudent_id().contains(searchText)||s.getName().getF_name().contains(searchText)||s.getName().getL_name().contains(searchText)||s.getAddress().contains(searchText)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");

                StudentTM tm=new StudentTM(s.getStudent_id(),s.getName().getF_name(),s.getName().getL_name(),s.getAddress(),s.getContact().getPhone_Num(),s.getContact().getType(),s.getDob(),s.getGender(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Student ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = studentService.deleteStudent(s.getStudent_id());
                            if(isDelete) {
                                loadStudent(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblStudent.setItems(tmList);
    }
    @FXML
    void addNewStudentOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isId=idPattern.matcher(txtID.getText()).matches();
        boolean isFName=stringPattern.matcher(txtFName.getText()).matches();
        boolean isLName=stringPattern.matcher(txtLName.getText()).matches();
        boolean isAddress=stringPattern.matcher(txtAddress.getText()).matches();
        boolean isPhone=phonePattern.matcher(txtContact.getText()).matches();
        if (isId){
            if (isFName){
                if (isLName){
                    if (isAddress){
                        if (isPhone){
                            try {
                                boolean isAdded = studentService.addStudent(new StudentDTO(txtID.getText(), new Name_Format(txtFName.getText(), txtLName.getText()), txtAddress.getText(), new Phone_Number_Format(txtContact.getText(), cmbcontact.getSelectionModel().getSelectedItem().toString()), txtDob.getValue(), rbtnFemale.isSelected() ? "female" : "male"));
                                if (isAdded) {
                                    loadStudent(searchText);
                                    clear();
                                    new Alert(Alert.AlertType.CONFIRMATION, "Student Added!").show();
                                }else {
                                    txtID.setFocusColor(Paint.valueOf("Red"));
                                    txtID.requestFocus();
                                    new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
                                }
                            } catch (SQLException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                            }

                        }else {
                            txtContact.setFocusColor(Paint.valueOf("Red"));
                            txtContact.requestFocus();
                        }

                    }else {
                        txtAddress.setFocusColor(Paint.valueOf("Red"));
                        txtAddress.requestFocus();
                    }

                }else {
                    txtLName.setFocusColor(Paint.valueOf("Red"));
                    txtLName.requestFocus();
                }

            }else {
                txtFName.setFocusColor(Paint.valueOf("Red"));
                txtFName.requestFocus();
            }

        }else{
            txtID.setFocusColor(Paint.valueOf("Red"));
            txtID.requestFocus();
        }
    }

    @FXML
    void updateStudentOnAction(ActionEvent event) {
        boolean isId=idPattern.matcher(txtID.getText()).matches();
        boolean isFName=stringPattern.matcher(txtFName.getText()).matches();
        boolean isLName=stringPattern.matcher(txtLName.getText()).matches();
        boolean isAddress=stringPattern.matcher(txtAddress.getText()).matches();
        boolean isPhone=phonePattern.matcher(txtContact.getText()).matches();
        if (isId){
            if (isFName){
                if (isLName){
                    if (isAddress){
                        if (isPhone){
                                try {
                                    boolean isUpdate = studentService.updateStudent(new StudentDTO(txtID.getText(), new Name_Format(txtFName.getText(), txtLName.getText()), txtAddress.getText(), new Phone_Number_Format(txtContact.getText(), cmbcontact.getSelectionModel().getSelectedItem().toString()), txtDob.getValue(), rbtnFemale.isSelected() ? "female" : "male"));
                                    if(isUpdate) {
                                        loadStudent(searchText);
                                        clear();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Student Updated!").show();
                                    }
                                } catch (SQLException | ClassNotFoundException e) {
                                    new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                                }
                        }else {
                            txtContact.setFocusColor(Paint.valueOf("Red"));
                            txtContact.requestFocus();
                        }

                    }else {
                        txtAddress.setFocusColor(Paint.valueOf("Red"));
                        txtAddress.requestFocus();
                    }

                }else {
                    txtLName.setFocusColor(Paint.valueOf("Red"));
                    txtLName.requestFocus();
                }

            }else {
                txtFName.setFocusColor(Paint.valueOf("Red"));
                txtFName.requestFocus();
            }

        }else{
            txtID.setFocusColor(Paint.valueOf("Red"));
            txtID.requestFocus();
        }
    }

    public void cmbClickOnAction(ActionEvent actionEvent) {
        btnAdd.setDisable(false);
    }
}
