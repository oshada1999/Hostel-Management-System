package lk.ijse.hostel.embeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@ToString
public class Phone_Number_Format {
    private String phone_Num;
    private String type;
}
