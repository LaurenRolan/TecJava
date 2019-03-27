package beanEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class InscriptionEntity {
    private int codeTournoi;
    private int numeroAdherent;
    private Date dateInscrition;

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
}
