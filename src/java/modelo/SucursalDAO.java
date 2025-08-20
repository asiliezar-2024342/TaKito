package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {

    Connection cn = Conexion.getInstance().getConexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    private void createSucursal(Sucursal sucursal) throws Exception {
        sucursal.setCodigoSucursal(rs.getInt("codigoSucursal"));
        sucursal.setUbicacionSucursal(rs.getString("ubicacionSucursal"));
        sucursal.setTelefonoSucursal(rs.getString("telefonoSucursal"));
        sucursal.setNombreSucursal(rs.getString("nombreSucursal"));
        sucursal.setFuncionamiento(Sucursal.Funcionamiento.valueOf(rs.getString("funcionamiento")));
        sucursal.setEstado(Sucursal.Estado.valueOf(rs.getString("estado")));
    }

    private void preparedSQL(Sucursal sucursal, String sql) throws Exception {
        ps = cn.prepareStatement(sql);
        ps.setString(1, sucursal.getUbicacionSucursal());
        ps.setString(2, sucursal.getTelefonoSucursal());
        ps.setString(3, sucursal.getNombreSucursal());
        ps.setString(4, sucursal.getFuncionamiento().name());
        ps.setString(5, sucursal.getEstado().name());
    }

    public int Agregar(Sucursal sucursal) {
        int resultado = 0;
        String sql = "INSERT INTO Sucursal (ubicacionSucursal, telefonoSucursal, nombreSucursal, funcionamiento, estado) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            preparedSQL(sucursal, sql);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public List<Sucursal> listar() {
        String sql = "SELECT * FROM Sucursal WHERE estado = 'Activo'";
        List<Sucursal> listaSucursal = new ArrayList<>();

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                createSucursal(sucursal);
                listaSucursal.add(sucursal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaSucursal;
    }

    public Sucursal Buscar(int codigoSucursal) {
        Sucursal sucursal = new Sucursal();
        String sql = "SELECT * FROM Sucursal WHERE codigoSucursal = ? AND estado = 'Activo'";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoSucursal);
            rs = ps.executeQuery();

            if (rs.next()) {
                sucursal = new Sucursal();
                createSucursal(sucursal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucursal;
    }

    public int Actualizar(Sucursal sucursal) {
        String sql = "update Sucursal set ubicacionSucursal = ?,"
                + "telefonoSucursal = ?, nombreSucursal = ?,"
                + "funcionamiento = ?, estado = ? where codigoSucursal = ?";

        try {
            preparedSQL(sucursal, sql);
            ps.setInt(6, sucursal.getCodigoSucursal());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public void Eliminar(int codigoSucursal) {
        String sql = "UPDATE Sucursal SET estado = 'Inactivo' WHERE codigoSucursal = ?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoSucursal);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}
