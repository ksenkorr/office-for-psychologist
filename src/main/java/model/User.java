package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name",nullable = false, length = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String firstName;

    @Column(name = "middle_name",nullable = false, length = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String middleName;

    @Column(name = "last_name",nullable = false, length = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String lastName;

    @Column(name = "name_acronym",nullable = false, unique = true, length = 10)
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @NotBlank(message = "Поле не может быть пустым")
    @Size(max = 10, message = "Превышено доступимое количество символов")
    private String nameAcronym;

    @Column(length = 20, unique = true)
    @Size(min = 3, max = 20, message = "Имя пользователя должно быть от 3 до 20 символов")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Только буквы латинского алфавитов, цифры, знаки подчеркивания, дефисы и точки разрешены для ввода")
    private String login;

    @Column(length = 20)
    @Size(min = 3, max = 20, message = "Имя пользователя должно быть от 3 до 20 символов")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Только буквы латинского алфавитов, цифры, знаки подчеркивания, дефисы и точки разрешены для ввода")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
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
