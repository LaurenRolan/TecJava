package beanEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdherentEntity {
    private int numeroAdherent;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String password;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    public int getNumeroAdherent() {
        return numeroAdherent;
    }

    public void setNumeroAdherent(int numeroAdherent) {
        this.numeroAdherent = numeroAdherent;
    }
}
