package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.QueryDAO;
import lk.ijse.hostel.entity.CustomEntity;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getLessPaidList() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT r.id,r.room.id,r.student.id,r.student.name.f_name,r.student.address,r.student.contact.phone_Num,r.endDate,r.room.key_money,r.lessAmount ,r.status FROM Reservation r INNER JOIN Student s ON r.student=s.student_id WHERE r.status LIKE '%Less paid%'";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        List<CustomEntity> customEntityList =new ArrayList<>();
        for (Object[] ob1:list) {
            customEntityList.add(new CustomEntity((String) ob1[0],(String) ob1[1],(String) ob1[2],(String) ob1[3],(String) ob1[4],(String) ob1[5],(LocalDate) ob1[6],(Double) ob1[7],(Double) ob1[8],(String) ob1[9]));
            System.out.println(ob1[0]+"-"+ob1[1]+"-"+ob1[2]+"-"+ob1[3]+"-"+ob1[4]+"-"+ob1[5]+"-"+ob1[6]+"-"+ob1[7]+"-"+ob1[8]+"-"+ob1[9]);
            //System.out.println(ob1[0].getClass().getName());
        }

        transaction.commit();
        session.close();

        return customEntityList;
    }
}
