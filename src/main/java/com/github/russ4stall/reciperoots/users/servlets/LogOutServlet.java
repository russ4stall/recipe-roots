package com.github.russ4stall.reciperoots.users.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date: 6/17/13
 * Time: 10:17 AM
 *
 * @author Russ Forstall
 */
public class LogOutServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.invalidate();
        resp.sendRedirect("/home");
    }
}
