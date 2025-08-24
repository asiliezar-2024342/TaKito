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
                            <th>Tipo</th>
                            <th>Título</th>
                            <th>Comentario</th>
                            <th>Calificación</th>
                            <th>Estado</th>
                            <th>Sucursal</th>
                            <th>Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="resena" items="${resenas}">
                            <tr>
                                <td>${resena.getCodigoResena()}</td>
                                <td>${resena.getTipo()}</td>
                                <td>${resena.getTituloResena()}</td>
                                <td>${resena.getComentarioResena()}</td>
                                <td>${resena.getCalificacionResena()}</td>
                                <td>${resena.getCodigoSucursal()}</td>
                                <td>${resena.getEstado()}</td>
                                <td>${resena.getCodigoUsuario()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Resena&accion=Editar&codigoResena=${resena.getCodigoResena()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Resena&accion=ConfirmarEliminar&codigoResena=${resena.getCodigoResena()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Resena" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/clasificacion.png" alt="Nombre">
                                    <label><strong>Tipo: </strong></label>
                                    <select name="txtTipo" class="select">
                                        <option value="Sucursal" ${resena.tipo == 'Sucursal' ? 'selected' : ''}>Sucursal</option>
                                        <option value="Empleado" ${resena.tipo == 'Empleado' ? 'selected' : ''}>Empleado</option>
                                        <option value="Producto" ${resena.tipo == 'Producto' ? 'selected' : ''}>Producto</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Título">
                                    <label><strong>Título: </strong></label>
                                    <input type="text" value="${resena.getTituloResena()}" name="txtTitulo">
                                </div>
                                <div class="form-row">
                                    <img src="img/comentario.png" alt="Comentario">
                                    <label><strong>Comentario: </strong></label>
                                    <input type="text" value="${resena.getComentarioResena()}" name="txtComentario">
                                </div>
                                <div class="form-row">
                                    <img src="img/calificacion.png" alt="Calificación">
                                    <label><strong>Calificación: </strong></label>
                                    <input type="number" value="${resena.getCalificacionResena()}" name="txtCalificacion">
                                </div>
                                <div class="form-row">
                                    <img src="img/tienda.png" alt="Sucursal">
                                    <label><strong>Sucursal </strong></label>
                                    <input type="number" value="${resena.getCodigoSucursal()}" name="txtSucursal">
                                </div>
                                <div class="form-row">
                                    <img src="img/usuario.png" alt="Usuario">
                                    <label><strong>Usuario </strong></label>
                                    <input type="number" value="${resena.getCodigoUsuario()}" name="txtUsuario">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Nombre">
                                    <label><strong>Estado: </strong></label>
                                    <input type="text" value="${resena.getEstado()}" name="txtEstado" disabled>
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