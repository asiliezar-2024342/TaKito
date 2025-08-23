package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleComboDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List listar(){
        String sql = "select * from DetalleCombo";
        List<DetalleCombo> listaDetalleCombo = new ArrayList<>();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DetalleCombo dc = new DetalleCombo();
                dc.setCodigoDetalleCombo(rs.getInt(1));
                dc.setCantidad(rs.getInt(2));
                dc.setCodigoCombo(rs.getInt(3));
                dc.setCodigoProducto(rs.getInt(4));
                listaDetalleCombo.add(dc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDetalleCombo;
    }
    
    public int agregar(DetalleCombo dco){
        String sql = "insert into DetalleCombo (cantidad, codigoCombo, codigoProducto) values (?, ?, ?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dco.getCantidad());
            ps.setInt(2, dco.getCodigoCombo());
            ps.setInt(3, dco.getCodigoProducto());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public DetalleCombo listarCodigoDetalleCombo(int id){
        DetalleCombo dco = new DetalleCombo();
        String sql = "Select * from DetalleCombo where codigoDetalleCombo = "+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                dco.setCantidad(rs.getInt(2));
                dco.setCodigoDetalleCombo(rs.getInt(3));
                dco.setCodigoProducto(rs.getInt(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dco;
    }
    
    public int actualizar(DetalleCombo dco){
        String sql = "update DetalleCombo set cantidad = ? where codigoDetalleCombo = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dco.getCantidad());
            ps.setInt(2, dco.getCodigoDetalleCombo());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from DetalleCombo where codigoDetalleCombo ="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
