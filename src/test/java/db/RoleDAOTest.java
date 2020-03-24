package db;

import model.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

public class RoleDAOTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private RoleDAO roleDAO;

    @Before
    public void connect() throws Exception {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        roleDAO = new RoleDAO(manager);
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
        Role role = roleDAO.createRole("Role-1");
        assertNotNull(role);
        assertEquals("Role-1", role.getRoleName());
        assertNotEquals(0, role.getId());

        Role foundRoleById = manager.find(Role.class, role.getId());
        manager.refresh(foundRoleById);
    }

    @Test
    public void createRoleDuplicate() {
        Role role = roleDAO.createRole("Role-2");
        assertNotNull(role);

        try {
            roleDAO.createRole("Role-2");
            fail("Role creation with the same name should fail");
        } catch (PersistenceException e) {
        }

    }

}