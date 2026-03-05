/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Local;

/**
 *
 * @author jtafu
 */
public interface LocalDAO {

   // boolean agregar(Local l);

    List<Local> listar();
    boolean insertar(Local loc, int[] idsCategorias);

    // Pelicula buscar(int id_local);
    // boolean actualizar(Local p);
    // boolean eliminar(int id_local);
}
