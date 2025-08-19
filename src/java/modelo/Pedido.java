package modelo;

import java.sql.Date;
import java.sql.Time;

public class Pedido {
    public enum TipoPedido {Recoger, Domicilio}
    public enum Estado {Activo, Inactivo}
    
    private int codigoPedido;
    private Date fechaCreacion;
    private Time horaCreacion;
    private Date fechaProgramado;
    private Time horaProgramado;
    private String ubicacionPedido;
    private TipoPedido tipoPedido;
    private Estado estado;
    private int codigoSucursal;
    private int codigoCliente;

    public Pedido() {
    }

    public Pedido(int codigoPedido, Date fechaCreacion, Time horaCreacion, Date fechaProgramado, Time horaProgramado, String ubicacionPedido, TipoPedido tipoPedido, Estado estado, int codigoSucursal, int codigoCliente) {
        this.codigoPedido = codigoPedido;
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
        this.fechaProgramado = fechaProgramado;
        this.horaProgramado = horaProgramado;
        this.ubicacionPedido = ubicacionPedido;
        this.tipoPedido = tipoPedido;
        this.estado = estado;
        this.codigoSucursal = codigoSucursal;
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Time getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Time horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public Date getFechaProgramado() {
        return fechaProgramado;
    }

    public void setFechaProgramado(Date fechaProgramado) {
        this.fechaProgramado = fechaProgramado;
    }

    public Time getHoraProgramado() {
        return horaProgramado;
    }

    public void setHoraProgramado(Time horaProgramado) {
        this.horaProgramado = horaProgramado;
    }

    public String getUbicacionPedido() {
        return ubicacionPedido;
    }

    public void setUbicacionPedido(String ubicacionPedido) {
        this.ubicacionPedido = ubicacionPedido;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
}