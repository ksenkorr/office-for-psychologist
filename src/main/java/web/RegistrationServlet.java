package web;

import db.RoleDAO;
import db.UserDAO;
import model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("enteredFirstName");
        String middleName = req.getParameter("enteredMiddleName");
        String lastName = req.getParameter("enteredLastName");
        String acronym = req.getParameter("enteredAcronym");
        String login = req.getParameter("enteredUsername");
        String password = req.getParameter("enteredPassword");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDAO userDAO = new UserDAO(manager);
        User user;

        try {
            user = userDAO.createUser(firstName, middleName, lastName, acronym, new RoleDAO(manager).findRoleByName("Пациент"), login, password);
        } finally {
            manager.close();
        }

        //resp.sendRedirect(req.getContextPath() + "/login?username=" +  login);

        if (userDAO.findUserByLogin(login) != null) {
            req.setAttribute("IsUserCreated", "yes");
            //req.getSession().setAttribute("IsUserCreated", "yes");
            resp.sendRedirect("register?firstName=" + firstName+"&middleName=" + middleName + "&lastName=" + lastName + "&acronym=" + acronym + "&username=" + login);
        }

    }
}
