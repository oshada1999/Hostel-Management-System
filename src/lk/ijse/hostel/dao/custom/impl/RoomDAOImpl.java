package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room dto) throws SQLException, ClassNotFoundException {
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
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
            ArrayList<Room> list=new ArrayList<>();
        try {
            String hql="From Room";
            List<Room> roomList =session.createQuery(hql).list();
            for (Room r :
                    roomList) {
               list.add(new Room(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));


            }

        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Room room = session.load(Room.class, s);
            session.delete(room);
            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean update(Room dto) throws SQLException, ClassNotFoundException {
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
    public Room search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Room.class, s);

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

            String hql="From Room WHERE room_type_id = : id";
            Query query = session.createQuery(hql);
            query.setParameter("id", pk);
            Room room = (Room) query.uniqueResult();
            if (room.getRoom_type_id().equalsIgnoreCase(pk)){
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
    public boolean updateRoomQty(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Room set qty = qty-1 WHERE room_type_id = :room_id";
        Query query = session.createQuery(hql);
        query.setParameter("room_id",id);
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i>0;
    }
}
