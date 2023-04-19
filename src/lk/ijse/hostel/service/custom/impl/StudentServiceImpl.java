package lk.ijse.hostel.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.service.util.Convertor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        if (studentDAO.existByPk(studentDTO.getStudent_id())){
            return false;
        }
        Student student = convertor.toStudent(studentDTO);
        return studentDAO.save(student);
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent=new ArrayList<>();

        for (Student student:all) {
            allStudent.add(convertor.fromStudent(student));
        }
        return allStudent;
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        Student student=convertor.toStudent(studentDTO);
        return studentDAO.update(student);
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student search = studentDAO.search(id);
        return convertor.fromStudent(search);

    }

    @Override
    public ObservableList<String> getAllRoomTypeIds() throws IOException {
        return null;
    }
}
