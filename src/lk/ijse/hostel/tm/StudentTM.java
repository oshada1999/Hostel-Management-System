package lk.ijse.hostel.tm;

import javafx.scene.control.Button;
import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StudentTM {
    private String student_id;
    private String fName;
    private String lName;
    private String address;
    private String contact;
    private String contact_status;
    private LocalDate dob;
    private String gender;
    private Button btn;

}
