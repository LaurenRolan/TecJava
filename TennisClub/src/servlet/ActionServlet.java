package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "actionServlet", urlPatterns = {"/action"})
public class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String email = req.getParameter("email"),
                pass = req.getParameter("password"),
                action = req.getParameter("code");

        out.write("<html><body><div id='info' style='text-align: center;'>");

        out.write("<p> Bonjour, " + email + " </p>");

        RequestDispatcher rdObj;

        if("".equals(email) || "".equals(pass)) {
            out.write("<p id='errMsg' style='color: red; font-size: larger;'>Please Enter Both Username & Password... !</p>");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req, resp);
        } else {
            if(action.equals("L")) {
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login");
                dispatcher.forward(req, resp);
            }
            System.out.println(action);
        }
        out.write("</div></body></html>");
        out.close();
    }
}
