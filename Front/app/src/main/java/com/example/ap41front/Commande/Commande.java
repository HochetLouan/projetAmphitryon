package com.example.ap41front.Commande;

public class Commande {
    private int id;
    private String etat;
    private int table;

    public Commande(int id, String etat, int table) {
        this.id = id;
        this.etat = etat;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
