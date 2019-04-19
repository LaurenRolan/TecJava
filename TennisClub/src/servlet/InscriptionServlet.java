package servlet;

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

@WebServlet(name = "InscriptionServlet", urlPatterns = {"/inscription"})
public class InscriptionServlet extends HttpServlet {
    RequestDispatcher rdObj;
    private EntityManager em;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    private void handle (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String tournoi = req.getParameter("tournoi");
        System.out.println("In handle of InscriptionServlet");

        if(tournoi == null) { //Pas de code tournoi
            getTournoiList(req, res);
            rdObj = getServletContext()
                    .getRequestDispatcher("/InscriptionTournois.jsp");
        } else {
            //Enregistrer inscription
            int codeTournoi = Integer.parseInt(tournoi);

            if(inscription(req, res, codeTournoi) == 0) { //Error
                req.setAttribute("Success", false);
            } else {
                req.setAttribute("Success", true);
            }

            rdObj = getServletContext()
                    .getRequestDispatcher("/InscriptionStatus.jsp");
        }
        rdObj.forward(req, res);
    }

    private long inscription(HttpServletRequest req, HttpServletResponse res, int code) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TennisUnit");
        em = emf.createEntityManager();

        HttpSession session = req.getSession();
        int numeroAdherent = Integer.parseInt(session.getAttribute("numeroAdherent").toString());

        InscriptionEntity inscriptionEntity = new InscriptionEntity();
        inscriptionEntity.setCodetournoi(code);
        inscriptionEntity.setNumeroadherent(numeroAdherent);

        try {
            em.getTransaction().begin();
            em.persist(inscriptionEntity);
            em.getTransaction().commit();

            Query infoQuery = em.createQuery("from TournoiEntity tournoi where tournoi.codetournoi=:code");
            infoQuery.setParameter("code", code);

            List results = infoQuery.getResultList();
            if(!results.isEmpty()) {
                TournoiEntity tournoiEntity = (TournoiEntity) results.get(0);
                req.setAttribute("tournoi", new Tournoi(tournoiEntity.getNom(), tournoiEntity.getLieu(),
                        tournoiEntity.getDate(), tournoiEntity.getCodetournoi()));
            } else {
                System.out.println("No tournois");
                return 0;
            }
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void getTournoiList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("In get tournoi list");
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("TennisUnit");
            EntityManager em = emf.createEntityManager();

            Query query = em.createQuery("from TournoiEntity");

            List results = query.getResultList();
            List<Tournoi> tournois = new ArrayList<>();
            if (!results.isEmpty()) { //There are tournois
                for (int i = 0; i < results.size(); i++) {
                    TournoiEntity tournoiEntity = (TournoiEntity) results.get(i);
                    tournois.add(new Tournoi(tournoiEntity.getNom(),
                            tournoiEntity.getLieu(), tournoiEntity.getDate(),
                            tournoiEntity.getCodetournoi()));
                    System.out.println(tournoiEntity.getNom());
                }
            }
            req.setAttribute("tournoiList", tournois);
        } catch (Exception e) {
            System.out.println("Error when getting tournois");
        }
    }

}
