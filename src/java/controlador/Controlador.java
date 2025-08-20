package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;
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
    Empleado empleado = new Empleado();
    EmpleadoDAO empleadoDao = new EmpleadoDAO();
    int codEmpleado;

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
            switch(accion){
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
