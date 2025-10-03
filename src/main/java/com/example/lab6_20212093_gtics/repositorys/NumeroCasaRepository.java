package com.example.lab6_20212093_gtics.repositorys;

import com.example.lab6_20212093_gtics.entitys.NumeroCasa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NumeroCasaRepository extends JpaRepository<NumeroCasa, Long> {
    Optional<NumeroCasa> findByUsuarioIdAndAdivinadoFalse(Long usuarioId);
    List<NumeroCasa> findTop10ByAdivinadoTrueOrderByIntentosAsc();
}