package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {

    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    // Metodos del crud 
    /*Lista Sucursal*/
    public List Listar() {
        String sql = "SELECT * FROM Sucursal WHERE estado = 'Activo'";
        List<Sucursal> listaSucursal = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sucursal su = new Sucursal();
                su.setCodigoSucursal(rs.getInt(1));
                su.setUbicacionSucursal(rs.getString(2));
                su.setTelefonoSucursal(rs.getString(3));
                su.setNombreSucursal(rs.getString(4));
                su.setFuncionamiento(Sucursal.Funcionamiento.valueOf(rs.getString(5)));
                su.setEstado(Sucursal.Estado.valueOf(rs.getString(6)));
                listaSucursal.add(su);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaSucursal;

    }

    /*Listar Sucursal*/
    public int Agregar(Sucursal su) {
        String sql = "INSERT INTO Sucursal (ubicacionSucursal, telefonoSucursal, nombreSucursal, funcionamiento, estado) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {

            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, su.getUbicacionSucursal());
            ps.setString(2, su.getTelefonoSucursal());
            ps.setString(3, su.getNombreSucursal());
            ps.setString(4, su.getFuncionamiento().name());
            ps.setString(5, su.getEstado().name());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resp;
    }

    //  BUSCAR SUCRUSAL 
    public int listarCodigoSucursal(int id) {
        String sql = "SELECT * FROM Sucursal WHERE codigoSucursal = ? AND estado = 'Activo'";
        try {
            Sucursal su = new Sucursal();
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            while (rs.next()) {
                su.setUbicacionSucursal(rs.getString(2));
                su.setTelefonoSucursal(rs.getString(3));
                su.setNombreSucursal(rs.getString(4));
                su.setFuncionamiento(Sucursal.Funcionamiento.valueOf(rs.getString(5)));
                su.setEstado(Sucursal.Estado.valueOf(rs.getString(6)));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;

    }

    // EDITAR
    public int Actualizar(Sucursal su) {
        String sql = "update Sucursal set ubicacionSucursal = ?,"
                + "telefonoSucursal = ?, nombreSucursal = ?,"
                + "funcionamiento = ?, estado = ? where codigoSucursal = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, su.getUbicacionSucursal());
            ps.setString(2, su.getTelefonoSucursal());
            ps.setString(3, su.getNombreSucursal());
            ps.setString(4, su.getFuncionamiento().name());
            ps.setString(5, su.getEstado().name());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int Eliminar(int id) {
        String sql = "UPDATE Sucursal SET estado = 'Inactivo' WHERE codigoSucursal = ? AND estado = 'Activo'";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

} 
