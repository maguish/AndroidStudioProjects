package com.example.exo9;

public class Evenement {
    private String nom, debut, fin, date;

    public Evenement(String nom, String debut, String fin, String date) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Evenement [nom=" + nom + ", debut=" + debut + ", fin=" + fin + ", date=" + date+ "]";
    }
}
