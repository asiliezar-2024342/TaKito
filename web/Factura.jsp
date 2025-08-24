

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Ta'Kabron</title>
        <link rel="stylesheet" href="styles/CRUD.css">
    </head>
    <body>
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Pedido</th>
                            <th>Empleado</th>
                            <th>Total</th>
                            <th>Donación</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Metodo</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="factura" items="${facturas}">
                            <tr>
                                <td>${factura.getCodigoFactura()}</td>
                                <td>${factura.getCodigoPedido()}</td>
                                <td>${factura.getCodigoEmpleado()}</td>
                                <td>${factura.getTotalFactura()}</td>
                                <td>${factura.getDonacion()}</td>
                                <td>${factura.getFechaFactura()}</td>
                                <td>${factura.getHoraFactura()}</td>
                                <td>${factura.getMetodo()}</td>
                                <td>${factura.getEstado()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Factura&accion=Editar&codigoFactura=${factura.getCodigoFactura()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar" href="Controlador?menu=Factura&accion=ConfirmarEliminar&codigoFactura=${factura.getCodigoFactura()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Factura" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/Pedido.png" alt="Pedido">
                                    <label><strong>Pedido:</strong></label>
                                    <input type="text" value="${factura.getCodigoPedido()}" name="txtCodigoPedido">
                                </div>
                                <div class="form-row">
                                    <img src="img/empleado.png" alt="Empleado">
                                    <label><strong>Empleado:</strong></label>
                                    <input type="text" value="${factura.getCodigoEmpleado()}" name="txtCodigoEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/total.png" alt="Total">
                                    <label><strong>Total:</strong></label>
                                    <input type="text" value="${factura.getTotalFactura()}" name="txtTotalFactura">
                                </div>
                                <div class="form-row">
                                    <img src="img/donacion.png" alt="Donacion">
                                    <label><strong>Donación:</strong></label>
                                    <input type="text" value="${factura.getDonacion()}" name="txtDonacion">
                                </div>
                                <div class="form-row">
                                    <img src="img/metodo.png" alt="Metodo">
                                    <label><strong>Método:</strong></label>
                                    <select name="txtMetodo" class="select">
                                        <option value="Efectivo" ${factura.metodo == 'Efectivo' ? 'selected' : ''}>Efectivo</option>
                                        <option value="Tarjeta" ${factura.metodo == 'Efectivo' ? 'selected' : ''}>Tarjeta</option>
                                        <option value="Transferencia" ${factura.metodo == 'Transferencia' ? 'selected' : ''}>Transferencia</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Nombre">
                                    <label><strong>Estado:</strong></label>
                                    <select name="txtEstado" class="select" disabled>
                                        <option value="Activo" ${factura.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                                        <option value="Inactivo" ${factura.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                                    </select>
                                </div>

                                <div class="form-row">
                                    <img src="img/icon-fecha.jpg" alt="Fecha">
                                    <label><strong>Fecha:</strong></label>
                                    <input type="date" value="${factura.getFechaFactura()}" name="txtFechaFactura">
                                </div>

                                <div class="form-row">
                                    <img src="img/hora.png" alt="Hora">
                                    <label><strong>Hora:</strong></label>
                                    <input type="time" value="${factura.getHoraFactura()}" name="txtHoraFactura">
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
