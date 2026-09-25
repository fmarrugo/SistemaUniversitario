package com.miapp.modelo;

import com.miapp.modelo.utilidades.EstadoMatricula;
import com.miapp.servicios.IBuscador;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo: representa la entidad Estudiante.
 */
public class Estudiante extends Persona implements IBuscador {  

    private static int totalEstudiantes = 0;
    
    public static final int MAX_MATERIAS = 7;
    public static final int PROMEDIO_MINIMO = 0;
    public static final int PROMEDIO_MAXIMO = 5;
    public static final String CARRERA_PREDETERMINADA = "Sin especificar";

    // ── Atributos de instancia ────────────────────────────────────────────────
    private String carrera;
    private double promedio;
    private EstadoMatricula estadoMatricula; // Tipo de dato corregido con Mayúscula inicial
    private List<Curso> cursosInscritos;

    // ── Constructores ────────────────────────────────────────────────────────
    
    // Constructor principal
    public Estudiante(int id, String nombre, String apellido, String carrera, double promedio) {
        super(id, nombre, apellido);
        this.carrera = carrera;
        this.estadoMatricula = EstadoMatricula.ACTIVO;
        this.cursosInscritos = new ArrayList<>();
        
        if (promedio >= PROMEDIO_MINIMO && promedio <= PROMEDIO_MAXIMO) {
            this.promedio = promedio;
        } else {
            this.promedio = 0.0;
        }
        
        totalEstudiantes++;        
    }

    // Constructor por defecto (faltaba agregar)
    public Estudiante() {
        super();
        this.carrera = CARRERA_PREDETERMINADA;
        this.promedio = 0.0;
        this.estadoMatricula = EstadoMatricula.ACTIVO;
        this.cursosInscritos = new ArrayList<>();
        totalEstudiantes++;
    }

    // ── Métodos estáticos ─────────────────────────────────────────────────────

    public static int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public static void reiniciarContador() {
        totalEstudiantes = 0;
    }

    public static int getProximoId() {  
        return totalEstudiantes + 1;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────────
    
    public String getCarrera() { 
        return carrera; 
    }

    public void setCarrera(String carrera) { 
        this.carrera = carrera; 
    }

    public double getPromedio() { 
        return promedio; 
    }

    public void setPromedio(double p) {
        if (p >= PROMEDIO_MINIMO && p <= PROMEDIO_MAXIMO) {
            this.promedio = p;
        }
    }

    public EstadoMatricula getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(EstadoMatricula estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    // ── Implementación de métodos ─────────────────────────────────────────────

    @Override
    public final String toString() {
        return "ID: " + getId()
             + " | Nombre: " + getNombre()
             + " | Apellido: " + getApellido()   
             + " | Carrera: " + carrera
             + " | Promedio: " + String.format("%.2f", promedio)
             + " | Estado: " + estadoMatricula;
    }

    @Override
    public double calcularPago() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void buscarEstudiante(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarEstudiantePorCarrera(String carrera) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void inscribirCurso(Curso curso) {
        if (curso == null) {
            return;
        }
        
        if (cursosInscritos.size() < MAX_MATERIAS && !cursosInscritos.contains(curso)) {
            cursosInscritos.add(curso);
            curso.agregarEstudiante(this);
        }
    }
}