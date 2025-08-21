
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author informatica
 */
public class FacturaDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List<Factura> listar(){
        String sql = "select * from factura";
        List<Factura> listaFactura = new ArrayList<>();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Factura fac = new Factura();
                fac.setCodigoFactura(rs.getInt("codigoFactura"));
                fac.setCodigoPedido(rs.getInt("codigoPedido"));
                fac.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                fac.setTotalFactura(rs.getFloat("totalFactura"));
                fac.setDonacion(rs.getFloat("donacion"));
                fac.setFechaFactura(rs.getDate("fechaFactura"));
                fac.setHoraFactura(rs.getString("horaFactura"));
                fac.setMetodo(rs.getString("metodo"));
                fac.setEstado(rs.getString("estado"));
                listaFactura.add(fac);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaFactura;
    }
    
    //Metodo agregar
    public int agregar(Factura fac){
        String sql = "insert into factura (codigoPedido, codigoEmpleado, totalFactura, donacion, fechaFactura, horaFactura, metodo, estado) "
                   + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fac.getCodigoPedido());
            ps.setInt(2, fac.getCodigoEmpleado());
            ps.setFloat(3, fac.getTotalFactura());
            ps.setFloat(4, fac.getDonacion());
            ps.setDate(5, fac.getFechaFactura());
            ps.setString(6, fac.getHoraFactura());
            ps.setString(7, fac.getMetodo());
            ps.setString(8, fac.getEstado());
            resp = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Metodo buscar
    public Factura listarCodigoFactura(int id){
        Factura fac = new Factura();
        String sql = "select * from factura where codigoFactura = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                fac.setCodigoFactura(rs.getInt("codigoFactura"));
                fac.setCodigoPedido(rs.getInt("codigoPedido"));
                fac.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                fac.setTotalFactura(rs.getFloat("totalFactura"));
                fac.setDonacion(rs.getFloat("donacion"));
                fac.setFechaFactura(rs.getDate("fechaFactura"));
                fac.setHoraFactura(rs.getString("horaFactura"));
                fac.setMetodo(rs.getString("metodo"));
                fac.setEstado(rs.getString("estado"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return fac;
    }
    
    //Metodo eliminar
    public int actualizar(Factura fac){
        String sql = "update factura set codigoPedido=?, codigoEmpleado=?, totalFactura=?, donacion=?, fechaFactura=?, horaFactura=?, metodo=?, estado=? "
                   + "where codigoFactura=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fac.getCodigoPedido());
            ps.setInt(2, fac.getCodigoEmpleado());
            ps.setFloat(3, fac.getTotalFactura());
            ps.setFloat(4, fac.getDonacion());
            ps.setDate(5, fac.getFechaFactura());
            ps.setString(6, fac.getHoraFactura());
            ps.setString(7, fac.getMetodo());
            ps.setString(8, fac.getEstado());
            ps.setInt(9, fac.getCodigoFactura());
            resp = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Metodo eliminar
    public void eliminar(int id){
        String sql = "delete from factura where codigoFactura=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
