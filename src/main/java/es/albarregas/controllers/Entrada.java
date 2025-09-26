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
 * @author manur
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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Entrada</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Entrada at " + request.getContextPath() + "</h1>");
            out.println("<p>¿Quieres volver atrás? Pulsa <a href=\".\">aquí</a>. El método de llamada es " + request.getMethod() + "</p>");
            out.println("<p>Estos son tus parámetros:</p>");
            if (!parametros.hasMoreElements()) {
                out.println("<p>Has escogido la opcion sin parametros</p>");
            } else {
                out.println("<ul>");
                while (parametros.hasMoreElements()) {
                    String nombreParametro = parametros.nextElement();
                    String valorParametros = request.getParameter(nombreParametro);
                    out.println("<li>" + nombreParametro + " = " + valorParametros + "</li>");
                }
                out.println("</ul>");
            }

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
        try (PrintWriter out = response.getWriter()) {

            Enumeration<String> parametros = request.getParameterNames();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Entrada</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Entrada at " + request.getContextPath() + "</h1>");
            out.println("<p>¿Quieres volver atrás? Pulsa <a href=\".\">aquí</a>. El método de llamada es " + request.getMethod() + "</p>");
            out.println("<p>Estos son tus parámetros:</p>");
            out.println("<ul>");
            while (parametros.hasMoreElements()) {
                String nombreParametro = parametros.nextElement();
                String valorParametros = request.getParameter(nombreParametro);
                out.println("<li>" + nombreParametro + " = " + valorParametros + "</li>");
            }
            out.println("</ul>");
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