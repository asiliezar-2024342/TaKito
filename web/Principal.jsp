<%-- 
    Document   : index
    Created on : 14 jul 2025, 00:56:07
    Author     : Wilson Del Cid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <button class="header-buttons__btn" title="Usuario">
                        <img class="header-buttons__icon" src="./img/usuario.png" alt="Usuario" />
                        <span class="header-buttons__user">juan@example.com</span>
                    </button>
                    <button class="header-buttons__btn header-buttons__btn--cart" title="Carrito">
                        <img class="header-buttons__icon" src="./img/carrito.png" alt="Carrito" />
                        <span class="header-buttons__badge">1</span>
                    </button>
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
                                <a href="Controlador?menu=Sucursal&accion=Listar" target="myFrame" >Sucursal</a>
                                <a href="Controlador?menu=Cliente&accion=Listar" target="myFrame" >Cliente</a>
                                <a href="Controlador?menu=Empleado&accion=Listar" target="myFrame" >Empleado</a>
                                <a href="Controlador?menu=Usuario&accion=Listar" target="myFrame" >Usuario</a>
                                <a href="Controlador?menu=Combo&accion=Listar" target="myFrame" >Combo</a>
                                <a href="Controlador?menu=Producto&accion=Listar" target="myFrame" >Producto</a>
                                <a href="Controlador?menu=Pedido&accion=Listar" target="myFrame" >Pedido</a>
                                <a href="Controlador?menu=Factura&accion=Listar" target="myFrame" >Factura</a>
                                <a href="Controlador?menu=Promocion&accion=Listar" target="myFrame" >Promoción</a>
                                <a href="Controlador?menu=Resena&accion=Mover" target="myFrame">Reseña</a>
                                <a href="Controlador?menu=Bitacora&accion=Listar" target="myFrame">Bitácora</a>
                            </div>
                        </li>
                        <li>
                            <a class="menu__item" href="#">
                                <img class="icono" src="./img/Menu.png" alt="Inicio"/>
                                Menú
                            </a>
                        </li>
                        <li>
                            <a class="menu__item" href="#">
                                <img class="icono" src="./img/Promociones.png" alt="Inicio"/>
                                Promociones
                            </a>
                        </li>
                        <li>
                            <a class="menu__item" href="#">
                                <img class="icono" src="./img/Ubicacion.png" alt="Inicio"/>
                                Ubicaciones
                            </a>
                        </li>
                        <li>
                            <a class="menu__item" href="#">
                                <img class="icono" src="./img/Pedido.png" alt="Inicio"/>
                                Hacer Pedido
                            </a>
                        </li>
                    </ul>
                </div>
            </section>
        </header>

        <div class="frame">
            <iframe style="height: 100%; width: 100%; box-sizing: border-box;" src="PrincipalContenido.jsp" class="frame__iframe" name="myFrame"></iframe>
        </div>



    </body>
</html>
