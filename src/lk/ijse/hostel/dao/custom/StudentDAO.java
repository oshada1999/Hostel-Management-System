package lk.ijse.hostel.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

import java.io.IOException;

public interface StudentDAO extends CrudDAO<Student,String> {
    ObservableList<String> studentIdList() throws IOException;
}
