package com.teccah.asistenciaapi.auth;

public class LoginResponse {

    private String username;
    private String nombre;
    private String rol;

    public LoginResponse(String username, String nombre, String rol) {
        this.username = username;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }
}