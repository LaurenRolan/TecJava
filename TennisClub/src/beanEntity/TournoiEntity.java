package beanEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class TournoiEntity {

    private int codeTournoi;
    private String nom;
    private Date date;
    private String lieu;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Id
    public int getCodeTournoi() {
        return codeTournoi;
    }

    public void setCodeTournoi(int tournoi) {
        this.codeTournoi = tournoi;
    }
}
