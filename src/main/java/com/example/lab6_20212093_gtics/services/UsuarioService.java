package com.example.lab6_20212093_gtics.services;

import com.example.lab6_20212093_gtics.entitys.Usuario;
import com.example.lab6_20212093_gtics.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioAutenticado(String correo) {
        return usuarioRepository.BuscarPorCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.BuscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }
}