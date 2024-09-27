package org.example.lab520202269.Controller;

import org.example.lab520202269.Repository.*;
import org.example.lab520202269.Entity.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/viajes")
public class ViajeController {

    final ViajeRepository viajeRepository;
    final PersonaRepository personaRepository;
    final LugaresRepository lugaresRepository;

    public ViajeController(ViajeRepository viajeRepository, PersonaRepository personaRepository, LugaresRepository lugaresRepository) {
        this.viajeRepository = viajeRepository;
        this.personaRepository = personaRepository;
        this.lugaresRepository = lugaresRepository;
    }

    @GetMapping("/list")
    public String listarViajes(Model model) {
        List<Viaje> listaViajes = viajeRepository.findAll();
        model.addAttribute("viajeList", listaViajes);
        return "viaje/list";
    }

    @GetMapping("/new")
    public String nuevoViajeFrm(Model model) {
        model.addAttribute("personas", personaRepository.findAll());
        model.addAttribute("lugares", lugaresRepository.findAll());
        return "viaje/new";
    }

    @PostMapping("/save")
    public String guardarNuevoViaje(Viaje viaje, @RequestParam("lugarId") int lugarId, RedirectAttributes attr) {
        Optional<Lugares> lugarOpt = lugaresRepository.findById(lugarId);
        if (lugarOpt.isPresent()) {
            viaje.setLugares(lugarOpt.get());
        } else {
            return "redirect:/viajes/list";
        }
        viajeRepository.save(viaje);
        attr.addFlashAttribute("msg", viaje.getId() == null ? "Viaje creado exitosamente" : "Viaje actualizado exitosamente");
        return "redirect:/viajes/list";
    }

    @GetMapping("/edit")
    public String editarViaje(Model model, @RequestParam("id") int id) {
        Optional<Viaje> optViaje = viajeRepository.findById(id);
        if (optViaje.isPresent()) {
            model.addAttribute("viaje", optViaje.get());
            model.addAttribute("personas", personaRepository.findAll());
            model.addAttribute("lugares", lugaresRepository.findAll());
            return "viaje/edit";
        } else {
            return "redirect:/viaje/list";
        }
    }

    @GetMapping("/delete")
    public String borrarViaje(@RequestParam("id") int id, RedirectAttributes attr) {
        viajeRepository.deleteById(id);
        attr.addFlashAttribute("msg", "Viaje borrado exitosamente");
        return "redirect:/viaje/list";
    }
}
