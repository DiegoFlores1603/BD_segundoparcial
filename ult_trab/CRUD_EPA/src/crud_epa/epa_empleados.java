/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_epa;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class epa_empleados {
    
    Connection conexion = null;
    Statement declaracion = null;
    ResultSet resultado = null;
    PreparedStatement preparacion = null;
    
    String id, id2, cedula, name, apelli, correo, sangre, direc;
    int id_aux, id_aux2;
    String sql;
    Boolean ban = true;
    
    public void db(){
        
        try{
             conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Empresa_agua_potable","postgres","Ecuador16");
            
        }catch(SQLException e){
            System.out.print("Error encontrado: " + e.getMessage());
        }       
    }
    
    public void insercion_cliente() throws SQLException, ParseException{
        
        if(ban == true){
                                  
            db();
            
            id = empleados_epa.c1.getText();
            id2 = empleados_epa.c2.getText();
            cedula = empleados_epa.c3.getText();
            name = empleados_epa.c4.getText();
            apelli = empleados_epa.c5.getText();
            correo = empleados_epa.c6.getText();
            sangre = empleados_epa.c8.getText();
            direc = empleados_epa.c9.getText();
            
            id_aux = Integer.parseInt(id);
            id_aux2 = Integer.parseInt(id2);
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");        
            String fech1 = empleados_epa.c7.getText();
            java.sql.Date fecha1;
            java.util.Date nfecha = formato.parse(fech1);
            fecha1 = new java.sql.Date(nfecha.getTime());
            
            sql = "INSERT INTO empleado (id_emp, id_tipo_emp, cedula_emp, nombre_emp, apellido_emp, email_emp, "
                + "fecha_nac_emp,tipo_sangre_emp, direccion_emp) VALUES (?,?,?,?,?,?,?,?,?)";
            preparacion = conexion.prepareStatement(sql);
            
            preparacion.setInt(1, id_aux);
            preparacion.setInt(2, id_aux2);            
            preparacion.setString(3, cedula);
            preparacion.setString(4, name);
            preparacion.setString(5, apelli);
            preparacion.setString(6, correo);
            preparacion.setDate(7, fecha1);
            preparacion.setString(8, sangre);
            preparacion.setString(9, direc);
            
            preparacion.executeUpdate();            
            JOptionPane.showMessageDialog(null, "Inserción exitosa!");            
        }       
    }
    
    public void actualizar_cliente() throws SQLException, ParseException{
        
        if(ban == true){
            
            db();
            
            id2 = empleados_epa.c2.getText();
            cedula = empleados_epa.c3.getText();
            name = empleados_epa.c4.getText();
            apelli = empleados_epa.c5.getText();
            correo = empleados_epa.c6.getText();
            sangre = empleados_epa.c8.getText();
            direc = empleados_epa.c9.getText();
            
            id_aux = Integer.parseInt(id);
            id_aux2 = Integer.parseInt(id2);
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");       
            String fech1 = empleados_epa.c7.getText();
            java.sql.Date fecha1;
            java.util.Date nfecha = formato.parse(fech1);
            fecha1 = new java.sql.Date(nfecha.getTime());
            
            sql = "UPDATE empleado set id_tipo_emp=?, cedula_emp=?, nombre_emp=?, apellido_emp=?, email_emp=?, "
                + "fecha_nac_emp=?, tipo_sangre_emp=?, direccion_emp=? WHERE id_emp = '"+id+"'";
            
            preparacion = conexion.prepareStatement(sql);
            
            preparacion.setInt(1, id_aux2);            
            preparacion.setString(2, cedula);
            preparacion.setString(3, name);
            preparacion.setString(4, apelli);
            preparacion.setString(5, correo);
            preparacion.setDate(6, fecha1);
            preparacion.setString(7, sangre);
            preparacion.setString(8, direc);
            
            preparacion.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Actualización exitosa!");
            
        }
        
    }
    
    public void consulta_cliente() throws SQLException{
        
        db();
        
        declaracion = conexion.createStatement();
        
        id = empleados_epa.c1.getText();
        
        resultado = declaracion.executeQuery("SELECT * FROM empleado WHERE id_emp = '"+id+"' ");
        
        if(resultado.next()){
            
            ban = true;
            
            empleados_epa.c2.setText(resultado.getString(2));
            empleados_epa.c3.setText(resultado.getString(3));
            empleados_epa.c4.setText(resultado.getString(4));
            empleados_epa.c5.setText(resultado.getString(5));
            empleados_epa.c6.setText(resultado.getString(6));
            empleados_epa.c7.setText(resultado.getString(7));
            empleados_epa.c8.setText(resultado.getString(8));
            empleados_epa.c9.setText(resultado.getString(9));    
            
        } else{
            
            JOptionPane.showMessageDialog(null, "No existe registro");
            
            ban = false;
            
        }
        
    }
    
    public void eliminar_cliente()throws SQLException{
    
        try{
            
            if(ban == true){
                
                db();
                
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminarlo?", "PRECAUCIÓN", JOptionPane.YES_NO_OPTION);
                
                if(respuesta != 1){
                    
                    declaracion.execute("DELETE FROM empleado WHERE id_emp = '"+id+"' ");
                    
                    JOptionPane.showMessageDialog(null,"Eliminado con éxito!");
                    
                }
                
            }
            
        }catch (SQLException e){
            
            JOptionPane.showMessageDialog(null,"No se puede eliminar!");
            
        }
        
    }
    
}
