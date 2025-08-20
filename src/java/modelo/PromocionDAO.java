package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PromocionDAO {
    
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
    private void createPromocion(Promocion promocion) throws Exception {
        promocion.setCodigoPromocion(rs.getInt("codigoPromocion"));
        promocion.setNombrePromocion(rs.getString("nombrePromocion"));
        promocion.setDescripcionPromocion(rs.getString("descripcionPromocion"));
        promocion.setDescuentoPromocion(rs.getDouble("descuentoPromocion"));
        promocion.setFechaInicio(rs.getDate("fechaInicio"));
        promocion.setFechaFin(rs.getDate("fechaFin"));
        promocion.setEstado(Promocion.Estado.valueOf(rs.getString("estado")));
    }
    
    private void preparedSQL(Promocion promocion, String sql) throws Exception {
        ps = con.prepareStatement(sql);
        ps.setString(1, promocion.getNombrePromocion());
        ps.setString(2, promocion.getDescripcionPromocion());
        ps.setDouble(3, promocion.getDescuentoPromocion());
        ps.setDate(4, promocion.getFechaInicio());
        ps.setDate(5, promocion.getFechaFin());
        ps.setString(6, promocion.getEstado().name());
    }
    
    public List listar(){
        String sql = "Select * from Promocion where estado = 'Activo'";
        List<Promocion> listaPromocion = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promocion pro = new Promocion();
                createPromocion(pro);
                listaPromocion.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPromocion;
    }
    
    public List<Promocion> listarPromocionesActivas() {
        String sql = "Select * from Promocion where estado = 'Activo' and current_date between fechaInicio and fechaFin";
        List<Promocion> promocionesActivas = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promocion pro = new Promocion();
                createPromocion(pro);
                promocionesActivas.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promocionesActivas;
    }
    
    public List<Promocion> listarPromocionesMasUsadas() {
        String sql = "Select * from Promocion where estado = 'Activo' order by fechaFin - fechaInicio desc limit 3";
        List<Promocion> promocionesMasUsadas = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promocion pro = new Promocion();
                createPromocion(pro);
                promocionesMasUsadas.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promocionesMasUsadas;
    }
    
    public List<Promocion> listarPromocionesFuturas() {
        String sql = "Select * from Promocion where estado = 'Activo' and fechaInicio between current_date() and date_add(current_date(), interval 30 day)";
        List<Promocion> promocionesFuturas = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promocion pro = new Promocion();
                createPromocion(pro);
                promocionesFuturas.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promocionesFuturas;
    }
    
    public Promocion buscar(int codigoPromocion) {
        String sql = "Select * from Promocion where codigoPromocion = ? and estado = 'Activo'";
        Promocion pro = new Promocion();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoPromocion);
            rs = ps.executeQuery();
            if (rs.next()) {
                createPromocion(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }
    
    public int agregar(Promocion pro){
        String sql = "Insert into Promocion (nombrePromocion, descripcionPromocion, descuentoPromocion, fechaInicio, fechaFin, estado) values (?, ?, ?, ?, ?, ?)";
        try {
            preparedSQL(pro, sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    public int actualizar(Promocion pro) {
        String sql = "Update Promocion set nombrePromocion = ?, descripcionPromocion = ?, descuentoPromocion = ?, fechaInicio = ?, fechaFin = ?, estado = ? where codigoPromocion = ?";
        try {
            preparedSQL(pro, sql);
            ps.setInt(7, pro.getCodigoPromocion());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    public void eliminar(int codigoPromocion) {
        String sql = "Update Promocion set estado = 'Inactivo' where codigoPromocion = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoPromocion);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

