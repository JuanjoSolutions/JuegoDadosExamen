package com.examen.juegodadosexamen.service;

import com.examen.juegodadosexamen.model.Lanzamiento;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class LanzamientoTask implements Callable<Lanzamiento> {

    private int id;
    // Lista de condiciones especiales
    private static final String[] CONDICIONES = {"Ninguna", "Viento Fuerte", "Superficie Irregular", "Lluvia Intensa"};

    public LanzamientoTask(int id) {
        this.id = id;
    }

    @Override
    public Lanzamiento call() throws Exception {
        try {
            // Mensaje de inicio de lanzamiento
            System.out.println("[" + Thread.currentThread().getName() + "] Iniciando lanzamiento del dado " + id + ".");

            // Generar el resultado inicial (valor entre 1 y 6)
            int resultadoInicial = ThreadLocalRandom.current().nextInt(1, 7);

            // Seleccionar aleatoriamente una condición especial
            String condicion = CONDICIONES[ThreadLocalRandom.current().nextInt(0, CONDICIONES.length)];

            // Aplicar el efecto de la condición sobre el resultado
            int resultadoFinal = aplicarCondicion(resultadoInicial, condicion);

            // Simulación de retardo para evidenciar la concurrencia
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500));

            // Mensaje de finalización del lanzamiento
            System.out.println("[" + Thread.currentThread().getName() + "] Dado " + id + " lanzó: " + resultadoInicial
                    + " -> Resultado final (con " + condicion + "): " + resultadoFinal);

            return new Lanzamiento(id, resultadoInicial, resultadoFinal, condicion);
        } catch (Exception e) {
            throw new Exception("Error en el lanzamiento del dado " + id + ": " + e.getMessage(), e);
        }
    }

    // Método para aplicar el efecto de cada condición especial
    private int aplicarCondicion(int resultado, String condicion) {
        switch (condicion) {
            case "Viento Fuerte aplicado":
                resultado = Math.max(resultado - 1, 1);
                break;
            case "Superficie Irregular aplicado":
                resultado = Math.min(resultado + 1, 6);
                break;
            case "Lluvia Intensa aplicada":
                // Efecto aleatorio: sumar o restar 1
                if (ThreadLocalRandom.current().nextBoolean()) {
                    resultado = Math.min(resultado + 1, 6);
                } else {
                    resultado = Math.max(resultado - 1, 1);
                }
                break;
            case "Ninguna modificación aplicada":
            default:
                // Sin modificación
                break;
        }
        return resultado;
    }
}
