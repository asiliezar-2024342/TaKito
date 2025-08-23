<%-- 
    Document   : Promociones.jsp
    Created on : 20 ago 2025, 05:34:54
    Author     : thevi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="styles/Promociones.css">
        <title>Ta'Kabron</title>
    </head>
    <body class="body">
        <h1>Populares</h1>

        <div class="contenedor-promociones">
            <c:forEach var="Promocion" items="${PromocionesMasUsadas}">
                <div class="tarjeta-promocion">
                    <h2>${Promocion.getNombrePromocion()}</h2>
                    <p><strong>Descripción:</strong> ${Promocion.getDescripcionPromocion()}</p>
                    <p><strong>Descuento:</strong> ${Promocion.getDescuentoPromocion()}%</p>
                    <p><strong>Vigencia:</strong> ${Promocion.getFechaInicio()} al ${Promocion.getFechaFin()}</p>
                </div>
            </c:forEach>
        </div>
        
        <h1>Promociones Activas</h1>

        <div class="contenedor-promociones">
            <c:forEach var="Promocion" items="${PromocionesActivas}">
                <div class="tarjeta-promocion">
                    <h2>${Promocion.getNombrePromocion()}</h2>
                    <p><strong>Descripción:</strong> ${Promocion.getDescripcionPromocion()}</p>
                    <p><strong>Descuento:</strong> ${Promocion.getDescuentoPromocion()}%</p>
                    <p><strong>Vigencia:</strong> ${Promocion.getFechaInicio()} al ${Promocion.getFechaFin()}</p>
                </div>
            </c:forEach>
        </div>
    
        <h1>Promociones Futuras</h1>

        <div class="contenedor-promociones">
            <c:forEach var="Promocion" items="${PromocionesFuturas}">
                <div class="tarjeta-promocion">
                    <h2>${Promocion.getNombrePromocion()}</h2>
                    <p><strong>Descripción:</strong> ${Promocion.getDescripcionPromocion()}</p>
                    <p><strong>Descuento:</strong> ${Promocion.getDescuentoPromocion()}%</p>
                    <p><strong>Vigencia:</strong> ${Promocion.getFechaInicio()} al ${Promocion.getFechaFin()}</p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
