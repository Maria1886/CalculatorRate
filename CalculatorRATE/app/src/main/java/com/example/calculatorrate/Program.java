package com.example.calculatorrate;

public class Program {


    String ora;
    String sucursala;
    String manager;


    public Program(){

    }

    public Program(String ora, String sucursala, String manager) {
        this.ora = ora;
        this.sucursala = sucursala;
        this.manager = manager;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getSucursala() {
        return sucursala;
    }

    public void setSucursala(String sucursala) {
        this.sucursala = sucursala;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
