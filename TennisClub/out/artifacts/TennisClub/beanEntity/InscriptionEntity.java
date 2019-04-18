package beanEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name= "inscription",schema= "lrolan", catalog = "tennis")
public class InscriptionEntity {
    private int codeTournoi;
    private int numeroAdherent;
    private Date dateInscrition;
    private Integer numeroadherent;
    private Date dateinscription;

    public int getNumeroAdherent() {
        return numeroAdherent;
    }

    public void setNumeroAdherent(int numeroAdherent) {
        this.numeroAdherent = numeroAdherent;
    }

    public Date getDateInscrition() {
        return dateInscrition;
    }

    public void setDateInscrition(Date dateInscrition) {
        this.dateInscrition = dateInscrition;
    }

    @Id
    public int getCodeTournoi() {
        return codeTournoi;
    }

    public void setCodeTournoi(int codeTournoi) {
        this.codeTournoi = codeTournoi;
    }

    @Basic
    @Column(name = "numeroadherent")
    public Integer getNumeroadherent() {
        return numeroadherent;
    }

    public void setNumeroadherent(Integer numeroadherent) {
        this.numeroadherent = numeroadherent;
    }

    @Basic
    @Column(name = "dateinscription")
    public Date getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(Date dateinscription) {
        this.dateinscription = dateinscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscriptionEntity that = (InscriptionEntity) o;
        return Objects.equals(numeroadherent, that.numeroadherent) &&
                Objects.equals(dateinscription, that.dateinscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroadherent, dateinscription);
    }
}
