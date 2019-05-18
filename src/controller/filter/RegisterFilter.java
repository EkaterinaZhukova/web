package controller.filter;

import controller.command.Command;
import controller.command.Constants.Parameters;
import controller.command.RegistrationCommand;
import model.CommandURL;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Parameter;

@WebFilter(filterName = "registerFilter", urlPatterns = { "/*" })
public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        String command = servletRequest.getParameter("command");
        User user = (User) session.getAttribute("user");

        if (req.getMethod().equalsIgnoreCase("post")) {
            if (command.equals(RegistrationCommand.urlPattern)) {
                String name = req.getParameter(Parameters.name);
                String surname = req.getParameter(Parameters.surname);
                String phoneNumber = req.getParameter(Parameters.phone);
                if (name.length() > 15 || surname.length() > 15) {
                    req.setAttribute(Parameters.registrationError, "Length of name and surname must be less 15 symbols");
                    res.sendRedirect(req.getContextPath() + CommandURL.registration);
                }
                else if (!name.matches("[a-zA-Zа-яА-ЯёЁ]+") || !surname.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
                    req.setAttribute(Parameters.registrationError, "Name and surname must contain only letters");
                    res.sendRedirect(req.getContextPath() + CommandURL.registration);
                }
                else if (phoneNumber.length() < 14 && phoneNumber.length() > 2 && !phoneNumber.matches("\\+?[0-9]+")) {
                        req.setAttribute(Parameters.registrationError, "Phone number must contain only '+' and numbers or only numbers");
                        res.sendRedirect(req.getContextPath() + CommandURL.registration);
                }
                else{
                    req.setAttribute(Parameters.registrationError, null);
                    filterChain.doFilter(req, res);
                }

            }
        } else {
            filterChain.doFilter(req, res);
        }
    }


    @Override
    public void destroy() {

    }
}
