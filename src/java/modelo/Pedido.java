package modelo;

import java.sql.Date;
import java.sql.Time;

public class Pedido {
    private int codigoPedido;
    private int codigoSucursal;
    private int codigoCliente;
    private Date fechaCreacion;
    private Time horaCreacion;
    private Date fechaProgramado;
    private Time horaProgramado;
    private String ubicacionPedido;
    private String tipoPedido;
    private String estado; 
    
    private int codigoCombo;
    private int codigoPromocion;
    private String text;
    private int cantidad;
    private double subTotal;
    
    private double monto;
    private int item;
    private String descripcionProd;

    public Pedido() {
    }

    public Pedido(int codigoPedido, int codigoSucursal, int codigoCliente, Date fechaCreacion, Time horaCreacion, Date fechaProgramado, Time horaProgramado, String ubicacionPedido, String tipoPedido, String estado, int codigoCombo, int codigoPromocion, String text, int cantidad, double subTotal, double monto, int item, String descripcionProd) {
        this.codigoPedido = codigoPedido;
        this.codigoSucursal = codigoSucursal;
        this.codigoCliente = codigoCliente;
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
        this.fechaProgramado = fechaProgramado;
        this.horaProgramado = horaProgramado;
        this.ubicacionPedido = ubicacionPedido;
        this.tipoPedido = tipoPedido;
        this.estado = estado;
        this.codigoCombo = codigoCombo;
        this.codigoPromocion = codigoPromocion;
        this.text = text;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.monto = monto;
        this.item = item;
        this.descripcionProd = descripcionProd;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
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

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoCombo() {
        return codigoCombo;
    }

    public void setCodigoCombo(int codigoCombo) {
        this.codigoCombo = codigoCombo;
    }

    public int getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(int codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }
    
    
}
