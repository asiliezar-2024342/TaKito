package modelo;

import java.io.InputStream;
import java.sql.Date;

public class Usuario {
    public enum Cargo{Consumidor, Administrativo, Empleado}
    
    private int codigoUsuario;
    private String correoUsuario;
    private String contrasenaUsuario;
    private Date fechaRegistro;
    private InputStream foto;
    private Cargo cargo;
    private String estado;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String correoUsuario, String contrasenaUsuario, Date fechaRegistro, InputStream foto, Cargo cargo, String estado) {
        this.codigoUsuario = codigoUsuario;
        this.correoUsuario = correoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.fechaRegistro = fechaRegistro;
        this.foto = foto;
        this.cargo = cargo;
        this.estado = estado;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}