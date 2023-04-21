package lk.ijse.hostel.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.ReservationService;
import lk.ijse.hostel.tm.LessDetailsTM;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AllStudentReservationFormController {

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
    private TextField txtSearch;

    private String searchText="";

    private final ReservationService reservationService = (ReservationService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.RESERVATION);

    public void initialize(){
        loadDataToTable(searchText);
        setCellValue();
        SearchListener();



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
        List<CustomDTO> lessPaidStudentList = reservationService.getRoomReserveStudentList();
        List<LessDetailsTM>remainingTmList=new ArrayList<>();
        for (CustomDTO c: lessPaidStudentList) {
            if (c.getName().contains(searchText)||c.getAddress().contains(searchText)){
                remainingTmList.add(new LessDetailsTM(c.getReID(),c.getRoID(),c.getStID(),c.getName(),c.getAddress(),c.getContact(),c.getDate(),c.getKeyMoney(),c.getLessAmount(),c.getStatus()));
            }

        }
        tblLessPaidStudent.setItems(FXCollections.observableArrayList(remainingTmList));
    }
}
