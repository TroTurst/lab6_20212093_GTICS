package com.example.lab6_20212093_gtics.services;

import com.example.lab6_20212093_gtics.entitys.NumeroCasa;
import com.example.lab6_20212093_gtics.entitys.Usuario;
import com.example.lab6_20212093_gtics.repositorys.NumeroCasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NumeroCasaService {
    @Autowired
    private NumeroCasaRepository numeroCasaRepository;
    @Autowired private UsuarioService usuarioService;

    public Optional<NumeroCasa> getAsignacionActiva(String correoUsuario) {
        Usuario usuario = usuarioService.getUsuarioAutenticado(correoUsuario);
        return numeroCasaRepository.BuscarPorUsuarioYAdivinanza(usuario.getId());
    }

    public void asignarNumero(Long usuarioId, Integer numeroObjetivo) {
        if (numeroObjetivo < 65) {
            throw new IllegalArgumentException("El número objetivo debe ser mayor a 64.");
        }

        Usuario usuario = usuarioService.getUsuarioById(usuarioId);

        NumeroCasa asignacion = new NumeroCasa();
        asignacion.setUsuario(usuario);
        asignacion.setNumeroObjetivo(numeroObjetivo);
        numeroCasaRepository.save(asignacion);
    }

    public List<NumeroCasa> getRanking() {
        return numeroCasaRepository.BuscarPorUsuarioYAdivinanzaAsc();
    }
}