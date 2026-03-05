/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author jtafu
 */
public class Usuario {

    private int id_usuario;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fecha_registro;
    private boolean estado;
    private int rol;

    // Constructores
    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre, String email, String password, LocalDateTime fecha_registro, boolean estado, int rol) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
        this.rol = rol;
    }

    public Usuario(String nombre, String email, String password, boolean estado, int rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
    }
    // Getters y Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getrol() {
        return rol;
    }

    public void setrol(int rol) {
        this.rol = rol;
    }
}
