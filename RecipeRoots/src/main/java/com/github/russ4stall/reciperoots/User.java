package com.github.russ4stall.reciperoots;

import java.util.Date;

/**
 * Date: 6/12/13
 * Time: 1:54 PM
 *
 * @author Russ Forstall
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date registeredOn;
    private Date lastLoginOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(Date lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }
}
