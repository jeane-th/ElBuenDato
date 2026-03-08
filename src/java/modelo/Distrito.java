/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jtafu
 */
public class Distrito {
    private int id_distrito;
    private String nombre_distrito;

    public Distrito() {
    }

    public Distrito(int id_distrito, String nombre_distrito) {
        this.id_distrito = id_distrito;
        this.nombre_distrito = nombre_distrito;
    }

    public int getId_distrito() {
        return id_distrito;
    }

    public void setId_distrito(int id_distrito) {
        this.id_distrito = id_distrito;
    }

    public String getNombre_distrito() {
        return nombre_distrito;
    }

    public void setNombre_distrito(String nombre_distrito) {
        this.nombre_distrito = nombre_distrito;
    }

    @Override
    public String toString() {
        return "Distrito{" + "id_distrito=" + id_distrito + ", nombre_distrito=" + nombre_distrito + '}';
    }
    
}
