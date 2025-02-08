package com.examen.juegodadosexamen.controller;
import com.examen.juegodadosexamen.model.ResultadoAnalisis;
import com.examen.juegodadosexamen.service.JuegoDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/juego")
public class JuegoDadosController {

    @Autowired
    private JuegoDadosService juegoDadosService;

    // Endpoint para iniciar la simulación; se recibe el número de dados (por defecto 5)
    @GetMapping("/lanzar")
    public ResultadoAnalisis lanzarDados(@RequestParam(name = "numeroDados", defaultValue = "5") int numeroDados) {
        try {
            return juegoDadosService.lanzarDados(numeroDados);
        } catch (Exception e) {
            // En caso de error se lanza una excepción runtime con detalle del problema
            throw new RuntimeException("Error en la simulación de lanzamiento de dados: " + e.getMessage(), e);
        }
    }
}
