package controller.filter;

import controller.command.*;
import model.CommandURL;
import model.JSPPath;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loging filter class
 * @author ekaterina
 * @version 1.0
 */
@WebFilter(filterName = "loginFilter", urlPatterns = { "/*" })
public class LogingFilter implements Filter {

    public LogingFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        String command = request.getParameter("command");
        User user = (User) session.getAttribute("user");

        if (user == null && !(command == null || command.equals("registration") || command.equals("home") || command.equals("login") || command.equals("allServicesAvailable") || command.equals("chooseLanguage"))) {
            res.sendRedirect(req.getContextPath() + "/servlet");
        }
        else if (user != null && user.isAbonent() && !abonentHaveAccessRight(command)){
            res.sendRedirect(req.getContextPath() + "/servlet");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    private boolean abonentHaveAccessRight(String command) {
        List<String> availableCommands = Arrays.asList(BlockUserListCommand.urlPattern, UnblockUserListCommand.urlPattern,BlockUserCommand.urlPattern, UnblockUserCommand.urlPattern);
        if (command == null) {
            return true;
        }
        for(String cm:availableCommands) {
            if (command.equals(cm)) {
                return false;
            }
        }
        return true;
    }

}