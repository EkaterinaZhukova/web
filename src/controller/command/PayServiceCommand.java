package controller.command;

import controller.command.Constants.Parameters;
import exceptions.NotFoundAbonentException;
import exceptions.NotFoundServiceException;
import model.Abonent;
import model.Account;
import model.CommandURL;
import model.Service;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PayServiceCommand implements Command {
    public static final String urlPattern = "payService";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public PayServiceCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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
        Integer id = Integer.parseInt(request.getParameter(Parameters.id));

        Abonent ab = abonentDAO.getUserWithName(name, surname);
        Service sv = serviceDAO.getServiceWithId(id);
        if (sv == null) {
            throw new NotFoundServiceException();
        }
        accoountDao.payForUser(ab, sv);

        response.sendRedirect(request.getContextPath() + CommandURL.pay(name, surname));
    }
}
