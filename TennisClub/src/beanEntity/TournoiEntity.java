package beanEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name= "tournoi", schema= "lrolan", catalog = "livres")
public class TournoiEntity {

    private String nom;
    private Date date;
    private String lieu;
    private int codetournoi;

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "lieu")
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Id
    @Column(name = "codetournoi")
    public int getCodetournoi() {
        return codetournoi;
    }

    public void setCodetournoi(int codetournoi) {
        this.codetournoi = codetournoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournoiEntity that = (TournoiEntity) o;
        return codetournoi == that.codetournoi &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(date, that.date) &&
                Objects.equals(lieu, that.lieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codetournoi, nom, date, lieu);
    }
}
