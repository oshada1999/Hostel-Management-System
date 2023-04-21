package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {
    public boolean updateUserName(String id,String username,String password) throws SQLException, ClassNotFoundException;
    String generateNewId() throws IOException;
}
