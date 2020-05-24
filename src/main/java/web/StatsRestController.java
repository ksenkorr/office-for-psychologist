package web;

import db.EmotionalDiaryRepository;
import db.UserDAO;
import db.UserRepository;
import model.EmotionalDiary;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatsRestController {

  @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmotionalDiaryRepository edRepository;

    @GetMapping("/api/users")
    public List<User> findUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            allUsers.add(user);
        }

        return allUsers;
    }


   @GetMapping("api/emotional-diaries")
   public List<EmotionalDiary> findAllEmotionalDiaries() {

        List<EmotionalDiary> diaries = new ArrayList<>();
        for (EmotionalDiary ed : edRepository.findAll()) {
            diaries.add(ed);
        }

        return diaries;
   }

   /*@GetMapping("api/emotional-diaries-count")
   public Long  countEmotionalDiariesPerUser(){
        userRepository.count()


    }*/






}
