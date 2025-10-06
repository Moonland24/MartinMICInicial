/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Inmaculada
 */
@WebServlet(name = "ProcesaForm", urlPatterns = {"/procesaform"})
public class ProcesaForm extends HttpServlet {

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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado Formulario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Datos recibidos</h1>");
            out.println("<ul>");

            Enumeration<String> parametros = request.getParameterNames();

            while (parametros.hasMoreElements()) {
                String nombre = parametros.nextElement();
                String titulo = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);

                if (nombre.equalsIgnoreCase("preferencias")) {
                    String[] valores = request.getParameterValues(nombre);
                    out.println("<li>" + titulo + ": ");
                    if (valores != null) {
                        for (String v : valores) {
                            out.print(v + " ");
                        }
                    }
                    out.println("</li>");
                } 
                else if (nombre.equalsIgnoreCase("fecha_nacimiento")) {
                    String fechaInput = request.getParameter(nombre);

                    if (fechaInput != null && !fechaInput.trim().isEmpty()) {
                        try {
                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

                            Date fecha = inputFormat.parse(fechaInput);
                            String fechaFormateada = outputFormat.format(fecha);

                            out.println("<li>" + titulo + ": " + fechaFormateada + "</li>");
                        } catch (ParseException e) {
                            out.println("<li>" + titulo + ": Formato de fecha inválido </li>");
                        }
                    } else {
                        out.println("<li>" + titulo + ": No ingresada </li>");
                    }
                } 
                else {
                    String valor = request.getParameter(nombre);
                    out.println("<li>" + titulo + ": " + valor + "</li>");
                }
            }

            out.println("</ul>");
            out.println("<p><a href=\"index.html\">Volver al índice</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

/*
else if (nombre.equalsIgnoreCase("fecha_nacimiento)){"
    String fechaInput = request.getParameter(nombre);
    SimpleDateFormat inputFormat = SimpleDateFormat("yyy-mm-dd");
    SimpleDateFormat outputFormat = new SimpleDateFormat ("dd-mm-yyy");
    Date fecha = inputFormat.parse(fechaInput);
    String fechaFormateada = outputFormat(fecha);
    out.println("<li>" + titulo + ": " + fechaFormateada + "</li>");
*/
