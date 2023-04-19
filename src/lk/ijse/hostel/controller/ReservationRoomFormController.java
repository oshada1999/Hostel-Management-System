package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.ReservationService;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.service.util.Convertor;
import lk.ijse.hostel.tm.ReservationTM;
import lk.ijse.hostel.tm.RoomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ReservationRoomFormController {

    public JFXComboBox cmbStatus;
    public Label lblRoomType;
    public Label lblStatus;

    public JFXDatePicker fromDatePicker;
    public Label lblReservationId;
    @FXML
    private AnchorPane reservationContext;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TableColumn<?, ?> clmReservationID;

    @FXML
    private TableColumn<?, ?> clmStudentID;

    @FXML
    private TableColumn<?, ?> clmRoomTypeID;

    @FXML
    private TableColumn<?, ?> clmStartDate;

    @FXML
    private TableColumn<?, ?> clmEndDate;

    @FXML
    private TableColumn<?, ?> clmPayingAmount;

    @FXML
    private TableColumn<?, ?> clmLessAmount;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private JFXTextField txtID;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXComboBox cmbStudentID;

    @FXML
    private JFXComboBox cmbRoomID;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblKeyMoney;

    @FXML
    private Label lblAvailableQty;

    @FXML
    private JFXDatePicker toDatePIcker;

    @FXML
    private JFXTextField txtPayingAmount;

    @FXML
    private Label lblLessAmount;
    private Pattern doublePattern;
    private String searchText = "";

    private final RoomService roomService = (RoomService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ROOM);
    private final StudentService studentService = (StudentService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    private final ReservationService reservationService = (ReservationService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.RESERVATION);
    private final Convertor convertor = new Convertor();

    public void initialize() throws SQLException, ClassNotFoundException, IOException {
        setIdCmbStudent();
        setIdRoom();
        loadReservation(searchText);
        setCellValue();
        SearchListener();
        setListener();
        setReservationId();

        doublePattern = Pattern.compile("^[1-9]{1}[(0-9,.)]{2,}$");

    }

    private void setReservationId() throws IOException {
        lblReservationId.setText(reservationService.getNewId());
    }

    private void setListener() {
        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                try {
                    setData(newValue);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setData(ReservationTM newValue) throws SQLException, ClassNotFoundException {
        lblReservationId.setText(newValue.getRes_id());
        cmbStudentID.setValue(newValue.getStId());
        cmbRoomID.setValue(newValue.getRoomID());
        lblStatus.setText(newValue.getStatus());
        txtPayingAmount.setText(String.valueOf(newValue.getPayingAmount()));
        fromDatePicker.setValue(newValue.getStartDate());
        toDatePIcker.setValue(newValue.getEndDate());
        setStudentDetails();
        setRoomDetails();

    }

    void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText = newValue;
                loadReservation(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCellValue() {
        clmReservationID.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        clmStudentID.setCellValueFactory(new PropertyValueFactory<>("stId"));
        clmRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        clmStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        clmEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        clmPayingAmount.setCellValueFactory(new PropertyValueFactory<>("payingAmount"));
        clmLessAmount.setCellValueFactory(new PropertyValueFactory<>("lessAmount"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void clear() {

        lblStudentName.setText("");
        txtPayingAmount.clear();
        txtSearch.clear();
        lblRoomType.setText("");
        lblKeyMoney.setText("");
        lblAvailableQty.setText("");
        lblLessAmount.setText("");
        lblStatus.setText("");

    }

    public void loadReservation(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<ReservationTM> tmList = FXCollections.observableArrayList();

        for (ReservationDTO r : reservationService.getAllReservation()) {
            if (r.getRes_id().contains(searchText) || r.getStatus().contains(searchText) || r.getStudent().getName().getF_name().contains(searchText)) {

                ReservationTM tm = new ReservationTM(r.getRes_id(), r.getStudent().getStudent_id(), r.getRoom().getRoom_type_id(), r.getStartDate(), r.getEndDate(), r.getPayingAmount(), r.getLessAmount(), r.getStatus());
                tmList.add(tm);

            }

        }
        tblReservation.setItems(tmList);
    }

    private void setIdRoom() throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> allRoom = roomService.getAllRoom();
        ObservableList<String> idList = FXCollections.observableArrayList();
        for (RoomDTO r : allRoom) {
            idList.add(r.getRoom_type_id());
        }
        cmbRoomID.setItems(idList);
    }


    void setIdCmbStudent() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudent = studentService.getAllStudent();
        ObservableList<String> idList = FXCollections.observableArrayList();
        for (StudentDTO s : allStudent) {
            idList.add(s.getStudent_id());
        }
        cmbStudentID.setItems(idList);

    }

    @FXML
    void addReservationOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isKeyMoney = doublePattern.matcher(txtPayingAmount.getText()).matches();
        if (isKeyMoney) {
            try {
                boolean isAdded = reservationService.addReservation(new ReservationDTO(lblReservationId.getText(), fromDatePicker.getValue(), toDatePIcker.getValue(), Double.parseDouble(txtPayingAmount.getText()), Double.parseDouble(lblLessAmount.getText()), lblStatus.getText(), convertor.toStudent(studentService.searchStudent(cmbStudentID.getSelectionModel().getSelectedItem().toString())), convertor.toRoom(roomService.searchRoom(cmbRoomID.getSelectionModel().getSelectedItem().toString()))));
                if (isAdded) {
                    loadReservation(searchText);
                    clear();
                    roomService.updateRoomQty(cmbRoomID.getSelectionModel().getSelectedItem().toString());
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation Added!").show();
                    setReservationId();

                } else {
                    txtID.setFocusColor(Paint.valueOf("Red"));
                    txtID.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            }

        } else {
            txtPayingAmount.setFocusColor(Paint.valueOf("Red"));
            txtPayingAmount.requestFocus();
        }

    }



    @FXML
    void updateReservationOnAction(ActionEvent event) {

    }
    public void setStudentDetails() throws SQLException, ClassNotFoundException {
        StudentDTO studentDTO = studentService.searchStudent(cmbStudentID.getSelectionModel().getSelectedItem().toString());
        Name_Format name = studentDTO.getName();
        lblStudentName.setText(name.getF_name());
    }

    public void cmbClickOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setStudentDetails();

    }
    public void setRoomDetails() throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomService.searchRoom(cmbRoomID.getSelectionModel().getSelectedItem().toString());
        lblRoomType.setText(roomDTO.getType());
        lblKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        lblAvailableQty.setText(String.valueOf(roomDTO.getQty()));
    }

    public void cmbRoomClickOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setRoomDetails();

    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isKeyMoney = doublePattern.matcher(txtPayingAmount.getText()).matches();
        if (isKeyMoney) {
            double keyMoney = Double.parseDouble(lblKeyMoney.getText());
            double paidAmount = Double.parseDouble(txtPayingAmount.getText());

            double lessAmount = keyMoney - paidAmount;

            lblLessAmount.setText(String.valueOf(lessAmount));
            if (keyMoney > paidAmount) {
                lblStatus.setText("Less Paid");
                lblStatus.setTextFill(Color.RED);
            } else {
                lblStatus.setText("Paid");
                lblStatus.setTextFill(Color.GREEN);
            }
        } else {
            txtPayingAmount.setFocusColor(Paint.valueOf("Red"));
            txtPayingAmount.requestFocus();
        }
    }
}
