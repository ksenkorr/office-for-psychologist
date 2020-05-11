package web;

import db.RoleDAO;
import db.RoleRepository;
import db.UserDAO;
import db.UserRepository;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @ModelAttribute("form")
    public RegistrationForm createForm() {
        RegistrationForm form = new RegistrationForm();
        form.setFirstName("");
        form.setMiddleName("");
        form.setLastName("");
        form.setAcronym("");
        form.setLogin("");
        form.setPassword("");

        return form;
    }


    @GetMapping(path = "/register")
    public String loadRegistrationForm(ModelMap model,
                                       @ModelAttribute("form") RegistrationForm form) {
        return "register";
    }


    @PostMapping(path = "/register")
    public String processRegistrationForm(ModelMap model,
                                          @Validated
                                          @ModelAttribute("form") RegistrationForm form,
                                          BindingResult validationResult)  {



        try {

           User user = new User(form.getFirstName(),
                   form.getMiddleName(),
                   form.getLastName(),
                   form.getAcronym());
           user.setRole(roleRepository.findByRoleName(Role.PATIENT));
           user.setLogin(form.getLogin());
           user.setPassword(form.getPassword());

           userRepository.save(user);


        } catch (Throwable cause) {

            // TODO: how to not clear login and acronym fields after showing a message

            if (userRepository.existsByLogin(form.getLogin())) {
                validationResult.addError(
                        new FieldError("form", "login",
                                "Пользователь с именем " + form.getLogin()
                                        + " уже зарегистрирован"));
            }

            if (userRepository.existsByNameAcronym(form.getAcronym())) {
                validationResult.addError(
                        new FieldError("form", "acronym",
                                "Сокращенное имя " + form.getAcronym()
                                        + " уже существует"));
            }

        }

        if (!validationResult.hasErrors() && userRepository.existsByLogin(form.getLogin())) {

            model.addAttribute("userCreated", form.getLogin());
            System.out.println("findUserByLogin");
            System.out.println(model.getAttribute("userCreated"));
        }

            return "register";

    }


}
