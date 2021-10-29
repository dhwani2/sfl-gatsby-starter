package com.example.byblos;

// general classes between employees and customers (since their methods aren't tp be implemented yet)

public class User {

    // Instance variables in common between employees (branch) and customers

    protected String username;
    protected String name;
    protected String password;
    protected String role;

    public User() {
    }

    public User(String username, String password, String name, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // getter methods

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

}
