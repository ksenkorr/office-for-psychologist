package db;

import model.Role;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

public class RoleDAO {

    private EntityManager manager;

    public RoleDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "Entity manager shouldn't be null");
        this.manager = manager;
    }

    public Role createRole(String roleName) {

        Role role = new Role(roleName);
        manager.getTransaction().begin();
        try {
            manager.persist(role);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
        }

        manager.getTransaction().commit();

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
