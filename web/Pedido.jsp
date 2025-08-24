<%-- 
    Document   : Sucursal
    Created on : Aug 18, 2025, 10:40:04 PM
    Author     : angel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Ta'Kabron</title>
        <link rel="stylesheet" href="styles/CRUD.css">
        <style>
            
        .crud:nth-of-type(2) {
            margin-top: 86vh;
            height: auto;
                min-height: 60vh;
        }

    </style>
    </head>
    <body>
        
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Fecha Creación</th>
                            <th>Hora Creación</th>
                            <th>Fecha Programado</th>
                            <th>Hora Programado</th>
                            <th>Ubicación</th>
                            <th>Tipo</th>
                            <th>Estado</th>
                            <th>Sucursal</th>
                            <th>Cliente</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pedido" items="${pedidos}">
                            <tr>
                                <td>${pedido.getCodigoPedido()}</td>
                                <td>${pedido.getFechaCreacion()}</td>
                                <td>${pedido.getHoraCreacion()}</td>
                                <td>${pedido.getFechaProgramado()}</td>
                                <td>${pedido.getHoraProgramado()}</td>
                                <td>${pedido.getUbicacionPedido()}</td>
                                <td>${pedido.getTipoPedido()}</td>
                                <td>${pedido.getEstado()}</td>
                                <td>${pedido.getCodigoSucursal()}</td>
                                <td>${pedido.getCodigoCliente()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Pedido&accion=Editar&codigoPedido=${pedido.getCodigoPedido()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Pedido&accion=Eliminar&codigoPedido=${pedido.getCodigoPedido()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Pedido" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/Fecha.png" alt="Fecha Creación">
                                    <label><strong>Fecha Creación: </strong></label>
                                    <input type="date" value="${pedido.getFechaCreacion()}" name="txtFechaCreacion">
                                    <input type="hidden" value="${pedido.getCodigoPedido()}" name="txtCodigoPedido">
                                </div>
                                <div class="form-row">
                                    <img src="img/Fecha.png" alt="Hora Creación">
                                    <label><strong>Hora Creación: </strong></label>
                                    <input type="text" value="${pedido.getHoraCreacion()}" name="txtHoraCreacion">
                                </div>
                                <div class="form-row">
                                    <img src="img/Fecha.png" alt="Fecha Programado">
                                    <label><strong>Fecha Programado: </strong></label>
                                    <input type="date" value="${pedido.getFechaProgramado()}" name="txtFechaProgramado">
                                </div>
                                <div class="form-row">
                                    <img src="img/Fecha.png" alt="Hora Programado">
                                    <label><strong>Hora Programado: </strong></label>
                                    <input type="text" value="${pedido.getHoraProgramado()}" name="txtHoraProgramado">
                                </div>
                                <div class="form-row">
                                    <img src="img/Ubicacion.png" alt="Ubicacion">
                                    <label><strong>Ubicación: </strong></label>
                                    <input type="text" value="${pedido.getUbicacionPedido()}" name="txtUbicacionPedido">
                                </div>
                                <div class="form-row">
                                    <img src="img/TipoPedido.png" alt="Tipo Pedido">
                                    <label><strong>Tipo Pedido: </strong></label>
                                    <select name="txtTipoPedido" class="select">
                                        <option value="Recoger" ${pedido.tipoPedido == 'Recoger' ? 'selected' : ''}>Recoger</option>
                                        <option value="Domicilio" ${pedido.tipoPedido == 'Domicilio' ? 'selected' : ''}>Domicilio</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Estado">
                                    <label><strong>Estado: </strong></label>
                                    <select name="txtEstado" class="select">
                                        <option value="Activo" ${pedido.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                                        <option value="Inactivo" ${pedido.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/tienda.png" alt="Codigo Sucursal">
                                    <label><strong>Codigo Sucursal: </strong></label>
                                    <input type="number" value="${pedido.getCodigoSucursal()}" name="txtCodigoSucursal">
                                </div>
                                <div class="form-row">
                                    <img src="img/usuario.png" alt="Codigo Cliente">
                                    <label><strong>Codigo Cliente: </strong></label>
                                    <input type="number" value="${pedido.getCodigoCliente()}" name="txtCodigoCliente">
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
        
        
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Instrucciones</th>
                            <th>Cantidad</th>
                            <th>SubTotal</th>
                            <th>Pedido</th>
                            <th>Combo</th>
                            <th>Promocion</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="DetallePedido" items="${DetallesPedidos}">
                            <tr>
                                <td>${DetallePedido.getCodigoDetallePedido()}</td>
                                <td>${DetallePedido.getInstrucciones()}</td>
                                <td>${DetallePedido.getCantidad()}</td>
                                <td>${DetallePedido.getSubTotal()}</td>
                                <td>${DetallePedido.getCodigoPedido()}</td>
                                <td>${DetallePedido.getCodigoCombo()}</td>
                                <td>${DetallePedido.getCodigoPromocion()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Pedido&accion=Editar2&codigoDetallePedido=${DetallePedido.getCodigoDetallePedido()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Pedido&accion=Eliminar2&codigoDetallePedido=${DetallePedido.getCodigoDetallePedido()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Pedido" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/Instrucciones.png" alt="Instrucciones">
                                    <label><strong>Instrucciones: </strong></label>
                                    <input type="text" value="${DetallePedido.getInstrucciones()}" name="txtInstrucciones" >
                                    <input type="hidden" value="${DetallePedido.getCodigoDetallePedido()}" name="txtCodigoDetallePedido">
                                </div>
                                <div class="form-row">
                                    <img src="img/Cantidad.png" alt="Cantidad">
                                    <label><strong>Cantidad: </strong></label>
                                    <input type="number" value="${DetallePedido.getCantidad()}" name="txtCantidad">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-descuento.png" alt="SubTotal">
                                    <label><strong>Sub Total: </strong></label>
                                    <input type="number" step="0.01" value="${DetallePedido.getSubTotal()}" name="txtSubTotal">
                                </div>
                                <div class="form-row">
                                    <img src="img/TipoPedido.png" alt="Pedido">
                                    <label><strong>Pedido: </strong></label>
                                    <input type="number" value="${DetallePedido.getCodigoPedido()}" name="txtCodigoPedido">
                                </div>
                                <div class="form-row">
                                    <img src="img/Combo.png" alt="Combo">
                                    <label><strong>Combo: </strong></label>
                                    <input type="number" value="${DetallePedido.getCodigoCombo()}" name="txtCodigoCombo">
                                </div>
                                <div class="form-row">
                                    <img src="img/Promociones.png" alt="Promocion">
                                    <label><strong>Promocion: </strong></label>
                                    <input type="number" value="${DetallePedido.getCodigoPromocion()}" name="txtCodigoPromocion">
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar2" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit" class="btn-icon Agregar" name="accion" value="Agregar2" title="Agregar">
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