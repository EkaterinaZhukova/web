package controller.command;

import controller.command.Constants.Parameters;
import exceptions.EmptyAbonentsListException;
import model.Abonent;
import model.JSPPath;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

public class UnblockUserListCommand implements Command {

    public static final String urlPattern = "unblockList";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public UnblockUserListCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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

        List<Abonent> list = abonentDAO.getAll();
        if (list.isEmpty()) {
            throw new EmptyAbonentsListException();
        }
        request.setAttribute(Parameters.abonentsList, list);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.unblockList);
        dispatcher.forward(request, response);
    }

}