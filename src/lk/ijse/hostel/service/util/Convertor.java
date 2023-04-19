package lk.ijse.hostel.service.util;

import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.User;

public class Convertor {
    public UserDTO formUser(User user){
        return new UserDTO(user.getUser_id(),user.getUserName(),user.getName(),user.getPassword(),user.getPhoneNumber());
    }
    public User toUser(UserDTO userDTO){
        return new User(userDTO.getUser_id(),userDTO.getUserName(),userDTO.getName(),userDTO.getPassword(),userDTO.getPhoneNumber());
    }

    public RoomDTO fromRoom(Room room){
        return new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
    }
     public Room toRoom(RoomDTO roomDTO){
        return new Room(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty());
    }
    public StudentDTO fromStudent(Student student){
        return new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender());
    }
    public Student toStudent(StudentDTO studentDTO){
        return new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact(),studentDTO.getDob(),studentDTO.getGender());
    }
    public ReservationDTO fromReservation(Reservation reservation){
        return new ReservationDTO(reservation.getRes_id(),reservation.getStartDate(),reservation.getEndDate(),reservation.getPayingAmount(),reservation.getLessAmount(),reservation.getStatus(),reservation.getStudent(),reservation.getRoom());
    }
    public Reservation toReservation(ReservationDTO reservationDTO){
        return new Reservation(reservationDTO.getRes_id(),reservationDTO.getStartDate(),reservationDTO.getEndDate(),reservationDTO.getPayingAmount(),reservationDTO.getLessAmount(),reservationDTO.getStatus(),reservationDTO.getStudent(),reservationDTO.getRoom());
    }

}
