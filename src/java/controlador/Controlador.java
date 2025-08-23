package controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DetallePromocion;
import modelo.DetallePromocionDAO;
import modelo.Promocion;
import modelo.PromocionDAO;
import modelo.Resena;
import modelo.ResenaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Resena resena = new Resena();
    ResenaDAO resenaDao = new ResenaDAO();
    Promocion promocion = new Promocion();
    PromocionDAO promocionDao = new PromocionDAO();
    DetallePromocion detallePromocion = new DetallePromocion();
    DetallePromocionDAO detallePromocionDao = new DetallePromocionDAO();
    int codResena;
    int codPromocion;
    int codDetallePromocion;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {

            // ANIMACIÓN DE TRANSICIÓN NO TOCAR
            request.setAttribute("jspFinal", "Principal.jsp");
            request.getRequestDispatcher("Inicial.jsp").forward(request, response);

        } else if (menu.equals("PrincipalContenido")) {
            // ANIMACIÓN DE TRANSICIÓN NO TOCAR
            request.setAttribute("jspFinal", "PrincipalContenido.jsp");
            request.getRequestDispatcher("Transicion.jsp").forward(request, response);

        } else if (menu.equals("Usuario")) {

            switch (accion) {
                case "Listar":
                    List listaUsuarios = usuarioDao.listar();

                    request.setAttribute("usuarios", listaUsuarios);
                    break;

                case "Agregar":
                    String correo = request.getParameter("txtCorreoUsuario");
                    String contrasena = request.getParameter("txtContrasenaUsuario");

                    if (correo != null && contrasena != null) {
                        usuario.setCorreoUsuario(correo);
                        usuario.setContrasenaUsuario(contrasena);

                        usuarioDao.agregar(usuario);

                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
            }

        } else if (menu.equals("Resena")) {

            switch (accion) {
                case "Listar":
                    List listaResenas = resenaDao.listar();
                    request.setAttribute("resenas", listaResenas);
                    break;
                case "Agregar":
                    Resena.Tipo tipo = Resena.Tipo.valueOf(request.getParameter("txtTipo"));
                    String titulo = request.getParameter("txtTitulo");
                    String comentario = request.getParameter("txtComentario");
                    int calificacion = Integer.parseInt(request.getParameter("txtCalificacion"));
                    Resena.Estado estado = Resena.Estado.valueOf(request.getParameter("txtEstado"));
                    int sucursal = Integer.parseInt(request.getParameter("txtSucursal"));
                    int usuario = Integer.parseInt(request.getParameter("txtUsuario"));

                    resena.setTipo(tipo);
                    resena.setTituloResena(titulo);
                    resena.setComentarioResena(comentario);
                    resena.setCalificacionResena(calificacion);
                    resena.setEstado(estado);
                    resena.setCodigoSucursal(sucursal);
                    resena.setCodigoUsuario(usuario);
                    resenaDao.agregar(resena);
                    request.getRequestDispatcher("Controlador?menu=Resena&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codResena = Integer.parseInt(request.getParameter("codigoResena"));
                    Resena r = resenaDao.buscar(codResena);
                    request.setAttribute("resena", r);
                    request.getRequestDispatcher("Controlador?menu=Resena&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    Resena.Tipo tipoA = Resena.Tipo.valueOf(request.getParameter("txtTipo"));
                    String tituloA = request.getParameter("txtTitulo");
                    String comentarioA = request.getParameter("txtComentario");

                    int calificacionA = Integer.parseInt(request.getParameter("txtCalificacion"));
                    Resena.Estado estadoA = Resena.Estado.valueOf(request.getParameter("txtEstado"));
                    int sucursalA = Integer.parseInt(request.getParameter("txtSucursal"));
                    int usuarioA = Integer.parseInt(request.getParameter("txtUsuario"));

                    resena.setTipo(tipoA);
                    resena.setTituloResena(tituloA);
                    resena.setComentarioResena(comentarioA);
                    resena.setCalificacionResena(calificacionA);
                    resena.setEstado(estadoA);
                    resena.setCodigoSucursal(sucursalA);
                    resena.setCodigoUsuario(usuarioA);
                    resena.setCodigoResena(codResena);
                    resenaDao.actualizar(resena);
                    request.getRequestDispatcher("Controlador?menu=Resena&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codResena = Integer.parseInt(request.getParameter("codigoResena"));
                    resenaDao.eliminar(codResena);
                    request.getRequestDispatcher("Controlador?menu=Resena&accion=Listar").forward(request, response);
                    break;
            }
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Resena&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Resena.jsp").forward(request, response);
            }

        } else if (menu.equals("Producto")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Producto&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }

        } else if (menu.equals("Bitacora")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Bitacora&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Bitacora.jsp").forward(request, response);
            }

        } else if (menu.equals("Cliente")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Cliente&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Cliente.jsp").forward(request, response);
            }

        } else if (menu.equals("Pedido")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Pedido&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Pedido.jsp").forward(request, response);
            }

        } else if (menu.equals("Empleado")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Empleado&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }

        } else if (menu.equals("Sucursal")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Sucursal&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Sucursal.jsp").forward(request, response);
            }

        } else if (menu.equals("Factura")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Factura&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Factura.jsp").forward(request, response);
            }

        } else if (menu.equals("Combo")) {

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Combo&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Combo.jsp").forward(request, response);
            }

        } else if (menu.equals("Promocion")) {
            
            switch (accion) {
                case "Listar":
                    request.setAttribute("Promociones", promocionDao.listar());
                    request.setAttribute("DetallesPromociones", detallePromocionDao.listar());
                    request.getRequestDispatcher("Promocion.jsp").forward(request, response);
                    break;
                //Solo tienen que añadir estas tres acciones al controlador con los datos que les corresponde a cada uno: Solamente cambian "Promocion" por su entidad.            
                case "ConfirmarAgregar":
                    request.setAttribute("accionReal", "Agregar");
                    request.setAttribute("menu", "Promocion");
                    request.getRequestDispatcher("ConfirmarAccion.jsp").forward(request, response);
                    break;
                case "ConfirmarActualizar":
                    request.setAttribute("accionReal", "Actualizar");
                    request.setAttribute("menu", "Promocion");
                    request.getRequestDispatcher("ConfirmarAccion.jsp").forward(request, response);
                    break;
                case "ConfirmarEliminar":
                    codPromocion = Integer.parseInt(request.getParameter("codigoPromocion"));
                    request.setAttribute("accionReal", "Eliminar");
                    request.setAttribute("codigoPromocion", codPromocion);
                    request.setAttribute("menu", "Promocion");
                    request.getRequestDispatcher("ConfirmarAccion.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombrePromocion");
                    String descripcion = request.getParameter("txtDescripcionPromocion");
                    double descuento = Double.parseDouble(request.getParameter("txtDescuentoPromocion"));
                    Date inicio = Date.valueOf(request.getParameter("txtFechaInicio"));
                    Date fin = Date.valueOf(request.getParameter("txtFechaFin"));
                    String estado = request.getParameter("txtEstado");
                    promocion.setNombrePromocion(nombre);
                    promocion.setDescripcionPromocion(descripcion);
                    promocion.setDescuentoPromocion(descuento);
                    promocion.setFechaInicio(inicio);
                    promocion.setFechaFin(fin);
                    promocion.setEstado(Promocion.Estado.valueOf(estado));
                    promocionDao.agregar(promocion);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codPromocion = Integer.parseInt(request.getParameter("codigoPromocion"));
                    Promocion p = promocionDao.buscar(codPromocion);
                    request.setAttribute("Promocion", p);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombre2 = request.getParameter("txtNombrePromocion");
                    String descripcion2 = request.getParameter("txtDescripcionPromocion");
                    double descuento2 = Double.parseDouble(request.getParameter("txtDescuentoPromocion"));
                    Date inicio2 = Date.valueOf(request.getParameter("txtFechaInicio"));
                    Date fin2 = Date.valueOf(request.getParameter("txtFechaFin"));
                    String estado2 = request.getParameter("txtEstado");
                    promocion.setNombrePromocion(nombre2);
                    promocion.setDescripcionPromocion(descripcion2);
                    promocion.setDescuentoPromocion(descuento2);
                    promocion.setFechaInicio(inicio2);
                    promocion.setFechaFin(fin2);
                    promocion.setEstado(Promocion.Estado.valueOf(estado2));
                    promocion.setCodigoPromocion(codPromocion);
                    promocionDao.actualizar(promocion);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codPromocion = Integer.parseInt(request.getParameter("codigoPromocion"));
                    promocionDao.eliminar(codPromocion);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Agregar2":
                    String observaciones = request.getParameter("txtObservaciones");
                    int codigoPromocion = Integer.parseInt(request.getParameter("txtCodigoPromocion"));
                    int codigoCombo = Integer.parseInt(request.getParameter("txtCodigoCombo"));
                    detallePromocion.setObservaciones(observaciones);
                    detallePromocion.setCodigoPromocion(codigoPromocion);
                    detallePromocion.setCodigoCombo(codigoCombo);
                    detallePromocionDao.agregar(detallePromocion);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Editar2":
                    codDetallePromocion = Integer.parseInt(request.getParameter("codigoDetallePromocion"));
                    DetallePromocion dp = detallePromocionDao.buscar(codDetallePromocion);
                    request.setAttribute("DetallePromocion", dp);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Actualizar2":
                    String observaciones2 = request.getParameter("txtObservaciones");
                    int codigoPromocion2 = Integer.parseInt(request.getParameter("txtCodigoPromocion"));
                    int codigoCombo2 = Integer.parseInt(request.getParameter("txtCodigoCombo"));
                    detallePromocion.setObservaciones(observaciones2);
                    detallePromocion.setCodigoPromocion(codigoPromocion2);
                    detallePromocion.setCodigoCombo(codigoCombo2);
                    detallePromocion.setCodigoDetallePromocion(codDetallePromocion);
                    detallePromocionDao.actualizar(detallePromocion);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
                case "Eliminar2":
                    codDetallePromocion = Integer.parseInt(request.getParameter("codigoDetallePromocion"));
                    detallePromocionDao.eliminar(codDetallePromocion);
                    request.getRequestDispatcher("Controlador?menu=Promocion&accion=Listar").forward(request, response);
                    break;
            } 
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Promocion&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Promocion.jsp").forward(request, response);
            }

        } else if (menu.equals("Promociones")) {
            
            switch (accion) {
                case "Listar":
                    List<Promocion> promocionesMasUsadas = promocionDao.listarPromocionesMasUsadas();
                    request.setAttribute("PromocionesMasUsadas", promocionesMasUsadas);
                    List<Promocion> promocionesActivas = promocionDao.listarPromocionesActivas();
                    request.setAttribute("PromocionesActivas", promocionesActivas);
                    List<Promocion> promocionesFuturas = promocionDao.listarPromocionesFuturas();
                    request.setAttribute("PromocionesFuturas", promocionesFuturas);
                    request.getRequestDispatcher("Promociones.jsp").forward(request, response);
                    break;
            }
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Promociones&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Promociones.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
