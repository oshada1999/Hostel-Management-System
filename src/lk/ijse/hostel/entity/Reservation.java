package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "reservation")
public class Reservation implements SupperEntity{
    @Id
    @Column(name = "res_id",length = 255)
    private String res_id;

    @Column(name = "startDate",columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "endDate",columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(name = "payingAmount")
    private double payingAmount;

    @Column(name = "lessAmount")
    private double lessAmount;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id")
    @ToString.Exclude
    private Room room;

    public Reservation(String res_id, LocalDate startDate, LocalDate endDate, double payingAmount, double lessAmount, String status) {
        this.res_id = res_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payingAmount = payingAmount;
        this.lessAmount = lessAmount;
        this.status = status;
    }
}
