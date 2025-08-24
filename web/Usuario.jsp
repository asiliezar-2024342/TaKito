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
    </head>
    <body>
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Correo</th>
                            <th>Contraseña</th>
                            <th>Fecha de Registro</th>
                            <th>Cargo</th>
                            <th>Foto</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                                <td>${usuario.getCodigoUsuario()}</td>
                                <td>${usuario.getCorreoUsuario()}</td>
                                <td>(Encrypted)</td>
                                <td>${usuario.getFechaRegistro()}</td>
                                <td>${usuario.getCargo()}</td>
                                <td>
                                    <img src="Validar?codigoUsuario=${usuario.getCodigoUsuario()}" width="110" height="150" loading="lazy" alt="Foto de usuario"/>
                                </td>
                                <td>${usuario.getEstado()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Usuario&accion=Editar&codigoUsuario=${usuario.getCodigoUsuario()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Usuario&accion=Eliminar&codigoUsuario=${usuario.getCodigoUsuario()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Usuario" method="POST" enctype="multipart/form-data">
                            <div>
                                <div class="form-row">
                                    <img src="img/comentario.png" alt="Nombre">
                                    <label><strong>Correo: </strong></label>
                                    <input type="text" value="${usuarioEdit.getCorreoUsuario()}" name="txtCorreoUsuario">
                                </div>
                                    
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Título">
                                    <label><strong>Contraseña: </strong></label>
                                    <input type="password" value="${usuarioEdit.getContrasenaUsuario()}" name="txtPasswordUsuario">
                                </div>
                                
                                <div class="form-row">
                                    <img src="img/Gestionar.png" alt="Comentario">
                                    <label><strong>Fecha de Registro: </strong></label>
                                    <input type="date" value="${usuarioEdit.getFechaRegistro()}" name="txtFechaRegistro">
                                </div>
                                
                                <div class="form-row">
                                    <img src="img/clasificacion.png" alt="Calificación">
                                    <label><strong>Cargo: </strong></label>
                                    <select name="txtCargo" class="select">
                                        <option value="Consumidor" ${usuarioEdit.cargo == 'Consumidor' ? 'selected' : ''}>Consumidor</option>
                                        <option value="Administrativo" ${usuarioEdit.cargo == 'Administrativo' ? 'selected' : ''}>Administrativo</option>
                                        <option value="Empleado" ${usuarioEdit.cargo == 'Empleado' ? 'selected' : ''}>Empleado</option>
                                    </select>
                                </div>
                                
                                <div class="form-row">
                                    <img src="img/usuario.png" alt="Sucursal">
                                    <label><strong>Foto: </strong></label>
                                    <input type="file" value="" name="txtFoto" accept="image/*">
                                </div>
                                
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Usuario">
                                    <label><strong>Estado: </strong></label>
                                    <input type="text" value="${usuarioEdit.getEstado()}" name="txtEstado" disabled>
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit" class="btn-icon Agregar" name="accion" value="AgregarCrud" title="Agregar">
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
