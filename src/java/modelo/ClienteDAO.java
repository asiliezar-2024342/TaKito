package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /*  Listar Clientes Activos */
    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente WHERE estado = 'Activo'";
        List<Cliente> listaCliente = new ArrayList<>();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setCodigoCliente(rs.getInt("codigoCliente"));
                cl.setPrimerNombreCliente(rs.getString("primerNombreCliente"));
                cl.setSegundoNombreCliente(rs.getString("segundoNombreCliente"));
                cl.setPrimerApellidoCliente(rs.getString("primerApellidoCliente"));
                cl.setSegundoApellidoCliente(rs.getString("segundoApellidoCliente"));
                cl.setTelefonoCliente(rs.getString("telefonoCliente"));
                cl.setDireccionCliente(rs.getString("direccionCliente"));
                cl.setSexoCliente(Cliente.SexoCliente.valueOf(rs.getString("sexoCliente")));
                cl.setNitCliente(rs.getString("nitCliente"));
                cl.setEstado(Cliente.EstadoCliente.valueOf(rs.getString("estado")));
                cl.setCodigoUsuario(rs.getInt("codigoUsuario"));
                listaCliente.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCliente;
    }

    /* Agregar Cliente*/
    public boolean agregar(Cliente cl) {
        String sql = "INSERT INTO cliente (primerNombreCliente, segundoNombreCliente, primerApellidoCliente, segundoApellidoCliente, telefonoCliente, direccionCliente, sexoCliente, nitCliente, estado, codigoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getPrimerNombreCliente());
            ps.setString(2, cl.getSegundoNombreCliente());
            ps.setString(3, cl.getPrimerApellidoCliente());
            ps.setString(4, cl.getSegundoApellidoCliente());
            ps.setString(5, cl.getTelefonoCliente());
            ps.setString(6, cl.getDireccionCliente());
            ps.setString(7, cl.getSexoCliente().name());
            ps.setString(8, cl.getNitCliente());
            ps.setString(9, cl.getEstado().name());
            ps.setInt(10, cl.getCodigoUsuario());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* Buscar Cliente por ID*/
    public Cliente buscar(int codigoCliente) {
        String sql = "SELECT * FROM cliente WHERE codigoCliente = ? AND estado = 'Activo'";
        Cliente cl = null;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl = new Cliente();
                cl.setCodigoCliente(rs.getInt("codigoCliente"));
                cl.setPrimerNombreCliente(rs.getString("primerNombreCliente"));
                cl.setSegundoNombreCliente(rs.getString("segundoNombreCliente"));
                cl.setPrimerApellidoCliente(rs.getString("primerApellidoCliente"));
                cl.setSegundoApellidoCliente(rs.getString("segundoApellidoCliente"));
                cl.setTelefonoCliente(rs.getString("telefonoCliente"));
                cl.setDireccionCliente(rs.getString("direccionCliente"));
                cl.setSexoCliente(Cliente.SexoCliente.valueOf(rs.getString("sexoCliente")));
                cl.setNitCliente(rs.getString("nitCliente"));
                cl.setEstado(Cliente.EstadoCliente.valueOf(rs.getString("estado")));
                cl.setCodigoUsuario(rs.getInt("codigoUsuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }

    /* Editar Cliente*/
    public boolean editar(Cliente cl) {
        String sql = "UPDATE cliente SET primerNombreCliente=?, segundoNombreCliente=?, primerApellidoCliente=?, segundoApellidoCliente=?, telefonoCliente=?, direccionCliente=?, sexoCliente=?, nitCliente=?, estado=?, codigoUsuario=? WHERE codigoCliente=?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getPrimerNombreCliente());
            ps.setString(2, cl.getSegundoNombreCliente());
            ps.setString(3, cl.getPrimerApellidoCliente());
            ps.setString(4, cl.getSegundoApellidoCliente());
            ps.setString(5, cl.getTelefonoCliente());
            ps.setString(6, cl.getDireccionCliente());
            ps.setString(7, cl.getSexoCliente().name());
            ps.setString(8, cl.getNitCliente());
            ps.setString(9, cl.getEstado().name());
            ps.setInt(10, cl.getCodigoUsuario());
            ps.setInt(11, cl.getCodigoCliente());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* Eliminar Cliente (borrado lógico)*/
    public boolean eliminar(int codigoCliente) {
        String sql = "UPDATE cliente SET estado = 'Inactivo' WHERE codigoCliente = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCliente);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /* Buscar nombre del cliente usando codigoUsuario */
    public Cliente buscarUsuario(int codigoUsuario) {
        String sql = "select * from cliente where codigoUsuario = ? and estado = 'Activo'";
        Cliente cl = null;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl = new Cliente();
                cl.setCodigoCliente(rs.getInt("codigoCliente"));
                cl.setPrimerNombreCliente(rs.getString("primerNombreCliente"));
                cl.setSegundoNombreCliente(rs.getString("segundoNombreCliente"));
                cl.setPrimerApellidoCliente(rs.getString("primerApellidoCliente"));
                cl.setSegundoApellidoCliente(rs.getString("segundoApellidoCliente"));
                cl.setTelefonoCliente(rs.getString("telefonoCliente"));
                cl.setDireccionCliente(rs.getString("direccionCliente"));
                cl.setSexoCliente(Cliente.SexoCliente.valueOf(rs.getString("sexoCliente")));
                cl.setNitCliente(rs.getString("nitCliente"));
                cl.setEstado(Cliente.EstadoCliente.valueOf(rs.getString("estado")));
                cl.setCodigoUsuario(rs.getInt("codigoUsuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }
}
