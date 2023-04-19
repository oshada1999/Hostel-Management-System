package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.ReservationService;
import lk.ijse.hostel.tm.LessDetailsTM;
import lk.ijse.hostel.tm.RoomTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LessPaidFormController {

    public Label lblReservationId;
    public Label lblStudentName;
    public Label lblLessAmount;
    public Label lblStatus;
    @FXML
    private AnchorPane RoomManageContext;

    @FXML
    private TableView<LessDetailsTM> tblLessPaidStudent;

    @FXML
    private TableColumn<?, ?> clmReId;

    @FXML
    private TableColumn<?, ?> clmRoId;

    @FXML
    private TableColumn<?, ?> clmStuID;

    @FXML
    private TableColumn<?, ?> clmStuName;

    @FXML
    private TableColumn<?, ?> clmAdress;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmEndDate;

    @FXML
    private TableColumn<?, ?> clmKeyMoney;

    @FXML
    private TableColumn<?, ?> clmLessAmount;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtPayAmount;

    @FXML
    private TextField txtSearch;

    private String searchText="";
    private Pattern doublePattern;

    public double roomKeyMoney;
    public double paidMoney;

    private final ReservationService reservationService = (ReservationService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.RESERVATION);

    public void initialize(){
        loadDataToTable(searchText);
        setCellValue();
        SearchListener();
        setListener();
        doublePattern = Pattern.compile("^[1-9]{1}[(0-9,.)]{2,}$");

    }
    private void setListener() {
        tblLessPaidStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }
    private void setData(LessDetailsTM newValue) {
        lblReservationId.setText(newValue.getReID());
        lblStudentName.setText(newValue.getName());
        lblLessAmount.setText(String.valueOf(newValue.getLessAmount()));
        lblStatus.setText(newValue.getStatus());
        roomKeyMoney=newValue.getKeyMoney();
    }
    void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            loadDataToTable(searchText);
        });
    }

    private void setCellValue() {
        clmReId.setCellValueFactory(new PropertyValueFactory<>("reID"));
        clmRoId.setCellValueFactory(new PropertyValueFactory<>("roID"));
        clmStuID.setCellValueFactory(new PropertyValueFactory<>("stID"));
        clmStuName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmEndDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        clmLessAmount.setCellValueFactory(new PropertyValueFactory<>("lessAmount"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataToTable(String searchText) {
        List<CustomDTO> lessPaidStudentList = reservationService.getLessPaidStudentList();
        List<LessDetailsTM>remainingTmList=new ArrayList<>();
        for (CustomDTO c: lessPaidStudentList) {
            if (c.getName().contains(searchText)||c.getAddress().contains(searchText)){
                remainingTmList.add(new LessDetailsTM(c.getReID(),c.getRoID(),c.getStID(),c.getName(),c.getAddress(),c.getContact(),c.getDate(),c.getKeyMoney(),c.getLessAmount(),c.getStatus()));
            }

        }
        tblLessPaidStudent.setItems(FXCollections.observableArrayList(remainingTmList));
    }

    @FXML
    void addNewPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void updateRoomOnAction(ActionEvent event) {

    }

    public void updatePaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isUpdate = reservationService.updateLessPayment(new ReservationDTO(lblReservationId.getText(), paidMoney, Double.parseDouble(lblLessAmount.getText()), lblStatus.getText()));
        if (isUpdate){
            loadDataToTable(searchText);
            new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isKeyMoney = doublePattern.matcher(txtPayAmount.getText()).matches();
        if (isKeyMoney) {
            double lessAmount = Double.parseDouble(lblLessAmount.getText());
            double paidAmount = Double.parseDouble(txtPayAmount.getText());
             paidMoney = (roomKeyMoney - lessAmount)+paidAmount;
            double lesskeyMoney = lessAmount - paidAmount;
            /*double lessMoney=roomKeyMoney-keyMoney;*/
            lblLessAmount.setText(String.valueOf(lesskeyMoney));
            if (lesskeyMoney > 0) {
                lblStatus.setText("Less Paid");
                lblStatus.setTextFill(Color.RED);
            } else {
                lblStatus.setText("Paid");
                lblStatus.setTextFill(Color.GREEN);
            }
        } else {
            txtPayAmount.setFocusColor(Paint.valueOf("Red"));
            txtPayAmount.requestFocus();
        }
    }

}
