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

    @Override
    public List<Local> listar() {
        List<Local> lista = new ArrayList<>();

        // 1. Agregamos l.id_distrito y el GROUP_CONCAT de los IDs de categorías
        String sql = "SELECT l.id_local, l.nombre, l.direccion, l.pagina_web, l.id_distrito, " // <-- Agregado id_distrito
                + "d.nombre_distrito, "
                + "GROUP_CONCAT(c.nombre_categoria SEPARATOR ', ') AS categoria, "
                + "GROUP_CONCAT(c.id_categoria SEPARATOR ', ') AS ids_categorias, " // <-- Agregado IDs
                + "l.especialidad, l.fecha_registro "
                + "FROM locales l "
                + "INNER JOIN distritos d ON l.id_distrito = d.id_distrito "
                + "INNER JOIN local_categoria lc ON l.id_local = lc.id_local "
                + "INNER JOIN categorias c ON lc.id_categoria = c.id_categoria "
                + "GROUP BY l.id_local";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            Local loc = new Local();
                loc.setId_local(rs.getInt("id_local"));
                loc.setNombre(rs.getString("nombre"));
                loc.setDireccion(rs.getString("direccion"));
                loc.setPagina_web(rs.getString("pagina_web"));
                loc.setId_distrito(rs.getInt("id_distrito"));
                loc.setDistrito(rs.getString("nombre_distrito"));
                
                loc.setCategoria(rs.getString("categoria")); 

                loc.setIds_categorias(rs.getString("ids_categorias")); 

                loc.setEspecialidad(rs.getString("especialidad"));
                loc.setFecha_registro(rs.getTimestamp("fecha_registro"));

                lista.add(loc);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar locales: " + e.getMessage());
        }
        return lista;
    }
    
    @Override
    public boolean insertar(Local loc, int[] idsCategorias) {
        String sqlLocal = "INSERT INTO locales (nombre, direccion, id_distrito, pagina_web, especialidad, fecha_registro) VALUES (?, ?, ?, ?, ?, NOW())";

        String sqlCategorias = "INSERT INTO local_categoria (id_local, id_categoria) VALUES (?, ?)";
        Connection con = null;
        try (Connection tempCon = Conexion.getConexion()) {
            con = tempCon;
            con.setAutoCommit(false);
            int idGenerado = 0;

            try (PreparedStatement ps = con.prepareStatement(sqlLocal, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, loc.getNombre());
                ps.setString(2, loc.getDireccion());
                ps.setInt(3, loc.getId_distrito());
                ps.setString(4, loc.getPagina_web());
                ps.setString(5, loc.getEspecialidad());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                    }
                }
            }

            try (PreparedStatement psCat = con.prepareStatement(sqlCategorias)) {
                for (int idCat : idsCategorias) {
                    psCat.setInt(1, idGenerado);
                    psCat.setInt(2, idCat);
                    psCat.addBatch();
                }
                psCat.executeBatch();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("No se pudo insertar local: " + e.getMessage());
            return realizarRollback(con);
        }
    }

    @Override
    public boolean eliminar(int id_local) {
        String sqlCategorias = "DELETE FROM local_categoria WHERE id_local = ?";
        String sqlLocal = "DELETE FROM locales WHERE id_local = ?";

        Connection con = null;
        try (Connection tempCon = Conexion.getConexion()) {
            con = tempCon;
            con.setAutoCommit(false);
            // Borrar categorias
            try (PreparedStatement psCat = con.prepareStatement(sqlCategorias)) {
                psCat.setInt(1, id_local);
                psCat.executeUpdate();
            }

            // Borrar local
            int filasAfectadas = 0;
            try (PreparedStatement psLoc = con.prepareStatement(sqlLocal)) {
                psLoc.setInt(1, id_local);
                filasAfectadas = psLoc.executeUpdate();
            }

            con.commit(); // Si todo salió bien, guardamos cambios
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return realizarRollback(con);
        }
    }

    @Override
    public boolean update(Local loc, int[] idsCategorias) {
        String sqlUpdateLocal = "UPDATE locales SET nombre=?, direccion=?, id_distrito=?, pagina_web=?, especialidad=? WHERE id_local=?";
        String sqlDeleteCategorias = "DELETE FROM local_categoria WHERE id_local=?";
        String sqlInsertarCategoria = "INSERT INTO local_categoria (id_local, id_categoria) VALUES (?, ?)";
        Connection con = null;
        try (Connection tempCon = Conexion.getConexion()) {
            con = tempCon;
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(sqlUpdateLocal)) {
                ps1.setString(1, loc.getNombre());
                ps1.setString(2, loc.getDireccion());
                ps1.setInt(3, loc.getId_distrito());
                ps1.setString(4, loc.getPagina_web());
                ps1.setString(5, loc.getEspecialidad());
                ps1.setInt(6, loc.getId_local());
                ps1.executeUpdate();

            }
            try (PreparedStatement ps2 = con.prepareStatement(sqlDeleteCategorias)) {
                ps2.setInt(1, loc.getId_local());
                ps2.executeUpdate();
            }
            try (PreparedStatement ps3 = con.prepareStatement(sqlInsertarCategoria)) {
                for (int idCat : idsCategorias) {
                    ps3.setInt(1, loc.getId_local());
                    ps3.setInt(2, idCat);
                    ps3.addBatch();
                }
                ps3.executeBatch();
            }
            con.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error de al actualizar" + e.getMessage());
            return realizarRollback(con);
        }
    }

    private boolean realizarRollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.err.println("❌ Error en rollback: " + ex.getMessage());
            }
        }
        return false;
    }
}
