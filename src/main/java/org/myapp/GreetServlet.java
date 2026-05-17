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

    @WebServlet("/DoPost/register")
    public static class RegisterServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            request.setCharacterEncoding("UTF-8");

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String agestr = request.getParameter("age");
            String gender = request.getParameter("gender");

            String[] hobbies = request.getParameterValues("hobbies");

            StringBuilder errors = new StringBuilder();

            if(name == null || name.trim().isEmpty()){
                errors.append("Full name is required<br>");
            }
            if(email == null || !email.contains("@")){
                errors.append("Valid email address is required<br>");
            }
            int age = 0;
            try {
                age = Integer.parseInt(agestr);
                if (age < 1 || age > 100) {
                    errors.append("Age must be between 1 and 100<br>");
                }
                }catch(NumberFormatException e){
                    errors.append("valid age is required<br>");
                }
                if (errors.length() > 0) {
                    sendError(response, errors.toString());
                    return;
                }
                String Hobbies = "None";
                if (hobbies != null && hobbies.length > 0) {
                    Hobbies = String.join(", ", hobbies);
                }

                sendSuccess(response, name, email, age, gender, Hobbies);
            }

        private void sendError(HttpServletResponse response, String errors) throws IOException{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h2 style='color:red;'> Validation Errors</h2>");
            out.println("<p>" + errors + "</p>");
            out.println("<a href='register.html'>← Go Back</a>");
            out.println("</body></html>");
        }


        private void sendSuccess(HttpServletResponse response, String name, String email, int age, String gender, String hobbies) throws IOException {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html><html>");
            out.println("<head><title>Registration Success</title></head>");
            out.println("<body style='font-family:Arial;margin:40px;'>");
            out.println("<div style='max-width:400px;margin:auto;" +
                    "padding:30px;border-radius:10px;" +
                    "box-shadow:0 2px 10px rgba(0,0,0,0.1);'>");
            out.println("<h2 style='color:green;'> Registration Successful!</h2>");
            out.println("<table style='width:100%;border-collapse:collapse;'>");

            printRow(out, "Name",    name);
            printRow(out, "Email",   email);
            printRow(out, "Age",     String.valueOf(age));
            printRow(out, "Gender",  gender);
            printRow(out, "Hobbies", hobbies);

            out.println("</table>");
            out.println("<br><a href='register.html'>← Register Another</a>");
            out.println("</div></body></html>");
        }

        private void printRow(PrintWriter out, String label, String value) {
            out.println("<tr style='border-bottom:1px solid #eee;'>");
            out.println("<td style='padding:8px;'><b>" +
                    label + ":</b></td>");
            out.println("<td style='padding:8px;'>" +
                    value + "</td>");
            out.println("</tr>");
        }

    }
}
