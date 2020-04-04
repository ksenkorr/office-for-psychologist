package web;

import db.RoleDAO;
import db.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Component
public class StartupListener {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @EventListener
    @Transactional
    public void applicationStarted(ContextRefreshedEvent event) {

        if (userDAO.findUserByLogin("admin") == null && roleDAO.findRoleByName("Психолог") == null && userDAO.findUserByLogin("patient") == null && roleDAO.findRoleByName("Пациент") == null) {

            userDAO.createUser("Андрей", "Андреевич", "Андреев", "ААА", roleDAO.createRole("Психолог"), "admin", "admin");
            userDAO.createUser("Иван", "Иванович", "Иванов", "ИИИ", roleDAO.createRole("Пациент"), "patient", "patient");

        }

    }


}


