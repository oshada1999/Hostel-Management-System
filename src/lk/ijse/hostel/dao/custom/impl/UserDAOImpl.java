package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.service.util.Convertor;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final Convertor convertor=new Convertor();
    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return true;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<User> list=new ArrayList<>();
        try {
            String hql="From User";
            List<User> userList =session.createQuery(hql).list();
            for (User u :
                    userList) {
                list.add(new User(u.getUser_id(),u.getUserName(),u.getName(),u.getPassword(),u.getPhoneNumber()));


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
        return false;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, s);
        System.out.println(user.getUserName());
        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public boolean updateUserName(String id,String username,String password) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("DAO"+ username);
        System.out.println(password);
        System.out.println(id);

        String hql="UPDATE User u set u.userName =:userName, u.password=:password WHERE u.user_id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("userID",id);
        query.setParameter("userName",username);
        query.setParameter("password",password);

        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i>0;
    }
}
