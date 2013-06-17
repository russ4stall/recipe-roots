package com.github.russ4stall.reciperoots;

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
        String logInLink = null;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);


        String sPassMatch = req.getParameter("passMatch");
        boolean passMatch = Boolean.valueOf(sPassMatch);
        String sEmailMatch = req.getParameter("emailMatch");
        boolean emailMatch = Boolean.valueOf(sEmailMatch);
        String sEmailExists = req.getParameter("emailExists");
        boolean emailExists = Boolean.valueOf(sEmailExists);
        String emailError = null;
        String passMatchError = null;
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
        boolean passMatch = false;
        boolean emailMatch = false;
        boolean emailExists = false;

        if (Objects.equal(email, email2)) {
            emailMatch = true;
        }
        if (Objects.equal(password, password2)) {
            passMatch = true;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (passMatch && emailMatch) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Couldn't load the MySQL driver!", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

                preparedStatement = connection.prepareStatement("SELECT count(1) FROM users WHERE email=?");
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                emailExists = resultSet.getInt("count(1)") == 1;

                if (!emailExists) {

                    String query = "INSERT INTO users (name, email, password, registered_on, last_login_on) " +
                            "VALUES (?, ?, ?, now(), now())";

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, req.getParameter("name"));
                    preparedStatement.setString(2, req.getParameter("email"));
                    preparedStatement.setString(3, req.getParameter("password"));

                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {

                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        // don't care
                    }
                }

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        // don't care
                    }
                }
            }

            if (!emailExists) {
                resp.sendRedirect("/login?email=" + email);
            } else{
                resp.sendRedirect("/register?emailExists=" + emailExists + "&name=" + name + "&email=" + email + "&email2=" + email2);
            }

        } else {

            resp.sendRedirect("/register?passMatch=" + passMatch + "&emailMatch=" + emailMatch + "&emailExists=" + emailExists
                    + "&name=" + name + "&email=" + email + "&email2=" + email2);
        }

    }

}