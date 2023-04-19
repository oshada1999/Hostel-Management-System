package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomEntity {
    private String reID;
    private String roID;
    private String stID;
    private String name;
    private String address;
    private String contact;
    private LocalDate date;
    private double keyMoney;
    private double lessAmount;
    private String status;
}
