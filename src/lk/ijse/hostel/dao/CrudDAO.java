package lk.ijse.hostel.dao;

import lk.ijse.hostel.entity.SupperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID>extends SupperDAO {
    boolean save(T dto) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    T search(ID id) throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;

    boolean existByPk(ID pk) ;

}
