package lk.ijse.hostel.dao;

import lk.ijse.hostel.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return   (null==daoFactory)? daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        USER,ROOM,STUDENT,RESERVATION,QUERY
    }
    public SupperDAO getDAO(DAOTypes types){
        switch (types){
            case USER :
                return new UserDAOImpl();
            case ROOM :
                return new RoomDAOImpl();
            case STUDENT :
                return new StudentDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
                case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
