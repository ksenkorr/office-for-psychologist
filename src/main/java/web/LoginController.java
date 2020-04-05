package web;

import db.RoleDAO;
import db.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController  {

    public static final String VERIFIED_USER_NAME_ATTRIBUTE = "verifiedUserName";
    public static final String VERIFIED_USER_FML_NAME_ATTRIBUTE = "verifiedUserFMLName";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @GetMapping(path = "/login")
    public String loadLoginForm(@RequestParam(required = false) String login,
                                HttpSession session) {

        //TODO: check user role and then redirect, need to clean name after log out
        /*if (session.getAttribute(VERIFIED_USER_NAME_ATTRIBUTE) != null) {
            return "redirect:/";
        }*/

        return "login";
    }

    @PostMapping(path = "/login")
    public String processLoginForm(HttpSession session,
                                   @RequestParam("enteredUsername") String username,
                                   @RequestParam("enteredPassword") String password)  {

        User user = userDAO.findUserByLogin(username);

        if (user != null && password != null && password.equals(user.getPassword())) {
            session.setAttribute(VERIFIED_USER_NAME_ATTRIBUTE, username);
            session.setAttribute(VERIFIED_USER_FML_NAME_ATTRIBUTE, user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());

            if (user.isPsychologist()) {
                return "psychologistMenu";
            } else {
                return "patientMenu";
            }

        } else {
            return "redirect:/login?username=" + username;
        }
    }


}
