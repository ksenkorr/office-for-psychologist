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

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
/*

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // if (req.getSession().getAttribute("verifiedUserName") != null) {
        //    resp.sendRedirect(req.getContextPath());
          //  return;
        //}

        String username = req.getParameter("enteredUsername");
        String password = req.getParameter("enteredPassword");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDAO userDAO = new UserDAO(manager);
        RoleDAO roleDAO = new RoleDAO(manager);

        User user = null;

        try {
            if (username != null) {
                user = userDAO.findUserByLogin(username);
            }
        } finally {
            manager.close();
        }

        if (user != null && password != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("verifiedUserName", username);
            req.getSession().setAttribute("verifiedUserFMLName", user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
           // resp.sendRedirect(req.getContextPath()); // to index, i.e. root

            if (user.isPsychologist()) {
                resp.sendRedirect("psychologistMenu");
            } else {
                resp.sendRedirect("patientMenu");
            }

        } else {
            resp.sendRedirect("login?username=" + username);
        }
    }
*/

}
