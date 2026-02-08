package com.teccah.asistenciaapi.controller;

import com.teccah.asistenciaapi.auth.LoginResponse;
import com.teccah.asistenciaapi.dto.LoginRequest;
import com.teccah.asistenciaapi.entity.Usuario;
import com.teccah.asistenciaapi.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        System.out.println("LOGIN USER: " + request.getUsername());

        Usuario usuario = usuarioRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuario no existe");
        }

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        return ResponseEntity.ok(
                new LoginResponse(
                        usuario.getUsername(),
                        usuario.getNombre(),
                        usuario.getRol()
                )
        );
    }

    @GetMapping("/encriptar/{pass}")
    public String encriptar(@PathVariable String pass) {
        return new BCryptPasswordEncoder().encode(pass);
    }

}