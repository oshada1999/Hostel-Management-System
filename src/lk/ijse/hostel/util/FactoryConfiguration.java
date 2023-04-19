package lk.ijse.hostel.util;

import lk.ijse.hostel.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;



    private FactoryConfiguration(){
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Hibernate.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configuration.setProperties(properties)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class);

        sessionFactory=configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance(){
        if (factoryConfiguration==null){
            factoryConfiguration=new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
