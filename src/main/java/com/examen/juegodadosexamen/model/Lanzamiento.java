package com.examen.juegodadosexamen.model;

public class Lanzamiento {
    private int id;
    private int resultadoInicial;
    private int resultadoFinal;
    private String condicionEspecial;

    public Lanzamiento(int id, int resultadoInicial, int resultadoFinal, String condicionEspecial) {
        this.id = id;
        this.resultadoInicial = resultadoInicial;
        this.resultadoFinal = resultadoFinal;
        this.condicionEspecial = condicionEspecial;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getResultadoInicial() {
        return resultadoInicial;
    }
    public void setResultadoInicial(int resultadoInicial) {
        this.resultadoInicial = resultadoInicial;
    }
    public int getResultadoFinal() {
        return resultadoFinal;
    }
    public void setResultadoFinal(int resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }
    public String getCondicionEspecial() {
        return condicionEspecial;
    }
    public void setCondicionEspecial(String condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    @Override
    public String toString() {
        return "Lanzamiento{" +
                "id=" + id +
                ", resultado Inicial=" + resultadoInicial +
                ", resultado final=" + resultadoFinal +
                ", condicion especial='" + condicionEspecial + '\'' +
                '}';
    }
}
