package modelo;

import java.sql.Date;
import java.sql.Time;


public class Bitacora {
    private int codigoBitacora;
    private String mensaje;
    private String tablaModificada;
    private Date fecha;
    private Time hora;
    private String datoAnterior;
    private String datoNuevo;
    private String accion;
    private int codigoUsuario;
    
    //constructor
    public Bitacora() {
    }

    public Bitacora(int codigoBitacora, String mensaje, String tablaModificada, Date fecha, Time hora, String datoAnterior, String datoNuevo, String accion, int codigoUsuario) {
        this.codigoBitacora = codigoBitacora;
        this.mensaje = mensaje;
        this.tablaModificada = tablaModificada;
        this.fecha = fecha;
        this.hora = hora;
        this.datoAnterior = datoAnterior;
        this.datoNuevo = datoNuevo;
        this.accion = accion;
        this.codigoUsuario = codigoUsuario;
    }
    // Getters y Setters
    public int getCodigoBitacora() {
        return codigoBitacora;
    }

    public void setCodigoBitacora(int codigoBitacora) {
        this.codigoBitacora = codigoBitacora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTablaModificada() {
        return tablaModificada;
    }

    public void setTablaModificada(String tablaModificada) {
        this.tablaModificada = tablaModificada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDatoAnterior() {
        return datoAnterior;
    }

    public void setDatoAnterior(String datoAnterior) {
        this.datoAnterior = datoAnterior;
    }

    public String getDatoNuevo() {
        return datoNuevo;
    }

    public void setDatoNuevo(String datoNuevo) {
        this.datoNuevo = datoNuevo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    
}
