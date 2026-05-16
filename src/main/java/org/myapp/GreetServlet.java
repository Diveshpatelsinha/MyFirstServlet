package org.myapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/greet")
public class GreetServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


        String name = request.getParameter("name");

        if(name == null || name.trim().isEmpty()){
            name = "Guest";
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Greeting</title></head>");
        out.println("<body style='font-family:Arial; "  +
                "text-align:center; margin-top:80px;'>");
        out.println("<h1 style='color:green;'>");
        out.println("  Hello, " + name + "! 👋");
        out.println("</h1>");
        out.println("<p>You sent a <b>GET</b> request!</p>");
        out.println("<a href='index.html'>Go Back</a>");
        out.println("</body>");
        out.println("</html>");

    }
}
