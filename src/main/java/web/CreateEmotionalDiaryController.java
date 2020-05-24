package web;

import db.EmotionalDiaryDAO;
import db.EmotionalDiaryRepository;
import db.UserDAO;
import db.UserRepository;
import model.EmotionalDiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CreateEmotionalDiaryController {

    @Autowired
    //private UserDAO userDAO;
    private UserRepository userRepository;

    @Autowired
    //private EmotionalDiaryDAO edDAO;
    private EmotionalDiaryRepository edRepository;

    @GetMapping(path = "/createEmotionalDiary")
    public String getCreateEmotionalDiaryForm() {

        return "createEmotionalDiary";

    }


    @PostMapping(path = "/createEmotionalDiary")
    public String processCreateEmotionalDiary(HttpSession session,
                                              ModelMap model,
                                              @RequestParam("diary") String diary) {

        EmotionalDiary ed = new EmotionalDiary(userRepository.findByLogin((String) session.getAttribute(LoginController.VERIFIED_USER_NAME_ATTRIBUTE)), diary);
        edRepository.save(ed);
       // edDAO.createEmotionalDiary(userDAO.findUserByLogin(LoginController.VERIFIED_USER_NAME_ATTRIBUTE), diary);

        model.addAttribute("diaryCreated", "yes");

        return "createEmotionalDiary";

    }

}
