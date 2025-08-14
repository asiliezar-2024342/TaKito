package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
                usuario.setFechaRegistro(rs.getDate("fechaRegistro"));
                usuario.setFoto(rs.getBytes("foto"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setEstado(rs.getString("estado"));
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
                us.setFoto(rs.getBytes(5));
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
    public int agregar(Usuario usu){
        String sql = "insert into Usuario (correoUsuario, contrasenaUsuario, fechaRegistro, foto, cargo) values (?,?,?,?,?)";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usu.getCorreoUsuario());
            ps.setString(2, usu.getContrasenaUsuario());
            ps.setDate(3, usu.getFechaRegistro());
            ps.setBytes(4, usu.getFoto());
            ps.setString(5, usu.getCargo());
            
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return respuesta;
    }
    
    // ----- Busca por Código -----
    public Usuario listarCodigoUsuario(int id){
        Usuario usu = new Usuario();
        String sql = "select * from Usuario where codigoUsuario = "+id;
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return usu;
    }
    
    // ----- Editar -----
    public int actualizar(Usuario usu){
        String sql = "update Usuario set correoUsuario = ?, contrasenaUsuario = ?, fechaRegistro = ?, foto = ?, cargo = ?, estado = ?";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, usu.getCorreoUsuario());
            ps.setString(2, usu.getContrasenaUsuario());
            ps.setDate(3, usu.getFechaRegistro());
            ps.setBytes(4, usu.getFoto());
            ps.setString(5, usu.getCargo());
            ps.setString(6, usu.getEstado());
            
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
}