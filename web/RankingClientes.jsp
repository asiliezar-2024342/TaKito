<%-- 
    Document   : RankingClientes
    Created on : 20/08/2025, 11:34:01
    Author     : evame
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
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Total Pedidos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${rankingClientes}">
                            <tr>
                                <td>${cliente.codigoCliente}</td>
                                <td>${cliente.primerNombreCliente}</td>
                                <td>${cliente.primerApellidoCliente}</td>
                                <td>${cliente.totalPedidos}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
            <a href="Controlador?menu=Cliente&accion=Listar"
               title="Regresar A Clientes"
               style="position: fixed; bottom: 20px; right: 20px; display: block; width: 80px; height: 80px; z-index: 1000;"> 
                <img src="img/return.png" alt="Regresar a Clientes" style="width: 100%; height:  100%">
            </a>
    </body>
</html>