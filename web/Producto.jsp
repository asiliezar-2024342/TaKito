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
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th>Existencias</th>
                            <th>Estado</th>
                            <th>Acciones</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="producto" items="${productos}">
                            <tr>
                                <td>${producto.getCodigoProducto()}</td>
                                <td>${producto.getNombreProducto()}</td>
                                <td>${producto.getDescripcionProducto()}</td>
                                <td>${producto.getPrecioUnitario()}</td>
                                <td>${producto.getExistencias()}</td>
                                <td>${producto.getEstado()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Producto&accion=Editar&codigoProducto=${producto.getCodigoProducto()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Producto&accion=Eliminar&codigoProducto=${producto.getCodigoProducto()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Producto" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Nombre">
                                    <label><strong>Nombre: </strong></label>
                                    <input type="text" value="${producto.getNombreProducto()}" name="txtNombreProducto">
                                </div>
                                <div class="form-row">
                                    <img src="img/comentario.png" alt="Descripción">
                                    <label><strong>Descripción: </strong></label>
                                    <input type="text" value="${producto.getDescripcionProducto()}" name="txtDescripcionProducto">
                                </div>
                                <div class="form-row">
                                    <img src="img/precio.png" alt="PrecioUnitario">
                                    <label><strong>Precio: </strong></label>
                                    <input type="number" value="${producto.getPrecioUnitario()}" name="txtPrecioUnitario">
                                </div>
                                <div class="form-row">
                                    <img src="img/tienda.png" alt="Existencias">
                                    <label><strong>Existencia: </strong></label>
                                    <input type="number" value="${producto.getExistencias()}" name="txtExistencias">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Estado">
                                    <label><strong>Estado: </strong></label>
                                    <select name="txtEstado" class="select">
                                        <option value="Activo" ${producto.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                                        <option value="Inactivo" ${producto.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
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