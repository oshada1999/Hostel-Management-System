package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.service.custom.UserService;
import lk.ijse.hostel.service.util.Convertor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        if (userDAO.existByPk(userDTO.getUser_id())){
            return false;
        }
        User user = convertor.toUser(userDTO);
        return userDAO.save(user);
    }

    @Override
    public String getNewId() throws IOException {
        return userDAO.generateNewId();
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserDTO> allUser=new ArrayList<>();

        for (User user:all) {
            allUser.add(convertor.formUser(user));
        }
        return allUser;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return false;
    }
    public boolean updateUserName(String id,String username,String password) throws SQLException, ClassNotFoundException {
       return userDAO.updateUserName(id,username,password);

    }

    @Override
    public UserDTO searchUser(String id) throws SQLException, ClassNotFoundException {
       User user= userDAO.search(id);
        return convertor.formUser(user);
    }
}
