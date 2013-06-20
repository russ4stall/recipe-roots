package com.github.russ4stall.reciperoots.users;

import com.sun.java.swing.plaf.motif.MotifFileChooserUI;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date: 6/18/13
 * Time: 3:53 PM
 *
 * @author Russ Forstall
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){


            response.sendRedirect("/login?validUser=true");
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
