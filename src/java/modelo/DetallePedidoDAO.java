package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //MÉTODOS DEL CRUD
    //MÉTODO LISTAR
    public List listar(){
        String sql="select * from detallePedido";
        List<DetallePedido> listaDetallePedido = new ArrayList<>();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DetallePedido depe = new DetallePedido();
                depe.setCodigoDetallePedido(rs.getInt(1));
                depe.setInstrucciones(rs.getString(2));
                depe.setCantidad(rs.getInt(3));
                depe.setSubTotal(rs.getDouble(4));
                depe.setCodigoPedido(rs.getInt(5));
                depe.setCodigoCombo(rs.getInt(6));
                depe.setCodigoPromocion(rs.getInt(7));
                listaDetallePedido.add(depe);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDetallePedido;
    }
    
    //MÉTODO AGREGAR
    public int agregar(DetallePedido depe){
        String sql = "insert into DetallePedido (instrucciones, cantidad, subTotal, codigoPedido, codigoCombo, codigoPromocion) values (?,?,?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, depe.getInstrucciones());
            ps.setInt(2, depe.getCantidad());
            ps.setDouble(3, depe.getSubTotal());
            ps.setInt(4, depe.getCodigoPedido());
            ps.setInt(5, depe.getCodigoCombo());
            ps.setInt(6, depe.getCodigoPromocion());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    //BUSCAR POR CÓDIGO
    public DetallePedido listarCodigoDetallePedido(int id){
        DetallePedido depe = new DetallePedido();
        String sql = "Select * from DetallePedido where codigoDetallePedido= "+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next()){
                depe.setInstrucciones(rs.getString(2));
                depe.setCantidad(rs.getInt(3));
                depe.setSubTotal(rs.getDouble(4));
                depe.setCodigoPedido(rs.getInt(5));
                depe.setCodigoCombo(rs.getInt(6));
                depe.setCodigoPromocion(rs.getInt(7));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return depe;
    }
    
    //MÉTODO EDITAR
    public int actualizar(DetallePedido depe){
        String sql = "update detallePedido set instrucciones = ?," 
                                    +"cantidad = ?,"
                                    +"subtotal = ?,"
                                    +"codigoPedido = ?,"
                                    +"codigoCombo = ?,"
                                    +"codigoPromocion = ? where codigoEmpleado = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, depe.getInstrucciones());
            ps.setInt(2, depe.getCantidad());
            ps.setDouble(3, depe.getSubTotal());
            ps.setInt(4, depe.getCodigoPedido());
            ps.setInt(5, depe.getCodigoCombo());
            ps.setInt(6, depe.getCodigoPromocion());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //MÉTODO ELIMINAR
    public void eliminar(int id){
        String sql = "delete from detallePedido where codigoDetallePedido="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
