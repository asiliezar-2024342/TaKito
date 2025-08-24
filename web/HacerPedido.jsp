<%-- 
    Document   : HacerPedido
    Created on : 21 ago 2025, 22:16:13
    Author     : LOGIN
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
        <section class="menu-container">
            <h1 class="menu-title">Nuestro Menú</h1>

            <c:if test="${sessionScope.productoAgregadoExito}">
                <div class="mensaje-exito">
                    ¡${sessionScope.nombreComboAgregado} agregado al carrito exitosamente! 
                    <br><strong>Total de productos: ${sessionScope.totalItemsCarrito}</strong>
                </div>
                <div class="instruccion-recarga">
                    Para ver el carrito actualizado en la barra lateral, presiona F5 o actualiza la página principal
                </div>
                <c:remove var="productoAgregadoExito" scope="session"/>
                <c:remove var="nombreComboAgregado" scope="session"/>
            </c:if>

            <div class="carrito-resumen">
                <img src="img/carrito.png" alt="Carrito" style="width: 20px; height: 20px; vertical-align: middle; margin-right: 8px;">
                <strong>Carrito actual: </strong>
                <c:choose>
                    <c:when test="${sessionScope.carrito != null && !sessionScope.carrito.isEmpty()}">
                        <c:set var="totalItems" value="0" />
                        <c:set var="totalPrecio" value="0" />
                        <c:forEach var="item" items="${sessionScope.carrito}">
                            <c:set var="totalItems" value="${totalItems + item.value.cantidad}" />
                            <c:set var="totalPrecio" value="${totalPrecio + item.value.subTotal}" />
                        </c:forEach>
                        <span style="color: #28a745; font-weight: bold;">
                            ${totalItems} productos - Total: Q${totalPrecio}
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: #6c757d;">Vacío</span>
                    </c:otherwise>
                </c:choose>
            </div>
            <form action="Controlador?accion=SeleccionarS"  method="POST">
                <div class="resena__input-container">
                    <label>Sucursal</label>
                    <select name="txtSucursal" required>
                        <c:forEach var="sucursal" items="${sucursales}">
                            <option value="${sucursal.getCodigoSucursal()}"
                                    <c:if test="${sucursal.getCodigoSucursal() == sucursalP}">selected</c:if>>
                                ${sucursal.getNombreSucursal()}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <input type="submit" value="Seleccionar Sucursal">
            </form>
            <div class="menu-grid">

                <c:forEach var="combo" items="${combos}">
                    <div class="combo-card">
                        <div style="display: flex; flex-direction: column;">
                            <div class="combo-code">Código: ${combo.getCodigoCombo()}</div>
                            <img src="Validar?codigoCombo=${combo.getCodigoCombo()}" width="110" height="150" loading="lazy" alt="Foto de combo"/>
                        </div>
                        <div class="combo-name">${combo.getNombreCombo()}</div>
                        <div class="combo-price">Q${combo.getPrecioCombo()}</div>                        
                        <form method="post" action="Controlador" style="margin-top: 10px;">
                            <input type="hidden" name="menu" value="Carrito">
                            <input type="hidden" name="accion" value="Agregar">
                            <input type="hidden" name="codigoCombo" value="${combo.getCodigoCombo()}">

                            <div style="margin-bottom: 10px;">
                                <label>Cantidad: </label>
                                <input type="number" name="cantidad" value="1" min="1" max="99" 
                                       style="width: 60px; margin: 0 5px;">
                            </div>

                            <button type="submit" class="add-btn">
                                Agregar al Carrito
                            </button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </section>
    </body>
</html>