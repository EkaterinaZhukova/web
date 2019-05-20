package controller.command.tag;

import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AbonentTableTag extends SimpleTagSupport {

    private List<AbonentWithBalance> abonents;

    public void setAbonents(List<AbonentWithBalance> abonents) {
        this.abonents = abonents;
    }


    public void doTag() throws JspException {

        PageContext pageContext = (PageContext) this.getJspContext();
        JspWriter out = pageContext.getOut();
        String langParameter = (String) this.getJspContext().getAttribute("lang", PageContext.SESSION_SCOPE);
        Locale loc = Locale.forLanguageTag(langParameter);
        if (loc == null) {
            loc = Locale.forLanguageTag("en");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("controller.resources.locale", loc, this.getClass().getClassLoader());

        String table = "<table class=\"table\">\n" +
                "                <thead>\n" +
                "                <tr>\n" +
                "                    <th scope=\"col\">" + bundle.getString("ID") + "</th>\n" +
                "                    <th scope=\"col\">" + bundle.getString("Name") + "</th>\n" +
                "                    <th scope=\"col\">" + bundle.getString("Surname") + "</th>\n" +
                "                    <th scope=\"col\">" + bundle.getString("Phone") + "</th>\n" +
                "                    <th scope=\"col\">" + bundle.getString("IsBlocked") + "</th>\n" +
                "                    <th scope=\"col\">" + bundle.getString("Balance") + "</th>\n" +
                "                    <th scope=\"col\">" + bundle.getString("ServicesList") + "</th>\n";

        String context = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
        for (AbonentWithBalance ab : abonents) {
            String blocked = (ab.isBlocked() == 1) ? "YES" : "NO";
            table += "<tr>\n" +
                    "<td>" + ab.getId() + "</td>\n" +
                    "<td>" + ab.getName() + "</td>\n" +
                    "<td>" + ab.getSurname() + "</td>\n" +
                    "<td>" + ab.getPhone() + "</td>\n" +
                    "<td>" + blocked + "</td>\n" +
                    "<td>" +"$"+ ab.getBalance() + "</td>\n" +
                    "<td>" + "<a href= " + context + CommandURL.pay(ab.getName(), ab.getSurname()) + ">"+ bundle.getString("GetServiceList") +"</a>\n" + "</td>\n";
        }
        table += "  </tbody>\n" +
                "</table>";
        try {
            out.write(table);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
//        return SKIP_BODY;
    }
}
