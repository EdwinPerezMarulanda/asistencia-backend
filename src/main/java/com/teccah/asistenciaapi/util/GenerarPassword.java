package com.teccah.asistenciaapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordPlano = "1234";
        String passwordEncriptado = encoder.encode(passwordPlano);

        System.out.println("PASSWORD ENCRIPTADO:");
        System.out.println(passwordEncriptado);
    }
}