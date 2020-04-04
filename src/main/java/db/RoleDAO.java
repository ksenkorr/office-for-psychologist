package db;

import model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Role createRole(String roleName) {
        Role role = new Role(roleName);
        manager.persist(role);
        return role;
    }

    public Role findRoleByName(String name) {
        try {
            return manager.createQuery("SELECT r from Role r WHERE r.roleName = :roleName", Role.class)
                    .setParameter("roleName", name)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }


}
