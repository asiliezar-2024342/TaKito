package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetallePromocionDAO {
    
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
    private void createDetallePromocion(DetallePromocion detallePromocion) throws Exception {
        detallePromocion.setCodigoDetallePromocion(rs.getInt("codigoDetallePromocion"));
        detallePromocion.setObservaciones(rs.getString("observaciones"));
        detallePromocion.setCodigoPromocion(rs.getInt("codigoPromocion"));
        detallePromocion.setCodigoCombo(rs.getInt("codigoCombo"));
    }
    
    private void preparedSQL(DetallePromocion detallePromocion, String sql) throws Exception {
        ps = con.prepareStatement(sql);
        ps.setString(1, detallePromocion.getObservaciones());
        ps.setInt(2, detallePromocion.getCodigoPromocion());
        ps.setInt(3, detallePromocion.getCodigoCombo());
    }
    
    public List listar(){
        String sql = "Select * from DetallePromocion";
        List<DetallePromocion> listaDetallePromocion = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePromocion detPro = new DetallePromocion();
                createDetallePromocion(detPro);
                listaDetallePromocion.add(detPro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetallePromocion;
    }
    
    public DetallePromocion buscar(int codigoDetallePromocion) {
        String sql = "Select * from DetallePromocion where codigoDetallePromocion = ?";
        DetallePromocion detPro = new DetallePromocion();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoDetallePromocion);
            rs = ps.executeQuery();
            if (rs.next()) {
                createDetallePromocion(detPro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detPro;
    }
    
    public int agregar(DetallePromocion detPro){
        String sql = "Insert into DetallePromocion (observaciones, codigoPromocion, codigoCombo) values (?, ?, ?)";
        try {
            preparedSQL(detPro, sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    public int actualizar(DetallePromocion detPro) {
        String sql = "Update DetallePromocion set observaciones = ?, codigoPromocion = ?, codigoCombo = ? where codigoDetallePromocion = ?";
        try {
            preparedSQL(detPro, sql);
            ps.setInt(4, detPro.getCodigoDetallePromocion());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    public void eliminar(int codigoDetallePromocion) {
        String sql = "Delete from DetallePromocion where codigoDetallePromocion = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoDetallePromocion);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
