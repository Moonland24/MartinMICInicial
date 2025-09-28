/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 *
 * @author Inmaculada
 */
@WebServlet(name = "Entrada", urlPatterns = {"/Entrada"})
public class Entrada extends HttpServlet {
    
        /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            Enumeration<String> parametros = request.getParameterNames();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Respuesta del Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Respuesta del Servlet (GET)</h1>");

            if (!parametros.hasMoreElements()) {
                out.println("<p>esta llamada no incluye parámetros</p>");
            } else {
                out.println("<p>esta llamada contiene parámetros (con nombre y valor):</p>");
                out.println("<ul>");
                while (parametros.hasMoreElements()) {
                    String nombreParametro = parametros.nextElement();
                    String valorParametro = request.getParameter(nombreParametro);
                    out.println("<li>" + nombreParametro + " = " + valorParametro + "</li>");
                }
                out.println("</ul>");
            }

            out.println("<p><a href=\"index.html\">← Volver al índice</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
        /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Respuesta del Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Respuesta del Servlet (POST)</h1>");
            out.println("<p><a href=\"index.html\">Volver al índice</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
        /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
        @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
