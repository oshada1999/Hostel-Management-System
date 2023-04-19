package lk.ijse.hostel.entity;

import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "student")
public class Student implements SupperEntity{
    @Id
    @Column(name = "student_id",nullable = false)
    private String student_id;

    @Column(name = "name")
    private Name_Format name;

    @Column(name = "address",columnDefinition = "TEXT")
    private String address;

    @Column(name = "contact")
    private Phone_Number_Format contact;

    @Column(name = "dob",columnDefinition = "DATE")
    private LocalDate dob;

    @Column(name = "gender")
    private String gender;

    @OneToMany(
            mappedBy = "student",
            targetEntity = Reservation.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Reservation> reservationList=new ArrayList<>();

    public Student(String student_id, Name_Format name, String address, Phone_Number_Format contact, LocalDate dob, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }
}
