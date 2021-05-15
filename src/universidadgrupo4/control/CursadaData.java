/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo4.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo4.modelo.Cursada;
import universidadgrupo4.modelo.Materia;

/**
 *
 * @author kevin
 */
public class CursadaData {
    
    Connection con;

    public CursadaData(Conexion con) {
        this.con = con.getConnection();
    }
    
    public void guardarCursada(Cursada cursada){
        try {
            String sql = "INSERT INTO cursada (idAlumno,idMateria,nota)VALUES (?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getIdAlumno());
            ps.setInt(2, cursada.getIdMateria());
            ps.setDouble(3, cursada.getNota());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                cursada.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se agrego correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro la id de cursada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear la inscripcion");
        }
    }
    
    public List<Cursada> obtenerCursadas(){
        List<Cursada> lCursada = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cursada";
            PreparedStatement ps = con.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Cursada cursada = new Cursada();
                cursada.setId_cursada(rs.getInt(1));
                cursada.setIdAlumno(rs.getInt(2));
                cursada.setIdMateria(rs.getInt(3));
                cursada.setNota(rs.getDouble(4));
                lCursada.add(cursada);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al trar las cursadas");
        }
        return lCursada;
    }
    
    public List<Cursada> obtCursadasAlumno(int id){
        List<Cursada> lCursada = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cursada WHERE idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Cursada cursada = new Cursada();
                cursada.setId_cursada(rs.getInt(1));
                cursada.setIdAlumno(rs.getInt(2));
                cursada.setIdMateria(rs.getInt(3));
                cursada.setNota(rs.getDouble(4));
                lCursada.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lCursada;
    }
    
    public List<Cursada> obtCursadasMateria(int id){
        List<Cursada> lCursada = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cursada WHERE idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Cursada cursada = new Cursada();
                cursada.setId_cursada(rs.getInt(1));
                cursada.setIdAlumno(rs.getInt(2));
                cursada.setIdMateria(rs.getInt(3));
                cursada.setNota(rs.getDouble(4));
                lCursada.add(cursada);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lCursada;
    }
    
    public void borrarCursadaDeUnaMateriaDeUnAlumno(int idAlumno, int idMateria){
        try {
            String sql ="DELETE FROM cursada WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Se borro correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "no se encuentra el id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar la cursada");
        }
    }
    
    public void actualizarNotaCursada(int idAlumno, int idMateria, double nota){
        try {
            String sql = "UPDATE cursada SET nota=? WHERE idAlumno=? AND idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            
           
            if(ps.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el alumno o la materia");
            }
             ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar");
        }
    }
    
    public List<Materia> traerMateriasCursaAlumno(int id){
        List<Materia> materias = new ArrayList<>();
        Materia materia;
        try {
            String sql = "SELECT materia.idMateria, materia.nombre, materia.anio, materia.estado FROM materia JOIN cursada ON cursada.idMateria = materia.idMateria WHERE cursada.idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAñio(rs.getInt(3));
                materia.setEstado(rs.getBoolean(4));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
    
    public List<Materia> traerMateriasNoCursaAlumno(int id){
        List<Materia> materias = new ArrayList<>();
        Materia materia;
        try {
            String sql = "SELECT * FROM materia WHERE idMateria NOT IN ( SELECT materia.idMateria FROM materia JOIN cursada ON cursada.idMateria = materia.idMateria WHERE cursada.idAlumno = ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAñio(rs.getInt(3));
                materia.setEstado(rs.getBoolean(4));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
    
    public double obtNotaEsp(int idAlumno, int idMateria){
        double notita = 0;
        try {
            String sql = "SELECT nota FROM cursada WHERE idAlumno=? AND idMateria=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                notita = rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notita;
    }
}
