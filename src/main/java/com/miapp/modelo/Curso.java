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
public class Curso {
    private String creditos;
    private String  codigo;
    private Profesor profesor;
    private List<Estudiante> estudiantesInscritos;

    public Curso(String creditos, String codigo, Profesor profesor, List<Estudiante> estudiantes) {
        this.creditos = creditos;
        this.codigo = codigo;
        this.profesor = profesor;
        this.estudiantesInscritos = new ArrayList<>();
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantesInscritos;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantesInscritos = estudiantes;
    }

    public void agregarEstudiante(Estudiante estudiante){
        if(!estudiantesInscritos.contains(estudiante)){
            estudiantesInscritos.add(estudiante);
        }
    }
    
    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    } 
}
