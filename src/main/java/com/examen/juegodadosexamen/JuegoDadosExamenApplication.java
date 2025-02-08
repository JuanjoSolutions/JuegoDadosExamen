package com.examen.juegodadosexamen;

import com.examen.juegodadosexamen.model.ResultadoAnalisis;
import com.examen.juegodadosexamen.service.JuegoDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JuegoDadosExamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuegoDadosExamenApplication.class, args);
    }

    @Autowired
    private JuegoDadosService juegoDadosService;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            int numeroDados = 10;
            System.out.println("------------------------------------------------");
            System.out.println("Iniciando simulación de lanzamiento de dados con " + numeroDados + " dados.");
            System.out.println("------------------------------------------------");

            try {
                ResultadoAnalisis analisis = juegoDadosService.lanzarDados(numeroDados);
                System.out.println("\n***** Simulación finalizada *****\n");
                System.out.println("Resultados de cada lanzamiento:");
                analisis.getLanzamientos().forEach(l -> System.out.println(l.toString()));
                System.out.println("\nAnálisis estadístico:");
                System.out.println("Promedio de resultados: " + analisis.getPromedio());
                System.out.println("Valor más frecuente: " + analisis.getValorMasFrecuente());
                System.out.println("Desviación estándar: " + analisis.getDesviacionEstandar());
                System.out.println("Promedio por condición especial: " + analisis.getPromedioPorCondicion());
            } catch (Exception e) {
                System.err.println("Error en la simulación: " + e.getMessage());
            }
        };
    }
}
