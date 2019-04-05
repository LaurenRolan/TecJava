package servlet;

import beanEntity.AdherentEntity;

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
import java.util.List;

//https://openclassrooms.com/fr/courses/626954-creez-votre-application-web-avec-java-ee/622596-la-session-connectez-vos-clients

@WebServlet(name = "LoginServlet",  urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email"),
                pass = req.getParameter("password");

        String hashed = CryptoFunction.encryptThisString(pass);
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("TennisUnit");
            EntityManager em = emf.createEntityManager();
            System.out.println("Created em");

            Query query = em.createQuery("from AdherentEntity adherent where adherent.email= :email");
            query.setParameter("email", email);
            System.out.println(query.toString());
            List results = query.getResultList();

            AdherentEntity adherentEntity = null;

            RequestDispatcher rdObj;

            if (!results.isEmpty()) { //The email exists
                adherentEntity = (AdherentEntity) results.get(0);
                if(hashed.equals(adherentEntity.getPassword())) {
                    System.out.println("Success");

                    HttpSession session = req.getSession();
                    session.setAttribute("nom", adherentEntity.getNom());
                    session.setAttribute("prenom", adherentEntity.getPrenom());
                    session.setAttribute("numeroAdherent", adherentEntity.getNumeroadherent());

                    rdObj = getServletContext()
                            .getRequestDispatcher("/adherent");
                    rdObj.forward(req, resp);

                } else {
                    System.out.println("Different hash");

                    rdObj = getServletContext()
                            .getRequestDispatcher("/login.html");
                    rdObj.forward(req, resp);
                }
            }
            else {
                rdObj = getServletContext()
                        .getRequestDispatcher("/login.html");
                rdObj.forward(req, resp);
            }
            System.out.println(adherentEntity.getNom() + " " + adherentEntity.getPrenom());

        } catch (Exception e) {
            System.out.println("Didn't work");
            e.printStackTrace();
        }
    }

}
