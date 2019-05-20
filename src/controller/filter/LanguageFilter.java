package controller.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "languageFilter", urlPatterns = {"/*"})
public class LanguageFilter implements Filter {
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.print(request);
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("utf8");
        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        }
        chain.doFilter(request, response);

    }
}