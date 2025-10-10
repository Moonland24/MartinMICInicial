/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Inmaculada
 */
@WebServlet(name = "ProcesaFormValidado", urlPatterns = {"/procesaformvalidado"})
public class ProcesaFormValidacion extends HttpServlet {
    
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

        boolean hayError = false;

        String nombre = request.getParameter("nombre");
        if (nombre == null || nombre.trim().isEmpty()) {
            hayError = true;
        }

        String fechaInput = request.getParameter("fecha_nacimiento");
        Date fecha = null;
        if (fechaInput == null || fechaInput.isEmpty()) {
            hayError = true;
        } else {
            try {
                fecha = new SimpleDateFormat("dd-MM-yyyy").parse(fechaInput);
            } catch (ParseException e) {
                hayError = true;
            }
        }

        String salario = request.getParameter("salario");
        if (salario == null || salario.trim().isEmpty()) {
            hayError = true;
        }

        String hijos = request.getParameter("hijos");
        if (hijos == null || hijos.trim().isEmpty()) {
            hayError = true;
        }

        String[] preferencias = request.getParameterValues("preferencias");

        try (PrintWriter out = response.getWriter()) {
            if (hayError) {
                out.println("<html><head><title>Formulario con errores</title></head><body>");
                out.println("<h2>Se han encontrado errores en el formulario</h2>");
                out.println("<form action='procesaformvalidado' method='post'>");
                out.println("Nombre: <input type='text' name='nombre' value='" + (nombre != null ? nombre : "") + "'><br><br>");
                out.println("Fecha de nacimiento: <input type='date' name='fecha_nacimiento' value='" + (fechaInput != null ? fechaInput : "") + "'><br><br>");
                out.println("Salario: <input type='number' step='0.01' name='salario' value='" + (salario != null ? salario : "") + "'><br><br>");
                out.println("Número de hijos: <input type='number' name='hijos' value='" + (hijos != null ? hijos : "") + "'><br><br>");
                out.println("Preferencias:<br>");
                String[] opciones = {"cine", "baile", "musica", "deporte", "viajes"};
                for (String op : opciones) {
                    boolean checked = preferencias != null && Arrays.asList(preferencias).contains(op);
                    out.println("<input type='checkbox' name='preferencias' value='" + op + "' " + (checked ? "checked" : "") + "> " + op + "<br>");
                }
                out.println("<br><input type='submit' value='Enviar'>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<html><head><title>Resultado Formulario</title></head><body>");
                out.println("<h1>Datos recibidos correctamente</h1>");
                out.println("<ul>");
                out.println("<li>Nombre: " + nombre + "</li>");
                out.println("<li>Fecha de nacimiento: " + new SimpleDateFormat("dd-MM-yyyy").format(fecha) + "</li>");
                out.println("<li>Salario: " + salario + "</li>");
                out.println("<li>Número de hijos: " + hijos + "</li>");
                if (preferencias != null) {
                    out.print("<li>Preferencias: ");
                    for (String p : preferencias) {
                        out.print(p + " ");
                    }
                    out.println("</li>");
                }
                out.println("</ul>");
                out.println("<a href='" + request.getContextPath() + "/index.html'>Volver al índice</a>");
                out.println("</body></html>");
            }
        }
    }
}
