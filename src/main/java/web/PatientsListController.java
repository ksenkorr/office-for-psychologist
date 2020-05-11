package web;

import db.UserDAO;
import db.UserRepository;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PatientsListController {

    @Autowired
    private UserRepository userRepository;


   @GetMapping(path = "/patientsList")
    public String showAllPatients(ModelMap model) {

        List<User> patients = userRepository.findAllByRoleRoleName(Role.PATIENT);

        model.addAttribute("patients", patients);

        return "patientsList";
    }




}
