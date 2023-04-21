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
public class RoomTM {
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;
   /* private Button btn;*/
}
