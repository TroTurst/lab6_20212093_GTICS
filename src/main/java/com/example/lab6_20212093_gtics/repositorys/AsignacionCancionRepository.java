package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.AsignacionCancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Repositorio para la asignacion de una cacnion
public interface AsignacionCancionRepository extends JpaRepository<AsignacionCancion, Long> {
    Optional<AsignacionCancion> EncontrarUsuario(Long usuarioId);
    List<AsignacionCancion> EncontrarTop10PorIntenttos();
}
