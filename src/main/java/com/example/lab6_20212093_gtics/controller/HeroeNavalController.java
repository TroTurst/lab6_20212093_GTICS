package com.example.lab6_20212093_gtics.controller;

import com.example.lab6_20212093_gtics.entitys.HeroeNaval;
import com.example.lab6_20212093_gtics.services.HeroeNavalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HeroeNavalController {

    @Autowired private HeroeNavalService heroeNavalService;

    @GetMapping("/heroes")
    public String listarHeroes(Model model) {
        model.addAttribute("heroes", heroeNavalService.findAllHeroes());
        return "heroes/lista_publica";
    }

    @GetMapping("/admin/heroes")
    public String adminListarHeroes(Model model) {
        model.addAttribute("heroes", heroeNavalService.findAllHeroes());
        return "heroes/admin_lista";
    }

    @GetMapping("/admin/heroes/form")
    public String mostrarFormulario(@RequestParam(required = false) Long id, Model model) {
        HeroeNaval heroe = id != null ? heroeNavalService.findHeroeById(id).orElse(new HeroeNaval()) : new HeroeNaval();
        model.addAttribute("heroe", heroe);
        return "heroes/admin_form";
    }

    @PostMapping("/admin/heroes/save")
    public String guardarHeroe(@ModelAttribute HeroeNaval heroe, RedirectAttributes redirect) {
        try {
            heroeNavalService.saveHeroe(heroe);
            redirect.addFlashAttribute("success", "Héroe Naval guardado con éxito!");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/admin/heroes";
    }

    @GetMapping("/admin/heroes/delete/{id}")
    public String eliminarHeroe(@PathVariable Long id, RedirectAttributes redirect) {
        heroeNavalService.deleteHeroe(id);
        redirect.addFlashAttribute("success", "Héroe Naval eliminado con éxito!");
        return "redirect:/admin/heroes";
    }
}