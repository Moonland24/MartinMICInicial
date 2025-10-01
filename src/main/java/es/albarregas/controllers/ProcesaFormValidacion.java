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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String fechaInput = request.getParameter("fecha_nacimiento");
        String salario = request.getParameter("salario");
        String hijos = request.getParameter("hijos");
        String[] preferencias = request.getParameterValues("preferencias");

        boolean hayError = false;
        String mensajeError = "";

        if (nombre == null || nombre.trim().isEmpty()) {
            hayError = true;
            mensajeError += "El campo Nombre no puede estar vacío.<br>";
            nombre = "";
        } else if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            hayError = true;
            mensajeError += "El campo Nombre solo puede contener letras y espacios.<br>";
            nombre = "";
        }

        if (fechaInput == null || fechaInput.trim().isEmpty()) {
            hayError = true;
            mensajeError += "El campo Fecha no puede estar vacío.<br>";
            fechaInput = "";
        }

        if (salario == null || salario.trim().isEmpty()) {
            hayError = true;
            mensajeError += "El campo Salario no puede estar vacío.<br>";
            salario = "";
        }

        if (hijos == null || hijos.trim().isEmpty()) {
            hayError = true;
            mensajeError += "El campo Número de hijos no puede estar vacío.<br>";
            hijos = "";
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (hayError) {
                out.println("<html><head><title>Formulario con errores</title></head><body>");
                out.println("<h2>Se han encontrado errores:</h2>");
                out.println("<p>" + mensajeError + "</p>");
                out.println("<form action='procesaformvalidado' method='post'>");

                out.println("Nombre: <input type='text' name='nombre' value='" + nombre + "'><br><br>");
                out.println("Fecha de nacimiento: <input type='date' name='fecha_nacimiento' value='" + fechaInput + "'><br><br>");
                out.println("Salario: <input type='number' name='salario' step='0.01' value='" + salario + "'><br><br>");
                out.println("Número de hijos: <input type='number' name='hijos' value='" + hijos + "'><br><br>");

                out.println("Preferencias:<br>");
                String[] opciones = {"cine", "baile", "musica", "deporte", "viajes"};
                for (String op : opciones) {
                    boolean checked = false;
                    if (preferencias != null) {
                        for (String p : preferencias) {
                            if (p.equals(op)) checked = true;
                        }
                    }
                    out.println("<input type='checkbox' name='preferencias' value='" + op + "' " + (checked ? "checked" : "") + "> " + op + "<br>");
                }

                out.println("<br><input type='submit' value='Enviar'>");
                out.println("</form>");
                out.println("</body></html>");
                return;
            }

            out.println("<html><head><title>Resultado Formulario</title></head><body>");
            out.println("<h1>Datos recibidos correctamente</h1>");
            out.println("<ul>");
            out.println("<li>Nombre: " + nombre + "</li>");

            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date fecha = inputFormat.parse(fechaInput);
                out.println("<li>Fecha de nacimiento: " + outputFormat.format(fecha) + "</li>");
            } catch (ParseException e) {
                out.println("<li>Fecha de nacimiento: formato inválido</li>");
            }

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
            out.println("<p><a href=\"index.html\">Volver al índice</a></p>");
            out.println("</body></html>");
        }
    }
}
