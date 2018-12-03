package com.example.mertcantoptas.volleyloginregister.Model;

public class User {

    private int id;
    private String nameSurname;
    private String email;
    private String country;
    private String city;
    private String password;


    public User(int id, String nameSurname, String email, String country, String city, String password) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.email = email;
        this.country = country;
        this.city = city;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
