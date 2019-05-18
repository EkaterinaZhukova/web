package controller.command.tag;

import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class AbonentTableTag extends TagSupport {

    private List<AbonentWithBalance> abonents;

    public void setAbonents(List<AbonentWithBalance> abonents) {
        this.abonents = abonents;
    }


    public int doStartTag() throws JspException {
//        Locale loc = (Locale) pageContext.getAttribute("userLocale", PageContext.SESSION_SCOPE);
//        ResourceBundle bundle = ResourceBundle.getBundle("controller.resources.locale", loc, this.getClass().getClassLoader());
        String table = "<table class=\"table\">\n" +
                "                <thead>\n" +
                "                <tr>\n" +
                "                    <th scope=\"col\">" + "ID" + "</th>\n" +
                "                    <th scope=\"col\">" + "Name" + "</th>\n" +
                "                    <th scope=\"col\">" + "Surname" + "</th>\n" +
                "                    <th scope=\"col\">" + "Phone" + "</th>\n" +
                "                    <th scope=\"col\">" + "Is blocked" + "</th>\n" +
                "                    <th scope=\"col\">" + "Balance" + "</th>\n" +
                "                    <th scope=\"col\">" + "Services list" + "</th>\n";

        String context = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
        for (AbonentWithBalance ab : abonents) {
            String blocked = (ab.isBlocked() == 1) ? "YES" : "NO";
            table += "<tr>\n" +
                    "<td>" + ab.getId() + "</td>\n" +
                    "<td>" + ab.getName() + "</td>\n" +
                    "<td>" + ab.getSurname() + "</td>\n" +
                    "<td>" + ab.getPhone() + "</td>\n" +
                    "<td>" + blocked + "</td>\n" +
                    "<td>" + ab.getBalance() + "</td>\n" +
                    "<td>" + "<a href= " + context + CommandURL.pay(ab.getName(), ab.getSurname()) + ">Get services list</a>\n" + "</td>\n";
        }
        table += "  </tbody>\n" +
                "</table>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(table);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
