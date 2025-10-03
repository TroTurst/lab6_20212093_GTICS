package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.Intencion;
import org.springframework.data.jpa.repository.JpaRepository;

//Repositio para la "intencion" que es basicamente una pre-accion
public interface IntencionRepository extends JpaRepository<Intencion, Long> {
    boolean UsuarisosPorId(Long usuarioId);
}
