package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BitacoraDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    //Método para listar todas las bitácoras
    public List listar(){
        // Consulta SQL para obtener todos los registros de la tabla Bitacora
        String sql = "select * from Bitacora";
        // Lista para almacenar los objetos Bitacora que se van a retornar
        List<Bitacora> listaBitacora = new ArrayList<>();
        try {
            // Establece la conexión con la base de datos
            con = cn.getConexion();
            // Prepara la sentencia SQL
            ps = con.prepareStatement(sql);
            // Ejecuta la consulta y guarda los resultados en un ResultSet
            rs = ps.executeQuery();
            // Itera sobre el ResultSet para construir objetos Bitacora
            while (rs.next()) {                
                Bitacora bi = new Bitacora();
                // Asigna los valores obtenidos desde la base de datos al objeto Bitacora
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
                // Agrega el objeto Bitacora a la lista
                listaBitacora.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaBitacora;
    }
    
    public Bitacora listarCodigoUsaurio(int codigo){
        Bitacora bi = new Bitacora();
        String sql = "select * from Bitacora where codigoUsuario = ?";
        try {
            con = cn.getConexion();
            // Prepara la sentencia SQL
            ps = con.prepareStatement(sql);
            // Ejecuta la consulta y guarda los resultados en un ResultSet
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            // Itera sobre el ResultSet para construir objetos Bitacora
            while (rs.next()) {                
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }
    
    public Bitacora listarPorFecha(Date fecha) {
        Bitacora bi = new Bitacora();
        String sql = "SELECT * FROM Bitacora WHERE fecha = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1,fecha); 
            rs = ps.executeQuery();

            while (rs.next()) {
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bi;
    }
    public Bitacora listarPorAccion(String accion) {
        Bitacora bi = new Bitacora();
        String sql = "SELECT * FROM Bitacora WHERE accion = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, accion);
            rs = ps.executeQuery();

            while (rs.next()) {
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bi;
    }
    
    public Bitacora listarPorTabla(String tablaModificada) {
        Bitacora bi = new Bitacora();
        String sql = "SELECT * FROM Bitacora WHERE tablaModificada = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, tablaModificada);
            rs = ps.executeQuery();

            while (rs.next()) {
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bi;
    }
    public int agregar(Bitacora bi){
        String sql = "insert into Bitacora (mensaje,tablaModificada ,fecha,hora"
                + ",datoAnterior,datoNuevo,accion,codigoUsuario) values"
                +"(?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1,bi.getMensaje());
            ps.setString(2, bi.getTablaModificada());
            ps.setDate(3, bi.getFecha());
            ps.setTime(4, bi.getHora());
            ps.setString(5, bi.getDatoAnterior());
            ps.setString(6, bi.getDatoNuevo());
            ps.setString(7, bi.getAccion());
            ps.setInt(8,bi.getCodigoUsuario());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    public int actualizar(Bitacora bi){
        String sql = "update Bitacora set mensaje = ?, tablaModificada = ?, "
                + "fecha = ?, hora = ?, datoAnterior = ?, datoNuevo = ?,"
                + " accion = ? where codigoBitacora = ?; ";
                   
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1,bi.getMensaje());
            ps.setString(2, bi.getTablaModificada());
            ps.setDate(3, bi.getFecha());
            ps.setTime(4, bi.getHora());
            ps.setString(5, bi.getDatoAnterior());
            ps.setString(6, bi.getDatoNuevo());
            ps.setString(7, bi.getAccion());
            ps.setInt(8, bi.getCodigoBitacora());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "delete from Bitacora where codigoBitacora = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Bitacora listarCodigoBitacora(int codigo){
        Bitacora bi = new Bitacora();
        String sql = "select * from Bitacora where codigoBitacora = ?";
        try {
            con = cn.getConexion();
            // Prepara la sentencia SQL
            ps = con.prepareStatement(sql);
            // Ejecuta la consulta y guarda los resultados en un ResultSet
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            // Itera sobre el ResultSet para construir objetos Bitacora
            while (rs.next()) {                
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }
    public List filtrarPorAccionYTabla(String accion, String tablaModificada) {
        List<Bitacora> listaBitacora = new ArrayList<>();
        String sql = "SELECT * FROM Bitacora WHERE accion = ? AND tablaModificada = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, accion);
            ps.setString(2, tablaModificada);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bitacora bi = new Bitacora();
                bi.setCodigoBitacora(rs.getInt(1));
                bi.setMensaje(rs.getString(2));
                bi.setTablaModificada(rs.getString(3));
                bi.setFecha(rs.getDate(4));
                bi.setHora(rs.getTime(5));
                bi.setDatoAnterior(rs.getString(6));
                bi.setDatoNuevo(rs.getString(7));
                bi.setAccion(rs.getString(8));
                bi.setCodigoUsuario(rs.getInt(9));
                listaBitacora.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaBitacora;
    }
}
