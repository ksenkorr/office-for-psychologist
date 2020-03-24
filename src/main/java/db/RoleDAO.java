package db;

import model.Role;

import javax.persistence.EntityManager;
import java.util.Objects;

public class RoleDAO {

    private EntityManager manager;

    public RoleDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "Entity manager shouldn't be null");
        this.manager = manager;
    }

    public Role createRole(String roleName) {

        Role role = new Role("Role-1");
        manager.getTransaction().begin();
        try {
            manager.persist(role);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
        }

        manager.getTransaction().commit();

        return role;
    }


}
