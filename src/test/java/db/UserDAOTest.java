package db;

import model.Role;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;


public class UserDAOTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDAO userDAO;

    @Before
    public void connect() throws Exception {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        userDAO = new UserDAO(manager);
    }

    @After
    public void close() throws Exception {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void createUser() {
        Role role = new Role("Role-3");
        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();

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

    @Test
    public void findUsersByRoleName() {

        Role role = new Role ("Role-5");
        User user1 = new User("First-3", "Middle-3", "Last-3", "FML3");
        User user2 = new User("First-4", "Middle-4", "Last-4", "FML4");
        user1.setRole(role);
        user2.setRole(role);

        manager.getTransaction().begin();
        manager.persist(role);
        manager.persist(user1);
        manager.persist(user2);
        manager.getTransaction().commit();

        List<User> foundUsers = userDAO.findUsersByRoleName("Role-5");
        assertNotNull(foundUsers);
        for (User foundUser : foundUsers) {
            assertEquals("Role-5", foundUser.getRole().getRoleName());
        }

        assertNotNull(userDAO.findUsersByRoleName("No-Role"));

    }

    @Test
    public void findUserByLogin() {
        Role role = new Role("Role-2");
        User user = new User("First-2", "Middle-2", "Last-2", "FML2");
        user.setRole(role);
        user.setLogin("login-2");

        manager.getTransaction().begin();
        manager.persist(role);
        manager.persist(user);
        manager.getTransaction().commit();

        User foundUser = userDAO.findUserByLogin("login-2");
        assertNotNull(foundUser);
        assertEquals("login-2", foundUser.getLogin());
        assertEquals("Role-2", foundUser.getRole().getRoleName());

    }

}
