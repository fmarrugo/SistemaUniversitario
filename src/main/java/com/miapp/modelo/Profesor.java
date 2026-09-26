package com.miapp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Persona {

    private final double salarioBase;
    private List<Curso> cursosAsignados;

    public Profesor(int id, String nombre, String apellido, double salarioBase) {
        super(id, nombre, apellido);
        this.salarioBase = salarioBase;
        this.cursosAsignados = new ArrayList<>();
    }

    public void impartirClase() {
        System.out.println(getNombre() + " esta impartiendo clase.");
    }

    void agregarCursoAsignado(Curso curso) {
        if (curso != null && !cursosAsignados.contains(curso)) {
            cursosAsignados.add(curso);
        }
    }

    public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    @Override
    public double calcularPago() {
        return salarioBase;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
             + " | Prof. " + getNombre()
             + " | Salario: $" + String.format("%.2f", salarioBase)
             + " | Cursos asignados: " + cursosAsignados.size();
    }
}