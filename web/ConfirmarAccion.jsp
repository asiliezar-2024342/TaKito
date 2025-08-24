<%-- 
    Document   : ConfirmarAccion
    Created on : 23 ago 2025, 12:34:30
    Author     : thevi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmar acción</title>
    <link rel="stylesheet" href="styles/Confirmacion.css">
</head>
<body>
    <div class="modal-overlay">
        <div class="modal-box">
            <img src="img/Logo.png" alt="Logo" class="confirm-logo">
            <h2>¿Estás seguro que deseas <span class="accion">${accionReal}</span> la promoción?</h2>

            <div class="modal-buttons">
                <form action="Controlador?menu=${menu}" method="POST">
                    <input type="hidden" name="accion" value="${accionReal}">
                    <%-- Nota jovenes: Esta accion se debe repetir para cada uno de las entidades solamente cambiando las variables por las suyas.--%>
                    <c:if test="${not empty codigoPromocion}">
                        <input type="hidden" name="${codigo}" value="${codigoPromocion}">
                    </c:if>
                    <button type="submit" class="btn-confirmar">Confirmar</button>
                    
                    <c:if test="${not empty codigoDetallePromocion}">
                        <input type="hidden" name="codigoDetallePromocion" value="${codigoDetallePromocion}">
                    </c:if>
                </form>
                <a href="Controlador?menu=${menu}&accion=Listar" class="btn-cancelar">Cancelar</a>
            </div>
        </div>
    </div>
</body>
</html>