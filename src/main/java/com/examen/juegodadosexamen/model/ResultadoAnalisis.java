package com.examen.juegodadosexamen.model;

import java.util.List;
import java.util.Map;

public class ResultadoAnalisis {
    private List<Lanzamiento> lanzamientos;
    private double promedio;
    private int valorMasFrecuente;
    private double desviacionEstandar;
    private Map<String, Double> promedioPorCondicion;

    public ResultadoAnalisis(List<Lanzamiento> lanzamientos, double promedio, int valorMasFrecuente, double desviacionEstandar, Map<String, Double> promedioPorCondicion) {
        this.lanzamientos = lanzamientos;
        this.promedio = promedio;
        this.valorMasFrecuente = valorMasFrecuente;
        this.desviacionEstandar = desviacionEstandar;
        this.promedioPorCondicion = promedioPorCondicion;
    }

    // Getters y Setters
    public List<Lanzamiento> getLanzamientos() {
        return lanzamientos;
    }
    public void setLanzamientos(List<Lanzamiento> lanzamientos) {
        this.lanzamientos = lanzamientos;
    }
    public double getPromedio() {
        return promedio;
    }
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    public int getValorMasFrecuente() {
        return valorMasFrecuente;
    }
    public void setValorMasFrecuente(int valorMasFrecuente) {
        this.valorMasFrecuente = valorMasFrecuente;
    }
    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }
    public void setDesviacionEstandar(double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }
    public Map<String, Double> getPromedioPorCondicion() {
        return promedioPorCondicion;
    }
    public void setPromedioPorCondicion(Map<String, Double> promedioPorCondicion) {
        this.promedioPorCondicion = promedioPorCondicion;
    }

    @Override
    public String toString() {
        return "Resultado Analisis{" +
                "lanzamientos=" + lanzamientos +
                ", promedio=" + promedio +
                ", valor mas frecuente=" + valorMasFrecuente +
                ", desviacion estandar=" + desviacionEstandar +
                ", promedio por condicion=" + promedioPorCondicion +
                '}';
    }
}
