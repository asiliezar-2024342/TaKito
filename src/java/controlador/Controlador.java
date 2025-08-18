package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;
import java.util.List;
import java.util.ArrayList;
public class Controlador extends HttpServlet {

    Cliente cliente = new Cliente();
    ClienteDAO clienteDao = new ClienteDAO();
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
        if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        } else if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List<Cliente> listaClientes = clienteDao.listar();
                    request.setAttribute("clientes", listaClientes);
                    break;
                case "Agregar":
                    String primerNombreCliente = request.getParameter("txtprimerNombreCliente");
                    String segundoNombreCliente = request.getParameter("txtsegundoNombreCliente");
                    String primerApellidoCliente = request.getParameter("txtprimerApellidoCliente");
                    String segundoApellidoCliente = request.getParameter("txtsegundoApellidoCliente");
                    String telefonoCliente = request.getParameter("txttelefonoCliente");
                    String direccionCliente = request.getParameter("txtdireccionCliente");
                    String sexoClienteStr = request.getParameter("txtsexoCliente");
                    String nitCliente = request.getParameter("txtnitCliente");
                    String estadoStr = request.getParameter("txtestado");
                    
                    try {
                        cliente.setPrimerNombreCliente(primerNombreCliente);
                        cliente.setSegundoNombreCliente(segundoNombreCliente);
                        cliente.setPrimerApellidoCliente(primerApellidoCliente);
                        cliente.setSegundoApellidoCliente(segundoApellidoCliente);
                        cliente.setTelefonoCliente(telefonoCliente);
                        cliente.setDireccionCliente(direccionCliente);
                        cliente.setSexoCliente(Cliente.SexoCliente.valueOf(sexoClienteStr));
                        cliente.setNitCliente(nitCliente);
                        cliente.setEstado(Cliente.EstadoCliente.valueOf(estadoStr));
                        
                        clienteDao.agregar(cliente);
                    } catch (IllegalArgumentException e) {
                        request.setAttribute("error", "Valores de Sexo o Estado no son válidos.");
                    }
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    return; 
                case "Editar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    Cliente clienteEditado = clienteDao.buscar(codCliente);
                    request.setAttribute("cliente", clienteEditado);
                    break;
                case "Actualizar":
                    String primerNombreClienteUpd = request.getParameter("txtprimerNombreCliente");
                    String segundoNombreClienteUpd = request.getParameter("txtsegundoNombreCliente");
                    String primerApellidoClienteUpd = request.getParameter("txtprimerApellidoCliente");
                    String segundoApellidoClienteUpd = request.getParameter("txtsegundoApellidoCliente");
                    String telefonoClienteUpd = request.getParameter("txttelefonoCliente");
                    String direccionClienteUpd = request.getParameter("txtdireccionCliente");
                    String sexoClienteStrUpd = request.getParameter("txtsexoCliente");
                    String nitClienteUpd = request.getParameter("txtnitCliente");
                    String estadoStrUpd = request.getParameter("txtestado");
                    int codigoClienteUpd = Integer.parseInt(request.getParameter("txtcodigoCliente"));
                    
                    try {
                        cliente.setPrimerNombreCliente(primerNombreClienteUpd);
                        cliente.setSegundoNombreCliente(segundoNombreClienteUpd);
                        cliente.setPrimerApellidoCliente(primerApellidoClienteUpd);
                        cliente.setSegundoApellidoCliente(segundoApellidoClienteUpd);
                        cliente.setTelefonoCliente(telefonoClienteUpd);
                        cliente.setDireccionCliente(direccionClienteUpd);
                        cliente.setSexoCliente(Cliente.SexoCliente.valueOf(sexoClienteStrUpd));
                        cliente.setNitCliente(nitClienteUpd);
                        cliente.setEstado(Cliente.EstadoCliente.valueOf(estadoStrUpd));
                        cliente.setCodigoCliente(codigoClienteUpd);
                        
                        clienteDao.editar(cliente);
                    } catch (IllegalArgumentException e) {
                        request.setAttribute("error", "Valores de Sexo o Estado no son válidos.");
                    }
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    return;
                case "Eliminar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    clienteDao.eliminar(codCliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    return;
                case "Buscar":
                    int codigoClienteBuscado = 0;
                    try {
                        codigoClienteBuscado = Integer.parseInt(request.getParameter("txtCodigoClienteBuscado"));
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Por favor, ingrese un código de cliente válido.");
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                        return;
                    }

                    Cliente clienteBuscado = clienteDao.buscar(codigoClienteBuscado);
                    if (clienteBuscado != null) {
                        List<Cliente> resultadoBusqueda = new ArrayList<>();
                        resultadoBusqueda.add(clienteBuscado);
                        request.setAttribute("clientes", resultadoBusqueda);
                    } else {
                        request.setAttribute("mensaje", "Cliente con código " + codigoClienteBuscado + " no encontrado.");
                        request.setAttribute("clientes", new ArrayList<Cliente>());
                    }
                     request.getRequestDispatcher("Cliente.jsp").forward(request, response);
                    break;
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
