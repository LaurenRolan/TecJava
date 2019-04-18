package beanEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "inscription", schema = "lrolan", catalog = "tennis")
public class InscriptionEntity {
    private Integer numeroadherent;
    private Integer codetournoi;
    private Date dateinscription;

    @Basic
    @Id
    @Column(name = "numeroadherent")
    public Integer getNumeroadherent() {
        return numeroadherent;
    }

    public void setNumeroadherent(Integer numeroadherent) {
        this.numeroadherent = numeroadherent;
    }

    @Basic
    @Column(name = "codetournoi")
    public Integer getCodetournoi() {
        return codetournoi;
    }

    public void setCodetournoi(Integer codetournoi) {
        this.codetournoi = codetournoi;
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
                Objects.equals(codetournoi, that.codetournoi) &&
                Objects.equals(dateinscription, that.dateinscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroadherent, codetournoi, dateinscription);
    }
}
