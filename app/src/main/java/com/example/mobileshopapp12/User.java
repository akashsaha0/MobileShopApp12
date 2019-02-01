package com.example.mobileshopapp12;
public class User{
    String fastname,lastname,email,contacno;

    public User() {
    }

    public User(String fastname, String lastname, String email, String contacno) {
        this.fastname = fastname;
        this.lastname = lastname;
        this.email = email;
        this.contacno = contacno;
    }

    public String getFastname() {
        return fastname;
    }

    public void setFastname(String fastname) {
        this.fastname = fastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacno() {
        return contacno;
    }

    public void setContacno(String contacno) {
        this.contacno = contacno;
    }
}