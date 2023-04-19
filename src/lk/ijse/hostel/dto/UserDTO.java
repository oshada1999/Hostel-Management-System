package lk.ijse.hostel.dto;

import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserDTO {
    private String user_id;
    private String userName;
    private Name_Format name;
    private String password;
    private Phone_Number_Format phoneNumber;

    public UserDTO(String user_id, String userName, String password) {
        this.user_id = user_id;
        this.userName = userName;
        this.password = password;
    }
}
