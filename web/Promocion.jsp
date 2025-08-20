<%-- 
    Document   : Sucursal
    Created on : Aug 18, 2025, 10:40:04 PM
    Author     : angel
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="styles/CRUD.css">
        <title>Ta'Kabron</title>
    </head>
    <body>
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Descuento</th>
                            <th>Inicio</th>
                            <th>Fin</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="Promocion" items="${Promociones}">
                            <tr>
                                <td>${Promocion.getCodigoPromocion()}</td>
                                <td>${Promocion.getNombrePromocion()}</td>
                                <td>${Promocion.getDescripcionPromocion()}</td>
                                <td>${Promocion.getDescuentoPromocion()}</td>
                                <td>${Promocion.getFechaInicio()}</td>
                                <td>${Promocion.getFechaFin()}</td>
                                <td>${Promocion.getEstado()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Promocion&accion=Editar&codigoPromocion=${Promocion.getCodigoPromocion()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Promocion&accion=Eliminar&codigoPromocion=${Promocion.getCodigoPromocion()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Promocion" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/icon-nombre.jpg" alt="Nombre">
                                    <label><strong>Nombre: </strong></label>
                                    <input type="text" value="${Promocion.getNombrePromocion()}" name="txtNombrePromocion" >
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-descripcion.jpg" alt="Nombre">
                                    <label><strong>Descripcion: </strong></label>
                                    <input type="text" value="${Promocion.getDescripcionPromocion()}" name="txtDescripcionPromocion">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-descuento.png" alt="Nombre">
                                    <label><strong>Descuento: </strong></label>
                                    <input type="text" value="${Promocion.getDescuentoPromocion()}" name="txtDescuentoPromocion">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-fecha.jpg" alt="Fecha">
                                    <label><strong>Fecha Inicio: </strong></label>
                                    <input type="date" value="${Promocion.getFechaInicio()}" name="txtFechaInicio">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-fecha.jpg" alt="Fecha">
                                    <label><strong>Fecha Fin: </strong></label>
                                    <input type="date" value="${Promocion.getFechaFin()}" name="txtFechaFin">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Nombre">
                                    <label><strong>Estado: </strong></label>
                                    <select name="txtEstado" class="select">
                                        <option value="Activo">Activo</option>
                                        <option value="Inactivo">Inactivo</option>
                                    </select>
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit"class="btn-icon Agregar" name="accion" value="Agregar" title="Agregar">
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
                            <th>Observacion</th>
                            <th>Promocion</th>
                            <th>Combo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="DetallePromocion" items="${DetallesPromociones}">
                            <tr>
                                <td>${DetallePromocion.getCodigoDetallePromocion()}</td>
                                <td>${DetallePromocion.getObservaciones()}</td>
                                <td>${DetallePromocion.getCodigoPromocion()}</td>
                                <td>${DetallePromocion.getCodigoCombo()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Promocion&accion=Editar2&codigoDetallePromocion=${DetallePromocion.getCodigoDetallePromocion()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Promocion&accion=Eliminar2&codigoDetallePromocion=${DetallePromocion.getCodigoDetallePromocion()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Promocion" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/icon-nombre.jpg" alt="Nombre">
                                    <label><strong>Observacion: </strong></label>
                                    <input type="text" value="${DetallePromocion.getObservaciones()}" name="txtObservaciones" >
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-descripcion.jpg" alt="Nombre">
                                    <label><strong>Promocion: </strong></label>
                                    <input type="text" value="${DetallePromocion.getCodigoPromocion()}" name="txtCodigoPromocion">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-descuento.png" alt="Nombre">
                                    <label><strong>Combo: </strong></label>
                                    <input type="text" value="${DetallePromocion.getCodigoCombo()}" name="txtCodigoCombo">
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar2" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit"class="btn-icon Agregar" name="accion" value="Agregar2" title="Agregar">
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
