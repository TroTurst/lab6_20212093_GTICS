package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.Rol;
import com.example.lab6_20212093_gtics.entitys.TodosLosRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Repositio para el rol, no lo termine usando
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> BuscarPorNombre(TodosLosRoles nombre);
}

