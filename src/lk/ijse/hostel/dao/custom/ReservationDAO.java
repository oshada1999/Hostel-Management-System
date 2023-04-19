package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;

import java.io.IOException;
import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    String generateNewId() throws IOException;
    boolean updateLessPayment(ReservationDTO dto) throws SQLException, ClassNotFoundException;
}
