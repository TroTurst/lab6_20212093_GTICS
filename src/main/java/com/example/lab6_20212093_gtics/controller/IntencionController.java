package com.example.lab6_20212093_gtics.controller;

import com.example.lab6_20212093_gtics.dto.IntencionDTO;
import com.example.lab6_20212093_gtics.entitys.Usuario;
import com.example.lab6_20212093_gtics.services.IntencionService;
import com.example.lab6_20212093_gtics.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IntencionController {

    @Autowired private IntencionService intencionService;
    @Autowired private UsuarioService usuarioService;

    @GetMapping("/admin/intenciones")
    public String listarIntencionesAdmin(Model model) {
        model.addAttribute("intenciones", intencionService.findAll());
        return "intenciones/admin_lista";
    }

    @GetMapping("/intenciones/registrar")
    public String mostrarFormulario(Model model, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioService.getUsuarioAutenticado(userDetails.getUsername());

        if (intencionService.yaExistePeticion(usuario.getId())) {
            redirectAttributes.addFlashAttribute("error", "Ya has registrado una petición en esta sesión.");
            return "redirect:/home";
        }

        model.addAttribute("intencionDto", new IntencionDTO());
        return "intenciones/registrar";
    }

    @PostMapping("/intenciones/registrar")
    public String registrarPeticion(@ModelAttribute IntencionDTO intencionDto,
                                    @AuthenticationPrincipal UserDetails userDetails,
                                    RedirectAttributes redirectAttributes) {
        try {
            intencionService.guardarPeticion(intencionDto.getDescripcion(), userDetails.getUsername());
            redirectAttributes.addFlashAttribute("success", "Petición registrada con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/home";
    }
}