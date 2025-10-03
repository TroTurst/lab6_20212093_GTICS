package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Repositorio para las mesas
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> BuscarDisponibles();
    long ContarDisponibles();
}

