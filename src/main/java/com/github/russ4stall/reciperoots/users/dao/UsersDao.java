package com.github.russ4stall.reciperoots.users.dao;

import com.github.russ4stall.reciperoots.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Date: 6/18/13
 * Time: 9:48 AM
 *
 * @author Russ Forstall
 */
public interface UsersDao {

    /**
     *
     * adds user to user table in database upon registration
     *
     * @param name users name obtained from registration page
     * @param email users email obtained from registration page
     * @param password users password obtained from registration page
     */
    void addUser(String name, String email, String password);

    /**
     *
     * checks if user is in database and authenticates password
     * returns a the user object
     *
     * @param email users email obtained from login page
     * @param password users password obtained from login page
     * @return User
     */
    User login(String email, String password);

    /**
     *
     * checks to see if email already exists in database
     *
     * @param email users email obtained from registration page
     * @return boolean emailExists
     */
    boolean emailExistsCheck(String email);





}
