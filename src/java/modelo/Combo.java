package modelo;

import java.io.InputStream;

public class Combo {
    
    public enum Categoria {Familiar, Duo, Individual, Unitario}

    public enum Estado {Activo, Inactivo}
    
    private int codigoCombo;
    private String nombreCombo;
    private String descripcionCombo;
    private Float precioCombo;
    private Categoria categoria;
    private Estado estado;
    private InputStream foto;

    public Combo() {
    }

    public Combo(int codigoCombo, String nombreCombo, String descripcionCombo, Float precioCombo, Categoria categoria, Estado estado, InputStream foto) {
        this.codigoCombo = codigoCombo;
        this.nombreCombo = nombreCombo;
        this.descripcionCombo = descripcionCombo;
        this.precioCombo = precioCombo;
        this.categoria = categoria;
        this.estado = estado;
        this.foto = foto;
    }

    public int getCodigoCombo() {
        return codigoCombo;
    }

    public void setCodigoCombo(int codigoCombo) {
        this.codigoCombo = codigoCombo;
    }

    public String getNombreCombo() {
        return nombreCombo;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

    public String getDescripcionCombo() {
        return descripcionCombo;
    }

    public void setDescripcionCombo(String descripcionCombo) {
        this.descripcionCombo = descripcionCombo;
    }

    public Float getPrecioCombo() {
        return precioCombo;
    }

    public void setPrecioCombo(Float precioCombo) {
        this.precioCombo = precioCombo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
    
    
}
