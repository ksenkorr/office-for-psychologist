package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name",nullable = false, length = 20)
    private String firstName;

    @Column(name = "middle_name",nullable = false, length = 20)
    private String middleName;

    @Column(name = "last_name",nullable = false, length = 40)
    private String lastName;

    @Column(name = "name_acronym",nullable = false, unique = true, length = 10)
    private String nameAcronym;

    @Column(length = 20, unique = true)
    private String login;

    @Column(length = 20)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<EmotionalDiary> emotionalDiaries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameAcronym() {
        return nameAcronym;
    }

    public void setNameAcronym(String nameAcronym) {
        this.nameAcronym = nameAcronym;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<EmotionalDiary> getEmotionalDiaries() {
        return emotionalDiaries;
    }

    public void setEmotionalDiaries(List<EmotionalDiary> emotionalDiaries) {
        this.emotionalDiaries = emotionalDiaries;
    }

    public User(String firstName, String middleName, String lastName, String nameAcronym) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nameAcronym = nameAcronym;
    }

    public User() {
    }
}
