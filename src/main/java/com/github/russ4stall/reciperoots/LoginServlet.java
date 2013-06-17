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
        boolean emailExists = false;
        boolean validUser = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


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

        System.out.println(emailExists); //for testing

            if (emailExists) {

                String query = "SELECT password, id, name, email FROM users WHERE email=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));

                if (Objects.equal(password, resultSet.getString("password"))) {
                    validUser = true;
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", user);

                } else {
                    validUser = false;
                }

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

        if (validUser) {
            resp.sendRedirect("/myrecipes");
        } else {
            resp.sendRedirect("/login?validUser=" + validUser);
        }

    }
}
