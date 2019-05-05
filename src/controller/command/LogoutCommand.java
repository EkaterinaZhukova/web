package controller.command;

import controller.command.Constants.Parameters;
import model.CommandURL;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    public static final String urlPattern = "logout";

    public LogoutCommand() {
    }
    public LogoutCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
        super();
        this.abonentDAO = abonentDAO;
        this.accoountDao = accoountDao;
        this.serviceDAO = serviceDAO;
    }

    AbonentDAO abonentDAO;
    AccountDAO accoountDao;
    ServiceDAO serviceDAO;

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(Parameters.user, null);
        response.sendRedirect(request.getContextPath() + CommandURL.logout);
    }
}
