package com.example.lab6_20212093_gtics.controller;
import com.example.lab6_20212093_gtics.dto.*;
import com.example.lab6_20212093_gtics.repositorys.*;
import com.example.lab6_20212093_gtics.entitys.*;
import com.example.lab6_20212093_gtics.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired private ReservaService reservaService;
    @Autowired private MesaRepository mesaRepository;

        //Para ver las mesas que hay disponibles
    @GetMapping
    public String listarMesas(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        long ocupadas = mesaRepository.ContarDisponibles();
        long libres = mesaRepository.count() - ocupadas;

        model.addAttribute("mesasDisponibles", mesaRepository.BuscarDisponibles());
        model.addAttribute("mesasLibres", libres);
        model.addAttribute("mesasOcupadas", ocupadas);
        model.addAttribute("reservaDto", new ReservaDTO());

        return "reservas/mesas_user";
    }

    // USUARIO: Reservar una mesa
    @PostMapping("/crear")
    public String crearReserva(@ModelAttribute ReservaDTO reservaDto,
                               @AuthenticationPrincipal UserDetails userDetails,
                               RedirectAttributes redirect) {
        try {
            reservaService.reservarMesa(reservaDto.getMesaId(), userDetails.getUsername());
            redirect.addFlashAttribute("success", "Reserva realizada con éxito.");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage()); // Maneja restricción de 1 reserva/cuenta
        }
        return "redirect:/reservas";
    }

    @GetMapping("/admin")
    public String adminReservas(Model model) {
        model.addAttribute("reservas", reservaService.findAllReservas());
        model.addAttribute("mesas", mesaRepository.findAll());
        return "reservas/admin_list";
    }

    @GetMapping("/admin/liberar/{mesaId}")
    public String liberarMesa(@PathVariable Long mesaId, RedirectAttributes redirect) {
        try {
            reservaService.liberarMesa(mesaId);
            redirect.addFlashAttribute("success", "Mesa liberada con éxito.");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/reservas/admin";
    }

    @PostMapping("/admin/capacidad")
    public String reasignarCapacidad(@RequestParam Long mesaId, @RequestParam Integer nuevaCapacidad) {
        Mesa mesa = mesaRepository.BuscarPorId(mesaId).orElseThrow(); //No se porque me da este problema
        mesa.setCapacidad(nuevaCapacidad);
        mesaRepository.save(mesa);
        return "redirect:/reservas/admin";
    }
}