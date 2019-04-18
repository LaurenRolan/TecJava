package bean;

import java.sql.Date;

public class Tournoi {
    private String nom;
    private String lieu;
    private Date date;
    private int codeTournoi;

    public Tournoi(String nom, String lieu, Date date, int codeTournoi) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.codeTournoi = codeTournoi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCodeTournoi() {
        return codeTournoi;
    }

    public void setCodeTournoi(int codeTournoi) {
        this.codeTournoi = codeTournoi;
    }
}
