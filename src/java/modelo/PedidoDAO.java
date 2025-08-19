package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //MÉTODOS DEL CRUD
    //MÉTODO LISTAR
    public List listar(){
        String sql="select * from pedido";
        List<Pedido> listaPedido = new ArrayList<>();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Pedido pe = new Pedido();
                pe.setCodigoPedido(rs.getInt(1));
                pe.setFechaCreacion(rs.getDate(2));
                pe.setHoraCreacion(rs.getTime(3));
                pe.setFechaProgramado(rs.getDate(4));
                pe.setHoraProgramado(rs.getTime(5));
                pe.setUbicacionPedido(rs.getString(6));
                pe.setTipoPedido(Pedido.TipoPedido.valueOf(rs.getString(7)));
                pe.setEstado(Pedido.Estado.valueOf(rs.getString(8)));
                pe.setCodigoSucursal(rs.getInt(9));
                pe.setCodigoCliente(rs.getInt(10));
                listaPedido.add(pe);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaPedido;
    }
    
    //MÉTODO AGREGAR
    public int agregar(Pedido pe){
        String sql = "insert into Pedido  (fechaCreacion, horaCreacion, fechaProgramado, horaProgramado, ubicacionPedido, tipoPedido, estado, codigoSucursal, codigoCliente) values (?,?,?,?,?,?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, pe.getFechaCreacion());
            ps.setTime(2, pe.getHoraCreacion());
            ps.setDate(3, pe.getFechaProgramado());
            ps.setTime(4, pe.getHoraProgramado());
            ps.setString(5, pe.getUbicacionPedido());
            ps.setString(6, pe.getTipoPedido().name());
            ps.setString(7, pe.getEstado().name());
            ps.setInt(8, pe.getCodigoSucursal());
            ps.setInt(9, pe.getCodigoCliente());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    }
    
    //BUSCAR POR CÓDIGO
    public Pedido listarCodigoPedido(int id){
        Pedido pe = new Pedido();
        String sql = "Select * from Pedido where codigoPedido = "+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next()){
                pe.setFechaCreacion(rs.getDate(2));
                pe.setHoraCreacion(rs.getTime(3));
                pe.setFechaProgramado(rs.getDate(4));
                pe.setHoraProgramado(rs.getTime(5));
                pe.setUbicacionPedido(rs.getString(6));
                pe.setTipoPedido(Pedido.TipoPedido.valueOf(rs.getString(7)));
                pe.setEstado(Pedido.Estado.valueOf(rs.getString(8)));
                pe.setCodigoSucursal(rs.getInt(9));
                pe.setCodigoCliente(rs.getInt(10));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return pe;
    }
    
    //MÉTODO EDITAR
    public int actualizar(Pedido pe){
        String sql = "update pedido set fechaCreacion = ?," 
                                    +"horaCreacion = ?,"
                                    +"fechaProgramado = ?,"
                                    +"horaProgramado = ?,"
                                    +"ubicacionPedido = ?,"
                                    + "tipoPedido = ?,"
                                    + "estado = ?,"
                                    + "codigoSucursal = ?,"
                                    + "codigoCliente = ? where codigoEmpleado = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, pe.getFechaCreacion());
            ps.setTime(2, pe.getHoraCreacion());
            ps.setDate(3, pe.getFechaProgramado());
            ps.setTime(4, pe.getHoraProgramado());
            ps.setString(5, pe.getUbicacionPedido());
            ps.setString(6, pe.getTipoPedido().name());
            ps.setString(6, pe.getEstado().name());
            ps.setInt(6, pe.getCodigoSucursal());
            ps.setInt(6, pe.getCodigoCliente());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //MÉTODO ELIMINAR
    public void eliminar(int id){
        String sql = "delete from pedido where codigoPedido="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
