package modelo;

public class DetalleCombo {
    private int codigoDetalleCombo;
    private int cantidad;
    private int codigoCombo;
    private int codigoProducto;

    public DetalleCombo() {
    }

    public DetalleCombo(int codigoDetalleCombo, int cantidad, int codigoCombo, int codigoProducto) {
        this.codigoDetalleCombo = codigoDetalleCombo;
        this.cantidad = cantidad;
        this.codigoCombo = codigoCombo;
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoDetalleCombo() {
        return codigoDetalleCombo;
    }

    public void setCodigoDetalleCombo(int codigoDetalleCombo) {
        this.codigoDetalleCombo = codigoDetalleCombo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodigoCombo() {
        return codigoCombo;
    }

    public void setCodigoCombo(int codigoCombo) {
        this.codigoCombo = codigoCombo;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    
}
