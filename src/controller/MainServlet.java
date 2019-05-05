package controller;

import controller.command.*;
import controller.utils.Utils;
import model.Account;
import model.dao.AbonentDAO;
import model.dao.AccountDAO;
import model.dao.ServiceDAO;
import controller.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main servlet class
 * @author Ekaterina Zhukova
 * @version 1.0
 */

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Map<String, Command> commands;

    public MainServlet() {
        super();
        this.commands = new HashMap<>();
    }

    @EJB
    AbonentDAO abonentDAO;
    @EJB
    AccountDAO accountDAO;
    @EJB
    ServiceDAO serviceDAO;

    @Override
    public void init() {
        Command[] commands = {
                new HomeCommand(abonentDAO, accountDAO, serviceDAO),
                new YourServicesCommand(abonentDAO, accountDAO, serviceDAO),
                new AllAbonents(abonentDAO, accountDAO, serviceDAO),
                new AllServiceListCommand(abonentDAO, accountDAO, serviceDAO),
                new PayServiceCommand(abonentDAO, accountDAO, serviceDAO),
                new BlockUserCommand(abonentDAO, accountDAO, serviceDAO),
                new BlockUserListCommand(abonentDAO, accountDAO, serviceDAO),
                new UnblockUserCommand(abonentDAO, accountDAO, serviceDAO),
                new UnblockUserListCommand(abonentDAO, accountDAO, serviceDAO),
                new LoginCommand(abonentDAO, accountDAO, serviceDAO),
                new LogoutCommand(abonentDAO, accountDAO, serviceDAO)


        };
        for (Command c : commands) {
            this.commands.put(c.getPattern(), c);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String checkSession = (String) session.getAttribute("check");
        if (checkSession == null) {
            Utils.checkCookies(request, response);
            session.setAttribute("check", "check");
        }
        String command = request.getParameter("command");
        System.out.println(command);

            if (command == null) {
            commands.get("home").doGet(request, response, this.getServletContext());
        } else if (commands.containsKey(command)) {
            commands.get(command).doGet(request, response, this.getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            commands.get("home").doGet(request, response, this.getServletContext());
        } else if (commands.containsKey(command)) {
            commands.get(command).doPost(request, response, this.getServletContext());
        }
    }
}
