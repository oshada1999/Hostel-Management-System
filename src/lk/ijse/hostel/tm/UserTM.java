package lk.ijse.hostel.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserTM {
    private String userName;
    private String fName;
    private String lName;
    private String password;
    private String phone;
    private String type;
    private Button btn;
}
