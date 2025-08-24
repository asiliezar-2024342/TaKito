/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class EmpleadoDAO {
    
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List listar(){
        String sql = "select * from Empleado where estado = 'Activo'";
        List<Empleado> listaEmpleado = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Empleado emp = new Empleado();
                emp.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                emp.setPrimerNombreEmpleado(rs.getString("primerNombreEmpleado"));
                emp.setSegundoNombreEmpleado(rs.getString("segundoNombreEmpleado"));
                emp.setPrimerApellidoEmpleado(rs.getString("primerApellidoEmpleado"));
                emp.setSegundoApellidoEmpleado(rs.getString("segundoApellidoEmpleado"));
                emp.setTelefonoEmpleado(rs.getString("telefonoEmpleado"));
                emp.setCorreoEmpleado(rs.getString("correoEmpleado"));
                emp.setDireccionEmpleado(rs.getString("direccionEmpleado"));
                emp.setEstado(Empleado.Estado.valueOf(rs.getString("estado")));
                emp.setSexoEmpleado(Empleado.SexoEmpleado.valueOf(rs.getString("sexoEmpleado")));
                emp.setCodigoSucursal(rs.getInt("codigoSucursal"));
                emp.setCodigoUsuario(rs.getInt("codigoUsuario"));
                listaEmpleado.add(emp);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpleado;
    }
    public int agregar(Empleado emp){
        String sql = "insert into Empleado (primerNombreEmpleado, segundoNombreEmpleado, "
                + "primerApellidoEmpleado, segundoApellidoEmpleado, telefonoEmpleado, "
                + "correoEmpleado, direccionEmpleado, estado, sexoEmpleado, codigoSucursal, "
                + "codigoUsuario) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getPrimerNombreEmpleado());
            ps.setString(2, emp.getSegundoNombreEmpleado());
            ps.setString(3, emp.getPrimerApellidoEmpleado());
            ps.setString(4, emp.getSegundoApellidoEmpleado());
            ps.setString(5, emp.getTelefonoEmpleado());
            ps.setString(6, emp.getCorreoEmpleado());
            ps.setString(7, emp.getDireccionEmpleado());
            ps.setString(8, emp.getEstado().name());
            ps.setString(9, emp.getSexoEmpleado().name());
            ps.setInt(10, emp.getCodigoSucursal());
            ps.setInt(11, emp.getCodigoUsuario());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    public Empleado buscar(int codigo){
        Empleado emp = new Empleado();
        String sql = "Select * from Empleado where codigoEmpleado = ? and estado = 'Activo'";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                emp.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                emp.setPrimerNombreEmpleado(rs.getString("primerNombreEmpleado"));
                emp.setSegundoNombreEmpleado(rs.getString("segundoNombreEmpleado"));
                emp.setPrimerApellidoEmpleado(rs.getString("primerApellidoEmpleado"));
                emp.setSegundoApellidoEmpleado(rs.getString("segundoApellidoEmpleado"));
                emp.setTelefonoEmpleado(rs.getString("telefonoEmpleado"));
                emp.setCorreoEmpleado(rs.getString("correoEmpleado"));
                emp.setDireccionEmpleado(rs.getString("direccionEmpleado"));
                emp.setEstado(Empleado.Estado.valueOf(rs.getString("estado")));
                emp.setSexoEmpleado(Empleado.SexoEmpleado.valueOf(rs.getString("sexoEmpleado")));
                emp.setCodigoSucursal(rs.getInt("codigoSucursal"));
                emp.setCodigoUsuario(rs.getInt("codigoUsuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
    
    public List<Empleado> buscarEmpleadosPorSucursal(int codigoS) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "select * from Empleado where codigoSucursal = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoS);
            rs = ps.executeQuery();
            while (rs.next()) { 
                Empleado emp = new Empleado();
                emp.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                emp.setPrimerNombreEmpleado(rs.getString("primerNombreEmpleado"));
                emp.setSegundoNombreEmpleado(rs.getString("segundoNombreEmpleado"));
                emp.setPrimerApellidoEmpleado(rs.getString("primerApellidoEmpleado"));
                emp.setSegundoApellidoEmpleado(rs.getString("segundoApellidoEmpleado"));
                emp.setTelefonoEmpleado(rs.getString("telefonoEmpleado"));
                emp.setCorreoEmpleado(rs.getString("correoEmpleado"));
                emp.setDireccionEmpleado(rs.getString("direccionEmpleado"));
                emp.setEstado(Empleado.Estado.valueOf(rs.getString("estado")));
                emp.setSexoEmpleado(Empleado.SexoEmpleado.valueOf(rs.getString("sexoEmpleado")));
                emp.setCodigoSucursal(rs.getInt("codigoSucursal"));
                emp.setCodigoUsuario(rs.getInt("codigoUsuario"));
                empleados.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }
    
    public List<Empleado.Facturacion> facturacionEmpleadoPorSucursal(int codigoS){
        List<Empleado.Facturacion> empleadoFacturacion = new ArrayList<>();
        String sql = "select e.codigoEmpleado, concat(e.primerNombreEmpleado, ' ', e.primerApellidoEmpleado)"
        + " as nombreCompleto, sum(f.totalFactura) as totalFacturado from Empleado e"
        + " left join Factura f on e.codigoEmpleado = f.codigoEmpleado where"
        + " e.codigoSucursal = ? group by e.codigoEmpleado order by totalFacturado desc";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoS);
            rs = ps.executeQuery();
            while(rs.next()){
            Empleado.Facturacion fac = new Empleado.Facturacion();
            fac.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
            fac.setNombreCompleto(rs.getString("nombreCompleto"));
            fac.setTotalFacturado(rs.getDouble("totalFacturado"));
            empleadoFacturacion.add(fac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleadoFacturacion;
    }
    
    public int actualizar(Empleado emp){
        String sql = "update empleado set primerNombreEmpleado = ?, segundoNombreEmpleado = ?, primerApellidoEmpleado = ?, "
        + "segundoApellidoEmpleado = ?, telefonoEmpleado = ?, correoEmpleado = ?, direccionEmpleado = ?, "
        + "estado = ?, sexoEmpleado = ?, codigoSucursal = ?, codigoUsuario = ? where codigoEmpleado = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getPrimerNombreEmpleado());
            ps.setString(2, emp.getSegundoNombreEmpleado());
            ps.setString(3, emp.getPrimerApellidoEmpleado());
            ps.setString(4, emp.getSegundoApellidoEmpleado());
            ps.setString(5, emp.getTelefonoEmpleado());
            ps.setString(6, emp.getCorreoEmpleado());
            ps.setString(7, emp.getDireccionEmpleado());
            ps.setString(8, emp.getEstado().name());
            ps.setString(9, emp.getSexoEmpleado().name());
            ps.setInt(10, emp.getCodigoSucursal());
            ps.setInt(11, emp.getCodigoUsuario());
            ps.setInt(12, emp.getCodigoEmpleado());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    public void eliminar(int codigo){
        String sql = "update empleado set estado = 'Inactivo' where codigoEmpleado = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
