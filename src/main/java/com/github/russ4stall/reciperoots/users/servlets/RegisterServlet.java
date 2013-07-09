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
 * Time: 4:58 PM
 *
 * @author Russ Forstall
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean passMatch = Boolean.valueOf(req.getParameter("passMatch"));
        boolean emailMatch = Boolean.valueOf(req.getParameter("emailMatch"));
        boolean emailExists = Boolean.valueOf(req.getParameter("emailExists"));
        String emailError = null;
        String passMatchError = null;
        String logInLink;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
            String signUpLink = "<a href=\"/register?passMatch=true&emailMatch=true\">Sign Up</a>";
            req.setAttribute("signUpLink", signUpLink);
        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);



        if (emailExists) {
            emailError = "That email already exists";
        } else {
            if (!passMatch) {
                passMatchError = "Passwords don't match";
            }
            if (!emailMatch) {
                emailError = "Emails don't match";
            }
        }
        req.setAttribute("passMatchError", passMatchError);
        req.setAttribute("emailError", emailError);
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String email2 = req.getParameter("email2");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        boolean passMatch = Objects.equal(password, password2);
        boolean emailMatch = Objects.equal(email, email2);
        boolean emailExists = false;

        if (passMatch && emailMatch) {
            UsersDao usersDao = new UsersDaoImpl();
            emailExists = usersDao.emailExistsCheck(email);
            if (!emailExists) {
                usersDao.addUser(name, email, password);
                resp.sendRedirect("/login?validUser=true&email=" + email);
            } else {
                resp.sendRedirect("/register?emailExists=" + emailExists + "&name=" + name + "&email=" + email + "&email2=" + email2);
            }
        } else {
            resp.sendRedirect("/register?passMatch=" + passMatch + "&emailMatch=" + emailMatch + "&emailExists=" + emailExists
                    + "&name=" + name + "&email=" + email + "&email2=" + email2);
        }
    }

}