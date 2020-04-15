package web;

import db.UserDAO;
import db.UserRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

   /* @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public List<User> findUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            allUsers.add(user);
        }

        return allUsers;
    }*/


}
