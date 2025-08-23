package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Connection cn = Conexion.getInstance().getConexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    //Método para crear datos de cliente
    private void createCliente(Cliente cliente) throws Exception {
        cliente.setCodigoCliente(rs.getInt("codigoCliente"));
        cliente.setPrimerNombreCliente(rs.getString("primerNombreCliente"));
        cliente.setSegundoNombreCliente(rs.getString("segundoNombreCliente"));
        cliente.setPrimerApellidoCliente(rs.getString("primerApellidoCliente"));
        cliente.setSegundoApellidoCliente(rs.getString("segundoApellidoCliente"));
        cliente.setTelefonoCliente(rs.getString("telefonoCliente"));
        cliente.setDireccionCliente(rs.getString("direccionCliente"));
        cliente.setSexoCliente(Cliente.SexoCliente.valueOf(rs.getString("sexoCliente")));
        cliente.setNitCliente(rs.getString("nitCliente"));
        cliente.setEstado(Cliente.EstadoCliente.valueOf(rs.getString("estado")));
        cliente.setCodigoUsuario(rs.getInt("codigoUsuario"));
    }

    //Método para preparar la consulta SQL
    private void preparedSQL(Cliente cliente, String sql) throws Exception {
        ps = cn.prepareStatement(sql);
        ps.setString(1, cliente.getPrimerNombreCliente());
        ps.setString(2, cliente.getSegundoNombreCliente());
        ps.setString(3, cliente.getPrimerApellidoCliente());
        ps.setString(4, cliente.getSegundoApellidoCliente());
        ps.setString(5, cliente.getTelefonoCliente());
        ps.setString(6, cliente.getDireccionCliente());
        ps.setString(7, cliente.getSexoCliente().name());
        ps.setString(8, cliente.getNitCliente());
        ps.setString(9, cliente.getEstado().name());
        ps.setInt(10, cliente.getCodigoUsuario());

    }


    /* Agregar Cliente*/
    public int Agregar(Cliente cliente) {
        String sql = "Insert Into cliente (primerNombreCliente, segundoNombreCliente, "
                + "primerApellidoCliente, segundoApellidoCliente, telefonoCliente, "
                + "direccionCliente, sexoCliente, nitCliente, estado, "
                + "codigoUsuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedSQL(cliente, sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    /*  Listar Clientes */
    public List listar() {
        String sql = "select * from cliente where estado = 'Activo'";
        List<Cliente> listaCliente = new ArrayList<>();
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                createCliente(cliente);

                listaCliente.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCliente;
    }

    /* Buscar Cliente */
    public Cliente buscar(int codigoCliente) {
        Cliente cliente = new Cliente();
        String sql = "select * from cliente where codigoCliente = ? and estado = 'Activo'";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                createCliente(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    /* Actualizar Cliente */
    public int actualizar(Cliente cliente) {
        String sql = "update cliente set primerNombreCliente=?,segundoNombreCliente = ?,"
                + "primerApellidoCliente = ?,segundoApellidoCliente = ?,telefonoCliente = ?,"
                + "direccionCliente = ?,sexoCliente = ?,nitCliente = ?,estado = ?,codigoUsuario =? where codigoCliente = ?";

        try {
            preparedSQL(cliente, sql);
            ps.setInt(11, cliente.getCodigoCliente());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    /* Eliminar Cliente */
    public void eleminar(int codigoCliente) {
        String sql = "UPDATE cliente SET estado = 'Inactivo' WHERE codigoCliente = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoCliente);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Historial De Usuario
    // HUOS-001 - Registro De Nuevo Cliente
    // HUOS-002 - Consultar Ranking De Cliente
    public List<Cliente> obtenerRankingClientesUltimoMes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT c.codigoCliente, c.primerNombreCliente, c.segundoNombreCliente, "
                + "c.primerApellidoCliente, c.segundoApellidoCliente, COUNT(p.codigoPedido) AS totalPedidos "
                + "FROM cliente c "
                + "JOIN pedido p ON c.codigoCliente = p.codigoCliente "
                + "WHERE p.fechaCreacion >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) "
                + "GROUP BY c.codigoCliente, c.primerNombreCliente, c.segundoNombreCliente, "
                + "c.primerApellidoCliente, c.segundoApellidoCliente "
                + "ORDER BY totalPedidos DESC";
        try (PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cr = new Cliente();
                cr.setCodigoCliente(rs.getInt("codigoCliente"));
                cr.setPrimerNombreCliente(rs.getString("primerNombreCliente"));
                cr.setSegundoNombreCliente(rs.getString("segundoNombreCliente"));
                cr.setPrimerApellidoCliente(rs.getString("primerApellidoCliente"));
                cr.setSegundoApellidoCliente(rs.getString("segundoApellidoCliente"));
                cr.setTotalPedidos(rs.getInt("totalPedidos"));
                lista.add(cr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
