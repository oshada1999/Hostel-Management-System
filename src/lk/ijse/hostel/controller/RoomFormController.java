package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.tm.RoomTM;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.awt.SystemColor.text;
import static org.jboss.logging.NDC.clear;

public class RoomFormController {

    public JFXButton btnAdd;
    @FXML
    private AnchorPane RoomManageContext;

    @FXML
    private TableView<RoomTM> tblRoom;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmType;

    @FXML
    private TableColumn<?, ?> clmKeyMony;

    @FXML
    private TableColumn<?, ?> clmRoomQty;

    @FXML
    private TableColumn<?, ?> clmOption;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtRoomQTY;

    @FXML
    private JFXTextField txtKeyMony;

    @FXML
    private TextField txtSearch;
    private String searchText="";
    private Pattern idPattern;
    private Pattern integerPattern;
    private Pattern doublePattern;

    @FXML
    private JFXComboBox cmbType;

    private final RoomService roomService = (RoomService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ROOM);

    public void initialize() throws SQLException, ClassNotFoundException {
        cmbType.getItems().addAll("AC","Non-AC","AC / Food","Non-AC / Food");
        loadRooms(searchText);
        setCellValue();
        SearchListener();
        setListener();
        idPattern=Pattern.compile("[(R)(0-9)]{4,}$");
        integerPattern=Pattern.compile("^[1-9]{1}[0-9]*$");
        doublePattern=Pattern.compile("^[1-9]{1}[(0-9,.)]{2,}$");
        //btnAdd.setDisable(true);

    }
    private void setListener() {
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }
    private void setData(RoomTM newValue) {
        txtID.setText(newValue.getRoom_type_id());
        txtKeyMony.setText(String.valueOf(newValue.getKey_money()));
        txtRoomQTY.setText(String.valueOf(newValue.getQty()));
        cmbType.setValue(newValue.getType());
    }
    void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadRooms(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCellValue() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmKeyMony.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        clmRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        //clmOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void clear(){
        txtID.clear();
        txtKeyMony.clear();
        txtRoomQTY.clear();

    }

    public void loadRooms(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<RoomTM> tmList= FXCollections.observableArrayList();

        for (RoomDTO r:roomService.getAllRoom() ) {
            if (r.getRoom_type_id().contains(searchText)||r.getType().contains(searchText)){
               /* Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");*/
                RoomTM tm=new RoomTM(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()/*,btn*/);
                tmList.add(tm);

               /* btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Room ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = roomService.deleteRoom(r.getRoom_type_id());
                            if(isDelete) {
                                loadRooms(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });*/
            }

        }
        tblRoom.setItems(tmList);
    }

    /*@FXML
    void addNewRoomOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isId=idPattern.matcher(txtID.getText()).matches();
        boolean isKeyMoney=doublePattern.matcher(txtKeyMony.getText()).matches();
        boolean isQty=integerPattern.matcher(txtRoomQTY.getText()).matches();

        if (isId){
            if (isKeyMoney){
                if (isQty){
                    try {
                        boolean isAdded = roomService.addRoom(new RoomDTO(txtID.getText(), cmbType.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(txtKeyMony.getText()), Integer.parseInt(txtRoomQTY.getText())));
                        if (isAdded) {
                            loadRooms(searchText);
                            clear();
                            new Alert(Alert.AlertType.CONFIRMATION, "Room Added!").show();
                        }else {
                            txtID.setFocusColor(Paint.valueOf("Red"));
                            txtID.requestFocus();
                            new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                    }
                }else{
                    txtRoomQTY.setFocusColor(Paint.valueOf("Red"));
                    txtRoomQTY.requestFocus();
                }
            }else {
                txtKeyMony.setFocusColor(Paint.valueOf("Red"));
                txtKeyMony.requestFocus();
            }
        }else {
            txtID.setFocusColor(Paint.valueOf("Red"));
            txtID.requestFocus();
        }


    }*/

    @FXML
    void updateRoomOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isId=idPattern.matcher(txtID.getText()).matches();
        boolean isKeyMoney=doublePattern.matcher(txtKeyMony.getText()).matches();
        boolean isQty=integerPattern.matcher(txtRoomQTY.getText()).matches();
        if (isId){
            if (isKeyMoney){
                if (isQty){
                    try {
                        boolean isUpdate = roomService.updateRoom(new RoomDTO(txtID.getText(), cmbType.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(txtKeyMony.getText()), Integer.parseInt(txtRoomQTY.getText())));
                        if(isUpdate) {
                            loadRooms(searchText);
                            clear();
                            new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                    }

                }else{
                    txtRoomQTY.setFocusColor(Paint.valueOf("Red"));
                    txtRoomQTY.requestFocus();
                }
            }else {
                txtKeyMony.setFocusColor(Paint.valueOf("Red"));
                txtKeyMony.requestFocus();
            }
        }else {
            txtID.setFocusColor(Paint.valueOf("Red"));
            txtID.requestFocus();
        }
    }

    public void cmbClickOnAction(ActionEvent actionEvent) {
        btnAdd.setDisable(false);
    }
}
