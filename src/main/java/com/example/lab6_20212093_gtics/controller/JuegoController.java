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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/juegos")
@SessionAttributes({"cancionAsignada", "numeroCasaAsignado"}) // Almacenar progreso en sesión
public class JuegoController {

    @Autowired private AsignacionCancionService cancionService;
    @Autowired private NumeroCasaService numeroCasaService;
    @Autowired private JuegoService juegoService;
    @Autowired private UsuarioService usuarioService;


    //Para el juego de la cancion
    @GetMapping("/cancion")
    public String juegoCancion(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<AsignacionCancion> optAsignacion = cancionService.getAsignacionActiva(userDetails.getUsername());

        if (optAsignacion.isEmpty()) {
            model.addAttribute("deshabilitado", true);
            return "juegos/cancion_game";
        }


        AsignacionCancion asignacion = optAsignacion.get();
        if (!model.containsAttribute("cancionAsignada")) {
            model.addAttribute("cancionAsignada", asignacion);
        }

        model.addAttribute("guessForm", new CancionDTO());

        return "juegos/cancion_game";
    }

    @PostMapping("/cancion/juego")
    public String adivinarCancion(@ModelAttribute("cancionAsignada") AsignacionCancion asignacion,
                                  @ModelAttribute("guessForm") CancionDTO guessForm,
                                  SessionStatus status, Model model, RedirectAttributes redirect) {

        asignacion.setIntentos(asignacion.getIntentos() + 1);
        String titulo = asignacion.getCancion().getTitulo();
        String resultado = juegoService.compararCancion(titulo, guessForm.getIntento());

        if (resultado.replaceAll("[^A]", "").length() == titulo.replaceAll("\\s", "").length()) {
            asignacion.setAdivinada(true);
            cancionService.asignacionRepository.save(asignacion); // Persistir el resultado
            status.setComplete(); // Limpiar sesión
            redirect.addFlashAttribute("success", "¡Felicidades! Adivinaste la canción en " + asignacion.getIntentos() + " intentos.");
            return "redirect:/juegos/cancion/ranking";
        }

        model.addAttribute("ultimoResultado", resultado);
        cancionService.asignacionRepository.save(asignacion);

        return "juegos/cancion_game";
    }

    //Para ver el ranking del juego
    @GetMapping("/cancion/ranking")
    public String rankingCancion(Model model) {
        model.addAttribute("ranking", cancionService.getRanking());
        return "juegos/cancion_ranking";
    }

    @GetMapping("/dulces")
    public String juegoDulces(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<NumeroCasa> optAsignacion = numeroCasaService.getAsignacionActiva(userDetails.getUsername());

        if (optAsignacion.isEmpty()) {
            model.addAttribute("deshabilitado", true);
            return "juegos/dulces_game";
        }

        NumeroCasa asignacion = optAsignacion.get();
        if (!model.containsAttribute("numeroCasaAsignado")) {
            model.addAttribute("numeroCasaAsignado", asignacion);
        }

        model.addAttribute("guessForm", new NumeroCasaDOT());
        model.addAttribute("objetivo", asignacion.getNumeroObjetivo());
        return "juegos/dulces_game";
    }

    //No me salio este
    @PostMapping("/dulces/Oktoberfest")
    public String adivinarDulces(@ModelAttribute("numeroCasaAsignado") NumeroCasa asignacion,
                                 @ModelAttribute("guessForm") NumeroCasaDOT guessForm,
                                 SessionStatus status, RedirectAttributes redirect) {

        //No me salio y me quede sin cafe
        return "Oktoberfest";
    }
}