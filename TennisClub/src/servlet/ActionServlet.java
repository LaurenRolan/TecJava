package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "actionServlet", urlPatterns = {"/action"})
public class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email"),
                pass = req.getParameter("password"),
                action = req.getParameter("code");

        RequestDispatcher rdObj;

        if("".equals(email) || "".equals(pass)) {
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req, resp);
        } else {
            HttpSession session = req.getSession(false);

            if(session == null) { //Session n'existe pas
                if(action.equals("L")) {
                    System.out.println("Login");
                    rdObj = getServletContext()
                            .getRequestDispatcher("/login");
                    rdObj.forward(req, resp);
                }
                else {
                    rdObj = getServletContext()
                            .getRequestDispatcher("/login.html");
                    rdObj.forward(req, resp);
                }
            }
            else { //session existe
                System.out.println("Session existe");
                if(action.equals("A")) {
                    System.out.println("Adherent");
                    rdObj = getServletContext()
                            .getRequestDispatcher("/adherent");
                    rdObj.forward(req, resp);
                }
                else if (action.equals("I")) {
                    System.out.println("Inscription");
                    rdObj = getServletContext()
                            .getRequestDispatcher("/inscription");
                    rdObj.forward(req, resp);
                }
                else {
                    System.out.println("Menu");
                    rdObj = getServletContext()
                            .getRequestDispatcher("/Menu.jsp");
                    rdObj.forward(req, resp);
                }
            }
        }
    }
}
