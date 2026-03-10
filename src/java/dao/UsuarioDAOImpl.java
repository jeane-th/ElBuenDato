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
        String sqlUsuario = "INSERT INTO usuarios (nombre, apellido, correo, password) VALUES (?,?,?,?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sqlUsuario)) {
           
            String hash = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, hash);
            
            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario:" + e.getMessage());
            return false;
        }

    }
}
