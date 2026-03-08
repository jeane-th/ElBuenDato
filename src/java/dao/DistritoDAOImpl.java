/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import modelo.Distrito;
import java.sql.*;
import util.Conexion;

/**
 *
 * @author jtafu
 */
public class DistritoDAOImpl  implements DistritoDAO{
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public List<Distrito> listar(){
        List<Distrito> lista = new ArrayList <>();
        
        String sqlLocal = "SELECT * FROM distritos";
        try{
            con = Conexion.getConexion();
            ps = con.prepareStatement(sqlLocal);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Distrito dist = new Distrito();
                dist.setId_distrito(rs.getInt("id_distrito"));
                dist.setNombre_distrito(rs.getString("nombre_distrito"));
                
                lista.add(dist);
            }
            
            
        } catch (SQLException e){
            System.err.println("Error al listar distritos:" + e.getMessage());
        }finally{
            try{
                if (con!=null){
                    con.close();
                }
            }catch (SQLException ex){
                System.err.println("Error al listar distritos:" + ex.getMessage());
            }
        }
          return lista;
    }
}
