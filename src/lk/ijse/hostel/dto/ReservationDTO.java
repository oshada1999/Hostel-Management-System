package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationDTO {
    private String res_id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double payingAmount;
    private double lessAmount;
    private String status;
    private Student student;
    private Room room;
}
