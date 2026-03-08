/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.Local;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jtafu
 */
public class LocalDAOImpl implements LocalDAO {

    // Objetos de JDBC
    // Se declaran ahí para que todos los métodos de tu clase LocalDAO (como listar, insertar, eliminar) puedan
    // usarlas sin tener que crearlas desde cero cada vez.
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Local> listar() {
        // 1. DECLARACIÓN DE LA LISTA: 
        // Creamos una lista vacía de objetos 'Local'. 
        // Aquí es donde guardaremos todos los locales que MySQL nos devuelva.
        List<Local> lista = new ArrayList<>();

        // 2. DEFINICIÓN DEL SQL: 
        // Escribimos la instrucción en lenguaje SQL que queremos ejecutar en la base de datos.
        //String sql = "SELECT * FROM locales";
        String sql = "SELECT l.id_local, l.nombre, l.direccion, l.pagina_web, d.nombre_distrito, "
                + "GROUP_CONCAT(c.nombre_categoria SEPARATOR ', ') AS categoria, "
                + "l.especialidad, l.fecha_registro "
                + "FROM locales l "
                + "INNER JOIN distritos d ON l.id_distrito = d.id_distrito "
                + "INNER JOIN local_categoria lc ON l.id_local = lc.id_local "
                + "INNER JOIN categorias c ON lc.id_categoria = c.id_categoria "
                + "GROUP BY l.id_local";

        try {
            // 3. APERTURA DE CONEXIÓN: 
            // Llamamos a nuestra clase de conexión para que nos dé una "llave" (con) de acceso a la BD.
            con = Conexion.getConexion();

            // 4. PREPARACIÓN DE LA CONSULTA: 
            // El PreparedStatement toma el SQL y lo "pre-limpia". 
            // Esto evita ataques de Inyección SQL y prepara el camino para la ejecución.
            ps = con.prepareStatement(sql);

            // 5. EJECUCIÓN DEL QUERY: 
            // executeQuery() se usa exclusivamente para SELECT. 
            // Envía el SQL a MySQL y recibe los datos en el ResultSet (rs).
            rs = ps.executeQuery();

            // 6. RECORRIDO DE LOS RESULTADOS: 
            // El ResultSet empieza "antes" de la primera fila. 
            // rs.next() mueve el puntero a la siguiente fila. Si hay datos, devuelve 'true'.
            while (rs.next()) {

                // 7. INSTANCIACIÓN DEL OBJETO (MAPEO): 
                // Por cada fila que encuentre el bucle, creamos un objeto 'Local' nuevo en memoria RAM.
                Local loc = new Local();

                // 8. EXTRACCIÓN Y ASIGNACIÓN (GETTERS Y SETTERS): 
                // rs.get... ("nombre_columna") saca el dato crudo de la tabla de la BD.
                // loc.set... (...) guarda ese dato dentro de los atributos de nuestro objeto Java.
                loc.setId_local(rs.getInt("id_local"));
                loc.setNombre(rs.getString("nombre"));
                loc.setDireccion(rs.getString("direccion"));
                loc.setPagina_web(rs.getString("pagina_web"));
                loc.setDistrito(rs.getString("nombre_distrito"));
                loc.setCategoria(rs.getString("categoria"));
                loc.setEspecialidad(rs.getString("especialidad"));
                loc.setFecha_registro(rs.getTimestamp("fecha_registro"));

                // 9. ALMACENAMIENTO EN LA LISTA: 
                // Una vez el objeto 'loc' tiene todos sus datos, lo agregamos a nuestra lista.
                lista.add(loc);
            }
        } catch (SQLException e) {

            // 10. MANEJO DE ERRORES: 
            // Si la conexión falla, el SQL está mal escrito o la tabla no existe, 
            // capturamos el error para que el programa no "explote" y nos diga qué pasó.
            System.err.println("Error al listar locales: " + e.getMessage());
        } finally {

            // 11. CIERRE DE RECURSOS: 
            // El bloque finally siempre se ejecuta. 
            // Cerramos la conexión para no dejar "puertas abiertas" en el servidor de Base de Datos.
            // Para que la bd no alcance el limite maximo de conexiones
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }

        // 12. RETORNO DE LA INFORMACIÓN: 
        // Finalmente, devolvemos la lista llena de objetos Local a quien haya llamado al método.
        return lista;
    }

    @Override
    public boolean insertar(Local loc, int[] idsCategorias) {
        // Definimos la consulta para la tabla principal (locales)
        String sqlLocal = "INSERT INTO locales (nombre, direccion, id_distrito, pagina_web, especialidad, fecha_registro) VALUES (?, ?, ?, ?, ?, NOW())";

        // Definimos la consulta para la tabla intermedia (relación local <-> categoría)
        String sqlCategorias = "INSERT INTO local_categoria (id_local, id_categoria) VALUES (?, ?)";

        try {
            // Obtenemos la conexión desde nuestra clase de configuración
            con = Conexion.getConexion();

            /* IMPORTANTE: Desactivamos el auto-commit. 
           Esto significa que MySQL no guardará nada permanentemente hasta que nosotros digamos 'commit'.
           Si algo falla en el medio, no quedará un local creado sin sus categorías.
             */
            con.setAutoCommit(false);

            // --- PASO 1: INSERTAR EL LOCAL ---
            /* Preparamos la sentencia indicando que queremos recuperar la llave (ID) que MySQL 
           genere automáticamente (Auto_increment).
             */
            ps = con.prepareStatement(sqlLocal, PreparedStatement.RETURN_GENERATED_KEYS);

            // Seteamos los valores que vienen del objeto 'loc'
            ps.setString(1, loc.getNombre());
            ps.setString(2, loc.getDireccion());
            ps.setInt(3, loc.getId_distrito()); // Llave foránea de la tabla distritos
            ps.setString(4, loc.getPagina_web());
            ps.setString(5, loc.getEspecialidad());

            // Ejecutamos la inserción del local
            ps.executeUpdate();

            // --- PASO 2: RECUPERAR EL ID GENERADO ---
            /* Pedimos al Driver el conjunto de llaves generadas (en este caso, el ID del nuevo local).
             */
            rs = ps.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                // Obtenemos el ID de la primera columna del resultado
                idGenerado = rs.getInt(1);
            }

            // --- PASO 3: INSERTAR LAS CATEGORÍAS (TABLA INTERMEDIA) ---
            /* Preparamos la segunda sentencia para llenar la tabla local_categoria.
             */
            ps = con.prepareStatement(sqlCategorias);

            // Recorremos el array de IDs de categorías que seleccionó el usuario
            for (int idCat : idsCategorias) {
                // El primer '?' es el ID del local que acabamos de crear
                ps.setInt(1, idGenerado);
                // El segundo '?' es el ID de la categoría actual del bucle
                ps.setInt(2, idCat);

                /* Ejecutamos la inserción en la tabla intermedia por cada categoría.
                 */
                ps.executeUpdate();
            }

            // --- PASO 4: CONFIRMAR TODO ---
            /* Si llegamos aquí sin errores, le decimos a la base de datos que guarde 
           todo (el local y sus categorías) de un solo golpe.
             */
            con.commit();
            return true;

        } catch (SQLException e) {
            /* Si hubo CUALQUIER error (ID duplicado, error de red, etc.), 
           ejecutamos rollback para deshacer los cambios parciales.
             */
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error en Rollback: " + ex.getMessage());
            }

            System.err.println("Error al insertar local completo: " + e.getMessage());
            return false;

        } finally {
            /* SIEMPRE cerramos los recursos (ResultSet, PreparedStatement y Conexión) 
           para no dejar procesos colgados en el servidor.
             */
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean eliminar(int id_local) {
        String sqlCategorias = "DELETE FROM local_categoria WHERE id_local = ?";
        String sqlLocal = "DELETE FROM locales WHERE id_local = ?";

        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

// Borrar categorías
            ps = con.prepareStatement(sqlCategorias);
            ps.setInt(1, id_local);
            ps.executeUpdate();

            // Borrar local
            ps = con.prepareStatement(sqlLocal);
            ps.setInt(1, id_local);
            int filasAfectadas = ps.executeUpdate();

            con.commit(); // Si todo salió bien, guardamos cambios
            return filasAfectadas > 0;

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
            }
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar BD: " + e.getMessage());
            }
        }
    }
}
