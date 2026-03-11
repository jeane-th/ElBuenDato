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
import jakarta.servlet.http.HttpSession;
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
        u.setContrasena(request.getParameter("contrasena"));

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
        // Aquí irá tu lógica de buscar usuario por correo y contrasena
        String correo = request.getParameter("correo"); // Lo que viene del input name="correo"
        String contrasena = request.getParameter("contrasena");

        Usuario u = dao.login(correo, contrasena);
        if (u != null) {
            // Creamos la sesión
            HttpSession session = request.getSession();
            // Guardamos al objeto usuario (para usar su nombre, rol, etc.)
            session.setAttribute("usuario", u);
            session.setAttribute("rol", u.getRol());

            if ("Admin".equalsIgnoreCase(u.getRol())) {
                response.sendRedirect(request.getContextPath() + "/dashboardAdmin");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }

        } else {
            // Si los datos están mal
            request.setAttribute("error", "Correo o contrasena incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
