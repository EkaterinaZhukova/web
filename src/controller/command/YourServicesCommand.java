package controller.command;

import controller.command.Constants.Parameters;
import exceptions.EmptyServiceListException;
import exceptions.NotFoundAbonentException;
import model.Abonent;
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
import java.lang.reflect.Parameter;
import java.util.List;

public class YourServicesCommand implements Command {
    public static final String urlPattern = "yourServices";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public YourServicesCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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

        List<Service> answer = null;
        String name = request.getParameter(Parameters.name);
        String surName = request.getParameter(Parameters.surname);

        Abonent ab = abonentDAO.getUserWithName(name, surName);
        if (ab == null) {
            throw new NotFoundAbonentException();
        }
        answer = accoountDao.getServicesByUser(ab);
        request.setAttribute(Parameters.userServices, answer);
        request.setAttribute(Parameters.abonent, ab);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.userServices);
        dispatcher.forward(request, response);
    }
}
