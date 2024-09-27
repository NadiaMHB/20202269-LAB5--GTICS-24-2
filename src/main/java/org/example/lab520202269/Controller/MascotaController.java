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
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaRepository mascotaRepository;
    private final PersonaRepository personaRepository;

    public MascotaController(MascotaRepository mascotaRepository, PersonaRepository personaRepository) {
        this.mascotaRepository = mascotaRepository;
        this.personaRepository = personaRepository;
    }

    @GetMapping("/list")
    public String listarMascotas(Model model) {
        List<Mascota> listaMascotas = mascotaRepository.findAll();
        model.addAttribute("mascotaList", listaMascotas);
        return "mascota/list";
    }

    @GetMapping("/new")
    public String nuevaMascotaFrm(Model model) {
        model.addAttribute("personas", personaRepository.findAll());
        model.addAttribute("mascota", new Mascota());
        return "mascota/new";
    }

    @PostMapping("/save")
    public String guardarMascota(Mascota mascota, @RequestParam("personaId") int personaId, RedirectAttributes attr) {
        Optional<Persona> personaOpt = personaRepository.findById(personaId);
        if (personaOpt.isPresent()) {
            mascota.setPersona(personaOpt.get());
        } else {
            attr.addFlashAttribute("msg", "Error: El dueño seleccionado no existe.");
            return "redirect:/mascotas/new";
        }
        mascotaRepository.save(mascota);
        attr.addFlashAttribute("msg", mascota.getId() == null ? "Mascota creada exitosamente" : "Mascota actualizada exitosamente");
        return "redirect:/mascotas/list";
    }


}
