package controller.command;

import controller.command.Constants.Parameters;
import controller.utils.Utils;
import model.Abonent;
import model.CommandURL;
import model.ErrorsString;
import model.User;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    final String urlPattern = "login";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/loginPage.jsp");
        dispatcher.forward(request, response);
    }

    public LoginCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
        super();
        this.abonentDAO = abonentDAO;
        this.accoountDao = accoountDao;
        this.serviceDAO = serviceDAO;
    }

    AbonentDAO abonentDAO;
    AccountDAO accoountDao;
    ServiceDAO serviceDAO;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User();
        String surName = request.getParameter(Parameters.surname);
        String phone = request.getParameter(Parameters.phone);
        if (surName.equals(Parameters.admin) &&  phone.equals(Parameters.adminPassword)) {
            user.setAdmin(true);
            session.setAttribute(Parameters.userType, Parameters.admin);
        } else {
            Abonent ab = abonentDAO.getUserWithSurname(surName, phone);
            if (ab == null) {
                request.setAttribute(ErrorsString.error, ErrorsString.notFoundAbonentInLogin);
                response.sendError(404);

            } else {
                user.setAbonent(true);
                session.setAttribute(Parameters.userType, surName);
                user.abonentEntity = ab;
            }
        }
        session.setAttribute(Parameters.user, user);
        Utils.checkCookies(request, response);
        response.sendRedirect(request.getContextPath() + CommandURL.home);
    }

}
