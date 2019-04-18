package servlet;

import bean.Adherent;
import bean.Inscription;
import bean.Tournoi;
import beanEntity.AdherentEntity;
import beanEntity.InscriptionEntity;
import beanEntity.TournoiEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdherentServlet", urlPatterns = {"/adherent"})
public class AdherentServlet extends HttpServlet {
    private EntityManager em;
    private RequestDispatcher rdObj;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }
    private void handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int numeroAdherent = Integer.parseInt(session.getAttribute("numeroAdherent").toString());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TennisUnit");
        em = emf.createEntityManager();

        getPersonalInfo(req, res, numeroAdherent);
        getTournoiInfo(req, res, numeroAdherent);

        rdObj = getServletContext()
                 .getRequestDispatcher("/Adherent.jsp");
        rdObj.forward(req, res);
    }

    private void getTournoiInfo(HttpServletRequest req, HttpServletResponse res, int numeroAdherent) throws ServletException, IOException {
        Query inscriptionQuery = em.createQuery("from InscriptionEntity inscription where inscription.numeroadherent= :numero");
        inscriptionQuery.setParameter("numero", numeroAdherent);
        List results = inscriptionQuery.getResultList();

        InscriptionEntity inscriptionEntity = null;
        List<Inscription> inscriptions = new ArrayList<>();

        if (!results.isEmpty()) { //There are inscriptions
            System.out.println("Found results " + results.size());
            for (int i = 0; i < results.size(); i++) {
                inscriptionEntity = (InscriptionEntity) results.get(i);
                Inscription inscription = new Inscription(inscriptionEntity.getDateinscription());

                Query query2 = em.createQuery("from TournoiEntity tournoi where tournoi.codetournoi= :code");
                query2.setParameter("code", inscriptionEntity.getCodetournoi());
                List results2 = inscriptionQuery.getResultList();

                if (!results2.isEmpty()) { //The tounoi exists
                    TournoiEntity tournoiEntity = (TournoiEntity) results2.get(0);
                    inscription.setTournoi(new Tournoi(tournoiEntity.getNom(),
                            tournoiEntity.getLieu(), tournoiEntity.getDate(),
                            tournoiEntity.getCodetournoi()));
                } else {
                    System.out.println("Tournoi not found");
                    rdObj = getServletContext()
                            .getRequestDispatcher("/index.jsp");
                    rdObj.forward(req, res);
                }
                inscriptions.add(inscription);
            }
        }
        req.setAttribute("tournoiList", inscriptions);
    }

    private void getPersonalInfo(HttpServletRequest req, HttpServletResponse res, int numeroAdherent) throws ServletException, IOException {
        Query infoQuery = em.createQuery("from AdherentEntity adherent where adherent.numeroadherent= :numero");
        infoQuery.setParameter("numero", numeroAdherent);
        List infoResults = infoQuery.getResultList();
        if(infoResults.isEmpty()) {
            System.out.println("Error : info is empty");
            rdObj = getServletContext()
                    .getRequestDispatcher("/index.jsp");
            rdObj.forward(req, res);
        } else {
            AdherentEntity adherentEntity = (AdherentEntity) infoResults.get(0);
            Adherent adherent = new Adherent(adherentEntity.getNom(), adherentEntity.getPrenom(),
                    adherentEntity.getAdresse(), adherentEntity.getTelephone(), adherentEntity.getEmail(),
                    adherentEntity.getPassword(), adherentEntity.getNumeroadherent());
            req.setAttribute("info", adherent);
        }
    }

}
