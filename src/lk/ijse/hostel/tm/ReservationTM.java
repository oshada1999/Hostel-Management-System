package lk.ijse.hostel.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationTM {
    private String res_id;
    private String stId;
    private String roomID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double payingAmount;
    private double lessAmount;
    private String status;

}
