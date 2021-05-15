/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo4.control;

import universidadgrupo4.modelo.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class AlumnoData {
    
    Connection con;

    public AlumnoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    
    
    public void agregarAlumno(Alumno alumno){
        
        try {
            
            String sql = "INSERT INTO alumno (nombre,apellido,fecha_nac,legajo,estado)VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, Date.valueOf(alumno.getFecha_nac()));
            ps.setInt(4, alumno.getLegajo());
            ps.setBoolean(5, alumno.isEstado());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El alumno se creo correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo traer el id");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TERRIBLE ERROR");
        }
    }
    
    public Alumno buscarAlumno(int legajo){
        
        String sql = "SELECT * FROM `alumno` WHERE legajo = ?";
        
        Alumno alumno = new Alumno();
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, legajo);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setFecha_nac(rs.getDate(4).toLocalDate());
                alumno.setLegajo(rs.getInt(5));
                alumno.setEstado(rs.getBoolean(6));
            }else{
                JOptionPane.showMessageDialog(null, "no se pudo crear el alumno");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se encontrar el alumno");
        }
        
        
        return alumno;
    }
    
    public List<Alumno> traerAlumnos(){
        
        String sql = "SELECT * FROM alumno";
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alumno;
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                alumno = new Alumno();
                
                alumno.setIdAlumno(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setFecha_nac(rs.getDate(4).toLocalDate());
                alumno.setLegajo(rs.getInt(5));
                alumno.setEstado(rs.getBoolean(6));
                
                alumnos.add(alumno);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se encontrar el alumno");
        }
        
        
        return alumnos;
    }
    
    public void actualizarAlumno(Alumno alumno){
        
        
        String sql = "UPDATE alumno set nombre=?,apellido=?,fecha_nac=?,legajo=?,estado=? WHERE idAlumno=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, alumno.getIdAlumno());
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, Date.valueOf(alumno.getFecha_nac()));
            ps.setInt(4, alumno.getLegajo());
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            
            if(ps.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "no se pudo agrego correctamente");
            }
            
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de base datos(algun dato mal ingresado)");
        }
        
    }
    
    public void borrarAlumno (int id){
        String sql = "DELETE FROM alumno WHERE idAlumno=?";
        
        
        
         try {
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             
             ps.setInt(1, id);
             
             if(ps.executeUpdate()==1){
                 JOptionPane.showMessageDialog(null, "Se borro correctamente");
             }else{
                 JOptionPane.showMessageDialog(null, "No se pudo borro correctamente");
             }
             
             ps.close();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error de base de datos()");
         }
    }
}
