package web;

import db.RoleDAO;
import db.UserDAO;
import model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProdPersistenceUnit");
        sce.getServletContext().setAttribute("factory", factory);

        EntityManager manager = factory.createEntityManager();
        UserDAO userDAO = new UserDAO(manager);
        RoleDAO roleDAO = new RoleDAO(manager);

        if (userDAO.findUserByLogin("admin") == null) {

            roleDAO.createRole("Пациент");
            Role role = roleDAO.createRole("Пcихолог");

            userDAO.createUser("Андрей", "Андреевич", "Андреев", "ААА", role, "admin", "admin");

        }

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory factory = (EntityManagerFactory) sce.getServletContext().getAttribute("factory");

        if (factory != null) {
            factory.close();
        }

    }
}


