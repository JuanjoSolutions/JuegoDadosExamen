package com.examen.juegodadosexamen.service;

import com.examen.juegodadosexamen.model.Lanzamiento;
import com.examen.juegodadosexamen.model.ResultadoAnalisis;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class JuegoDadosService {

    // Utilizamos un pool de hilos dinámico
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    // Método para lanzar los dados y obtener el análisis
    public ResultadoAnalisis lanzarDados(int numeroDados) throws InterruptedException, ExecutionException {
        List<Future<Lanzamiento>> futures = new ArrayList<>();

        // Enviar una tarea por cada dado
        for (int i = 1; i <= numeroDados; i++) {
            futures.add(executorService.submit(new LanzamientoTask(i)));
        }

        // Recoger los resultados de cada tarea
        List<Lanzamiento> lanzamientos = new ArrayList<>();
        for (Future<Lanzamiento> future : futures) {
            try {
                lanzamientos.add(future.get());
            } catch (ExecutionException | InterruptedException e) {
                System.err.println("Error al obtener el resultado: " + e.getMessage());
                throw e;
            }
        }

        // Realizar el análisis de los resultados
        return analizarResultados(lanzamientos);
    }

    // Método que realiza el análisis estadístico de los lanzamientos
    private ResultadoAnalisis analizarResultados(List<Lanzamiento> lanzamientos) {
        int suma = 0;
        Map<Integer, Integer> frecuencia = new HashMap<>();
        Map<String, List<Integer>> resultadosPorCondicion = new HashMap<>();

        // Recorrer cada lanzamiento para acumular datos
        for (Lanzamiento lan : lanzamientos) {
            int res = lan.getResultadoFinal();
            suma += res;

            // Calcular la frecuencia de cada resultado
            frecuencia.put(res, frecuencia.getOrDefault(res, 0) + 1);

            // Agrupar resultados por condición especial
            String condicion = lan.getCondicionEspecial();
            resultadosPorCondicion.putIfAbsent(condicion, new ArrayList<>());
            resultadosPorCondicion.get(condicion).add(res);
        }

        double promedio = lanzamientos.isEmpty() ? 0 : (double) suma / lanzamientos.size();

        // Determinar el valor más frecuente
        int valorMasFrecuente = 0;
        int maxFrecuencia = 0;
        for (Map.Entry<Integer, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                valorMasFrecuente = entry.getKey();
            }
        }

        // Calcular la desviación estándar
        double sumaDesviacion = 0;
        for (Lanzamiento lan : lanzamientos) {
            sumaDesviacion += Math.pow(lan.getResultadoFinal() - promedio, 2);
        }
        double desviacionEstandar = lanzamientos.isEmpty() ? 0 : Math.sqrt(sumaDesviacion / lanzamientos.size());

        // Calcular el promedio de resultados por cada condición especial
        Map<String, Double> promedioPorCondicion = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : resultadosPorCondicion.entrySet()) {
            List<Integer> lista = entry.getValue();
            double sumaCondicion = 0;
            for (int valor : lista) {
                sumaCondicion += valor;
            }
            double promCond = lista.isEmpty() ? 0 : sumaCondicion / lista.size();
            promedioPorCondicion.put(entry.getKey(), promCond);
        }

        return new ResultadoAnalisis(lanzamientos, promedio, valorMasFrecuente, desviacionEstandar, promedioPorCondicion);
    }
}
