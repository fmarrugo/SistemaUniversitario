/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miapp.modelo;

/**
 *
 * @author Usuario
 */
public class Profesor extends Persona{
    private double salarioBase;

    public Profesor() {
    }

    public Profesor(int id, String nombre, String apellido) {
        super(id, nombre, apellido);
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

    @Override
    public double calcularPago() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
