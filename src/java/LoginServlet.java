/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author MAJORINFO
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request LoginServlet request
     * @param response LoginServlet response
     * @throws ServletException if a LoginServlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if(token != null){
            response.setStatus(HttpServletResponse.SC_FOUND);
            response.setHeader("Location", "../dashboard");
            response.sendRedirect("../dashboard");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("auth/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            try (Connection connection = ConnectDB.getConnection()) {
                PreparedStatement pst;
                ResultSet rs;
                String name = request.getParameter("email").toLowerCase();
                String pass = request.getParameter("pass");
                pst = connection.prepareStatement("select * from login where email=? and pass=?");
                pst.setString(1, name);
                pst.setString(2, pass);
                rs = pst.executeQuery();
                boolean status = rs.next();
                if (status) {
                    HttpSession session = request.getSession();
                    session.setAttribute("token", name);
                    response.setStatus(HttpServletResponse.SC_FOUND);
                    response.setHeader("Location", "../dashboard");
                    response.sendRedirect("../dashboard");
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    request.setAttribute("error", "<h3 style=\"color:#e74c3c;\">Login failed</h3>");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("auth/login.jsp");
                    dispatcher.forward(request, response);
                }

                rs.close();
                pst.close();
                connection.close();
            }
        } catch (ServletException | IOException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request LoginServlet request
     * @param response LoginServlet response
     * @throws ServletException if a LoginServlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request LoginServlet request
     * @param response LoginServlet response
     * @throws ServletException if a LoginServlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        login(request, response);
    }

    /**
     * Returns a short description of the LoginServlet.
     *
     * @return a String containing LoginServlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
