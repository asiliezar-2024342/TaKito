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
        <link rel="stylesheet" href="styles/Ubicacion.css">
    </head>
    <body> 
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead> 
                        <tr>
                            <th>Ubicacion</th>
                            <th>Nombre</th>
                            <th>Estado</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sucursal" items="${sucursales}">
                            <tr>

                                <td>${sucursal.getUbicacionSucursal()}</td>
                                <td>${sucursal.getNombreSucursal()}</td>
                                <td>${sucursal.getEstado()}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <a type="submit"class="btn-icon Ubicacion" href="https://www.google.com/maps">
                    <img src="img/Ubicacion.png" >
                </a>
            </div>
        </section>  
    </body>
</html>