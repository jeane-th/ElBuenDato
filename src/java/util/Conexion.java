/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jtafu
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://u5xzwm9qnudqviup:6ILIvntMDhwALiBo8dS6@bgy0qbpmaqti2rhlvlbz-mysql.services.clever-cloud.com:3306/bgy0qbpmaqti2rhlvlbz";
    private static final String USER = "u5xzwm9qnudqviup";
    private static final String PASSWORD = "6ILIvntMDhwALiBo8dS6";

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.print("se conecto a la base de datos ElBuenDato");
        } catch (Exception ex) {
            System.out.print("Error de conexion a la BD " + ex.getMessage());
        }
        return con;
    }
}
