package Servlets;

import org.omg.CORBA.INTERNAL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "gestion", urlPatterns = {"/gestion"})
public class GestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idString = req.getParameter("id");

        System.out.println("In handle request");

        RequestDispatcher rdObj;
        if ("".equals(idString)) {
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req, res);
        } else {
            rdObj = req.getRequestDispatcher("/info.jsp");
            rdObj.forward(req, res);
        }
    }

}
