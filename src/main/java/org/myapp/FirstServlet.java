package org.myapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/hello")
public class FirstServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("HelloServlet initialized!");
        // One-time setup code goes here
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");

    PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <title>Hello Servlet</title>");
        out.println("  <style>");
        out.println("    body { font-family: Arial; text-align: center;");
        out.println("           margin-top: 80px; }");
        out.println("    h1 { color: green; }");
        out.println("    p { font-size: 18px; }");
        out.println("  </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <h1>Hello, World!</h1>");
        out.println("  <p>Welcome to Servlets!</p>");
        out.println("  <p>Current Time: <b>" + new Date() + "</b></p>");
        out.println("  <p>Request Method: <b>" +
                request.getMethod() + "</b></p>");
        out.println("  <a href='index.html'>Go Back</a>");
        out.println("</body>");
        out.println("</html>");
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String username = request.getParameter("username");

        if(username == null || username.trim().isEmpty()){
            username = "Stranger";
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Hello " + username + "</title></head>");
        out.println("<body style='font-family:Arial; text-align:center;");
        out.println("             margin-top:80px;'>");
        out.println("  <h1 style='color:blue;'>Hello, " +
                username + "!</h1>");
        out.println("  <p>Your request was processed via: <b>" +
                request.getMethod() + "</b></p>");
        out.println("  <p>Time: <b>" + new Date() + "</b></p>");
        out.println("  <a href='index.html'>Go Back</a>");
        out.println("</body>");
        out.println("</html>");
    }

    public  void destroy(){
        System.out.println("Destroyed!");
    }

    @WebServlet("/DoPost/Login1")
    public static class LoginServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

            response.sendRedirect(
                    request.getContextPath()
                            + "/DoPost/Login1.html");
        }

        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException, IOException {

            request.setCharacterEncoding("utf-8");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(username == null || password == null ||
                    username.trim().isEmpty() ||
                    password.trim().isEmpty()) {

                sendResponse(response, false,
                        "username and password required!");

                return;
            }

            boolean isValid = checkCredentials(username, password);

            if(isValid) {
                sendResponse(response, true, "Welcome " + username + "! Login successful.");

            }else{
                sendResponse(response, false, "Invalid username or password!");
            }


        }

        private boolean checkCredentials(String username, String password) {
            return username.equals("admin") && password.equals("pass");
        }

        private void sendResponse(HttpServletResponse response, boolean b, String s) throws IOException {

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            String color = b? "green" : "red";


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Login Result</title></head>");
            out.println("<body style='font-family:Arial;" +
                    "text-align:center; margin-top:80px;'>");
            out.println("<h2 style='color:" + color + ";'>");
            out.println(" " + s);
            out.println("</h2>");
            out.println("<a href='Login1.html'>← Back to Login</a>");
            out.println("</body></html>");

        }


    }
}
