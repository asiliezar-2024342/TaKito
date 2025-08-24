package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DetallePromocion;
import modelo.DetallePromocionDAO;
import modelo.Promocion;
import modelo.PromocionDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Factura;
import modelo.FacturaDAO;
import modelo.Combo;
import modelo.ComboDAO;
import modelo.DetalleCombo;
import modelo.DetalleComboDAO;
import javax.servlet.http.HttpSession;
import modelo.Combo;
import modelo.ComboDAO;
import modelo.DetallePedido;
import modelo.DetallePedidoDAO;
import modelo.Pedido;
import modelo.PedidoDAO;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Resena;
import modelo.ResenaDAO;
import modelo.Sucursal;
import modelo.SucursalDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.Cliente;
import modelo.ClienteDAO;

import modelo.Producto;
import modelo.ProductoDAO;

@MultipartConfig
public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Resena resena = new Resena();
    ResenaDAO resenaDao = new ResenaDAO();
    Cliente cliente = new Cliente();
    ClienteDAO clienteDao = new ClienteDAO();
    Promocion promocion = new Promocion();
    PromocionDAO promocionDao = new PromocionDAO();
    DetallePromocion detallePromocion = new DetallePromocion();
    DetallePromocionDAO detallePromocionDao = new DetallePromocionDAO();
    Sucursal sucursal = new Sucursal();
    SucursalDAO sucursalDao = new SucursalDAO();
    int codResena;
    int codSucursal;
    int codCliente;
    int codPromocion;
    int codDetallePromocion;
    Empleado empleado = new Empleado();
    EmpleadoDAO empleadoDao = new EmpleadoDAO();
    int codEmpleado;
    Producto producto = new Producto();
    ProductoDAO productoDao = new ProductoDAO();
    int codProducto;
    Factura factura = new Factura();
    FacturaDAO facturaDao = new FacturaDAO();
    int codFactura;
    DetalleCombo detalleCombo = new DetalleCombo();
    DetalleComboDAO detalleComboDao = new DetalleComboDAO();
    int codDetalleCombo;
    Combo combo = new Combo();
    ComboDAO comboDao = new ComboDAO();
    int codCombo;

    Pedido pedido = new Pedido();
    PedidoDAO pedidoDao = new PedidoDAO();
    int codPedido;
    DetallePedido detallePedido = new DetallePedido();
    DetallePedidoDAO detallePedidoDao = new DetallePedidoDAO();
    int codDetallePedido;
    int codUsuario;

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
        Usuario usuarioActual = new Usuario();
        String nombres;
        String apellidos;
        HttpSession session = request.getSession();

        HashMap<Integer, DetallePedido> carrito = (HashMap<Integer, DetallePedido>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
            session.setAttribute("carrito", carrito);
        }

        if (menu.equals("Principal")) {
            usuarioActual = (Usuario) request.getAttribute("usuario");
            HttpSession usuarioSesion = request.getSession();

            if (usuarioActual.getCargo().toString().equalsIgnoreCase("Consumidor")) {
                nombres = clienteDao.buscarUsuario(usuarioActual.getCodigoUsuario()).getPrimerNombreCliente();
                apellidos = clienteDao.buscarUsuario(usuarioActual.getCodigoUsuario()).getPrimerApellidoCliente();

                usuarioSesion.setAttribute("usuarioActual", usuarioActual);
                usuarioSesion.setAttribute("nombreCliente", nombres);
                usuarioSesion.setAttribute("apellidoCliente", apellidos);
            } else {
                usuarioSesion.setAttribute("usuarioActual", usuarioActual);
            }

            // ANIMACIÓN DE TRANSICIÓN NO TOCAR
            request.setAttribute("jspFinal", "Principal.jsp");
            request.getRequestDispatcher("Inicial.jsp").forward(request, response);

            response.sendRedirect("Principal.jsp");

        } else if (menu.equals("PrincipalContenido")) {
            // ANIMACIÓN DE TRANSICIÓN NO TOCAR
            request.setAttribute("jspFinal", "PrincipalContenido.jsp");
            request.getRequestDispatcher("Transicion.jsp").forward(request, response);

        } else if (menu.equals("Usuario")) {

            switch (accion) {
                case "Listar":
                    List<Usuario> listaUsuarios = usuarioDao.listar();

                    request.setAttribute("usuarios", listaUsuarios);
                    break;

                case "Agregar":
                    String correo = request.getParameter("txtCorreoUsuario");
                    String contrasena = request.getParameter("txtContrasenaUsuario");

                    if (correo != null && contrasena != null) {
                        usuario.setCorreoUsuario(correo);
                        usuario.setContrasenaUsuario(contrasena);

                        usuarioDao.agregar(usuario);

                        cliente.setPrimerNombreCliente("---");
                        cliente.setSegundoNombreCliente("---");
                        cliente.setPrimerApellidoCliente("---");
                        cliente.setSegundoApellidoCliente("---");
                        cliente.setTelefonoCliente("--------");
                        cliente.setDireccionCliente("---");
                        cliente.setSexoCliente(Cliente.SexoCliente.Hombre);
                        cliente.setNitCliente("---");
                        cliente.setEstado(Cliente.EstadoCliente.Activo);
                        cliente.setCodigoUsuario(usuarioDao.obtenerCodigo(correo).getCodigoUsuario());

                        clienteDao.agregar(cliente);

                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;

                case "AgregarCrud":
                    String correoCrud = request.getParameter("txtCorreoUsuario");
                    String contrasenaCrud = request.getParameter("txtPasswordUsuario");
                    Part archivo = request.getPart("txtFoto");
                    InputStream is = archivo.getInputStream();

                    usuario.setCorreoUsuario(correoCrud);
                    usuario.setContrasenaUsuario(contrasenaCrud);
                    usuario.setFoto(is);

                    usuarioDao.agregar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    codUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                    Usuario us = usuarioDao.listarCodigoUsuario(codUsuario);
                    request.setAttribute("usuarioEdit", us);
                    request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String correoUsu = request.getParameter("txtCorreoUsuario");
                    String contrasenaUsu = request.getParameter("txtPasswordUsuario");
                    String fechaRegistro = request.getParameter("txtFechaRegistro");

                    Part archivoAct = request.getPart("txtFoto");
                    InputStream ist = archivoAct.getInputStream();

                    Usuario.Cargo cargo = Usuario.Cargo.valueOf(request.getParameter("txtCargo"));

                    usuario.setCorreoUsuario(correoUsu);
                    usuario.setContrasenaUsuario(contrasenaUsu);
                    usuario.setFechaRegistro(Date.valueOf(fechaRegistro));
                    usuario.setFoto(ist);
                    usuario.setCargo(cargo);
                    usuario.setCodigoUsuario(codUsuario);

                    usuarioDao.actualizar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    codUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                    usuarioDao.eliminar(codUsuario);
                    request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                    break;
            }

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Usuario&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Usuario.jsp").forward(request, response);
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
            switch (accion) {
                case "Listar":
                    List listaProductos = productoDao.listar();
                    request.setAttribute("productos", listaProductos);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombreProducto");
                    String descripcion = request.getParameter("txtDescripcionProducto");
                    Double precio = Double.parseDouble(request.getParameter("txtPrecioUnitario"));
                    int existencia = Integer.parseInt(request.getParameter("txtExistencias"));
                    String est = request.getParameter("txtEstado");
                    producto.setNombreProducto(nombre);
                    producto.setDescripcionProducto(descripcion);
                    producto.setPrecioUnitario(precio);
                    producto.setExistencias(existencia);
                    producto.setEstado(Producto.Estado.valueOf(est));
                    productoDao.agregar(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codProducto = Integer.parseInt(request.getParameter("codigoProducto"));
                    Producto pro = productoDao.listaCodigoProducto(codProducto);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombrePro = request.getParameter("txtNombreProducto");
                    String descripcionPro = request.getParameter("txtDescripcionProducto");
                    Double precioUn = Double.parseDouble(request.getParameter("txtPrecioUnitario"));
                    int existencias = Integer.parseInt(request.getParameter("txtExistencias"));
                    String esta = request.getParameter("txtEstado");
                    producto.setNombreProducto(nombrePro);
                    producto.setDescripcionProducto(descripcionPro);
                    producto.setPrecioUnitario(precioUn);
                    producto.setExistencias(existencias);
                    producto.setEstado(Producto.Estado.valueOf(esta));
                    producto.setCodigoProducto(codProducto);
                    productoDao.actualizar(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codProducto = Integer.parseInt(request.getParameter("codigoProducto"));
                    productoDao.eliminar(codProducto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
            }
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Producto&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
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
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Bitacora&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Bitacora.jsp").forward(request, response);
            }

        } else if (menu.equals("Cliente")) {

            switch (accion) {

                // Listar Cliente
                case "Listar":
                    List listaClientes = clienteDao.listar();
                    request.setAttribute("clientes", listaClientes);
                    break;

                // Agregar Cliente 
                case "Agregar":
                    String primerNombre = request.getParameter("txtprimerNombreCliente");
                    String segundoNombre = request.getParameter("txtsegundoNombreCliente");
                    String primerApellido = request.getParameter("txtprimerApellidoCliente");
                    String segundoApellido = request.getParameter("txtsegundoApellidoCliente");
                    String telefono = request.getParameter("txttelefonoCliente");
                    String direccion = request.getParameter("txtdireccionCliente");
                    Cliente.SexoCliente sexoCliente = Cliente.SexoCliente.valueOf(request.getParameter("txtSexoCliente"));
                    String nitCliente = request.getParameter("txtnitCliente");
                    Cliente.EstadoCliente estadoCliente = Cliente.EstadoCliente.valueOf(request.getParameter("txtEstadoCliente"));
                    int usuario = Integer.parseInt(request.getParameter("txtcodigoUsuario"));

                    cliente.setPrimerNombreCliente(primerNombre);
                    cliente.setSegundoNombreCliente(segundoNombre);
                    cliente.setPrimerApellidoCliente(primerApellido);
                    cliente.setSegundoApellidoCliente(segundoApellido);
                    cliente.setTelefonoCliente(telefono);
                    cliente.setDireccionCliente(direccion);
                    cliente.setSexoCliente(sexoCliente);
                    cliente.setNitCliente(nitCliente);
                    cliente.setEstado(estadoCliente);
                    cliente.setCodigoUsuario(usuario);
                    clienteDao.agregar(cliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);

                    break;

                //Editar Cliente
                case "Editar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    Cliente c = clienteDao.buscar(codCliente);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                // Actualizar Cliente
                case "Actualizar":
                    String primerNombreA = request.getParameter("txtprimerNombreCliente");
                    String segundoNombreA = request.getParameter("txtsegundoNombreCliente");
                    String primerApellidoA = request.getParameter("txtprimerApellidoCliente");
                    String segundoApellidoA = request.getParameter("txtsegundoApellidoCliente");
                    String telefonoA = request.getParameter("txttelefonoCliente");
                    String direccionA = request.getParameter("txtdireccionCliente");
                    Cliente.SexoCliente sexoClienteA = Cliente.SexoCliente.valueOf(request.getParameter("txtSexoCliente"));
                    String nitClienteA = request.getParameter("txtnitCliente");
                    Cliente.EstadoCliente estadoClienteA = Cliente.EstadoCliente.valueOf(request.getParameter("txtEstadoCliente"));
                    int usuarioA = Integer.parseInt(request.getParameter("txtcodigoUsuario"));

                    cliente.setPrimerNombreCliente(primerNombreA);
                    cliente.setSegundoNombreCliente(segundoNombreA);
                    cliente.setPrimerApellidoCliente(primerApellidoA);
                    cliente.setSegundoApellidoCliente(segundoApellidoA);
                    cliente.setTelefonoCliente(telefonoA);
                    cliente.setDireccionCliente(direccionA);
                    cliente.setSexoCliente(sexoClienteA);
                    cliente.setNitCliente(nitClienteA);
                    cliente.setEstado(estadoClienteA);
                    cliente.setCodigoUsuario(usuarioA);
                    cliente.setCodigoCliente(codCliente);
                    clienteDao.actualizar(cliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);

                    break;
                case "Eliminar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    clienteDao.eleminar(codCliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "MostrarRanking":
                    List<Cliente> clientes = clienteDao.listar();
                    request.setAttribute("clientes", clientes);

                    List<Cliente> rankingClientes = clienteDao.obtenerRankingClientesUltimoMes();
                    request.setAttribute("rankingClientes", rankingClientes);

                    request.setAttribute("mostrarRanking", true);

                    request.getRequestDispatcher("RankingClientes.jsp").forward(request, response);
                    break;

            }

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
            switch (accion) {
                case "Listar":
                    List listaEmpleados = empleadoDao.listar();
                    request.setAttribute("empleados", listaEmpleados);
                    break;

                case "Buscar":
                    int codSucursal = Integer.parseInt(request.getParameter("txtCodigoSucursalBuscar"));
                    List empleadosS = empleadoDao.buscarEmpleadosPorSucursal(codSucursal);
                    request.setAttribute("empleados", empleadosS);
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;

                case "BuscarFactura":
                    int codigoSucur = Integer.parseInt(request.getParameter("txtCodigoSucursalBuscar"));
                    List empleadoFac = empleadoDao.facturacionEmpleadoPorSucursal(codigoSucur);
                    request.setAttribute("empleadosFacturacion", empleadoFac);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;

                case "Agregar":
                    String priNombre = request.getParameter("txtPrimerNombreEmpleado");
                    String segNombre = request.getParameter("txtSegundoNombreEmpleado");
                    String priApellido = request.getParameter("txtPrimerApellidoEmpleado");
                    String segApellido = request.getParameter("txtSegundoApellidoEmpleado");
                    String telefono = request.getParameter("txtTelefonoEmpleado");
                    String correo = request.getParameter("txtCorreoEmpleado");
                    String direccion = request.getParameter("txtDireccionEmpleado");
                    Empleado.Estado estado = Empleado.Estado.valueOf(request.getParameter("txtEstado"));
                    Empleado.SexoEmpleado sexo = Empleado.SexoEmpleado.valueOf(request.getParameter("txtSexo"));
                    int codigoSucursal = Integer.parseInt(request.getParameter("txtCodigoSucursal"));
                    int codigoUsuario = Integer.parseInt(request.getParameter("txtCodigoUsuario"));
                    empleado.setPrimerNombreEmpleado(priNombre);
                    empleado.setSegundoNombreEmpleado(segNombre);
                    empleado.setPrimerApellidoEmpleado(priApellido);
                    empleado.setSegundoApellidoEmpleado(segApellido);
                    empleado.setTelefonoEmpleado(telefono);
                    empleado.setCorreoEmpleado(correo);
                    empleado.setDireccionEmpleado(direccion);
                    empleado.setEstado(estado);
                    empleado.setSexoEmpleado(sexo);
                    empleado.setCodigoSucursal(codigoSucursal);
                    empleado.setCodigoUsuario(codigoUsuario);
                    empleadoDao.agregar(empleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                    Empleado e = empleadoDao.buscar(codEmpleado);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String priN = request.getParameter("txtPrimerNombreEmpleado");
                    String secN = request.getParameter("txtSegundoNombreEmpleado");
                    String priA = request.getParameter("txtPrimerApellidoEmpleado");
                    String secA = request.getParameter("txtSegundoApellidoEmpleado");
                    String tel = request.getParameter("txtTelefonoEmpleado");
                    String cor = request.getParameter("txtCorreoEmpleado");
                    String dir = request.getParameter("txtDireccionEmpleado");
                    Empleado.Estado est = Empleado.Estado.valueOf(request.getParameter("txtEstado"));
                    Empleado.SexoEmpleado sex = Empleado.SexoEmpleado.valueOf(request.getParameter("txtSexo"));
                    int suc = Integer.parseInt(request.getParameter("txtCodigoSucursal"));
                    int usu = Integer.parseInt(request.getParameter("txtCodigoUsuario"));

                    empleado.setPrimerNombreEmpleado(priN);
                    empleado.setSegundoNombreEmpleado(secN);
                    empleado.setPrimerApellidoEmpleado(priA);
                    empleado.setSegundoApellidoEmpleado(secA);
                    empleado.setTelefonoEmpleado(tel);
                    empleado.setCorreoEmpleado(cor);
                    empleado.setDireccionEmpleado(dir);
                    empleado.setEstado(est);
                    empleado.setSexoEmpleado(sex);
                    empleado.setCodigoSucursal(suc);
                    empleado.setCodigoUsuario(usu);
                    empleado.setCodigoEmpleado(codEmpleado);
                    empleadoDao.actualizar(empleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                    empleadoDao.eliminar(codEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
            }

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Empleado&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Empleado&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }
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
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Sucursal&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Sucursal.jsp").forward(request, response);
            }

        } else if (menu.equals("Ubicacion")) {
            switch (accion) {
                case "Listar":
                    List listaSucursal = sucursalDao.listar();
                    request.setAttribute("sucursales", listaSucursal);
                    break;
            }if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Ubicacion&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Ubicacion.jsp").forward(request, response);
            }

        } else if (menu.equals("Factura")) {
            switch (accion) {
                case "Listar":
                    List listaFacturas = facturaDao.listar();
                    request.setAttribute("facturas", listaFacturas);
                    break;

                case "Agregar":
                    int codigoPedido = Integer.parseInt(request.getParameter("txtCodigoPedido"));
                    int codigoEmpleado = Integer.parseInt(request.getParameter("txtCodigoEmpleado"));
                    float totalFactura = Float.parseFloat(request.getParameter("txtTotalFactura"));
                    float donacion = Float.parseFloat(request.getParameter("txtDonacion"));
                    Date fechaFactura = new Date(System.currentTimeMillis());
                    String horaFactura = java.time.LocalTime.now().toString().substring(0, 5);

                    String metodo = request.getParameter("txtMetodo");
                    String estado = request.getParameter("txtEstado");

                    factura.setCodigoPedido(codigoPedido);
                    factura.setCodigoEmpleado(codigoEmpleado);
                    factura.setTotalFactura(totalFactura);
                    factura.setDonacion(donacion);
                    factura.setFechaFactura(fechaFactura);
                    factura.setHoraFactura(horaFactura);
                    factura.setMetodo(metodo);
                    factura.setEstado(estado);

                    facturaDao.agregar(factura);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    codFactura = Integer.parseInt(request.getParameter("codigoFactura"));
                    Factura f = facturaDao.listarCodigoFactura(codFactura);
                    request.setAttribute("factura", f);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    int codigoPedido2 = Integer.parseInt(request.getParameter("txtCodigoPedido"));
                    int codigoEmpleado2 = Integer.parseInt(request.getParameter("txtCodigoEmpleado"));
                    float totalFactura2 = Float.parseFloat(request.getParameter("txtTotalFactura"));
                    float donacion2 = Float.parseFloat(request.getParameter("txtDonacion"));
                    Date fechaFactura2 = new Date(System.currentTimeMillis());
                    String horaFactura2 = java.time.LocalTime.now().toString().substring(0, 5);

                    String metodo2 = request.getParameter("txtMetodo");
                    String estado2 = request.getParameter("txtEstado");

                    factura.setCodigoPedido(codigoPedido2);
                    factura.setCodigoEmpleado(codigoEmpleado2);
                    factura.setTotalFactura(totalFactura2);
                    factura.setDonacion(donacion2);
                    factura.setFechaFactura(fechaFactura2);
                    factura.setHoraFactura(horaFactura2);
                    factura.setMetodo(metodo2);
                    factura.setEstado(estado2);

                    facturaDao.agregar(factura);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    codFactura = Integer.parseInt(request.getParameter("codigoFactura"));
                    facturaDao.eliminar(codFactura);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;
            }

            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Factura&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Factura.jsp").forward(request, response);
            }
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Factura&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Factura.jsp").forward(request, response);
            }

        } else if (menu.equals("DetalleCombo")) {

            switch (accion) {
                case "Listar":
                    List listaDetalleCombos = detalleComboDao.listar();
                    request.setAttribute("detalleCombos", listaDetalleCombos);
                    break;
                case "Agregar":
                    int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    int combo = Integer.parseInt(request.getParameter("txtCombo"));
                    int producto = Integer.parseInt(request.getParameter("txtProducto"));

                    detalleCombo.setCantidad(cantidad);
                    detalleCombo.setCodigoCombo(combo);
                    detalleCombo.setCodigoProducto(producto);
                    request.getRequestDispatcher("Controlador?menu=DetalleCombo&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codDetalleCombo = Integer.parseInt(request.getParameter("codigoDetalleCombo"));
                    DetalleCombo dc = detalleComboDao.listarCodigoDetalleCombo(codDetalleCombo);
                    request.setAttribute("detalleCombo", dc);
                    request.getRequestDispatcher("Controlador?menu=DetalleCombo&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    int cantidadA = Integer.parseInt(request.getParameter("txtCantidad"));
                    detalleCombo.setCantidad(cantidadA);
                    detalleCombo.setCodigoDetalleCombo(codDetalleCombo);
                    detalleComboDao.actualizar(detalleCombo);
                    request.getRequestDispatcher("Controlador?menu=DetalleCombo&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codDetalleCombo = Integer.parseInt(request.getParameter("codigoDetalleCombo"));
                    detalleComboDao.eliminar(codDetalleCombo);
                    request.getRequestDispatcher("Controlador?menu=DetalleCombo&accion=Listar").forward(request, response);
                    break;
            }
            if (accion.equals("Mover")) {
                // ANIMACIÓN DE TRANSICIÓN NO TOCAR
                request.setAttribute("jspFinal", "Controlador?menu=Resena&accion=Listar");
                request.getRequestDispatcher("Transicion.jsp").forward(request, response);
            } else if (accion.equals("Listar")) {
                request.getRequestDispatcher("Resena.jsp").forward(request, response);
            }

        } else if (menu.equals("Combo")) {

            switch (accion) {
                case "Listar":
                    List listaCombos = comboDao.listar();
                    request.setAttribute("combos", listaCombos);
                    break;
                case "Agregar":
                    String nombreCombo = request.getParameter("txtNombreCombo");
                    String descripcionCombo = request.getParameter("txtDescripcion");
                    Float precioCombo = Float.valueOf(request.getParameter("txtPrecioCombo"));
                    Combo.Categoria categoria = Combo.Categoria.valueOf(request.getParameter("txtCategoria"));
                    Combo.Estado estado = Combo.Estado.valueOf(request.getParameter("txtEstado"));
                    InputStream foto = (InputStream) request.getPart("foto");

                    combo.setNombreCombo(nombreCombo);
                    combo.setDescripcionCombo(descripcionCombo);
                    combo.setPrecioCombo(precioCombo);
                    combo.setCategoria(categoria);
                    combo.setEstado(estado);
                    combo.setFoto(foto);
                    comboDao.agregar(combo);
                    request.getRequestDispatcher("Controlador?menu=Combo&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codCombo = Integer.parseInt(request.getParameter("codigoCombo"));
                    Combo c = comboDao.buscar(codCombo);
                    request.setAttribute("combo", c);
                    request.getRequestDispatcher("Controlador?menu=Combo&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombreA = request.getParameter("txtNombreCombo");
                    String descripcionA = request.getParameter("txtDescripcion");
                    Float precioA = Float.valueOf(request.getParameter("txtPrecioCombo"));
                    Combo.Categoria categoriaA = Combo.Categoria.valueOf(request.getParameter("txtCategoria"));
                    Combo.Estado estadoA = Combo.Estado.valueOf(request.getParameter("txtEstado"));
                    InputStream fotoA = (InputStream) request.getPart("foto");

                    combo.setNombreCombo(nombreA);
                    combo.setDescripcionCombo(descripcionA);
                    combo.setPrecioCombo(precioA);
                    combo.setCategoria(categoriaA);
                    combo.setEstado(estadoA);
                    combo.setFoto(fotoA);
                    combo.setCodigoCombo(codCombo);
                    comboDao.actualizar(combo);
                    request.getRequestDispatcher("Controlador?menu=Combo&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codCombo = Integer.parseInt(request.getParameter("codigoCombo"));
                    comboDao.eliminar(codCombo);
                    request.getRequestDispatcher("Controlador?menu&accion=Listar").forward(request, response);
                    break;
            }
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
                case "ConfirmarEliminar2":
                    codDetallePromocion = Integer.parseInt(request.getParameter("codigoDetallePromocion"));
                    request.setAttribute("accionReal", "Eliminar");
                    request.setAttribute("codigoDetallePromocion", codDetallePromocion);
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
