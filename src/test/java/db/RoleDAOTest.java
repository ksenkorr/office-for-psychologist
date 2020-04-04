package db;

import model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import web.TestConfiguration;

import javax.persistence.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleDAOTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private RoleDAO roleDAO;

    @Test
    public void createRole() {
        Role role = roleDAO.createRole("Role-1");
        assertNotNull(role);
        assertEquals("Role-1", role.getRoleName());
        assertNotEquals(0, role.getId());

        Role foundRoleById = manager.find(Role.class, role.getId());
      //  manager.refresh(foundRoleById);
    }

    @Transactional
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