package org.myapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String subject = request.getParameter("subject");
        String minAge = request.getParameter("minAge");
        String maxAge = request.getParameter("maxAge");

        int min = 0;
        int max = 100;
        try {
            if (minAge != null) min = Integer.parseInt(minAge);
            if (maxAge != null) max = Integer.parseInt(maxAge);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><body>");
        out.println("<h2>Search Results</h2>");

        // Show what was searched
        out.println("<p><b>Subject:</b> " +
                (subject != null ? subject : "All") + "</p>");
        out.println("<p><b>Age Range:</b> " +
                minAge + " to " + maxAge + "</p>");

        // In real app → call DAO with these filters
        out.println("<p>Results would appear here...</p>");

        out.println("</body></html>");
    }


    }

