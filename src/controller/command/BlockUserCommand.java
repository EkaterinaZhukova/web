package controller.command;

import controller.command.Constants.Parameters;
import exceptions.NotFoundAbonentException;
import model.Abonent;
import model.CommandURL;
import model.Service;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserCommand implements Command {
    public static final String urlPattern = "block";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public BlockUserCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
        super();
        this.abonentDAO = abonentDAO;
        this.accoountDao = accoountDao;
        this.serviceDAO = serviceDAO;
    }

    AbonentDAO abonentDAO;
    AccountDAO accoountDao;
    ServiceDAO serviceDAO;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        String name = request.getParameter(Parameters.name);
        String surname = request.getParameter(Parameters.surname);

        Abonent ab = abonentDAO.getUserWithName(name, surname);

        if (ab == null) {
            throw new NotFoundAbonentException(ab.getName() + " " + ab.getSurname() + " doesn't exist\nPlease check input data");
        }
        abonentDAO.blockUser(ab, true);
        response.sendRedirect(request.getContextPath() + CommandURL.blockList);
    }
}
