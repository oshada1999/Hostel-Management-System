package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.service.util.Convertor;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        if (roomDAO.existByPk(roomDTO.getRoom_type_id())){
            return false;
        }
        Room room = convertor.toRoom(roomDTO);
       return roomDAO.save(room);
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRooms=new ArrayList<>();

        for (Room room:all) {
            allRooms.add(convertor.fromRoom(room));
        }
        return allRooms;
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        Room room = convertor.toRoom(roomDTO);
        return roomDAO.update(room);
    }

    @Override
    public boolean updateRoomQty(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.updateRoomQty(id);
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room room = roomDAO.search(id);
        return convertor.fromRoom(room);
    }
}
