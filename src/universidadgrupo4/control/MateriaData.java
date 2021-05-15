/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo4.control;

import universidadgrupo4.modelo.Materia;
import java.sql.Connection;
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
public class MateriaData {
    
    Connection con;

    public MateriaData(Conexion con) {
        this.con = con.getConnection();
    }
    
    public void agregarMateria(Materia materia){
        
        try {
            String sql = "INSERT INTO materia(nombre, anio, estado) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAñio());
            ps.setBoolean(3, materia.isEstado());
            
            if(ps.executeUpdate()==1){JOptionPane.showMessageDialog(null, "Se agrego correctamente");}
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo traer el id");
            }
            ps.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al crear la materia");
        }
    }
    
    public Materia buscarMateria(int id){
        
        Materia materia = null;
        
        try {
            
            String sql = "SELECT * FROM `materia` WHERE idMateria=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAñio(rs.getInt(3));
                materia.setEstado(rs.getBoolean(4));
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar la materia");
        }
        
        return materia;
    }
    
    public List<Materia> traerMaterias(){
        
        List<Materia> materias = new ArrayList<>();
        Materia materia;
        
        try {
            String sql = "SELECT * FROM materia";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
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
            JOptionPane.showMessageDialog(null, "No se pudo traer la lista de materias");
        }
        
        return materias;
    }
    
    public void borrarMateria(int id){
        String sql = "DELETE FROM materia WHERE idMateria=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, id);

            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "La materia se borro correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo borrar la materia");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la materia en la base de datos");
        }
    }
    
    public void actualizarMateria(Materia materia){
        try {
            String sql = "UPDATE materia SET nombre=?,anio=?,estado=? WHERE idMateria=?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAñio());
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getIdMateria());
            
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente la materia");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la materia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al hacer la consulta");
        }
    }
}
