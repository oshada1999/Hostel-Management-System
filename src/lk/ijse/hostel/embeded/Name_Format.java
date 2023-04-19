package lk.ijse.hostel.embeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Name_Format {
    private String f_name;
    private String l_name;
}
