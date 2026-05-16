package org.myapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;



@WebServlet("/students")
public class StudentListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<String[]> students = getStudentData();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student List</title>");
        out.println("  <style>");
        out.println("    body { font-family: Arial; margin: 30px; }");
        out.println("    h1   { color: #333; }");
        out.println("    table{ border-collapse: collapse;");
        out.println("           width: 60%; }");
        out.println("    th   { background: #4CAF50; color: white;");
        out.println("           padding: 10px; }");
        out.println("    td   { padding: 8px; text-align: center;");
        out.println("           border-bottom: 1px solid #ddd; }");
        out.println("    tr:hover { background-color: #f5f5f5; }");
        out.println("  </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Student List</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Age</th>");
        out.println("</tr>");

        for(String[] student:students) {
            out.println("  <tr>");
            out.println("    <td>" + student[0] + "</td>"); // ID
            out.println("    <td>" + student[1] + "</td>"); // Name
            out.println("    <td>" + student[2] + "</td>"); // Email
            out.println("    <td>" + student[3] + "</td>"); // Age
            out.println("  </tr>");

        }
            out.println("</table>");
            out.println("<br><a href='index.html'>← Back to Home</a>");
            out.println("</body>");
            out.println("</html>");



        }
    private List<String[]> getStudentData(){
        List<String[]> list = new ArrayList<>();

        list.add(new String[]{"1", "Divesh Patel", "Email", "24"});
        list.add(new String[]{"2", "Priya Singh", "priya@email.com", "21"});
        list.add(new String[]{"3", "Amit Kumar","amit@email.com", "22"});
        list.add(new String[]{"4", "Neha Patel", "neha@email.com", "20"});

        return list;
    }

}
