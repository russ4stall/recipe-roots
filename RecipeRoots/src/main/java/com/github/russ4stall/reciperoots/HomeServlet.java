package com.github.russ4stall.reciperoots;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date: 6/12/13
 * Time: 12:13 PM
 *
 * @author Russ Forstall
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get recipes from the db to post on the Home page

        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}
