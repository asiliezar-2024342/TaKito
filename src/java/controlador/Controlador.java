package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Resena;
import modelo.ResenaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.Cliente;
import modelo.ClienteDAO;


public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Resena resena = new Resena();
    ResenaDAO resenaDao = new ResenaDAO();
    Cliente cliente = new Cliente();
    ClienteDAO clienteDao = new ClienteDAO();
    int codResena;
    int codCliente;

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
                    clienteDao.Agregar(cliente);
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
