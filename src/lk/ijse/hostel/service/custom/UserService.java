package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.SupperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService extends SupperService {
    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;

    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;

    boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    public boolean updateUserName(String id,String username,String password) throws SQLException, ClassNotFoundException;

    UserDTO searchUser(String id) throws SQLException, ClassNotFoundException;
}
