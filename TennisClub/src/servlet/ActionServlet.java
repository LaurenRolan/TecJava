package servlet;

import beanEntity.AdherentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "actionServlet")
public class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<html><body><div id='info' style='text-align: center;'>");

        // Post Parameters From The Request
        String email = req.getParameter("email").trim(),
                pass = req.getParameter("password").trim();

        // Creating The 'RequestDispatcher' Object For Forwading The HTTP Request
        RequestDispatcher rdObj = null;

        // Checking For Null & Empty Values
        if("".equals(email) || "".equals(pass)) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please Enter Both Username & Password... !</p>");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req, resp);
        } else {
            // Call encryption
            String hashed = CryptoFunction.encryptThisString(pass);
            //Do HQL stuff

            try {

                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();

                Query query = session.createQuery("FROM AdherentEntity WHERE email=" + email);
                AdherentEntity adherentEntity = (AdherentEntity) query.uniqueResult();

                session.close();

                System.out.println(adherentEntity.getNom() + " " + adherentEntity.getPrenom());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            req.getSession().setAttribute("uname", email);
            resp.sendRedirect("welcomeServlet");
        }
        out.write("</div></body></html>");
        out.close();
    }
}
