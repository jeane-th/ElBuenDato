/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.DistritoDAO;
import dao.DistritoDAOImpl;
import dao.LocalDAO;
import dao.LocalDAOImpl;
import modelo.Local;

/**
 *
 * @author jtafu
 */
public class Test {

    public static void main(String[] args) {

        //dao.listar().forEach(System.out::println);
        // 1. Instanciamos el DAO
        LocalDAO dao = new LocalDAOImpl();
        dao.eliminar(15);
        //DistritoDAO dao = new DistritoDAOImpl();
        //dao.listar().forEach(System.out::println);
        // 2. Creamos el objeto Local (asegúrate que tu constructor coincida con estos parámetros)
        // nombre, direccion, id_distrito, pagina_web, especialidad
        // Local nuevo = new Local("La Lucha Sanguchería", "Av. Santa Cruz 847", 1, "https://lalucha.com.pe", "Sánguches Criollos");

        // 3. Creamos el array de IDs de categorías (ejemplo: 1=Bar, 2=Restaurante)
        //  int[] categoriasSeleccionadas = {1, 2};
        // 4. Ejecutamos el método y verificamos
        //boolean exito = dao.insertar(nuevo, categoriasSeleccionadas);

        /* if (exito) {
            System.out.println("¡Local y categorías guardados correctamente!");
        } else {
            System.out.println("Hubo un error al insertar.");
        }
         */
    }
}
