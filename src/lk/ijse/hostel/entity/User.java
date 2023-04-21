package lk.ijse.hostel.entity;

import lk.ijse.hostel.embeded.Name_Format;
import lk.ijse.hostel.embeded.Phone_Number_Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "user")
public class User implements SupperEntity{

    @Column(name = "user_id")
    private String user_id;

    @Id
    @Column(name = "userName")
    private String userName;

    @Column(name = "name",nullable = false)
    private Name_Format name;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNumber",length = 10)
    private Phone_Number_Format phoneNumber;

}
