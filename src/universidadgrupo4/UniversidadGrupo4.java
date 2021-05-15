/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo4;

import universidadgrupo4.modelo.Materia;
import universidadgrupo4.modelo.Alumno;
import universidadgrupo4.control.MateriaData;
import universidadgrupo4.control.Conexion;
import universidadgrupo4.control.AlumnoData;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import universidadgrupo4.control.CursadaData;
import universidadgrupo4.modelo.Cursada;

/**
 *
 * @author kevin
 */
public class UniversidadGrupo4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion con = new Conexion ();
  
        
        Alumno juan = new Alumno(1,"jonathan","salas",LocalDate.of(1998, Month.AUGUST, 11), 123123123,true);
        Alumno manuel = new Alumno(3,"fiero","acevedo",LocalDate.of(1998, Month.AUGUST, 12), 123124345,true);
        
        Materia m1 = new Materia("Lengua",1,true);
        Materia m2 = new Materia("Matematicas",1,true);
        Materia m3 = new Materia("Laboratorio",1,true);
        Materia m4 = new Materia("Web",1,true);
        
        Cursada c1 = new Cursada(11, 2, 9.3);
        
        AlumnoData ad = new AlumnoData(con);
        MateriaData md = new MateriaData(con);
        CursadaData cd = new CursadaData(con);
        //md.agregarMateria(m4);
        System.out.println(md.buscarMateria(3));
        //ad.actualizarAlumno(juan);
        
//        Iterator it = ad.traerAlumnos().iterator();
//        
//        while(it.hasNext()){
//            System.out.println((Alumno)it.next());
//        }
//        
//        Iterator at = md.traerMaterias().iterator();
//        
//        while(at.hasNext()){
//            System.out.println((Materia)at.next());
//        }
        
        cd.guardarCursada(c1);
        
    }
    
}
