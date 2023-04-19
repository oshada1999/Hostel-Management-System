package lk.ijse.hostel.service;

import lk.ijse.hostel.service.custom.impl.ReservationServiceImpl;
import lk.ijse.hostel.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostel.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostel.service.custom.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }
    public static ServiceFactory getServiceFactory(){
        return  (null== serviceFactory) ? serviceFactory = new ServiceFactory(): serviceFactory;
    }

    public enum ServiceTypes {
       USER,ROOM,STUDENT,RESERVATION
    }

    public SupperService getService(ServiceTypes types){
        switch (types){
            case USER :
                return new UserServiceImpl();
                case ROOM :
                return new RoomServiceImpl();
            case STUDENT:
                return new StudentServiceImpl();
            case RESERVATION:
                return new ReservationServiceImpl();
            default:
                return null;
        }
    }
}
