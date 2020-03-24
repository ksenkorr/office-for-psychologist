package db;

import com.sun.istack.Nullable;
import model.EmotionalDiary;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

public class EmotionalDiaryDAO {

    private EntityManager manager;

    public EmotionalDiaryDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "Entity manager shouldn't be null");
        this.manager = manager;
    }

    public EmotionalDiary createEmotionalDiary(User user, String comment) {

        EmotionalDiary ed = new EmotionalDiary(user, comment);
        manager.getTransaction().begin();
        try {
            manager.persist(ed);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();

        return ed;
    }


    @Nullable
    public List<EmotionalDiary> findEmotionalDiariesByUser(User user) {

        return manager.createQuery("SELECT ed from EmotionalDiary ed, User u WHERE ed.user = :userToSearch")
                .setParameter("userToSearch", user)
                .getResultList();
    }


}
