package lk.ijse.hostel.dto;

import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentDTO {
    private String student_id;
    private Name_Format name;
    private String address;
    private Phone_Number_Format contact;
    private LocalDate dob;
    private String gender;

}
