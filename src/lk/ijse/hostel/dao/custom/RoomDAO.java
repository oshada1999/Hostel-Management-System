package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.dao.SupperDAO;
import lk.ijse.hostel.entity.Room;

import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<Room,String> {
    boolean updateRoomQty(String id) throws SQLException, ClassNotFoundException;
}
