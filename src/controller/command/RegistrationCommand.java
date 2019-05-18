package controller.command;

import controller.command.Constants.Parameters;
import controller.utils.Utils;
import exceptions.DAOException;
import model.*;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;

import javax.persistence.OneToOne;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class RegistrationCommand implements Command{
    public static final String urlPattern = "registration";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        List<Service> list = serviceDAO.getAvailibleServices();
        request.setAttribute("services", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
        dispatcher.forward(request, response);
    }

    public RegistrationCommand(AbonentDAO abonentDAO, AccountDAO accoountDao, ServiceDAO serviceDAO) {
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

        List<Abonent> abonents = abonentDAO.getAll();
        List<Service> services = serviceDAO.getAvailibleServices();
        List<Account> accounts = accoountDao.getAll();
        User user = new User();
        int newAbonentId = abonents.get(abonents.size() - 1).getId() + 1;
        int newAccountId = accounts.get(accounts.size() - 1).getId() + 1;

        String name = request.getParameter(Parameters.name);
        String surname = request.getParameter(Parameters.surname);
        String phone = request.getParameter(Parameters.phone);

        try {
            Abonent newAbonent = new Abonent(newAbonentId,name,surname,phone);
            abonentDAO.createObject(newAbonent);
            String[] names = request.getParameterValues("servicesCheckbox");
            if (names != null) {
                List list = Arrays.asList(names);
                if (!list.isEmpty() && list != null) {
                    for (Service sv : services) {
                        if (list.contains(sv.getName())) {
                            Account newAccount = new Account(newAccountId, newAbonent, sv);
                            accoountDao.createObject(newAccount);
                            newAccountId += 1;
                        }
                    }
                }
            }
            user.setAbonent(true);
            session.setAttribute(Parameters.userType, surname);
            session.setAttribute(Parameters.abonent, newAbonent);
            session.setAttribute(Parameters.user, user);
            user.abonentEntity = newAbonent;

        }
        catch (DAOException ex) {
            request.setAttribute(ErrorsString.error, ErrorsString.notFoundAbonentInLogin);
            response.sendError(404);
            session.setAttribute(Parameters.user, null);
        }
        Utils.checkCookies(request, response);
        response.sendRedirect(request.getContextPath() + CommandURL.home);

}


}
