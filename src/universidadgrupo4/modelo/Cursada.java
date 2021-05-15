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
public class Cursada {
    
    private int id_cursada;
    private int  idAlumno;
    private int idMateria;
    private double nota;

    public Cursada(int id_cursada, int idAlumno, int idMateria, double nota) {
        this.id_cursada = id_cursada;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
        this.nota = nota;
    }

    public Cursada(int idAlumno, int idMateria, double nota) {
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
        this.nota = nota;
    }
    
    public Cursada(){};

    public int getId_cursada() {
        return id_cursada;
    }

    public void setId_cursada(int id_cursada) {
        this.id_cursada = id_cursada;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
