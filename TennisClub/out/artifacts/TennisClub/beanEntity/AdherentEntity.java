package beanEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "adherent", schema = "lrolan", catalog = "tennis")
public class AdherentEntity {
    private int numeroadherent;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String password;

    @Id
    @Column(name = "numeroadherent")
    public int getNumeroadherent() {
        return numeroadherent;
    }

    public void setNumeroadherent(int numeroadherent) {
        this.numeroadherent = numeroadherent;
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
    @Column(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdherentEntity that = (AdherentEntity) o;
        return numeroadherent == that.numeroadherent &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroadherent, nom, prenom, adresse, telephone, email, password);
    }
}
