package db;

import model.EmotionalDiary;
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

public class EmotionalDiaryDAOTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private EmotionalDiaryDAO edDAO;

    @Before
    public void connect() throws Exception {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        edDAO = new EmotionalDiaryDAO(manager);
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
    public void createEmotionalDiary() {
        Role role = new Role("Role-11");
        User user = new User("First-11", "Middle-11", "Last-11", "FML11");
        user.setRole(role);

        manager.getTransaction().begin();
        manager.persist(role);
        manager.persist(user);
        manager.getTransaction().commit();

        EmotionalDiary ed = edDAO.createEmotionalDiary(user, "test-1");
        assertNotNull(ed);
        assertEquals("test-1", ed.getComment());
        assertEquals(user, ed.getUser());
        assertNotEquals(0, ed.getId());

        EmotionalDiary foundEdById = manager.find(EmotionalDiary.class, ed.getId());
        manager.refresh(foundEdById);

    }

    @Test
    public void findEmotionalDiariesByUser() {

        Role role = new Role("Role-12");
        User user = new User("First-12", "Middle-12", "Last-12", "FML12");
        user.setRole(role);
        EmotionalDiary ed1 = new EmotionalDiary(user, "test-2");
        EmotionalDiary ed2 = new EmotionalDiary(user, "test-3");

        manager.getTransaction().begin();
        manager.persist(role);
        manager.persist(user);
        manager.persist(ed1);
        manager.persist(ed2);
        manager.getTransaction().commit();

        List<EmotionalDiary> foundEmotionalDiaries = edDAO.findEmotionalDiariesByUser(user);
        assertNotNull(foundEmotionalDiaries);
        for (EmotionalDiary foundEmotionalDiary : foundEmotionalDiaries) {
            assertEquals(user, foundEmotionalDiary.getUser());
            EmotionalDiary foundEdById = manager.find(EmotionalDiary.class, foundEmotionalDiary.getId());
            manager.refresh(foundEdById);
        }

    }
}