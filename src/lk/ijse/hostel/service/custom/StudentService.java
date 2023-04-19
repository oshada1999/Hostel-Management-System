package lk.ijse.hostel.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.SupperService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentService extends SupperService {
    boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;

    ObservableList<String> getAllRoomTypeIds() throws IOException;
}
