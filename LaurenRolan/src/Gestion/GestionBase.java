package Gestion;

import BeanEntity.ComptesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;

public class GestionBase {
    EntityManager em;
    boolean crediter(int id, double value) {
        ComptesEntity toCredit = getCompte(id);
        if(toCredit == null) {
            System.out.println("Null at crediter");
            return false;
        }
        toCredit.setSolde(toCredit.getSolde() + value);
        updateCompte(toCredit);
        return true;
    }

    boolean debiter(int id, double value) {
        ComptesEntity toDebit = getCompte(id);
        if(toDebit == null) {
            System.out.println("Null at debiter");
            return false;
        }
        toDebit.setSolde(toDebit.getSolde() - value);
        updateCompte(toDebit);
        return true;
    }

    double retourner(int id) {
        ComptesEntity toReturn = getCompte(id);
        if(toReturn == null) {
            System.out.println("Null at retourner");
        }
        return toReturn.getSolde();
    }

    boolean updateCompte(ComptesEntity compte) {
        EntityTransaction tx = null;

        Session session = null;

        session = em.unwrap(Session.class);
        tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery(
                "update ComptesEntity set solde = :solde" + " where numerocompte = :id");
        query.setParameter("solde", compte.getSolde());
        query.setParameter("id", compte.getNumerocompte());
        int result = query.executeUpdate();
        tx.commit();
        return true;
    }

    ComptesEntity getCompte(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
        em = emf.createEntityManager();

        Query query = em.createQuery("from ComptesEntity compte where compte.numerocompte= :id");
        query.setParameter("id", id);
        List results = query.getResultList();

        ComptesEntity compte = null;

        if (!results.isEmpty()) //The account exists
             return (ComptesEntity) results.get(0);

        return compte;
    }

}
