package modelo;

import java.sql.Date;

public class Promocion {
    
    public enum Estado {Activo, Inactivo}
    
    private int codigoPromocion;
    private String nombrePromocion;
    private String descripcionPromocion;
    private double descuentoPromocion;
    private Date fechaInicio;
    private Date fechaFin;
    private Estado estado;

    public Promocion() {
    }

    public Promocion(int codigoPromocion, String nombrePromocion, String descripcionPromocion, double descuentoPromocion, Date fechaInicio, Date fechaFin, Estado estado) {
        this.codigoPromocion = codigoPromocion;
        this.nombrePromocion = nombrePromocion;
        this.descripcionPromocion = descripcionPromocion;
        this.descuentoPromocion = descuentoPromocion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(int codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getDescripcionPromocion() {
        return descripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.descripcionPromocion = descripcionPromocion;
    }

    public double getDescuentoPromocion() {
        return descuentoPromocion;
    }

    public void setDescuentoPromocion(double descuentoPromocion) {
        this.descuentoPromocion = descuentoPromocion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
}


