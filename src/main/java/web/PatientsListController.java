package web;

import db.EmotionalDiaryRepository;
import db.UserDAO;
import db.UserRepository;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientsListController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmotionalDiaryRepository edRepository;


   @GetMapping(path = "/patientsList")
    public String showAllPatients(ModelMap model) {

        List<User> patients = userRepository.findAllByRoleRoleName(Role.PATIENT);

       List<Integer> edCountPerPatient = new ArrayList<>();
       for (User patient: patients) {
           Integer count = (edRepository.countByUser(patient));
           edCountPerPatient.add(count);
       }
        model.addAttribute("patients", patients);
        model.addAttribute("edCountPerPatient", edCountPerPatient);

        return "patientsList";
    }




}
