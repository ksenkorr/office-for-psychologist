package db;

import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import web.TestConfiguration;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager manager;

    @Test
    public void findUsers() {

        ArrayList<User> allUsers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            allUsers.add(user);
        }

        for (User user : allUsers) {
            Assert.assertNotNull(user);
        }


    }


}
