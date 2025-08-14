package modelo;

import config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResenaDAO {

    Connection cn = Conexion.getInstance().getConexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    // Método para crear una reseña a partir de datos
    private void createResena(Resena resena) throws Exception {
        resena.setCodigoResena(rs.getInt("codigoResena"));
        resena.setTipoResena(Resena.TipoResena.valueOf(
                rs.getString("tipoResena")));
        resena.setTituloResena(rs.getString("tituloResena"));
        resena.setComentarioResena(rs.getString(
                "comentarioResena"));
        resena.setCalificacionResena(rs.getInt(
                "calificacionResena"));
        resena.setEstado(Resena.Estado.valueOf(rs.getString(
                "estado")));
        resena.setCodigoSucursal(rs.getInt(
                "codigoSucursal"));
        resena.setCodigoUsuario(rs.getInt("codigoUsuario"));
    }

    // Método para preparar la consulta SQL
    private void preparedSQL(Resena resena, String sql) throws Exception {
        ps = cn.prepareStatement(sql);
        ps.setString(1, resena.getTipoResena().name());
        ps.setString(2, resena.getTituloResena());
        ps.setString(3, resena.getComentarioResena());
        ps.setInt(4, resena.getCalificacionResena());
        ps.setString(5, resena.getEstado().name());
        ps.setInt(6, resena.getCodigoSucursal());
        ps.setInt(7, resena.getCodigoUsuario());
    }


    // Método para agregar una reseña
    public int agregar(Resena resena) {
        String sql = "Insert into Resena (tipoResena, tituloResena, "
                + "comentarioResena, calificacionResena, estado,"
                + " codigoSucursal, codigoUsuario) values (?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedSQL(resena, sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    // Método para listar todas las reseñas
    public List listar() {
        String sql = "Select * from Resena and estado = 'Activo'";
        List<Resena> listaResena = new ArrayList<>();
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Resena resena = new Resena();
                createResena(resena);

                listaResena.add(resena);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaResena;
    }

    // Método para buscar una reseña por su código
    public Resena buscar(int codigoResena) {
        Resena resena = new Resena();
        String sql = "Select * from Resena where codigoResena = ? "
                + "and estado = 'Activo'";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoResena);
            rs = ps.executeQuery();

            if (rs.next()) {
                createResena(resena);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resena;
    }

    // Método para actulizar una reseña
    public int actualizar(Resena resena) {
        String sql = "Update Resena set tipoResena = ?, tituloResena = ?, "
                + "comentarioResena = ?," +
                " calificacionResena = ?, estado = ?," + " codigoSucursal = ?," +
                " codigoUsuario = ? where codigoResena = ?";

        try {
            preparedSQL(resena, sql);
            ps.setInt(8, resena.getCodigoResena());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método para eliminar una reseña
    public void eliminar(int codigoResena) {
        String sql = "Update Resena set estado = 'Inactivo' where "
                + "codigoResena = ?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoResena);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
