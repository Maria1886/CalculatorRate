package com.example.calculatorrate;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Utilizator {


    int id;

    String email;
    String parola;
    String nume;
    byte[] imagine;





    public Utilizator(){

    }


    public Utilizator(String email, String parola) {
        this.email = email;
        this.parola = parola;

    }

    public Utilizator(String email, String parola, String nume, byte[] imagine) {
        this.email = email;
        this.parola = parola;
        this.nume = nume;
        this.imagine = imagine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }
}
