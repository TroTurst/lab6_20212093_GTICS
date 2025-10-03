package com.example.lab6_20212093_gtics.services;

import com.example.lab6_20212093_gtics.entitys.Mesa;
import com.example.lab6_20212093_gtics.entitys.Reserva;
import com.example.lab6_20212093_gtics.entitys.Usuario;
import com.example.lab6_20212093_gtics.repositorys.MesaRepository;
import com.example.lab6_20212093_gtics.repositorys.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired private MesaRepository mesaRepository;
    @Autowired private UsuarioService usuarioService;

    public Reserva reservarMesa(Long mesaId, String correoUsuario) {
        Usuario usuario = usuarioService.getUsuarioAutenticado(correoUsuario);
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada."));

        // En caso la cuenta ya tenga una reserva
        if (reservaRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            throw new IllegalStateException("Ya tienes una reserva activa.");
        }

        // Para ver la disponivilidad de las meses
        if (!mesa.getDisponible() || mesa.getCapacidad() > 4) {
            throw new IllegalArgumentException("Mesa no disponible o excede la capacidad máxima (4).");
        }

        // Para hacer la reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setMesa(mesa);
        reservaRepository.save(reserva);

        // Para mostrar que la mesa ya no esta disponible
        mesa.setDisponible(false);
        mesaRepository.save(mesa);

        return reserva;
    }

    // Lógica ADMIN
    public void liberarMesa(Long mesaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada."));

        reservaRepository.findByMesa(mesa).ifPresent(reservaRepository::delete);

        mesa.setDisponible(true);
        mesaRepository.save(mesa);
    }

    public List<Reserva> findAllReservas() { return reservaRepository.findAll(); }

}