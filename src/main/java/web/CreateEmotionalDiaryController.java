package web;

import db.EmotionalDiaryDAO;
import db.UserDAO;
import model.EmotionalDiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateEmotionalDiaryController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmotionalDiaryDAO edDAO;

    @GetMapping(path = "/createEmotionalDiary")
    public String getCreateEmotionalDiaryForm() {

        return "createEmotionalDiary";

    }


    @PostMapping(path = "/createEmotionalDiary")
    public String processCreateEmotionalDiary(ModelMap model,
                                              @RequestParam("diary") String diary) {

        edDAO.createEmotionalDiary(userDAO.findUserByLogin(LoginController.VERIFIED_USER_NAME_ATTRIBUTE), diary);

        model.addAttribute("diaryCreated", "yes");

        return "createEmotionalDiary";

    }

}
