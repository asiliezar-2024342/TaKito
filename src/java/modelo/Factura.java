
package modelo;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 */
public class Factura {
    private int codigoFactura;
    private int codigoPedido;
    private int codigoEmpleado;
    private Float totalFactura;
    private Float donacion;
    private Date fechaFactura;
    private String horaFactura;
    private String metodo;
    private String estado;

    public Factura() {
    }

    public Factura(int codigoFactura, int codigoPedido, int codigoEmpleado, Float totalFactura, Float donacion, Date fechaFactura, String horaFactura, String metodo, String estado) {
        this.codigoFactura = codigoFactura;
        this.codigoPedido = codigoPedido;
        this.codigoEmpleado = codigoEmpleado;
        this.totalFactura = totalFactura;
        this.donacion = donacion;
        this.fechaFactura = fechaFactura;
        this.horaFactura = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.metodo = metodo;
        this.estado = estado;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Float getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Float totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Float getDonacion() {
        return donacion;
    }

    public void setDonacion(Float donacion) {
        this.donacion = donacion;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getHoraFactura() {
        return horaFactura;
    }

    public void setHoraFactura(String horaFactura) {
        this.horaFactura = horaFactura;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
