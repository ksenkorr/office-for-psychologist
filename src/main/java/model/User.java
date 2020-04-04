package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name",nullable = false, length = 100)
    private String firstName;

    @Column(name = "middle_name",nullable = false, length = 100)
    private String middleName;

    @Column(name = "last_name",nullable = false, length = 100)
    private String lastName;

    @Column(name = "name_acronym",nullable = false, unique = true, length = 20)
    private String nameAcronym;

    @Column(length = 50, unique = true)
    private String login;

    @Column(length = 50)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<EmotionalDiary> emotionalDiaries;

    public User(String firstName, String middleName, String lastName, String nameAcronym) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nameAcronym = nameAcronym;
    }

    public boolean isPsychologist() {
        if (role == null) {
            return false;
        }
        if (role.getRoleName().equals(Role.PSYCHOLOGIST)) {
            return true;
        }

        return false;

    }
}
