/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo4.modelo;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class Alumno {
    
    private int idAlumno;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nac;
    private int legajo;
    private boolean estado;
    
    public Alumno(){};

    public Alumno(int idAlumno, String nombre, String apellido, LocalDate fecha_nac, int legajo, boolean estado) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.legajo = legajo;
        this.estado = estado;
    }

    public Alumno(String nombre, String apellido, LocalDate fecha_nac, int legajo, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.legajo = legajo;
        this.estado = estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return apellido+", "+nombre;
    }
    
    
}
