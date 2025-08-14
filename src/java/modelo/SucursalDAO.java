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

     
    public int agregar(Sucursal sucursal) {
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
        List<Sucursal> lista = new ArrayList<>();

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                createSucursal(sucursal);
                lista.add(sucursal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public Sucursal buscar(int codigoSucursal) {
        Sucursal sucursal = null;
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

   
    public int eliminar(int codigoSucursal) {
        int resultado = 0;
        String sql = "UPDATE Sucursal SET estado = 'Inactivo' WHERE codigoSucursal = ? AND estado = 'Activo'";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoSucursal);
            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
