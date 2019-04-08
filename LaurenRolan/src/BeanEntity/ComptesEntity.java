package BeanEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comptes", schema = "lrolan", catalog = "livres")
public class ComptesEntity {
    private int numerocompte;
    private double solde;

    @Id
    @Column(name = "numerocompte")
    public int getNumerocompte() {
        return numerocompte;
    }

    public void setNumerocompte(int numerocompte) {
        this.numerocompte = numerocompte;
    }

    @Basic
    @Column(name = "solde")
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComptesEntity that = (ComptesEntity) o;
        return numerocompte == that.numerocompte &&
                Double.compare(that.solde, solde) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerocompte, solde);
    }
}
