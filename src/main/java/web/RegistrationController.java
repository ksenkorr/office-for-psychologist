package web;

import db.RoleDAO;
import db.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @GetMapping(path = "/register")
    public String loadRegistrationForm() {
        return "register";
    }


    @PostMapping(path = "/register")
    public String processRegistrationForm(@RequestParam("enteredFirstName") String firstName,
                                          @RequestParam("enteredMiddleName") String middleName,
                                          @RequestParam("enteredLastName") String lastName,
                                          @RequestParam("enteredAcronym") String acronym,
                                          @RequestParam("enteredUserName") String login,
                                          @RequestParam("enteredPassword") String password,
                                          ModelMap model)  {

            userDAO.createUser(firstName, middleName, lastName, acronym, roleDAO.findRoleByName("Пациент"), login, password);
            if (userDAO.findUserByLogin(login) != null) {

                model.addAttribute("userCreated", login);
                System.out.println("findUserByLogin");
                System.out.println(model.getAttribute("userCreated"));
           }

            return "register";

            // TODO: load the page with filled fields after registering, currently it does not work
        /*return "register?firstName=" + firstName +
                "&middleName=" + middleName +
                "&lastName=" + lastName +
                "&acronym=" + acronym +
                "&username=" + login;*/

    }


}
