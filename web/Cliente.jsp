<%-- 
    Document   : Sucursal
    Created on : Aug 18, 2025, 10:40:04 PM
    Author     : angel
--%>

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
                            <th>Primer Nombre</th>
                            <th>Segundo Nombre</th>
                            <th>Primer Apellido</th>
                            <th>Segundo Apellido</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                            <th>Sexo</th>
                            <th>NIT</th>
                            <th>Estado</th>
                            <th>Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}">
                            <tr>
                                <td>${cliente.getCodigoCliente()}</td>
                                <td>${cliente.getPrimerNombreCliente()}</td>
                                <td>${cliente.getSegundoNombreCliente()}</td>
                                <td>${cliente.getPrimerApellidoCliente()}</td>
                                <td>${cliente.getSegundoApellidoCliente()}</td>
                                <td>${cliente.getTelefonoCliente()}</td>
                                <td>${cliente.getDireccionCliente()}</td>
                                <td>${cliente.getSexoCliente()}</td>
                                <td>${cliente.getNitCliente()}</td>
                                <td>${cliente.getEstado()}</td>
                                <td>${cliente.getCodigoUsuario()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Cliente&accion=Editar&codigoCliente=${cliente.getCodigoCliente()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar" href="Controlador?menu=Cliente&accion=Eliminar&codigoCliente=${cliente.getCodigoCliente()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Cliente" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Primer Nombre">
                                    <label><strong>Primer Nombre: </strong></label>
                                    <input type="text" value="${cliente.getPrimerNombreCliente()}" name="txtprimerNombreCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Segundo Nombre">
                                    <label><strong>Segundo Nombre: </strong></label>
                                    <input type="text" value="${cliente.getSegundoNombreCliente()}" name="txtsegundoNombreCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Primer Apellido">
                                    <label><strong>Primer Apellido: </strong></label>
                                    <input type="text" value="${cliente.getPrimerApellidoCliente()}" name="txtprimerApellidoCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Segundo Apellido">
                                    <label><strong>Segundo Apellido: </strong></label>
                                    <input type="text" value="${cliente.getSegundoApellidoCliente()}" name="txtsegundoApellidoCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/Telefono.png" alt="Teléfono">
                                    <label><strong>Teléfono: </strong></label>
                                    <input type="text" value="${cliente.getTelefonoCliente()}" name="txttelefonoCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/Ubicacion.png" alt="Dirección">
                                    <label><strong>Dirección: </strong></label>
                                    <input type="text" value="${cliente.getDireccionCliente()}" name="txtdireccionCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/genero.png" alt="Sexo">
                                    <label><strong>Sexo: </strong></label>
                                    <select name="txtSexoCliente" class="select">
                                        <option value="Hombre" ${cliente.getSexoCliente() == 'Hombre' ? 'selected' : ''}>Hombre</option>
                                        <option value="Mujer" ${cliente.getSexoCliente() == 'Mujer' ? 'selected' : ''}>Mujer</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-nombre.jpg" alt="NIT">
                                    <label><strong>NIT: </strong></label>
                                    <input type="text" value="${cliente.getNitCliente()}" name="txtnitCliente">
                                </div>
                                <div class="form-row">
                                    <img src="img/usuario.png" alt="Usuario">
                                    <label><strong>Usuario </strong></label>
                                    <input type="number" value="${cliente.getCodigoUsuario()}" name="txtcodigoUsuario">
                                    <a href="../src/java/modelo/Cliente.java"></a>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Estado">
                                    <label><strong>Estado: </strong></label>
                                    <input type="text" value="${cliente.getEstado()}" name="txtEstadoCliente">
                                </div>
                            </div>
                                <a href="../src/java/modelo/Resena.java"></a>
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
