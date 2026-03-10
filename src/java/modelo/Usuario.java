/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author jtafu
 */
public class Usuario {

    private int id_usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String rol;
    private boolean estado;
    private LocalDateTime fecha_registro;

    // Constructores
    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int id_usuario, String nombre, String apellido, String correo, String password, String rol, boolean estado, LocalDateTime fecha_registro) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    // dar formato fecha
    public String getFechaSoloFecha() {
        if (this.fecha_registro != null) {
            return new SimpleDateFormat("dd-MM-yyyy").format(this.fecha_registro);
        }
        return "";
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", password=" + password + ", rol=" + rol + ", estado=" + estado + ", fecha_registro=" + fecha_registro + '}';
    }


}
