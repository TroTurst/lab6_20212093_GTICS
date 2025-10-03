package com.example.lab6_20212093_gtics.services;

import com.example.lab6_20212093_gtics.entitys.AsignacionCancion;
import com.example.lab6_20212093_gtics.entitys.CancionCriolla;
import com.example.lab6_20212093_gtics.entitys.Usuario;
import com.example.lab6_20212093_gtics.repositorys.AsignacionCancionRepository;
import com.example.lab6_20212093_gtics.repositorys.CancionCriollaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignacionCancionService {
    @Autowired
    public AsignacionCancionRepository asignacionRepository;
    @Autowired private CancionCriollaRepository cancionRepository;
    @Autowired private UsuarioService usuarioService;

    public Optional<AsignacionCancion> getAsignacionActiva(String correoUsuario) {
        Usuario usuario = usuarioService.getUsuarioAutenticado(correoUsuario);
        return asignacionRepository.EncontrarUsuario(usuario.getId());
    }

    public void asignarCancion(Long usuarioId, Long cancionId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        CancionCriolla cancion = cancionRepository.BuscarPorId(cancionId) //No se porque me da este problema
                .orElseThrow(() -> new IllegalArgumentException("Canción no encontrada."));

        AsignacionCancion asignacion = new AsignacionCancion();
        asignacion.setUsuario(usuario);
        asignacion.setCancion(cancion);
        asignacionRepository.save(asignacion);
    }

    public List<AsignacionCancion> getRanking() {
        return asignacionRepository.EncontrarTop10PorIntenttos();
    }

}