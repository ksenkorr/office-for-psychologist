package db;

import com.sun.istack.Nullable;
import model.Role;
import model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public User createUser(String firstName, String middleName, String lastName, String nameAconym, Role role , String login, String password) {
        User user = new User(firstName, middleName, lastName, nameAconym);
        user.setRole(role);
        user.setLogin(login);
        user.setPassword(password);
        manager.persist(user);
        return user;
    }

    public List<User> findUsersByRoleName(String roleName) {
        return manager.createQuery("SELECT u FROM User u, Role r WHERE r.roleName = :nameToSearch", User.class)
                .setParameter("nameToSearch", roleName)
                .getResultList();
    }

    @Nullable
    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("SELECT u from User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }


    @Nullable
    public User findUserByAcronym(String acronym) {
        try {
            return manager.createQuery("SELECT u from User u WHERE u.nameAcronym = :acronymToSearch", User.class)
                    .setParameter("acronymToSearch", acronym)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }



    public List<User> findAllPatients() {

        return manager.createQuery("SELECT u FROM User u, Role r " +
                "WHERE u.role.id= r.id AND " +
                "r.roleName = :nameToSearch", User.class)
                .setParameter("nameToSearch", Role.PATIENT)
                .getResultList();
    }


}
