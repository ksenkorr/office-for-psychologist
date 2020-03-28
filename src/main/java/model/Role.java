package model;

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

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private List<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
