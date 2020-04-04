package db;

import com.sun.istack.Nullable;
import model.EmotionalDiary;
import model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class EmotionalDiaryDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public EmotionalDiary createEmotionalDiary(User user, String comment) {
        EmotionalDiary ed = new EmotionalDiary(user, comment);
        manager.persist(ed);
        return ed;
    }


    @Nullable
    public List<EmotionalDiary> findEmotionalDiariesByUser(User user) {

        return manager.createQuery("SELECT ed from EmotionalDiary ed, User u WHERE ed.user = :userToSearch", EmotionalDiary.class)
                .setParameter("userToSearch", user)
                .getResultList();
    }


}
