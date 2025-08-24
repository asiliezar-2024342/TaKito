package modelo;

public class Resena {

    public enum Tipo {
        Sucursal, Empleado, Producto
    }

    public enum Estado {
        Activo, Inactivo
    }

    private int codigoResena;
    private Tipo tipo;
    private String tituloResena;
    private String comentarioResena;
    private int calificacionResena;
    private Estado estado;
    private int codigoSucursal;
    private int codigoUsuario;

    public Resena() {
    }

    public Resena(int codigoResena, Tipo tipoResena, String tituloResena, String comentarioResena,
            int calificacionResena, Estado estado, int codigoSucursal, int codigoUsuario) {
        this.codigoResena = codigoResena;
        this.tipo = tipoResena;
        this.tituloResena = tituloResena;
        this.comentarioResena = comentarioResena;
        this.calificacionResena = calificacionResena;
        this.estado = estado;
        this.codigoSucursal = codigoSucursal;
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoResena() {
        return codigoResena;
    }

    public void setCodigoResena(int codigoResena) {
        this.codigoResena = codigoResena;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipoResena) {
        this.tipo = tipoResena;
    }

    public String getTituloResena() {
        return tituloResena;
    }

    public void setTituloResena(String tituloResena) {
        this.tituloResena = tituloResena;
    }

    public String getComentarioResena() {
        return comentarioResena;
    }

    public void setComentarioResena(String comentarioResena) {
        this.comentarioResena = comentarioResena;
    }

    public int getCalificacionResena() {
        return calificacionResena;
    }

    public void setCalificacionResena(int calificacionResena) {
        this.calificacionResena = calificacionResena;
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

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public class PromedioSucursal {

        private Sucursal sucursal;
        private double promedio;

        public PromedioSucursal() {
        }

        public PromedioSucursal(Sucursal sucursal, double promedio) {
            this.sucursal = sucursal;
            this.promedio = promedio;
        }

        public Sucursal getSucursal() {
            return sucursal;
        }

        public void setSucursal(Sucursal sucursal) {
            this.sucursal = sucursal;
        }

        public double getPromedio() {
            return promedio;
        }

        public void setPromedio(double promedio) {
            this.promedio = promedio;
        }

    }
}
