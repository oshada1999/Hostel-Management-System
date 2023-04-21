package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.QueryDAO;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.CustomEntity;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.custom.ReservationService;
import lk.ijse.hostel.service.util.Convertor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
    private final ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private final QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
       return reservationDAO.save(convertor.toReservation(reservationDTO));
    }

    @Override
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> all = reservationDAO.getAll();
        ArrayList<ReservationDTO> allRooms=new ArrayList<>();

        for (Reservation reservation:all) {
            allRooms.add(convertor.fromReservation(reservation));
        }
        return allRooms;
    }

    @Override
    public String getNewId() throws IOException {
        return reservationDAO.generateNewId();
    }

    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateLessPayment(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return reservationDAO.updateLessPayment(reservationDTO);
    }

    @Override
    public ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CustomDTO> getLessPaidStudentList() {
        List<CustomEntity> all = queryDAO.getLessPaidList();
        List<CustomDTO> allDetails=new ArrayList<>();

        for (CustomEntity customEntity:all) {
            allDetails.add(convertor.fromCustom(customEntity));
        }
        return allDetails;
    }

    @Override
    public List<CustomDTO> getRoomReserveStudentList() {
        List<CustomEntity> all = queryDAO.getRoomReserveList();
        List<CustomDTO> allDetails=new ArrayList<>();

        for (CustomEntity customEntity:all) {
            allDetails.add(convertor.fromCustom(customEntity));
        }
        return allDetails;
    }
}
