package com.futbol.app.controller;

import com.futbol.app.entity.*;
import com.futbol.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ClubController {

    @Autowired private ClubRepository clubRepository;
    @Autowired private EntrenadorRepository entrenadorRepository;
    @Autowired private JugadorRepository jugadorRepository;
    @Autowired private AsociacionRepository asociacionRepository;
    @Autowired private CompeticionRepository competicionRepository;

    // ==================== INICIO ====================
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalClubes", clubRepository.count());
        model.addAttribute("totalEntrenadores", entrenadorRepository.count());
        model.addAttribute("totalJugadores", jugadorRepository.count());
        model.addAttribute("totalAsociaciones", asociacionRepository.count());
        model.addAttribute("totalCompeticiones", competicionRepository.count());
        return "index";
    }
    
    // ==================== CLUBES ====================
    
    @GetMapping("/clubes")
    public String listarClubes(Model model) {
        model.addAttribute("clubes", clubRepository.findAll());
        return "clubes";
    }
    
    @GetMapping("/club/nuevo")
    public String nuevoClub(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "club-form";
    }
    
    @PostMapping("/club/guardar")
    public String guardarClub(@ModelAttribute Club club,
                              @RequestParam(required = false) Long entrenadorId,
                              @RequestParam(required = false) Long asociacionId,
                              @RequestParam(required = false) List<Long> competicionesIds) {
        if (entrenadorId != null && entrenadorId > 0) {
            club.setEntrenador(entrenadorRepository.findById(entrenadorId).orElse(null));
        }
        if (asociacionId != null && asociacionId > 0) {
            club.setAsociacion(asociacionRepository.findById(asociacionId).orElse(null));
        }
        if (competicionesIds != null && !competicionesIds.isEmpty()) {
            club.setCompeticiones(competicionRepository.findAllById(competicionesIds));
        }
        clubRepository.save(club);
        return "redirect:/clubes";
    }
    
    @GetMapping("/club/editar/{id}")
    public String editarClub(@PathVariable Long id, Model model) {
        Club club = clubRepository.findById(id).orElse(new Club());
        model.addAttribute("club", club);
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "club-form";
    }
    
    @GetMapping("/club/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        clubRepository.deleteById(id);
        return "redirect:/clubes";
    }
    
    // ==================== ENTRENADORES ====================
    
    @GetMapping("/entrenadores")
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenadores";
    }
    
    @GetMapping("/entrenador/nuevo")
    public String nuevoEntrenador(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador-form";
    }
    
    @PostMapping("/entrenador/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenadores";
    }
    
    @GetMapping("/entrenador/editar/{id}")
    public String editarEntrenador(@PathVariable Long id, Model model) {
        model.addAttribute("entrenador", entrenadorRepository.findById(id).orElse(new Entrenador()));
        return "entrenador-form";
    }
    
    @GetMapping("/entrenador/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenadores";
    }
    
    // ==================== JUGADORES ====================
    
    @GetMapping("/jugadores")
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        return "jugadores";
    }
    
    @GetMapping("/jugador/nuevo")
    public String nuevoJugador(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugador-form";
    }
    
    @PostMapping("/jugador/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorRepository.save(jugador);
        return "redirect:/jugadores";
    }
    
    @GetMapping("/jugador/editar/{id}")
    public String editarJugador(@PathVariable Long id, Model model) {
        model.addAttribute("jugador", jugadorRepository.findById(id).orElse(new Jugador()));
        return "jugador-form";
    }
    
    @GetMapping("/jugador/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorRepository.deleteById(id);
        return "redirect:/jugadores";
    }
    
    // ==================== ASOCIACIONES ====================
    
    @GetMapping("/asociaciones")
    public String listarAsociaciones(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "asociaciones";
    }
    
    @GetMapping("/asociacion/nuevo")
    public String nuevaAsociacion(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion-form";
    }
    
    @PostMapping("/asociacion/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        asociacionRepository.save(asociacion);
        return "redirect:/asociaciones";
    }
    
    @GetMapping("/asociacion/editar/{id}")
    public String editarAsociacion(@PathVariable Long id, Model model) {
        model.addAttribute("asociacion", asociacionRepository.findById(id).orElse(new Asociacion()));
        return "asociacion-form";
    }
    
    @GetMapping("/asociacion/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionRepository.deleteById(id);
        return "redirect:/asociaciones";
    }
    
    // ==================== COMPETICIONES ====================
    
    @GetMapping("/competiciones")
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competiciones";
    }
    
    @GetMapping("/competicion/nuevo")
    public String nuevaCompeticion(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competicion-form";
    }
    
    @PostMapping("/competicion/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion) {
        competicionRepository.save(competicion);
        return "redirect:/competiciones";
    }
    
    @GetMapping("/competicion/editar/{id}")
    public String editarCompeticion(@PathVariable Long id, Model model) {
        model.addAttribute("competicion", competicionRepository.findById(id).orElse(new Competicion()));
        return "competicion-form";
    }
    
    @GetMapping("/competicion/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionRepository.deleteById(id);
        return "redirect:/competiciones";
    }
    
    // ==================== INICIALIZAR DATOS ====================
    
    @GetMapping("/inicializar")
    public String inicializarDatos() {
        if (entrenadorRepository.count() == 0) {
            entrenadorRepository.save(new Entrenador("Carlos", "Restrepo", 55, "Colombiana"));
            entrenadorRepository.save(new Entrenador("Juan", "Perez", 48, "Argentina"));
            entrenadorRepository.save(new Entrenador("Luis", "Zubeldia", 42, "Argentina"));
        }
        
        if (asociacionRepository.count() == 0) {
            asociacionRepository.save(new Asociacion("FCF", "Colombia", "Fernando Jaramillo"));
            asociacionRepository.save(new Asociacion("AFA", "Argentina", "Claudio Tapia"));
            asociacionRepository.save(new Asociacion("FBF", "Brasil", "Ednaldo Rodrigues"));
        }
        
        if (competicionRepository.count() == 0) {
            competicionRepository.save(new Competicion("Liga BetPlay", 5000000, LocalDate.now(), LocalDate.now().plusMonths(6)));
            competicionRepository.save(new Competicion("Copa Libertadores", 15000000, LocalDate.now(), LocalDate.now().plusMonths(8)));
            competicionRepository.save(new Competicion("Copa Sudamericana", 8000000, LocalDate.now(), LocalDate.now().plusMonths(7)));
        }
        
        if (jugadorRepository.count() == 0) {
            jugadorRepository.save(new Jugador("James", "Rodriguez", 10, "Volante"));
            jugadorRepository.save(new Jugador("Radamel", "Falcao", 9, "Delantero"));
            jugadorRepository.save(new Jugador("Juan", "Cuadrado", 11, "Extremo"));
            jugadorRepository.save(new Jugador("David", "Ospina", 1, "Portero"));
        }
        
        if (clubRepository.count() == 0) {
            Club club1 = new Club("Millonarios", "Bogotá", 1946);
            Club club2 = new Club("Nacional", "Medellín", 1947);
            Club club3 = new Club("Junior", "Barranquilla", 1924);
            clubRepository.save(club1);
            clubRepository.save(club2);
            clubRepository.save(club3);
        }
        
        return "redirect:/";
    }
}