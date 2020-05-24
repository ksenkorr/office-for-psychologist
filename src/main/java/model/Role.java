package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "role_name",nullable = false, unique = true, length = 100)
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private List<User> users;

    public final static String PSYCHOLOGIST = "Психолог";
    public final static String PATIENT = "Пациент";

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
