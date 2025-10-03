package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.NumeroCasa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Repositio para el numero de casa
public interface NumeroCasaRepository extends JpaRepository<NumeroCasa, Long> {
    Optional<NumeroCasa> BuscarPorUsuarioYAdivinanza(Long usuarioId);
    List<NumeroCasa> BuscarPorUsuarioYAdivinanzaAsc();
}
