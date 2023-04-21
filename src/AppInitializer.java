import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lk.ijse.hostel.entity.*;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppInitializer extends Application{
    /*public static void main(String[] args){
        Customer customer = new Customer();
        customer.setId("C001");
        customer.setName("niml");

        User user = new User();
        user.setUserName("admin");
        user.setName(new Name_Format("chathura","oshada"));
        user.setPhoneNumber(new Phone_Number_Format("4444","jjjkjbkj"));
        user.setPassword("Ch070@osh");*/
   /* public static void main(String[] args) {
        Room room = new Room();
        room.setRoom_type_id("R004");
        room.setType("Non-AC / Food");
        room.setKey_money(6000);
        room.setQty(25);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(room);
        transaction.commit();
        session.close();
    }*/


        /*Student student = new Student();
        student.setStudent_id("S001");
        student.setName(new Name_Format("chathura","oshada"));
        student.setAddress("147/1,colombo 06");
        student.setContact(new Phone_Number_Format("01000","work"));
        student.setDob(LocalDate.now());
        student.setGender("Male");

        Reservation reservation = new Reservation();
        reservation.setRes_id("R001");
        reservation.setDate(LocalDate.now());
        reservation.setStudent(student);
        reservation.setRoom(room);
        reservation.setStatus("pending");

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
           // session.save(customer);
            session.save(student);
            session.save(room);
            session.save(user);
            session.save(reservation);
            *//*Reservation s001 = session.load(Reservation.class, "qqqq");
            System.out.println(s001.getRes_id()+","+ s001.getStatus());*//*
            transaction.commit();
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
        }finally {
            session.close();
        }
    }*/
    /*User user = new User();
        user.setUser_id("U001");
        user.setUserName("chathura123");
        user.setUserName("Ch070@osh");
        user.setName(new Name_Format("kamal","oshada"));
        user.setPhoneNumber(new Phone_Number_Format("0702229087","work"));
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();*/
    /*public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT r.room.id,r.id,r.room.key_money,r.endDate,r.student.id,r.student.name.f_name,r.student.address,r.student.contact.phone_Num,r.status,r.lessAmount FROM Reservation r INNER JOIN Student s ON r.student=s.student_id WHERE r.status LIKE '%Less paid%'";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        for (Object[] ob1:list) {
            System.out.println(ob1[0]+"-"+ob1[1]+"-"+ob1[2]+"-"+ob1[3]+"-"+ob1[4]+"-"+ob1[5]+"-"+ob1[6]+"-"+ob1[7]+"-"+ob1[8]+"-"+ob1[9]);
            //System.out.println(ob1[0].getClass().getName());
        }

        transaction.commit();
        session.close();
    }*/
   public static void main(String[] args) {
       launch(args);
   }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/hostel/view/LoginForm.fxml"))));
        primaryStage.show();

    }
}
