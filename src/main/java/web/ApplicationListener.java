package web;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
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


        if (userDAO.findUserByLogin("admin") == null && roleDAO.findRoleByName("Психолог") == null && roleDAO.findRoleByName("Пациент") == null) {

            System.out.println("test");

            userDAO.createUser("Андрей", "Андреевич", "Андреев", "ААА", roleDAO.createRole("Психолог"), "admin", "admin");
            roleDAO.createRole("Пациент");

        } else {
            System.out.println("test1");
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


