package controller.command;

import controller.command.Constants.Parameters;
import exceptions.EmptyAbonentsListException;
import model.Abonent;
import model.AbonentWithBalance;
import model.ErrorsString;
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
import java.util.ArrayList;
import java.util.List;

public class BlockUserListCommand implements Command {

    public static final String urlPattern = "blockList";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public BlockUserListCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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
            request.setAttribute(ErrorsString.error, "List of abonents is empty");
            throw new EmptyAbonentsListException();
        }
        request.setAttribute(Parameters.abonentsList, list);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.blockList);
        dispatcher.forward(request, response);
    }

}