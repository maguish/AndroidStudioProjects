package com.example.exo8.data;

public class Trajet {
    private String depart, arrive, horaire;

    public Trajet(String depart, String arrive, String horaire) {
        this.depart = depart;
        this.arrive = arrive;
        this.horaire = horaire;
    }
    public String getDepart() {
        return depart;
    }
    public void setDepart(String depart) {
        this.depart = depart;
    }
    public String getArrive() {
        return arrive;
    }
    public void setArrive(String arrive) {
        this.arrive = arrive;
    }
    public String getHoraire() {
        return horaire;
    }
    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    @Override
    public String toString() {
        return depart + " ---> " + arrive
                + "\n" + horaire;
    }
}
