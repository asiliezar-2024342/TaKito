package modelo;

public class DetallePromocion {
    
    private int codigoDetallePromocion;
    private String observaciones;
    private int codigoPromocion;
    private int codigoCombo;

    public DetallePromocion() {
    }

    public DetallePromocion(int codigoDetallePromocion, String observaciones, int codigoPromocion, int codigoCombo) {
        this.codigoDetallePromocion = codigoDetallePromocion;
        this.observaciones = observaciones;
        this.codigoPromocion = codigoPromocion;
        this.codigoCombo = codigoCombo;
    }

    public int getCodigoDetallePromocion() {
        return codigoDetallePromocion;
    }

    public void setCodigoDetallePromocion(int codigoDetallePromocion) {
        this.codigoDetallePromocion = codigoDetallePromocion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(int codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public int getCodigoCombo() {
        return codigoCombo;
    }

    public void setCodigoCombo(int codigoCombo) {
        this.codigoCombo = codigoCombo;
    }
    
}
