package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.SupperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomService extends SupperService {
    boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    ArrayList<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    boolean updateRoomQty(String id) throws SQLException, ClassNotFoundException;

    RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException;
}
