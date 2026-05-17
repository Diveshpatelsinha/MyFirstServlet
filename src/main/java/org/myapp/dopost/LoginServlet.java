package org.myapp.dopost;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DoPost/Login1")
public class LoginServlet extends HttpServlet {

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
