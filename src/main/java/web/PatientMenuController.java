package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientMenuController{

    @GetMapping(path = "/patientMenu")
    public String showPatientMenu() {
        return "/patientMenu";
    }
}
