package modelo;

public class Sucursal {

    private int codigoSucursal;
    private String ubicacionSucursal;
    private String telefonoSucursal;
    private String nombreSucursal;
    private Funcionamiento funcionamiento;
    private Estado estado;

    public enum Funcionamiento {
        Abierto,
        Cerrado,
        Suspendido
    }

    public enum Estado {
        Activo,
        Inactivo
    }

    public Sucursal() {
    }

    public Sucursal(int codigoSucursal, String ubicacionSucursal, String telefonoSucursal,
         String nombreSucursal, Funcionamiento funcionamiento, Estado estado) {
        this.codigoSucursal = codigoSucursal;
        this.ubicacionSucursal = ubicacionSucursal;
        this.telefonoSucursal = telefonoSucursal;
        this.nombreSucursal = nombreSucursal;
        this.funcionamiento = funcionamiento;
        this.estado = estado;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getUbicacionSucursal() {
        return ubicacionSucursal;
    }

    public void setUbicacionSucursal(String ubicacionSucursal) {
        this.ubicacionSucursal = ubicacionSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Funcionamiento getFuncionamiento() {
        return funcionamiento;
    }

    public void setFuncionamiento(Funcionamiento funcionamiento) {
        this.funcionamiento = funcionamiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
} 
