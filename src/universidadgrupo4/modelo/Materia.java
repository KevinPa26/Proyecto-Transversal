/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo4.modelo;

/**
 *
 * @author kevin
 */
public class Materia {
    
    private int idMateria;
    private String nombre;
    private int añio;
    private boolean estado;
    
    public Materia(){};

    public Materia(int idMateria, String nombre, int añio, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.añio = añio;
        this.estado = estado;
    }

    public Materia(String nombre, int añio, boolean estado) {
        this.nombre = nombre;
        this.añio = añio;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñio() {
        return añio;
    }

    public void setAñio(int añio) {
        this.añio = añio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
