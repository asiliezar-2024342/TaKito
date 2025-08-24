<%-- 
    Document   : index
    Created on : 14 jul 2025, 00:56:07
    Author     : Wilson Del Cid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <title>Ta'Kabrón</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="styles/Principal.css"/> 
    </head>
    <body>
        <!-- HEADER -->
        <header>
            <section>
                <div class="header-buttons">
                    <div class="header-buttons__dropuser">
                        <button class="header-buttons__btn" title="Usuario">
                            <img class="header-buttons__icon" src="Validar?codigoUsuario=${usuarioActual.getCodigoUsuario()}" width="200" height="200" alt="Foto de usuario" />
                            <span class="header-buttons__user">${nombreCliente} ${apellidoCliente}</span>
                        </button>

                        <div class="header-buttons__dropuser-content">
                            <a class="" href="#">
                                <img src="Validar?codigoUsuario=${usuarioActual.getCodigoUsuario()}" alt="100" width="100">
                            </a>

                            <a class="" href="#">
                                Correo: ${usuarioActual.getCorreoUsuario()}
                            </a>

                            <a class="" href="#">
                                ${usuarioActual.getCargo()}
                            </a>


                            <form action="Validar" method="POST">
                                <button name="accion" name="Salir" class="header-buttons__exitbtn">Salir</button>
                            </form>
                            <a href="#"></a>
                        </div>
                    </div>
                    
                    <button class="header-buttons__btn header-buttons__btn--cart" title="Carrito">
                        <img class="header-buttons__icon" src="./img/carrito.png" alt="Carrito" />
                        <span class="header-buttons__badge">1</span>
                    </button>
                </div>
    <head>
        <meta charset="UTF-8" />
        <title>Ta'Kabrón</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="styles/Principal.css"/> 
    </head>
    <body>
        <input class="cart__toggle" type="checkbox" id="cart-toggle" />
        
        <!-- HEADER -->
        <header>
            <section>
                <div class="header-buttons">
                    <button class="header-buttons__btn" title="Usuario">
                        <img class="header-buttons__icon" src="./img/usuario.png" alt="Usuario" />
                        <span class="header-buttons__user">juan@example.com</span>
                    </button>

                    <label for="cart-toggle" class="header-buttons__btn header-buttons__btn--cart" title="Carrito">
                        <img class="header-buttons__icon" src="./img/carrito.png" alt="Carrito" />
                        <span class="header-buttons__badge">
                            <c:choose>
                                <c:when test="${sessionScope.carrito != null}">
                                    <c:set var="totalItems" value="0" />
                                    <c:forEach var="item" items="${sessionScope.carrito}">
                                        <c:set var="totalItems" value="${totalItems + item.value.cantidad}" />
                                    </c:forEach>
                                    ${totalItems}
                                </c:when>
                                <c:otherwise>0</c:otherwise>
                            </c:choose>
                        </span>
                    </label>
                </div>
    <head>
        <meta charset="UTF-8" />
        <title>Ta'Kabrón</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="styles/Principal.css"/> 
    </head>
    <body>
        <input class="cart__toggle" type="checkbox" id="cart-toggle" />
        
        <!-- HEADER -->
        <header>
            <section>
                <div class="header-buttons">
                    <button class="header-buttons__btn" title="Usuario">
                        <img class="header-buttons__icon" src="./img/usuario.png" alt="Usuario" />
                        <span class="header-buttons__user">juan@example.com</span>
                    </button>

                    <label for="cart-toggle" class="header-buttons__btn header-buttons__btn--cart" title="Carrito">
                        <img class="header-buttons__icon" src="./img/carrito.png" alt="Carrito" />
                        <span class="header-buttons__badge">
                            <c:choose>
                                <c:when test="${sessionScope.carrito != null}">
                                    <c:set var="totalItems" value="0" />
                                    <c:forEach var="item" items="${sessionScope.carrito}">
                                        <c:set var="totalItems" value="${totalItems + item.value.cantidad}" />
                                    </c:forEach>
                                    ${totalItems}
                                </c:when>
                                <c:otherwise>0</c:otherwise>
                            </c:choose>
                        </span>
                    </label>
                </div>

                <div class="menu">
                    <input class="menu__toggle" type="checkbox" id="menu-toggle" />
                    <label class="menu__btn" for="menu-toggle">
                        <span class="menu__btn-icon"></span>
                    </label>

                    <div class="main-title">
                        <h1 class="main-title__text">Ta'Kabrón</h1>
                    </div>

                    <ul class="menu__box">
                        <li><img class="menu__logo" src="./img/Logo.png" alt="Logo" /></li>
                        <li>
                            <a class="menu__item" href="Controlador?menu=PrincipalContenido" target="myFrame">
                                <img class="icono" src="./img/Inicio.png" alt="Inicio"/>
                                Inicio
                            </a>
                        </li>
                        <li class="menu__item--dropdown">
                            <a class="menu__item" href="#">
                                <img class="icono" src="./img/Gestionar.png" alt="Gestionar">
                                Gestionar
                            </a>
                            <div class="dropdown__content">
                                <a href="Controlador?menu=Sucursal&accion=Mover" target="myFrame" >Sucursal</a>
                                <a href="Controlador?menu=Cliente&accion=Mover" target="myFrame" >Cliente</a>
                                <a href="Controlador?menu=Empleado&accion=Mover" target="myFrame" >Empleado</a>
                                <a href="Controlador?menu=Usuario&accion=Mover" target="myFrame" >Usuario</a>
                                <a href="Controlador?menu=Combo&accion=Mover" target="myFrame" >Combo</a>
                                <a href="Controlador?menu=Producto&accion=Mover" target="myFrame" >Producto</a>
                                <a href="Controlador?menu=Pedido&accion=Mover" target="myFrame" >Pedido</a>
                                <a href="Controlador?menu=Factura&accion=Mover" target="myFrame" >Factura</a>
                                <a href="Controlador?menu=Promocion&accion=Mover" target="myFrame" >Promoción</a>
                                <a href="Controlador?menu=Resena&accion=Mover" target="myFrame">Reseña</a>
                                <a href="Controlador?menu=Bitacora&accion=Mover" target="myFrame">Bitácora</a>
                            </div>
                        </li>
                        <li>
                            <a class="menu__item" href="#">
                                <img class="icono" src="./img/Menu.png" alt="Inicio"/>
                                Menú
                            </a>
                        </li>
                        <li>
                            <a class="menu__item" href="Controlador?menu=Promociones&accion=Mover" target="myFrame">
                                <img class="icono" src="./img/Promociones.png" alt="Inicio"/>
                                Promociones
                            </a>
                        </li>
                        <li>
                            <a class="menu__item" href="Controlador?menu=Ubicacion&accion=Mover" target="myFrame">
                                <img class="icono" src="./img/Ubicacion.png" alt="Inicio"/>
                                Ubicaciones
                            </a>
                        </li>
                        <li>
                            <a class="menu__item" href="Controlador?menu=HacerPedido&accion=Mover" target="myFrame">
                                <img class="icono" src="./img/Pedido.png" alt="Inicio"/>
                                Hacer Pedido
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="cart__box">
                    <div class="cart__header">
                        <img src="img/carrito.png" alt="Carrito" style="width: 30px; height: 30px; padding-right:5px;">Mi Carrito
                    </div>

                    <div id="cart-content">
                        <c:choose>
                            <c:when test="${not empty sessionScope.carrito}">
                                <c:set var="total" value="0" />
                                <c:set var="totalItems" value="0" />

                                <c:forEach var="item" items="${sessionScope.carrito}">
                                    <c:set var="detalle" value="${item.value}" />
                                    <c:set var="total" value="${total + detalle.subTotal}" />
                                    <c:set var="totalItems" value="${totalItems + detalle.cantidad}" />

                                    <div class="cart-item" data-combo="${item.key}">
                                        <div class="cart-item-name">
                                            <c:choose>
                                                <c:when test="${not empty sessionScope.combosInfo[item.key]}">
                                                    ${sessionScope.combosInfo[item.key].nombreCombo}
                                                </c:when>
                                                <c:otherwise>
                                                    Combo #${item.key}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <div class="cart-item-details">
                                            Cantidad: <strong>${detalle.cantidad}</strong><br>
                                            Subtotal: <strong>Q${detalle.subTotal}</strong>
                                        </div>

                                        <div class="cart-item-controls">
                                            <form method="post" action="Controlador" target="_top">
                                                <input type="hidden" name="menu" value="Carrito"/>
                                                <input type="hidden" name="accion" value="Eliminar"/>
                                                <input type="hidden" name="codigoCombo" value="${item.key}"/>
                                                <button type="submit" class="cart-btn cart-btn-remove" onclick="return confirm('¿Eliminar este producto?')">
                                                    <img src="./img/eliminarCarrito.png" alt="Eliminar" style="width: 20px; height: 20px;"/>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>

                                <div class="cart-total">
                                    <strong>Total (${totalItems} productos): Q${total}</strong>
                                </div>

                                <div class="cart-actions">
                                    <form method="post" action="Controlador">
                                        <input type="hidden" name="menu" value="Carrito"/>
                                        <input type="hidden" name="accion" value="Vaciar"/>
                                        <button type="submit" class="cart-action-btn btn-clear" onclick="return confirm('¿Vaciar todo el carrito?')">
                                            Vaciar Carrito
                                        </button>
                                    </form>

                                    <form method="post" action="Controlador" style="display:inline;">
                                        <input type="hidden" name="menu" value="Carrito"/>
                                        <input type="hidden" name="accion" value="HacerPedido"/>
                                        <button type="submit" class="cart-action-btn btn-checkout">
                                            Hacer Pedido
                                        </button>
                                    </form>
                                </div>

                            </c:when>
                            <c:otherwise>
                                <div class="cart-empty">
                                    <h3>Tu carrito está vacío</h3>
                                    <p>¡Agrega algunos deliciosos combos desde el menú!</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </section>
        </header>

        <div class="frame" style="height: 100dvh; width: 100dvw;">
            <iframe style="height: 100%; width: 100%; box-sizing: border-box; border:none;" src="PrincipalContenido.jsp" class="frame__iframe" name="myFrame"></iframe>
        </div>
        
        <footer class="site-footer">
            <div class="footer-container">
                <div class="footer-logo">
                    <img src="img/Logo.png" alt="Logo Ta'Kabron">
                    <span class="site-name">Ta'Kabron</span>
                </div>
                <nav class="footer-links">
                  <a href="#">Acerca de</a>
                  <a href="#">Menú</a>
                  <a href="#">Contacto</a>
                  <a href="#">FAQ</a>
                </nav>
            </div>
            <div class="footer-bottom">
                <p>&copy; 2025 Ta'Kabron. Todos los derechos reservados.</p>
            </div>
        </footer>
    </body>
</html>