package com.example.healthsheet.Models;

import java.util.ArrayList;

public class User  {


    public static User usercur = new User() ;

    String password;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public ArrayList<String> getListofdp() {
        return listofdp;
    }

    public void setListofdp(ArrayList<String> listofdp) {
        this.listofdp = listofdp;
    }

    String username;
    String roles;
    String firstname;
    String lastname;
    String email;
    Role role;
    ArrayList<User> relations ;
    ArrayList<String> listofdp;
    ArrayList<String> analyses;

    public ArrayList<String> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(ArrayList<String> analyses) {
        this.analyses = analyses;
    }

    public User(String password, String username, String roles, String firstname, String lastname, String email, Role role, ArrayList<User> relations, ArrayList<String> listofdp, ArrayList<String> analyses) {
        this.password = password;
        this.username = username;
        this.roles = roles;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.relations = relations;
        this.listofdp = listofdp;
        this.analyses = analyses;
    }

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

        this.password = "x"   ;
        this.username = "x";
        this.firstname = "a";
        this.lastname = "a";
        this.email = "hdg";
        this.role = new Role();
        this.listofdp = new ArrayList<>();
        this.relations = new ArrayList<>();
    }


    public User(String username, String firstName, String lastName, String email, ArrayList<String> listdp) {
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.listofdp = listdp;
    }

    public ArrayList<User> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<User> relations) {
        this.relations = relations;
    }

    public ArrayList<String> getListdp() {
        return listofdp;
    }

    public void setListdp(ArrayList<String> listdp) {
        this.listofdp = listdp;
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
