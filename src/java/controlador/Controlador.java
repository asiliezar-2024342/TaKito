package controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bitacora;
import modelo.BitacoraDAO;
import modelo.Resena;
import modelo.ResenaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Resena resena = new Resena();
    ResenaDAO resenaDao = new ResenaDAO();
    Bitacora bitacora = new Bitacora();
    BitacoraDAO bitacoraDao = new BitacoraDAO();
    int codResena;
    int codBitacora;

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
            switch (accion) {
                case "Listar":
                    List listaBitacora = bitacoraDao.listar();
                    request.setAttribute("bitacoras", listaBitacora);
                    break;
                case "Agregar":
                    String mensaje = request.getParameter("txtMensaje");
                    String tablaModificada = request.getParameter("txtTablaModificada");
                    Date fecha = Date.valueOf(request.getParameter("txtFecha"));
                    String hora = request.getParameter("txtHora");
                    LocalTime localTime = LocalTime.parse(hora);
                    Time horaSql = Time.valueOf(localTime);
                    String datoAnterior = request.getParameter("txtDatoAnterior");
                    String datoNuevo = request.getParameter("txtDatoNuevo");
                    String accionBitacora = request.getParameter("txtAccion");
                    int codigoUsuario = Integer.parseInt(request.getParameter("txtCodigoUsuario"));
                    bitacora.setMensaje(mensaje);
                    bitacora.setTablaModificada(tablaModificada);
                    bitacora.setFecha(fecha);
                    bitacora.setHora(horaSql);
                    bitacora.setDatoAnterior(datoAnterior);
                    bitacora.setDatoNuevo(datoNuevo);
                    bitacora.setAccion(accionBitacora);
                    bitacora.setCodigoUsuario(codigoUsuario);
                    bitacoraDao.agregar(bitacora);
                    request.getRequestDispatcher("Controlador?menu=Bitacora&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codBitacora = Integer.parseInt(request.getParameter("codigoBitacora"));
                    Bitacora bi = bitacoraDao.listarCodigoBitacora(codBitacora);
                    request.setAttribute("bitacora", bi);
                    request.getRequestDispatcher("Controlador?menu=Bitacora&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String mensajeBi = request.getParameter("txtMensaje");
                    String tablaModificadaBi = request.getParameter("txtTablaModificada");
                    Date fechaBi = Date.valueOf(request.getParameter("txtFecha"));
                    String horaBi = request.getParameter("txtHora");
                    LocalTime localTimeBI = LocalTime.parse(horaBi);
                    Time horaSqlBi = Time.valueOf(localTimeBI);
                    String datoAnteriorBi = request.getParameter("txtDatoAnterior");
                    String datoNuevoBi = request.getParameter("txtDatoNuevo");
                    String accionBi = request.getParameter("txtAccion");
                    bitacora.setMensaje(mensajeBi);
                    bitacora.setTablaModificada(tablaModificadaBi);
                    bitacora.setFecha(fechaBi);
                    bitacora.setHora(horaSqlBi);
                    bitacora.setDatoAnterior(datoAnteriorBi);
                    bitacora.setDatoNuevo(datoNuevoBi);
                    bitacora.setAccion(accionBi);
                    bitacora.setCodigoBitacora(codBitacora);
                    bitacoraDao.actualizar(bitacora);
                    request.getRequestDispatcher("Controlador?menu=Bitacora&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codBitacora = Integer.parseInt(request.getParameter("codigoBitacora"));
                    bitacoraDao.eliminar(codBitacora);
                    request.getRequestDispatcher("Controlador?menu=Bitacora&accion=Listar").forward(request, response);
                    break;
            }
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
