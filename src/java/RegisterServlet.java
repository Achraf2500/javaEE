/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MAJORINFO
 */
@WebServlet(urlPatterns = {"/signup"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request RegisterServlet request
     * @param response RegisterServlet response
     * @throws ServletException if a RegisterServlet error occurs
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("auth/registration.jsp");
            dispatcher.forward(request, response);
        }
    }

    public boolean CheckAvailability(String email, String name) {
        boolean status = false;
        try {
            try (Connection connection = ConnectDB.getConnection()) {
                PreparedStatement pst;
                ResultSet rs;

                pst = connection.prepareStatement("select * from login where email=? or name=?");
                pst.setString(1, email);
                pst.setString(2, name);
                rs = pst.executeQuery();
                status = rs.next();

                rs.close();
                pst.close();
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return status;
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                try (Connection connection = ConnectDB.getConnection()) {
                    PreparedStatement pst;

                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String pass = request.getParameter("pass");
                    if (!CheckAvailability(email.toLowerCase(), name)) {
                        pst = connection.prepareStatement("insert into login(name,email,pass)values(?,?,?)");
                        pst.setString(1, name.toLowerCase());
                        pst.setString(2, email.toLowerCase());
                        pst.setString(3, pass);
                        pst.executeUpdate();

                        pst.close();
                        connection.close();
                    }else{
                        response.setContentType("text/html;charset=UTF-8");
                        request.setAttribute("error", "<h3 style=\"color:#e74c3c;\">User already exists with this email or name</h3>");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("auth/registration.jsp");
                        dispatcher.forward(request, response);
                    }

                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request RegisterServlet request
     * @param response RegisterServlet response
     * @throws ServletException if a RegisterServlet error occurs
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
     * @param request RegisterServlet request
     * @param response RegisterServlet response
     * @throws ServletException if a RegisterServlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        register(request, response);
    }

    /**
     * Returns a short description of the RegisterServlet.
     *
     * @return a String containing RegisterServlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
