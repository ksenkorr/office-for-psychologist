package db;

import model.EmotionalDiary;
import model.Role;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import web.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmotionalDiaryDAOTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EmotionalDiaryDAO edDAO;

    @Transactional
    @Test
    public void createEmotionalDiary() {
        Role role = new Role("Role-11");
        User user = new User("First-11", "Middle-11", "Last-11", "FML11");
        user.setRole(role);

        manager.persist(role);
        manager.persist(user);

        EmotionalDiary ed = edDAO.createEmotionalDiary(user, "test-1");

        assertNotNull(ed);
        assertEquals("test-1", ed.getComment());
        assertEquals(user, ed.getUser());
        assertNotEquals(0, ed.getId());

        EmotionalDiary foundEdById = manager.find(EmotionalDiary.class, ed.getId());
        //? manager.refresh(foundEdById);

    }

    @Transactional
    @Test
    public void findEmotionalDiariesByUser() {

        Role role = new Role("Role-12");
        User user = new User("First-12", "Middle-12", "Last-12", "FML12");
        user.setRole(role);
        EmotionalDiary ed1 = new EmotionalDiary(user, "test-2");
        EmotionalDiary ed2 = new EmotionalDiary(user, "test-3");

        manager.persist(role);
        manager.persist(user);
        manager.persist(ed1);
        manager.persist(ed2);

        List<EmotionalDiary> foundEmotionalDiaries = edDAO.findEmotionalDiariesByUser(user);
        assertNotNull(foundEmotionalDiaries);

        for (EmotionalDiary foundEmotionalDiary : foundEmotionalDiaries) {
            assertEquals(user, foundEmotionalDiary.getUser());
            EmotionalDiary foundEdById = manager.find(EmotionalDiary.class, foundEmotionalDiary.getId());
            manager.refresh(foundEdById);
        }

    }
}