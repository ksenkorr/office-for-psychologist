package web;

import db.UserDAO;
import model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/patientsList")
public class PatientsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        List<User> patients;

        try {
            patients = new UserDAO(manager).findAllPatients();
        } finally {
            manager.close();
        }

        req.setAttribute("patients", patients);
        req.getRequestDispatcher("pages/patientsList.jsp").forward(req, resp);

    }




}
