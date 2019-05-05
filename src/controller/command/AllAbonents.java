package controller.command;

import controller.command.Constants.Parameters;
import model.Abonent;
import model.AbonentWithBalance;
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

public class AllAbonents implements Command {

    public static final String urlPattern = "allAbonents";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public AllAbonents(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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
        List<AbonentWithBalance> abonents = new ArrayList<>();
        for (Abonent ab:list) {
            double balance = accoountDao.getBalanceFor(ab);
            AbonentWithBalance newAb = new AbonentWithBalance(ab,balance);
            abonents.add(newAb);
        }

        request.setAttribute(Parameters.abonentsList, abonents);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.abonents);
        dispatcher.forward(request, response);
    }

}
