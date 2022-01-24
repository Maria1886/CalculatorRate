package com.example.calculatorrate;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Credite")
public class Credite {

    @PrimaryKey(autoGenerate = true)
    public int idCredit;

    public int salariu;
    public int nrRate;
    public int credit;


    public Credite(){

    }

    public Credite(int salariu, int nrRate, int credit) {
        this.salariu = salariu;
        this.nrRate = nrRate;
        this.credit = credit;
    }


    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    public int getNrRate() {
        return nrRate;
    }

    public void setNrRate(int nrRate) {
        this.nrRate = nrRate;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
