/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author jtafu
 */
public class Local {

    private int id_local;
    private String nombre;
    private String direccion;
    private int id_distrito; // numero id
    private String pagina_web;
    private String especialidad;
    private Timestamp fecha_registro; //getFechaSoloFecha

    // para listar 
    private String distrito; // nombre string
    private String categoria;

    public Local() {
    }

    public Local(String nombre, String direccion, int id_distrito, String pagina_web, String especialidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id_distrito = id_distrito;
        this.pagina_web = pagina_web;
        this.especialidad = especialidad;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_distrito() {
        return id_distrito;
    }

    public void setId_distrito(int id_distrito) {
        this.id_distrito = id_distrito;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String nombre_distrito) {
        this.distrito = nombre_distrito;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String nombres_categoria) {
        this.categoria = nombres_categoria;
    }

    // dar formato fecha
    public String getFechaSoloFecha() {
        if (this.fecha_registro != null) {
            return new SimpleDateFormat("dd-MM-yyyy").format(this.fecha_registro);
        }
        return "";
    }
    
    // convierte la lista de categoria en un objeto
    public String[] getListaCategorias() {
    if (this.categoria != null) {
        return this.categoria.split(", ");
    }
    return new String[0];
}

    @Override
    public String toString() {
        return "Local{" + ", nombre=" + nombre + ", direccion=" + direccion + ", pagina_web=" + pagina_web + ", especialidad=" + especialidad + ", fecha_registro=" + getFechaSoloFecha() + ", nombre_distrito=" + distrito + ", nombres_categoria=" + categoria + '}';
    }

// usa alt + insert para setters y getters
}
