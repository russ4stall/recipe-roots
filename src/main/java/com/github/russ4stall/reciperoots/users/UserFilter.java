package com.github.russ4stall.reciperoots.users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Date: 7/2/13
 * Time: 10:21 AM
 *
 * @author Russ Forstall
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user);
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
