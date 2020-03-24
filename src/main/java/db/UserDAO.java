package db;

import com.sun.istack.Nullable;
import model.EmotionalDiary;
import model.Role;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

public class UserDAO {

    private EntityManager manager;

    public UserDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "Entity manager shouldn't be null");
        this.manager = manager;
    }

    public User createUser(String firstName, String middleName, String lastName, String nameAconym, Role role , String login, String password) {
        User user = new User(firstName, middleName, lastName, nameAconym);
        user.setRole(role);
        user.setLogin(login);
        user.setPassword(password);

        manager.getTransaction().begin();
        try {
            manager.persist(user);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return user;
    }

    @Nullable
    public List<User> findUsersByRoleName(String roleName) {
        try {
            return manager.createQuery("SELECT u FROM User u, Role r WHERE r.roleName =: nameToSearch", User.class)
                    .setParameter("nameToSearch", roleName)
                    .getResultList();
        } catch (NoResultException cause) {
            return null;
        }
    }

    @Nullable
    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("SELECT u from User u WHERE u.login =:login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }


    }

}
