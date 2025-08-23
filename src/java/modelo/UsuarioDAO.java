package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
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
                usuario.setCargo(Usuario.Cargo.valueOf(rs.getString("cargo")));
                usuario.setFechaRegistro(rs.getDate("fechaRegistro"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    // ---------- CRUD ----------
    // ----- Listar -----
    public List listar(){
        String sql = "select * from Usuario where estado = 'Activo'";
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
                us.setCargo(Usuario.Cargo.valueOf(rs.getString(6)));
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
        String sql = "insert into Usuario (correoUsuario, contrasenaUsuario, foto, cargo) values (?,?,?,?)";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, us.getCorreoUsuario());
            ps.setString(2, us.getContrasenaUsuario());
            
            if(us.getFoto() != null){
                ps.setBlob(3, us.getFoto());
            } else {
                File archivo = new File("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/icon_default.png");
                FileInputStream fis = new FileInputStream(archivo);
                
                byte[] bytes = new byte[(int) archivo.length()];
                fis.read(bytes);
                fis.close();
                
                ps.setBytes(3, bytes);
            }
            
            ps.setString(4, "Consumidor");
            
            ps.executeUpdate();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return respuesta;
    }
    
    // Busqueda por Código
    public Usuario listarCodigoUsuario(int id){
        Usuario us = new Usuario();
        String sql = "select * from Usuario where codigoUsuario = "+id+" and estado = 'Activo'";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                us.setCorreoUsuario(rs.getString(2));
                us.setContrasenaUsuario(rs.getString(3));
                us.setFechaRegistro(rs.getDate(4));
                us.setFoto(rs.getBinaryStream(5));
                us.setCargo(Usuario.Cargo.valueOf(rs.getString("cargo")));
                us.setEstado(rs.getString(7));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return us;
    }
    
    // Editar
    public int actualizar(Usuario us){
        String sql = "update Usuario set correoUsuario = ?, contrasenaUsuario = ?, fechaRegistro = ?, foto = ?, cargo = ? where codigoUsuario = ?";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, us.getCorreoUsuario());
            ps.setString(2, us.getContrasenaUsuario());
            ps.setDate(3, us.getFechaRegistro());
            ps.setBlob(4, us.getFoto());
            ps.setString(5, us.getCargo().name());
            ps.setInt(6, us.getCodigoUsuario());
            
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return respuesta;
    }
    
    // ----- Eliminar -----
    public void eliminar(int id){
        String sql = "update Usuario set estado = 'Inactivo' where codigoUsuario = "+id;
        
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
        String sql = "select * from Usuario where codigoUsuario = ? and estado = 'Activo' order by codigoUsuario";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                byte[] fotoByte = rs.getBytes("foto");
                
                if(fotoByte != null && fotoByte.length > 0){
                    String tipo = "image/png";
                    response.setContentType(tipo);
                    
                    response.setContentLength(fotoByte.length);
                    
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    
                    try(OutputStream os = response.getOutputStream()){
                        os.write(fotoByte);
                        os.flush();
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Usuario obtenerCodigo(String correoUsuario){
        Usuario us = new Usuario();
        String sql = "select * from Usuario where correoUsuario = ? and estado = 'Activo'";
        
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ps.setString(1, correoUsuario);
            
            while(rs.next()){
                us.setCorreoUsuario(rs.getString(2));
                us.setContrasenaUsuario(rs.getString(3));
                us.setFechaRegistro(rs.getDate(4));
                us.setFoto(rs.getBinaryStream(5));
                us.setCargo(Usuario.Cargo.valueOf(rs.getString("cargo")));
                us.setEstado(rs.getString(7));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return us;
    }
}