package lk.ijse.hostel.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(dto);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Student> list=new ArrayList<>();
        try {
            String hql="From Student";
            List<Student> studentList =session.createQuery(hql).list();
            for (Student s :
                    studentList) {
                list.add(new Student(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact(),s.getDob(),s.getGender()));


            }

        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = session.get(Student.class, s);
            session.delete(student);
            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(dto);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();


        try {
            return session.get(Student.class, s);

        }catch (Exception e){
            System.out.println(e);
        }finally {
            session.close();
        }
        return null;

    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {

            String hql="From Student WHERE student_id = : id";
            Query query = session.createQuery(hql);
            query.setParameter("id", pk);
            Student student = (Student) query.uniqueResult();
            if (student.getStudent_id().equalsIgnoreCase(pk)){
                return true;
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public ObservableList<String> studentIdList() throws IOException {
        return null;
    }
}
