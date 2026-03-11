/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Usuario;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import util.Conexion;

/**
 *
 * @author jtafu
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean insertar(Usuario u) {
        String sqlUsuario = "INSERT INTO usuarios (nombre, apellido, correo, contrasena) VALUES (?,?,?,?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sqlUsuario)) {

            String hash = BCrypt.hashpw(u.getContrasena(), BCrypt.gensalt());

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, hash);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario:" + e.getMessage());
            return false;
        }

    }

    @Override
    public Usuario login(String correo, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE correo=? AND estado=1";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("contrasena");
                    if (BCrypt.checkpw(contrasena, hash)) {
                        Usuario u = new Usuario();
                        u.setId_usuario(rs.getInt("id_usuario"));
                        u.setNombre(rs.getString("nombre"));
                        u.setApellido(rs.getString("apellido"));
                        u.setCorreo(rs.getString("correo"));
                        u.setContrasena(rs.getString("contrasena"));
                        return u;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return null;
    }
}
