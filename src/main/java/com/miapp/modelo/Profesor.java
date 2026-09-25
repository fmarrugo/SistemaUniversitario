/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miapp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Profesor extends Persona{
    private double salarioBase;
    private List<Curso> cursoAsignado;

    public Profesor(int id, String nombre, String apellido, double salarioBase) {
        super(id, nombre, apellido);
        this.salarioBase = salarioBase;
        this.cursoAsignado = new ArrayList<>();
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    
    public void impartirClase(){
        System.out.println("Impartiendo clase...");
    }

    public void asignarCurso(Curso curso){
        if(curso != null && !cursoAsignado.contains(curso)){
            cursoAsignado.add(curso);
            curso.setProfesor(this);
        }
    }
    
    @Override
    public double calcularPago() {
        return salarioBase;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Prof. " + getNombre() + " " + getApellido() 
             + " | Salario: $" + String.format("%.2f", salarioBase);
    }
       
}
