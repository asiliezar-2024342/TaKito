package modelo;

import config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComboDAO {
    
    Connection cn = Conexion.getInstance().getConexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    private void createCombo (Combo combo) throws Exception{
        combo.setCodigoCombo(rs.getInt("codigoCombo"));
        combo.setNombreCombo(rs.getString("nombreCombo"));
        combo.setDescripcionCombo(rs.getString("descripcionCombo"));
        combo.setPrecioCombo(rs.getFloat("precioCombo"));
        combo.setCategoria(Combo.Categoria.valueOf(rs.getString("categoria")));
        combo.setEstado(Combo.Estado.valueOf(rs.getString("estado")));
        combo.setFoto(rs.getBinaryStream("foto"));
    }
    
    private void preparedSQL(Combo combo, String sql)throws Exception{
        ps = cn.prepareStatement(sql);
        ps.setString(1, combo.getNombreCombo());
        ps.setString(2, combo.getDescripcionCombo());
        ps.setFloat(3, combo.getPrecioCombo());
        ps.setString(4, combo.getCategoria().name());
        ps.setString(5, combo.getEstado().name());
        ps.setBlob(6, combo.getFoto());
    }
    
    public int agregar(Combo combo){
        String sql = "Insert into Combo(nombreCombo, descripcionCombo, precioCombo, categoria, estado, foto) values (?, ?, ?, ?, ?, ?)";
        try{
            preparedSQL(combo, sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    public List listar(){
        String sql = "Select * from Combo where estado = 'Activo'";
        List<Combo> listaCombo = new ArrayList<>();
        try{
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Combo combo = new Combo();
                createCombo(combo);
                
                listaCombo.add(combo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCombo;
    }
    
    public Combo buscar(int codigoCombo){
        Combo combo = new Combo();
        String sql = "Select * from Combo where codigoCombo = ?" 
                + "and estado = 'Activo'";
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoCombo);
            rs = ps.executeQuery();
            
            if(rs.next()){
                createCombo(combo);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return combo;
    }
    
    public int actualizar(Combo combo){
        String sql = "Update Combo set nombreCombo = ?, "
                + "descripcionCombo = ?, precioCombo = ?," +
                "categoria = ?, estado = ?," + "foto = ? where codigoCombo = ?";
        try{
            preparedSQL(combo,sql);
            ps.setInt(8, combo.getCodigoCombo());
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int codigoCombo){
        String sql = "Update Combo set estado = 'Inactivo' where "
                + "codigoCombo = ?";
        try{
            ps = cn.prepareStatement(sql);
            ps.setInt(1,codigoCombo);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
