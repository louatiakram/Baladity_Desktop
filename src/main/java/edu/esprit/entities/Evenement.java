package edu.esprit.entities;

import edu.esprit.services.IService;

import java.util.Objects;
import java.util.Set;

public class Evenement{
    private int id_E;
    private String nom_E;
    private int id_user;
    private String date_DHE;
    private String date_DHF;
    private int capacite_E;
    private String categorie_E;

    public Evenement(){

    }

    public Evenement(int id_E, String nom_E, int id_user, String date_DHE, String date_DHF, int capacite_E, String categorie_E) {
        this.id_E = id_E;
        this.nom_E = nom_E;
        this.id_user = id_user;
        this.date_DHE = date_DHE;
        this.date_DHF = date_DHF;
        this.capacite_E = capacite_E;
        this.categorie_E = categorie_E;
    }

    public Evenement(int id_user, String nom_E, String date_DHE, String date_DHF, int capacite_E, String categorie_E) {
        this.id_user=id_user;
        this.nom_E=nom_E;
        this.date_DHE = date_DHE;
        this.date_DHF = date_DHF;
        this.capacite_E = capacite_E;
        this.categorie_E = categorie_E;
    }

    public int getId_E() {
        return id_E;
    }

    public void setId(int id) {
        this.id_E = id_E;
    }

    public String getNomEvent() {
        return nom_E;
    }

    public void setNomEvent(String nom_E) {
        this.nom_E = nom_E;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDateEtHeureDeb() {
        return date_DHE;
    }

    public void setDateEtHeureDeb(String date_DHE) {
        this.date_DHE = date_DHE;
    }

    public String getDateEtHeureFin() {
        return date_DHF;
    }

    public void setDateEtHeureFin(String date_DHF) {
        this.date_DHF = date_DHF;
    }

    public int getCapaciteMax() {
        return capacite_E;
    }

    public void setCapaciteMax(int capacite_E) {
        this.capacite_E = capacite_E;
    }

    public String getCategorie() {
        return categorie_E;
    }

    public void setCategorie(String categorie_E) {
        this.categorie_E = categorie_E;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id_E=" + id_E +
                ", nom_E='" + nom_E + '\'' +
                ", id_user=" + id_user +
                ", date_DHE='" + date_DHE + '\'' +
                ", date_DHF='" + date_DHF + '\'' +
                ", capacite_E=" + capacite_E +
                ", categorie_E='" + categorie_E + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evenement evenement)) return false;
        return getId_E() == evenement.getId_E() && Objects.equals(getNomEvent(), evenement.getNomEvent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_E(), getNomEvent());
    }

}