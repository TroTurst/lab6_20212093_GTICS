package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.Mesa;
import com.example.lab6_20212093_gtics.entitys.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Repositorio para hacer recervas
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> PorUsuarioId(Long usuarioId);

    Optional<Reserva> BuscarPorMesa(Mesa mesa);
}

