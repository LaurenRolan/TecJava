package beanEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tournoi", schema = "lrolan", catalog = "tennis")
public class TournoiEntity {
    private int codetournoi;
    private String nom;
    private String lieu;
    private Date date;

    @Id
    @Column(name = "codetournoi")
    public int getCodetournoi() {
        return codetournoi;
    }

    public void setCodetournoi(int codetournoi) {
        this.codetournoi = codetournoi;
    }

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "lieu")
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournoiEntity that = (TournoiEntity) o;
        return codetournoi == that.codetournoi &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(lieu, that.lieu) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codetournoi, nom, lieu, date);
    }
}
