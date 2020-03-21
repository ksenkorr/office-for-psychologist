package db;

import model.Role;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

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
    public void createRole() {
        Role role = userDAO.createRole("Role-1");
        assertNotNull(role);
        assertEquals("Role-1", role.getRoleName());
        assertNotEquals(0, role.getId());

        Role foundRoleById = manager.find(Role.class, role.getId());
        manager.refresh(foundRoleById);
    }

    @Test
    public void createRoleDuplicate() {
        Role role = userDAO.createRole("Role-2");
        assertNotNull(role);

        try {
            userDAO.createRole("Role-2");
            fail("Role creation with the same name should fail");
        } catch (PersistenceException e) {
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
        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();

        User user1 = userDAO.createUser("First-3", "Middle-3", "Last-3", "FML3",role, "login-3", "password-3");
        User user2 = userDAO.createUser("First-4", "Middle-4", "Last-4", "FML4",role, "login-4", "password-4");

        List<User> foundUsers = userDAO.findUsersByRoleName("Role-5");
        assertNotNull(foundUsers);
        for (User foundUser : foundUsers) {
            assertEquals("Role-5", foundUser.getRole().getRoleName());
        }

        assertNotNull(userDAO.findUsersByRoleName("No-Role"));

    }

    @Test
    public void findUserByLogin() {
        Role role = new Role("Role-4");
        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();

        User user = userDAO.createUser("First-2", "Middle-2", "Last-2", "FML2",role, "login-2", "password-2");

        User foundUser = userDAO.findUserByLogin("login-2");
        assertNotNull(foundUser);
        assertEquals("login-2", foundUser.getLogin());
        assertEquals("Role-4", foundUser.getRole().getRoleName());

    }
}
