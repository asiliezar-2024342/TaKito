<%-- 
    Document   : Sucursal
    Created on : Aug 18, 2025, 10:40:04 PM
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ta'Kabron</title>
    </head>
    <body>
          <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Mensaje</th>
                            <th>Tabla</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Dato Anterior</th>
                            <th>Dato Nuevo</th>
                            <th>Accion</th>
                            <th>Codigo Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bitacora" items="${bitacoras}">
                            <tr>
                                <td>${bitacora.getCodigoBitacora()}</td>
                                <td>${bitacora.getMensaje()}</td>
                                <td>${bitacora.getTablaModificada()}</td>
                                <td>${bitacora.getFecha()}</td>
                                <td>${bitacora.getHora()}</td>
                                <td>${bitacora.getDatoAnterior()}</td>
                                <td>${bitacora.getDatoNuevo()}</td>
                                <td>${bitacora.getAccion()}</td>
                                <td>${bitacora.getCodigoUsuario()}</td>
                                  <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Bitacora&accion=Editar&codigoBitacora=${bitacora.getCodigoBitacora()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Bitacora&accion=Eliminar&codigoBitacora=${bitacora.getCodigoBitacora()}">
                                        <img src="img/Eliminar.png" alt="Eliminar">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="form-section">
                <div class="form-layout">
                    <div class="form-container">
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Bitacora" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/icon-mensaje.png" alt="Mensaje">
                                    <label><strong>Mensaje: </strong></label>
                                    <input type="text" name="txtMensaje" value="${bitacora.getMensaje()}">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-Tabla.png" alt="Título">
                                    <label><strong>Tabla Modificada: </strong></label>
                                    <select name="txtTablaModificada" class="select">
                                        <option value="Sucursal" <c:if test="${bitacora.getTablaModificada() == 'Sucursal'}">selected</c:if>>Sucursal</option>
                                        <option value="Usuario" <c:if test="${bitacora.getTablaModificada() == 'Usuario'}">selected</c:if>>Usuario</option>
                                        <option value="Combo" <c:if test="${bitacora.getTablaModificada() == 'Combo'}">selected</c:if>>Combo</option>
                                        <option value="Producto" <c:if test="${bitacora.getTablaModificada() == 'Producto'}">selected</c:if>>Producto</option>
                                        <option value="Promocion" <c:if test="${bitacora.getTablaModificada() == 'Promocion'}">selected</c:if>>Promocion</option>
                                        <option value="Resena" <c:if test="${bitacora.getTablaModificada() == 'Resena'}">selected</c:if>>Resena</option>
                                        <option value="DetalleCombo" <c:if test="${bitacora.getTablaModificada() == 'DetalleCombo'}">selected</c:if>>DetalleCombo</option>
                                        <option value="DetallePromocion" <c:if test="${bitacora.getTablaModificada() == 'DetallePromocion'}">selected</c:if>>DetallePromocion</option>
                                        <option value="Cliente" <c:if test="${bitacora.getTablaModificada() == 'Cliente'}">selected</c:if>>Cliente</option>
                                        <option value="Empleado" <c:if test="${bitacora.getTablaModificada() == 'Empleado'}">selected</c:if>>Empleado</option>
                                        <option value="Pedido" <c:if test="${bitacora.getTablaModificada() == 'Pedido'}">selected</c:if>>Pedido</option>
                                        <option value="DetallePedido" <c:if test="${bitacora.getTablaModificada() == 'DetallePedido'}">selected</c:if>>DetallePedido</option>
                                        <option value="Factura" <c:if test="${bitacora.getTablaModificada() == 'Factura'}">selected</c:if>>Factura</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-fecha.jpg" alt="Comentario">
                                    <label><strong>Fecha: </strong></label>
                                    <input type="date" name="txtFecha" value="${bitacora.getFecha()}">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-Hora.png" alt="Calificación">
                                    <label><strong>Hora: </strong></label>
                                    <input type="Time" name="txtHora" value="${bitacora.getHora()}" required="">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-Dato.png" alt="Sucursal">
                                    <label><strong>Dato Anterior: </strong></label>
                                    <input type="text" name="txtDatoAnterior" value="${bitacora.getDatoAnterior()}">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-Dato.png" alt="Usuario">
                                    <label><strong>Dato Nuevo: </strong></label>
                                    <input type="text" name="txtDatoNuevo" value="${bitacora.getDatoNuevo()}">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-accion.png" alt="Nombre">
                                    <label><strong>Acción: </strong></label>
                                    <select name="txtAccion" class="select">
                                        <option value="Crear" <c:if test="${bitacora.getAccion() == 'Crear'}">selected</c:if>>Crear</option>
                                        <option value="Actualizar" <c:if test="${bitacora.getAccion() == 'Actualizar'}">selected</c:if>>Actualizar</option>
                                        <option value="Eliminar" <c:if test="${bitacora.getAccion() == 'Eliminar'}">selected</c:if>>Eliminar</option>
                                    </select>
                                </div>
                                
                                 <div class="form-row">
                                    <img src="img/icon-nombre.jpg" alt="Nombre">
                                    <label><strong>Código Usuario: </strong></label>
                                    <input type="number" name="txtCodigoUsuario" value="${bitacora.getCodigoUsuario()}">
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit" class="btn-icon Agregar" name="accion" value="Agregar" title="Agregar">
                                    <img src="img/Agregar.png" alt="Agregar">
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
