package web;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationForm {

    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String firstName;

    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String middleName;

    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String lastName;

    @Pattern(regexp = "[a-zA-Zа-яА-Я]*",
            message = "Только буквы русского или латинского алфавитов разрешены для ввода")
    @Size(max = 50, message = "Превышено доступимое количество символов")
    private String acronym;

    @Size(min = 3, max = 20, message = "Имя пользователя должно быть от 3 до 20 символов")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Только буквы латинского алфавитов, цифры, знаки подчеркивания, дефисы и точки разрешены для ввода")
    private String login;

    @Size(min = 3, max = 20, message = "Имя пользователя должно быть от 3 до 20 символов")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Только буквы латинского алфавитов, цифры, знаки подчеркивания, дефисы и точки разрешены для ввода")
    private String password;




}
