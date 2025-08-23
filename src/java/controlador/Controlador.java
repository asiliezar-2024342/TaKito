package controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Combo;
import modelo.ComboDAO;
import modelo.DetallePedido;
import modelo.DetallePedidoDAO;
import modelo.Pedido;
import modelo.PedidoDAO;
import modelo.Resena;
import modelo.ResenaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Resena resena = new Resena();
    ResenaDAO resenaDao = new ResenaDAO();
    int codResena;

    Pedido pedido = new Pedido();
    PedidoDAO pedidoDao = new PedidoDAO();
    int codPedido;
    DetallePedido detallePedido = new DetallePedido();
    DetallePedidoDAO detallePedidoDao = new DetallePedidoDAO();
    int codDetallePedido;

    Combo combo = new Combo();
    ComboDAO comboDao = new ComboDAO();
    int codCombo;

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
        HttpSession session = request.getSession();

        HashMap<Integer, DetallePedido> carrito = (HashMap<Integer, DetallePedido>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
            session.setAttribute("carrito", carrito);
        }

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
            
            switch (accion) {
                case "Listar":
                    request.setAttribute("pedidos", pedidoDao.listar());
                    request.setAttribute("DetallesPedidos", detallePedidoDao.listar());
                    break;
                case "Agregar":
                    Date fechaCreacion = Date.valueOf(request.getParameter("txtFechaCreacion"));
                    Time horaCreacion = Time.valueOf(request.getParameter("txtHoraCreacion"));
                    Date fechaProgramado = Date.valueOf(request.getParameter("txtFechaProgramado"));
                    Time horaProgramado = Time.valueOf(request.getParameter("txtHoraProgramado"));
                    String ubicacion = request.getParameter("txtUbicacionPedido");
                    Pedido.TipoPedido tipoPedido = Pedido.TipoPedido.valueOf(request.getParameter("txtTipoPedido"));
                    Pedido.Estado estado = Pedido.Estado.valueOf(request.getParameter("txtEstado"));
                    int sucursal = Integer.parseInt(request.getParameter("txtCodigoSucursal"));
                    int cliente = Integer.parseInt(request.getParameter("txtCodigoCliente"));
                    
                    pedido.setFechaCreacion(fechaCreacion); 
                    pedido.setHoraCreacion(horaCreacion);
                    pedido.setFechaProgramado(fechaProgramado);
                    pedido.setHoraProgramado(horaProgramado);
                    pedido.setUbicacionPedido(ubicacion);
                    pedido.setTipoPedido(tipoPedido);
                    pedido.setEstado(estado);
                    pedido.setCodigoSucursal(sucursal);
                    pedido.setCodigoCliente(cliente);
                    pedidoDao.agregar(pedido);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    codPedido = Integer.parseInt(request.getParameter("codigoPedido"));
                    Pedido pe = pedidoDao.listarCodigoPedido(codPedido);
                    request.setAttribute("pedido", pe);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    Date fechaCreacion2 = Date.valueOf(request.getParameter("txtFechaCreacion"));
                    Time horaCreacion2 = Time.valueOf(request.getParameter("txtHoraCreacion"));
                    Date fechaProgramado2 = Date.valueOf(request.getParameter("txtFechaProgramado"));
                    Time horaProgramado2 = Time.valueOf(request.getParameter("txtHoraProgramado"));
                    String ubicacion2 = request.getParameter("txtUbicacionPedido");
                    Pedido.TipoPedido tipoPedido2 = Pedido.TipoPedido.valueOf(request.getParameter("txtTipoPedido"));
                    Pedido.Estado estado2 = Pedido.Estado.valueOf(request.getParameter("txtEstado"));

                    pedido.setFechaCreacion(fechaCreacion2); 
                    pedido.setHoraCreacion(horaCreacion2);
                    pedido.setFechaProgramado(fechaProgramado2);
                    pedido.setHoraProgramado(horaProgramado2);
                    pedido.setUbicacionPedido(ubicacion2);
                    pedido.setTipoPedido(tipoPedido2);
                    pedido.setEstado(estado2);
                    pedido.setCodigoPedido(codPedido);
                    pedidoDao.actualizar(pedido);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codPedido = Integer.parseInt(request.getParameter("codigoPedido"));
                    pedidoDao.eliminar(codPedido);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Agregar2":
                    String instrucciones = request.getParameter("txtInstrucciones");
                    int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    Double subTotal = Double.parseDouble(request.getParameter("txtSubTotal"));
                    int codigoPedido = Integer.parseInt(request.getParameter("txtCodigoPedido"));
                    int codigoCombo = Integer.parseInt(request.getParameter("txtCodigoCombo"));
                    int codigoPromocion = Integer.parseInt(request.getParameter("txtCodigoPromocion"));
                    
                    detallePedido.setInstrucciones(instrucciones);
                    detallePedido.setCantidad(cantidad);
                    detallePedido.setSubTotal(subTotal);
                    detallePedido.setCodigoPedido(codigoPedido);
                    detallePedido.setCodigoCombo(codigoCombo);
                    detallePedido.setCodigoPromocion(codigoPromocion);
                    detallePedidoDao.agregar(detallePedido);
                    
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Editar2":
                    codDetallePedido = Integer.parseInt(request.getParameter("codigoDetallePedido"));
                    DetallePedido depe = detallePedidoDao.listarCodigoDetallePedido(codDetallePedido);
                    request.setAttribute("DetallePedido", depe);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Actualizar2":
                    String instrucciones2 = request.getParameter("txtInstrucciones");
                    int cantidad2 = Integer.parseInt(request.getParameter("txtCantidad"));
                    Double subTotal2 = Double.parseDouble(request.getParameter("txtSubTotal"));
                    
                    detallePedido.setInstrucciones(instrucciones2);
                    detallePedido.setCantidad(cantidad2);
                    detallePedido.setSubTotal(subTotal2);
                    detallePedido.setCodigoDetallePedido(codDetallePedido);
                    detallePedidoDao.actualizar(detallePedido);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Eliminar2":
                    codDetallePedido = Integer.parseInt(request.getParameter("codigoDetallePedido"));
                    detallePedidoDao.eliminar(codDetallePedido);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
            }
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

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Promocion&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Promocion.jsp").forward(request, response);
            }

        } else if (menu.equals("HacerPedido")) {
            List listaCombos = comboDao.listar();
            request.setAttribute("combos", listaCombos);

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=HacerPedido&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("HacerPedido.jsp").forward(request, response);
            }
        } else if (menu.equals("Carrito")) {
            switch (accion) {
                case "Agregar":
                    int codigoCombo = Integer.parseInt(request.getParameter("codigoCombo"));
                    int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                    Combo comboSeleccionado = null;
                    try {
                        comboSeleccionado = comboDao.buscar(codigoCombo);
                        System.out.println("Combo encontrado: " + comboSeleccionado.getNombreCombo());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (comboSeleccionado != null && comboSeleccionado.getPrecioCombo() != null) {

                        if (carrito == null) {
                            carrito = new HashMap<>();
                            session.setAttribute("carrito", carrito);
                        }
                        HashMap<Integer, Combo> combosInfo = (HashMap<Integer, Combo>) session.getAttribute("combosInfo");
                        if (combosInfo == null) {
                            combosInfo = new HashMap<>();
                            session.setAttribute("combosInfo", combosInfo);
                        }

                        if (carrito.containsKey(codigoCombo)) {
                            DetallePedido detalleExistente = carrito.get(codigoCombo);
                            int nuevaCantidad = detalleExistente.getCantidad() + cantidad;
                            double nuevoSubtotal = Math.round((comboSeleccionado.getPrecioCombo().doubleValue() * nuevaCantidad) * 100.0) / 100.0;
                            detalleExistente.setCantidad(nuevaCantidad);
                            detalleExistente.setSubTotal(nuevoSubtotal);
                        } else {
                            DetallePedido nuevoDetalle = new DetallePedido();
                            nuevoDetalle.setCodigoCombo(codigoCombo);
                            nuevoDetalle.setCantidad(cantidad);
                            double subtotal = Math.round((comboSeleccionado.getPrecioCombo().doubleValue() * cantidad) * 100.0) / 100.0;
                            nuevoDetalle.setSubTotal(subtotal);
                            nuevoDetalle.setInstrucciones("");
                            carrito.put(codigoCombo, nuevoDetalle);
                        }

                        combosInfo.put(codigoCombo, comboSeleccionado);
                        session.setAttribute("carrito", carrito);
                        session.setAttribute("combosInfo", combosInfo);

                        int totalItemsCarrito = 0;
                        double totalPrecioCarrito = 0.0;
                        for (DetallePedido detalle : carrito.values()) {
                            totalItemsCarrito += detalle.getCantidad();
                            totalPrecioCarrito += detalle.getSubTotal();

                            session.setAttribute("subtotalFormateado_" + detalle.getCodigoCombo(),
                                    String.format("%.2f", detalle.getSubTotal()));
                        }

                        session.setAttribute("totalItemsCarrito", totalItemsCarrito);
                        session.setAttribute("totalPrecioFormateado", String.format("%.2f", totalPrecioCarrito));

                        session.setAttribute("productoAgregadoExito", true);
                        session.setAttribute("nombreComboAgregado", comboSeleccionado.getNombreCombo());
                    }

                    response.sendRedirect("Controlador?menu=HacerPedido&accion=Listar");
                    return;

                case "Eliminar":
                    int comboEliminar = Integer.parseInt(request.getParameter("codigoCombo"));
                    if (carrito != null) {
                        carrito.remove(comboEliminar);
                    }
                    HashMap<Integer, Combo> combosInfoElim = (HashMap<Integer, Combo>) session.getAttribute("combosInfo");
                    if (combosInfoElim != null) {
                        combosInfoElim.remove(comboEliminar);
                        session.setAttribute("combosInfo", combosInfoElim);
                    }
                    session.setAttribute("carrito", carrito);
                    response.sendRedirect("Principal.jsp");
                    return;

                case "Vaciar":
                    if (carrito != null) {
                        carrito.clear();
                    }
                    session.removeAttribute("combosInfo");
                    session.setAttribute("carrito", carrito);
                    response.sendRedirect("Principal.jsp");
                    return;

                case "HacerPedido":
                    try {
                        if (carrito == null || carrito.isEmpty()) {
                            session.setAttribute("mensajeError", "El carrito está vacío. Agrega productos antes de hacer un pedido.");
                            response.sendRedirect("Principal.jsp");
                            return;
                        }

                        Pedido pedido = new Pedido();
                        pedido.setFechaCreacion(Date.valueOf(LocalDate.now()));
                        pedido.setHoraCreacion(Time.valueOf(LocalTime.now()));
                        pedido.setEstado(Pedido.Estado.Activo);
                        pedido.setTipoPedido(Pedido.TipoPedido.Recoger);
                        pedido.setUbicacionPedido("Sucursal Central");
                        pedido.setCodigoSucursal(1);
                        pedido.setCodigoCliente(1);

                        pedidoDao.agregar(pedido);

                        List<Pedido> pedidos = pedidoDao.listar();
                        int idPedido = pedidos.get(pedidos.size() - 1).getCodigoPedido();

                        double totalPedido = 0.0;
                        for (DetallePedido detalle : carrito.values()) {
                            DetallePedido nuevoDetalle = new DetallePedido();
                            nuevoDetalle.setCodigoPedido(idPedido);
                            nuevoDetalle.setCodigoCombo(detalle.getCodigoCombo());
                            nuevoDetalle.setCantidad(detalle.getCantidad());
                            nuevoDetalle.setSubTotal(detalle.getSubTotal());
                            nuevoDetalle.setCodigoPromocion(1);
                            nuevoDetalle.setInstrucciones(detalle.getInstrucciones());

                            detallePedidoDao.agregar(nuevoDetalle);
                            totalPedido += detalle.getSubTotal();
                        }

                        carrito.clear();
                        session.removeAttribute("combosInfo");
                        session.setAttribute("carrito", carrito);

                        session.setAttribute("pedidoExitoso", true);
                        session.setAttribute("numeroPedido", idPedido);
                        session.setAttribute("totalPedido", String.format("%.2f", totalPedido));

                        response.sendRedirect("Principal.jsp");
                        return;

                    } catch (Exception e) {
                        e.printStackTrace();
                        session.setAttribute("mensajeError", "Error al procesar el pedido. Inténtalo nuevamente.");
                        response.sendRedirect("Principal.jsp");
                        return;
                    }
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
