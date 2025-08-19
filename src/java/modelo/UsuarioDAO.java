package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class UsuarioDAO {
    Conexion cn = Conexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
    public Usuario validar(String correoUsuario, String contrasenaUsuario){
        Usuario usuario = new Usuario();
        String sql = "select * from Usuario where correoUsuario = ? and contrasenaUsuario = ?";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correoUsuario);
            ps.setString(2, contrasenaUsuario);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuario.setCodigoUsuario(rs.getInt("codigoUsuario"));
                usuario.setCorreoUsuario(rs.getString("correoUsuario"));
                usuario.setContrasenaUsuario(rs.getString("contrasenaUsuario"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    // ---------- CRUD ----------
    // ----- Listar -----
    public List listar(){
        String sql = "select * from Usuario";
        List<Usuario> listaUsuario = new ArrayList<>();
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario us = new Usuario();
                
                us.setCodigoUsuario(rs.getInt(1));
                us.setCorreoUsuario(rs.getString(2));
                us.setContrasenaUsuario(rs.getString(3));
                us.setFechaRegistro(rs.getDate(4));
                us.setFoto(rs.getBinaryStream(5));
                us.setCargo(rs.getString(6));
                us.setEstado(rs.getString(7));
                
                listaUsuario.add(us);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return listaUsuario;
    }
    
    // ----- Agregar -----
    public int agregar(Usuario us){
        String sql = "insert into Usuario (correoUsuario, contrasenaUsuario, cargo) values (?,?,?)";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, us.getCorreoUsuario());
            ps.setString(2, us.getContrasenaUsuario());
            ps.setString(3, "Consumidor");
            
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return respuesta;
    }
    
    // Busqueda por Código
    public Usuario listarCodigoUsuario(int id){
        Usuario us = new Usuario();
        String sql = "select * from Usuario where codigoUsuario = "+id;
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                us.setCorreoUsuario(rs.getString(2));
                us.setContrasenaUsuario(rs.getString(3));
                us.setFechaRegistro(rs.getDate(4));
                us.setFoto(rs.getBinaryStream(5));
                us.setCargo(rs.getString(6));
                us.setEstado(rs.getString(7));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return us;
    }
    
    // Editar
    public int actualizar(Usuario us){
        String sql = "update Usuario set correoUsuario = ?, contrasenaUsuario = ?, fechaRegistro = ?, foto = ?, cargo = ?, estado = ? where codigoUsuario = ?";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, us.getCorreoUsuario());
            ps.setString(2, us.getContrasenaUsuario());
            ps.setDate(3, us.getFechaRegistro());
            ps.setBlob(4, us.getFoto());
            ps.setString(5, us.getCargo());
            ps.setString(6, us.getEstado());
            
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return respuesta;
    }
    
    // ----- Eliminar -----
    public void eliminar(int id){
        String sql = "delete from Usuario where codigoUsuario = "+id;
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // ----- Opcion para ver Imagen -----
    public void listarUsImg(int id, HttpServletResponse response){
        String sql = "select * from Usuario where codigoUsuario = ?";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                byte[] fotoByte = rs.getBytes("foto");
                response.setContentType("image/png");
                response.getOutputStream().write(fotoByte);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}