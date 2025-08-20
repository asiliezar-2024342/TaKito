/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author informatica
 */
public class Empleado {
    
    public enum Estado {Activo, Inactivo}
    public enum SexoEmpleado {Hombre, Mujer};
    private int codigoEmpleado;
    private String primerNombreEmpleado;
    private String segundoNombreEmpleado;
    private String primerApellidoEmpleado;
    private String segundoApellidoEmpleado;
    private String telefonoEmpleado;
    private String correoEmpleado;
    private String direccionEmpleado;
    private Estado estado;
    private SexoEmpleado sexoEmpleado;
    private int codigoSucursal;
    private int codigoUsuario;
 
    public Empleado() {
    }
 
    public Empleado(int codigoEmpleado, String primerNombreEmpleado, String segundoNombreEmpleado, String primerApellidoEmpleado, String segundoApellidoEmpleado, String telefonoEmpleado, String correoEmpleado, String direccionEmpleado, Estado estado, SexoEmpleado sexoEmpleado, int codigoSucursal, int codigoUsuario) {
        this.codigoEmpleado = codigoEmpleado;
        this.primerNombreEmpleado = primerNombreEmpleado;
        this.segundoNombreEmpleado = segundoNombreEmpleado;
        this.primerApellidoEmpleado = primerApellidoEmpleado;
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.estado = estado;
        this.sexoEmpleado = sexoEmpleado;
        this.codigoSucursal = codigoSucursal;
        this.codigoUsuario = codigoUsuario;
    }
 
    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }
 
    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }
 
    public String getPrimerNombreEmpleado() {
        return primerNombreEmpleado;
    }
 
    public void setPrimerNombreEmpleado(String primerNombreEmpleado) {
        this.primerNombreEmpleado = primerNombreEmpleado;
    }
 
    public String getSegundoNombreEmpleado() {
        return segundoNombreEmpleado;
    }
 
    public void setSegundoNombreEmpleado(String segundoNombreEmpleado) {
        this.segundoNombreEmpleado = segundoNombreEmpleado;
    }
 
    public String getPrimerApellidoEmpleado() {
        return primerApellidoEmpleado;
    }
 
    public void setPrimerApellidoEmpleado(String primerApellidoEmpleado) {
        this.primerApellidoEmpleado = primerApellidoEmpleado;
    }
 
    public String getSegundoApellidoEmpleado() {
        return segundoApellidoEmpleado;
    }
 
    public void setSegundoApellidoEmpleado(String segundoApellidoEmpleado) {
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
    }
 
    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }
 
    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }
 
    public String getCorreoEmpleado() {
        return correoEmpleado;
    }
 
    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }
 
    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }
 
    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }
 
    public Estado getEstado() {
        return estado;
    }
 
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
 
    public SexoEmpleado getSexoEmpleado() {
        return sexoEmpleado;
    }
 
    public void setSexoEmpleado(SexoEmpleado sexoEmpleado) {
        this.sexoEmpleado = sexoEmpleado;
    }
 
    public int getCodigoSucursal() {
        return codigoSucursal;
    }
 
    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }
 
    public int getCodigoUsuario() {
        return codigoUsuario;
    }
 
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    
}
