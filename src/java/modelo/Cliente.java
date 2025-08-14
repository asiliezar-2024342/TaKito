package modelo;

/**
 *
 * @author Oscar Sicajau
 */
public class Cliente {

    public enum SexoCliente {
        Hombre,Mujer
}

    public enum EstadoCliente {
        Activo,Inactivo
}

    
    private int codigoCliente;
    private String primerNombreCliente;
    private String segundoNombreCliente;
    private String primerApellidoCliente;
    private String segundoApellidoCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private SexoCliente sexoCliente;
    private String nitCliente;
    private EstadoCliente estado;
    private int codigoUsuario;

    /* Contrusctor vacio */

    public Cliente() {
    }
    
    
    /* Contrustor lleno */

    public Cliente(int codigoCliente, String primerNombreCliente, String segundoNombreCliente, String primerApellidoCliente, String segundoApellidoCliente, String telefonoCliente, String direccionCliente, SexoCliente sexoCliente, String nitCliente, EstadoCliente estado, int codigoUsuario) {
        this.codigoCliente = codigoCliente;
        this.primerNombreCliente = primerNombreCliente;
        this.segundoNombreCliente = segundoNombreCliente;
        this.primerApellidoCliente = primerApellidoCliente;
        this.segundoApellidoCliente = segundoApellidoCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.sexoCliente = sexoCliente;
        this.nitCliente = nitCliente;
        this.estado = estado;
        this.codigoUsuario = codigoUsuario;
    }
    
    /* Getter y Setter*/

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getPrimerNombreCliente() {
        return primerNombreCliente;
    }

    public void setPrimerNombreCliente(String primerNombreCliente) {
        this.primerNombreCliente = primerNombreCliente;
    }

    public String getSegundoNombreCliente() {
        return segundoNombreCliente;
    }

    public void setSegundoNombreCliente(String segundoNombreCliente) {
        this.segundoNombreCliente = segundoNombreCliente;
    }

    public String getPrimerApellidoCliente() {
        return primerApellidoCliente;
    }

    public void setPrimerApellidoCliente(String primerApellidoCliente) {
        this.primerApellidoCliente = primerApellidoCliente;
    }

    public String getSegundoApellidoCliente() {
        return segundoApellidoCliente;
    }

    public void setSegundoApellidoCliente(String segundoApellidoCliente) {
        this.segundoApellidoCliente = segundoApellidoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public SexoCliente getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(SexoCliente sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    
    
}
