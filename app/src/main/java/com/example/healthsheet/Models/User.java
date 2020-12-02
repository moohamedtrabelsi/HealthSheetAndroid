package com.example.healthsheet.Models;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class User extends RecyclerView.ViewHolder {
    String password;
    String username;
    String firstName;
    String lastName;
    String email;
    Role role;
    ArrayList<User> listdp ;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {
    }

    public ArrayList<User> getListdp() {
        return listdp;
    }

    public void setListdp(ArrayList<User> listdp) {
        this.listdp = listdp;
    }

    public User(String password, String username, String firstName, String lastName, String email, Role role, ArrayList<User> listdp) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.listdp = listdp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
