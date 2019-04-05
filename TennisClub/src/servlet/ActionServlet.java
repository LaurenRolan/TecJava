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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "actionServlet", urlPatterns = {"/action"})
public class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String email = req.getParameter("email"),
                pass = req.getParameter("password");

        out.write("<html><body><div id='info' style='text-align: center;'>");

        out.write("<p> Bonjour, " + email + " </p>");

        RequestDispatcher rdObj;

        if("".equals(email) || "".equals(pass)) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please Enter Both Username & Password... !</p>");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req, resp);
        } else {
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
                if (!results.isEmpty())
                    adherentEntity = (AdherentEntity) results.get(0);

                System.out.println(adherentEntity.getNom() + " " + adherentEntity.getPrenom());

            } catch (Exception e) {
                System.out.println("Didn't work");
                e.printStackTrace();
            }
        }
        out.write("</div></body></html>");
        out.close();
    }
}
