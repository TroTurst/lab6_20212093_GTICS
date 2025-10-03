package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//No se porque me da problemas este repositorio
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> BuscarPorCorreo(String correo);
    Optional<Usuario> BuscarPorId(Long id);
}

