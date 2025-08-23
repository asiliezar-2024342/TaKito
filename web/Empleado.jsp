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
        <link rel="stylesheet" href="styles/EmpleadoHU.css">
    </head>
    <body>
        <form class="form-row2" style="flex-direction: row;" action="Controlador?menu=Empleado" method="POST">
            <img src="img/tienda.png" alt="Sucursal"/>   
            <label><strong>Sucursal a filtrar para ver los empleados: </strong></label>
            <input type="text" name="txtCodigoSucursalBuscar" value="${empleado.getCodigoSucursal()}">
            <div class="form__emp">
            <button type="submit" class="btn-Buscar" name="accion" value="Buscar" title="Buscar">
                <img src="img/Buscar.png" alt="Buscar">
            </button>
            </div>
            <label class="label-1"><strong>Listar tabla:  </strong></label>
            <button type="submit" class="btn-Listar" name="accion" value="Listar" title="Listar">
                <img src="img/Actualizar.png" alt="Listar">
            </button>
        </form>
        <section class="crud">
            <div class="crud__table">
                <form class="form-row" action="Controlador?menu=Empleado&accion=Buscar" method="POST">
                    <img src="img/tienda.png" alt="Sucursal"/>
                    <label><strong>Sucursal a filtrar: </strong></label>
                    <input type="text" name="txtCodigoSucursalBuscar" value="${empleado.getCodigoSucursal()}">
                    <button type="submit" class="btn-icon Buscar" name="accion" value="Buscar" title="Buscar">
                        <img src="img/Buscar.png" alt="Buscar">
                    </button>
                </form>
                
                <table>
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Primer Nombre</th>
                            <th>Segundo Nombre</th>
                            <th>Primer Apellido</th>
                            <th>Segundo Apellido</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th>Dirección</th>
                            <th>Estado</th>
                            <th>Sexo</th>
                            <th>Codigo Sucursal</th>
                            <th>Codigo Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="empleado" items="${empleados}">
                            <tr>
                                <td>${empleado.getCodigoEmpleado()}</td>
                                <td>${empleado.getPrimerNombreEmpleado()}</td>
                                <td>${empleado.getSegundoNombreEmpleado()}</td>
                                <td>${empleado.getPrimerApellidoEmpleado()}</td>
                                <td>${empleado.getSegundoApellidoEmpleado()}</td>
                                <td>${empleado.getTelefonoEmpleado()}</td>
                                <td>${empleado.getCorreoEmpleado()}</td>
                                <td>${empleado.getDireccionEmpleado()}</td>
                                <td>${empleado.getEstado()}</td>
                                <td>${empleado.getSexoEmpleado()}</td>
                                <td>${empleado.getCodigoSucursal()}</td>
                                <td>${empleado.getCodigoUsuario()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Empleado&accion=Editar&codigoEmpleado=${empleado.getCodigoEmpleado()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Empleado&accion=Eliminar&codigoEmpleado=${empleado.getCodigoEmpleado()}">
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
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Empleado" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/nombres.png" alt="PrimerNombre">
                                    <label><strong>Primer nombre: </strong></label>
                                    <input type="text" value="${empleado.getPrimerNombreEmpleado()}" name="txtPrimerNombreEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/nombres.png" alt="SegundoNombre">
                                    <label><strong>Segundo nombre: </strong></label>
                                    <input type="text" value="${empleado.getSegundoNombreEmpleado()}" name="txtSegundoNombreEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/nombres.png" alt="PrimerApellido">
                                    <label><strong>Primer apellido: </strong></label>
                                    <input type="text" value="${empleado.getPrimerApellidoEmpleado()}" name="txtPrimerApellidoEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/nombres.png" alt="SegundoApellido">
                                    <label><strong>Segundo Apellido: </strong></label>
                                    <input type="text" value="${empleado.getSegundoApellidoEmpleado()}" name="txtSegundoApellidoEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/telefono.png" alt="Telefono">
                                    <label><strong>Teléfono: </strong></label>
                                    <input type="text" value="${empleado.getTelefonoEmpleado()}" name="txtTelefonoEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/correo.png" alt="Correo">
                                    <label><strong>Correo: </strong></label>
                                    <input type="text" value="${empleado.getCorreoEmpleado()}" name="txtCorreoEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/direccion.png" alt="Direccion">
                                    <label><strong>Dirección: </strong></label>
                                    <input type="text" value="${empleado.getDireccionEmpleado()}" name="txtDireccionEmpleado">
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Estado">
                                    <label><strong>Estado: </strong></label>
                                    <select name="txtEstado" class="select">
                                        <option value="Activo" ${empleado.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                                        <option value="Inactivo" ${empleado.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                                    </select>
                                </div>
                                    <div class="form-row">
                                    <img src="img/genero.png" alt="SexoEmpleado">
                                    <label><strong>Sexo: </strong></label>
                                    <select name="txtSexo" class="select">
                                        <option value="Hombre" ${empleado.sexoEmpleado == 'Hombre' ? 'selected' : ''}>Hombre</option>
                                        <option value="Mujer" ${empleado.sexoEmpleado == 'Mujer' ? 'selected' : ''}>Mujer</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/tienda.png" alt="Sucursal">
                                    <label><strong>Sucursal: </strong></label>
                                    <input type="number" value="${empleado.getCodigoSucursal()}" name="txtCodigoSucursal">
                                </div>
                                <div class="form-row">
                                    <img src="img/usuario.png" alt="Usuario">
                                    <label><strong>Usuario: </strong></label>
                                    <input type="number" value="${empleado.getCodigoUsuario()}" name="txtCodigoUsuario">
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
                    <div class="form-2">
                        <form class="form-row" style="flex-direction: row;" action="Controlador?menu=Empleado&accion=BuscarFactura" method="POST">
                            <img src="img/tienda.png" alt="Sucursal"/>
                            <label><strong>Sucursal a filtrar para ver empleados con mayor facturación: </strong></label>
                            <div class="form__suc">
                            <input type="text" name="txtCodigoSucursalBuscar" value="${empleado.getCodigoSucursal()}">
                            <button type="submit" class="btn-Buscar" name="accion" value="BuscarFactura" title="BuscarFactura"> 
                                <img src="img/Buscar.png" alt="BuscarFactura">
                            </button>
                            </div>
                        </form>
                        <table>
                            <thead>
                                <tr>
                                    <th>CódigoEmpleado</th>
                                    <th>Nombre Completo</th>
                                    <th>TotalFacturado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="empleadoFactura" items="${empleadosFacturacion}">
                                    <tr>
                                        <td>${empleadoFactura.getCodigoEmpleado()}</td>
                                        <td>${empleadoFactura.getNombreCompleto()}</td>
                                        <td>${empleadoFactura.getTotalFacturado()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>                 
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
