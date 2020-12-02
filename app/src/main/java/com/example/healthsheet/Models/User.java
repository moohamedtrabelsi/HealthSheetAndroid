package com.example.healthsheet.Models;

import java.util.ArrayList;

public class User  {


    public static User usercur = new User();

    String password;
    String username;
    String firstname;
    String lastname;
    String email;
    Role role;
    ArrayList<User> relations ;
    ArrayList<String> listdp;


    public Role getRole() {
        return role;
    }

    public User(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {
    }


    public User(String username, String firstName, String lastName, String email, ArrayList<String> listdp) {
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.listdp = listdp;
    }

    public ArrayList<User> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<User> relations) {
        this.relations = relations;
    }

    public ArrayList<String> getListdp() {
        return listdp;
    }

    public void setListdp(ArrayList<String> listdp) {
        this.listdp = listdp;
    }

    public User(String password, String username, String firstName, String lastName, String email, Role role, ArrayList<User> listdp) {

        this.password = password;
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.role = role;
        this.relations = listdp;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
