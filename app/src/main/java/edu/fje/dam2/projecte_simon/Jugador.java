package edu.fje.dam2.projecte_simon;

public class Jugador {

    private String id;
    private String nom;
    private String punts;

    public Jugador(){
    }

    public Jugador(String id, String nom, String punts) {
        this.id = id;
        this.nom = nom;
        this.punts = punts;
    }


    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPunts() {
        return punts;
    }

    public void setPunts(String punts){
        this.punts = punts;
    }

}
