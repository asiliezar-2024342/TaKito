package modelo;

public class DetallePedido {
    private int codigoDetallePedido;
    private String instrucciones;
    private int cantidad;
    private Double subTotal;
    private int codigoPedido;
    private int codigoCombo;
    private int codigoPromocion;

    public DetallePedido() {
    }

    public DetallePedido(int codigoDetallePedido, String instrucciones, int cantidad, Double subTotal, int codigoPedido, int codigoCombo, int codigoPromocion) {
        this.codigoDetallePedido = codigoDetallePedido;
        this.instrucciones = instrucciones;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.codigoPedido = codigoPedido;
        this.codigoCombo = codigoCombo;
        this.codigoPromocion = codigoPromocion;
    }

    public int getCodigoDetallePedido() {
        return codigoDetallePedido;
    }

    public void setCodigoDetallePedido(int codigoDetallePedido) {
        this.codigoDetallePedido = codigoDetallePedido;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
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
    
    
}
