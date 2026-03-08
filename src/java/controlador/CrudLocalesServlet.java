/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.DistritoDAO;
import dao.DistritoDAOImpl;
import dao.LocalDAO;
import dao.LocalDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelo.Distrito;
import modelo.Local;

/**
 *
 * @author jtafu
 */
@WebServlet(name = "CrudLocalesServlet", urlPatterns = {"/CrudLocalesServlet"})
public class CrudLocalesServlet extends HttpServlet {

    // mostrar
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LocalDAO LocalDao = new LocalDAOImpl();
        DistritoDAO DistritoDao = new DistritoDAOImpl();

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }
        
        try {
            if (accion.equals("eliminar")) {
                // Lógica de eliminar
                int id = Integer.parseInt(request.getParameter("id"));
                LocalDao.eliminar(id);
                // IMPORTANTE: Después de eliminar, redirigimos al servlet limpio
                response.sendRedirect("CrudLocalesServlet");
                return; // Cortamos la ejecución aquí para que no intente hacer el forward de abajo
            }

            // Si la acción es "listar" o cualquier otra cosa que no sea eliminar
            List<Local> listaLocales = LocalDao.listar();
            List<Distrito> listaDistritos = DistritoDao.listar();

            request.setAttribute("locales", listaLocales);
            request.setAttribute("distritos", listaDistritos);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("Error en el Servlet: " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Capturamos los datos simples
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        int idDistrito = Integer.parseInt(request.getParameter("id_distrito"));
        String web = request.getParameter("pagina_web");
        String especialidad = request.getParameter("especialidad");

        // 2. Capturamos los checkboxes (vienen como String[])
        String[] catStrings = request.getParameterValues("categorias");

        // 3. Convertimos el String[] a int[] para que el DAO lo entienda
        int[] idsCategorias = new int[0];
        if (catStrings != null) {
            idsCategorias = new int[catStrings.length];
            for (int i = 0; i < catStrings.length; i++) {
                idsCategorias[i] = Integer.parseInt(catStrings[i]);
            }
        }

        // 4. Creamos el objeto Local y llamamos al DAO
        Local nuevoLocal = new Local(nombre, direccion, idDistrito, web, especialidad);
        LocalDAO dao = new LocalDAOImpl();

        boolean insertado = dao.insertar(nuevoLocal, idsCategorias);

        // 5. Redireccionamos de vuelta a la lista
        if (insertado) {
            response.sendRedirect("CrudLocalesServlet"); // Recarga la lista
        } else {
            request.setAttribute("error", "No se pudo guardar el local");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
