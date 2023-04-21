package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.SupperService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationService extends SupperService {
    boolean addReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException;

    String getNewId() throws IOException;

    boolean deleteReservation(String id) throws SQLException, ClassNotFoundException;

    boolean updateReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    boolean updateLessPayment(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException;

    public List<CustomDTO> getLessPaidStudentList();

    List<CustomDTO> getRoomReserveStudentList();
}
