package controller.command;


import controller.command.Constants.Parameters;
import model.JSPPath;
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

public class AllServiceListCommand implements Command {
    final String urlPattern = "allServicesAvailable";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public AllServiceListCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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

        List<Service> list = serviceDAO.getAvailibleServices();
        request.setAttribute(Parameters.servicesList, list);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.services);
        dispatcher.forward(request, response);
    }
}
