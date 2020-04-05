package web;

import db.RoleDAO;
import db.UserDAO;
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
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

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
            userDAO.createUser(form.getFirstName(),
                                form.getMiddleName(),
                                form.getLastName(),
                                form.getAcronym(),
                                roleDAO.findRoleByName("Пациент"),
                                form.getLogin(),
                                form.getPassword());
        } catch (Throwable cause) {

            // TODO: how to not clear login and acronym fields after showing a message

            if (userDAO.findUserByLogin(form.getLogin()) != null) {
                validationResult.addError(
                        new FieldError("form", "login",
                                "Пользователь с именем " + form.getLogin()
                                        + " уже зарегистрирован"));
            }

            if (userDAO.findUserByAcronym(form.getAcronym()) != null) {
                validationResult.addError(
                        new FieldError("form", "acronym",
                                "Сокращенное имя " + form.getAcronym()
                                        + " уже существует"));
            }

        }

            if (!validationResult.hasErrors() && userDAO.findUserByLogin(form.getLogin()) != null) {

                model.addAttribute("userCreated", form.getLogin());
                System.out.println("findUserByLogin");
                System.out.println(model.getAttribute("userCreated"));
           }

            return "register";

    }


}
