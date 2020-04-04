package model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import web.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SmokeTest {

    @PersistenceContext
    private EntityManager manager;


    @Transactional
    @Test
    public void createUserTest() {

        User user = new User("Леонид", "Леонидович", "Леонидов", "ЛЛЛ");
        Role role = new Role("Пациент");
        user.setRole(role);

        manager.persist(role);
        manager.persist(user);

        User foundUser = manager.find(User.class, user.getId());
        assertNotNull(foundUser);
        assertSame(foundUser, user);

        manager.refresh(foundUser);

    }

}
