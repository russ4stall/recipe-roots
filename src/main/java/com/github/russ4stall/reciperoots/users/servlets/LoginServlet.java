package com.github.russ4stall.reciperoots.users.servlets;

import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.users.dao.UsersDao;
import com.github.russ4stall.reciperoots.users.dao.UsersDaoImpl;
import com.google.common.base.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Date: 6/11/13
 * Time: 4:25 PM
 *
 * @author Russ Forstall
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink = null;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);

        String email = req.getParameter("email"); //from registration page
        String loginError = null;
        String sValidUser = req.getParameter("validUser");
        boolean validUser = Boolean.valueOf(sValidUser);

        if (!validUser) {
            loginError = "Invalid email address or password.";
        }else{
            loginError = null;
        }
        req.setAttribute("email", email);
        req.setAttribute("loginError", loginError);
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        UsersDao usersDao = new UsersDaoImpl();
        boolean emailExists = usersDao.emailExistsCheck(email);
        boolean validUser = false;

            if (emailExists) {
                user = usersDao.login(email, password);
                if (Objects.equal(password, user.getPassword())) {
                    validUser = true;
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", user);
                } else {
                    validUser = false;
                }
            }
        if (validUser) {
            resp.sendRedirect("/myrecipes");
        } else {
            resp.sendRedirect("/login?validUser=" + validUser);
        }
    }
}
