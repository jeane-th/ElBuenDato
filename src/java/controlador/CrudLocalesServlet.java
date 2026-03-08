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

        // 1. Instanciamos el DAO para acceder a sus métodos
        LocalDAO LocalDao = new LocalDAOImpl();
        DistritoDAO DistritoDao = new DistritoDAOImpl();

        // 2. Llamamos al método listar() que ya tiene el SQL con JOIN y GROUP_CONCAT
        // Esto nos devuelve la lista de objetos 'Local'
        List<Local> listaLocales = LocalDao.listar();
        List<Distrito> listaDistritos = DistritoDao.listar();


        // 3. Guardamos la lista en el "baúl" de la petición (request)
        // El primer parámetro es el "apodo" que usaremos en el JSP (${locales})
        request.setAttribute("locales", listaLocales);
        request.setAttribute("distritos", listaDistritos);

        // 4. Despachamos (enviamos) la petición hacia el archivo JSP que tiene la tabla
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
