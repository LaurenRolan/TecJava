package servlet;

import bean.Inscription;
import beanEntity.InscriptionEntity;

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

        if(tournoi.equals("")) { //Pas de code tournoi
            rdObj = getServletContext()
                    .getRequestDispatcher("/InscriptionTournois.jsp");
        } else {
            //Enregistrer inscription
            int codeTournoi = Integer.parseInt(tournoi);

            if(inscription(req, res, codeTournoi) == 0) { //Error
                req.setAttribute("Sucess", false);
            } else {
                req.setAttribute("Sucess", true);
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

        //TODO
        Query inscriptionQuery = em.createQuery("insert into InscriptionEntity(numeroadherent, codetournoi)");
        inscriptionQuery.setParameter("code", code);
        return id;
    }

}
