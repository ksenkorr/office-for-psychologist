package db;

import model.Role;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import web.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Test
    public void createUser() {
        Role role = new Role("Role-3");
        manager.persist(role);

        User user = userDAO.createUser("First-1", "Middle-1", "Last-1", "FML1",role, "login-1", "password-1");

        assertNotNull(user);
        assertEquals("login-1", user.getLogin());
        assertNotNull(user.getRole());
        assertEquals(role.getId(), user.getRole().getId());
        assertNotEquals(0, user.getId());

        User foundUser = manager.find(User.class, user.getId());
        assertNotNull(foundUser);
        manager.refresh(foundUser);
    }

    @Transactional
    @Test
    public void findUsersByRoleName() {

        Role role = new Role ("Role-5");

        User user1 = new User("First-3", "Middle-3", "Last-3", "FML3");
        User user2 = new User("First-4", "Middle-4", "Last-4", "FML4");

        user1.setRole(role);
        user2.setRole(role);

        manager.persist(role);
        manager.persist(user1);
        manager.persist(user2);

        List<User> foundUsers = userDAO.findUsersByRoleName("Role-5");
        assertNotNull(foundUsers);
        for (User foundUser : foundUsers) {
            assertEquals("Role-5", foundUser.getRole().getRoleName());
        }

        assertNotNull(userDAO.findUsersByRoleName("No-Role"));

    }

    @Transactional
    @Test
    public void findUserByLogin() {
        Role role = new Role("Role-2");
        User user = new User("First-2", "Middle-2", "Last-2", "FML2");
        user.setRole(role);
        user.setLogin("login-2");

        manager.persist(role);
        manager.persist(user);

        User foundUser = userDAO.findUserByLogin("login-2");
        assertNotNull(foundUser);
        assertEquals("login-2", foundUser.getLogin());
        assertEquals("Role-2", foundUser.getRole().getRoleName());

    }

}
