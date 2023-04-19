package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservation dto) throws SQLException, ClassNotFoundException {
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
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Reservation> list=new ArrayList<>();
        try {
            String hql="From Reservation ";
            List<Reservation> reservationListList =session.createQuery(hql).list();
            for (Reservation r :
                    reservationListList) {
                list.add(new Reservation(r.getRes_id(),r.getStartDate(),r.getEndDate(),r.getPayingAmount(),r.getLessAmount(),r.getStatus(),r.getStudent(),r.getRoom()));


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
    public boolean update(Reservation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
        return null;
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
    public String generateNewId() throws IOException {
        String newId="P001";
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql="SELECT res_id FROM Reservation ORDER BY res_id DESC LIMIT 1";
        List<String> list = session.createSQLQuery(sql).list();

        for (String id : list) {
            if (id!=null){
                int num = Integer.valueOf(id.substring(1));
                num++;

                if (num<=9){
                    newId="P00"+num;
                }else if (num>9&&num<100){
                    newId="P0"+num;
                }else if (num>=100){
                    newId="P"+num;
                }
            }
        }

        transaction.commit();
        session.close();

        return newId;
    }
}
