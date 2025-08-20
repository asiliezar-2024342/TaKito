package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List listar(){
        String sql = "select * from Producto where estado = 'Activo'";
        List<Producto> listaProducto = new ArrayList<>();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Producto producto = new Producto();
                producto.setCodigoProducto(rs.getInt(1));
                producto.setNombreProducto(rs.getString(2));
                producto.setDescripcionProducto(rs.getString(3));
                producto.setPrecioUnitario(rs.getDouble(4));
                producto.setExistencias(rs.getInt(5));
                producto.setEstado(Producto.Estado.valueOf(rs.getString(6)));
                listaProducto.add(producto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaProducto;
    }
    
    public int agregar (Producto producto){
        String sql = "Insert into Producto (nombreProducto, descripcionProducto, precioUnitario, existencias, estado) values (?, ?, ?, ?, ?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setDouble(3, producto.getPrecioUnitario());
            ps.setInt(4, producto.getExistencias());
            ps.setString(5, producto.getEstado().name());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    public Producto listaCodigoProducto(int id){
        Producto producto = new Producto();
        String sql ="Select * from Producto where codigoProducto ="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                producto.setNombreProducto(rs.getString(2));
                producto.setDescripcionProducto(rs.getString(3));
                producto.setPrecioUnitario(rs.getDouble(4));
                producto.setExistencias(rs.getInt(5));
                producto.setEstado(Producto.Estado.valueOf(rs.getString(6)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return producto;
    } 
    
    public int actualizar(Producto producto){
        String sql = "Update Producto set nombreProducto = ?,"
                + "descripcionProducto = ?,"
                + "precioUnitario = ?,"
                + "existencias = ?,"
                + "estado = ? where codigoProducto = ?";
        try{
            con = cn.getConexion();           
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setDouble(3, producto.getPrecioUnitario());
            ps.setInt(4, producto.getExistencias());
            ps.setString(5, producto.getEstado().name());
            ps.setInt(6, producto.getCodigoProducto());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from Producto where codigoProducto ="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
