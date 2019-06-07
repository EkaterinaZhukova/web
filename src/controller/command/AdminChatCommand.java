package controller.command;

import controller.command.Constants.Parameters;
import model.Abonent;
import model.JSPPath;
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
import java.util.List;

public class AdminChatCommand implements Command {
    private final String urlPattern = "adminChat";

    public AdminChatCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
        super();
        this.abonentDAO = abonentDAO;
        this.accoountDao = accoountDao;
        this.serviceDAO = serviceDAO;
    }

    AbonentDAO abonentDAO;
    AccountDAO accoountDao;
    ServiceDAO serviceDAO;

    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Parameters.user);
            if (user.isAdmin()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.adminChat);
                List<Abonent> abonents = abonentDAO.getAll();
                request.setAttribute(Parameters.abonentsList, abonents);
                     request.setAttribute(Parameters.abonentsList, abonents);
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            request.setAttribute("error", e.getMessage());
            response.sendError(404);
        }
    }

}
