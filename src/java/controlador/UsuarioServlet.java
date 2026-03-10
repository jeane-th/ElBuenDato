/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author jtafu
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        UsuarioDAO dao = new UsuarioDAOImpl();

        if (accion == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        switch (accion) {
            case "registrar":
                registrarUsuario(request, response, dao);
                break;

            case "login":
                validarLogin(request, response, dao);
                break;

            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
            throws ServletException, IOException {

        Usuario u = new Usuario();
        u.setNombre(request.getParameter("nombre"));
        u.setApellido(request.getParameter("apellido"));
        u.setCorreo(request.getParameter("correo"));
        u.setPassword(request.getParameter("password"));

        if (dao.insertar(u)) {
            // Registro exitoso, lo mandamos al login o al inicio
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("error", "El correo ya está registrado o hubo un error.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

// Método para manejar el login (Bloque 2)
    private void validarLogin(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
            throws ServletException, IOException {
        // Aquí irá tu lógica de buscar usuario por correo y password
    }

}
