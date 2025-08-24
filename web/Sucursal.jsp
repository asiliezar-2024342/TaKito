<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
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
                            <th>Ubicacion</th>
                            <th>Telefono</th>
                            <th>Nombre</th>
                            <th>Funcionamiento</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sucursal" items="${sucursales}">
                            <tr>
                                <td>${sucursal.getCodigoSucursal()}</td>
                                <td>${sucursal.getUbicacionSucursal()}</td>
                                <td>${sucursal.getTelefonoSucursal()}</td>
                                <td>${sucursal.getNombreSucursal()}</td>
                                <td>${sucursal.getFuncionamiento()}</td>
                                <td>${sucursal.getEstado()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Sucursal&accion=Editar&codigoSucursal=${sucursal.getCodigoSucursal()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Sucursal&accion=ConfirmarEliminar&codigoSucursal=${sucursal.getCodigoSucursal()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Sucursal" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Título">
                                    <label><strong>Ubicacion: </strong></label>
                                    <input type="text" value="${sucursal.getUbicacionSucursal()}" name="txtUbicacionSucursal">
                                </div>
                                <div class="form-row">
                                    <img src="img/comentario.png" alt="Comentario">
                                    <label><strong>Telefono: </strong></label>
                                    <input type="text" value="${sucursal.getTelefonoSucursal()}" name="txtTelefonoSucursal">
                                </div>
                                <div class="form-row">
                                    <img src="img/calificacion.png" alt="Calificación">
                                    <label><strong>Nombre: </strong></label>
                                    <input type="text" value="${sucursal.getNombreSucursal()}" name="txtNombreSucursal">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Nombre">
                                    <label><strong>Funcionamiento: </strong></label>
                                    <select name="txtFuncionamiento" class="select">
                                        <option value="Abierto">Abierto</option>
                                        <option value="Cerrado">Cerrado</option>
                                        <option value="Suspendido">Suspendido</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Nombre">
                                    <label><strong>Estado: </strong></label>
                                    <select name="txtEstado" class="select" disabled>
                                        <option value="Activo">Activo</option>
                                        <option value="Inactivo">Inactivo</option>
                                    </select>
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