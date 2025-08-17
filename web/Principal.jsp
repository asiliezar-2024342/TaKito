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
                        <a class="menu__item" href="#">
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
                            <a href="#">Sucursal</a>
                            <a href="#">Cliente</a>
                            <a href="#">Empleado</a>
                            <a href="#">Usuario</a>
                            <a href="#">Combo</a>
                            <a href="#">Producto</a>
                            <a href="#">Pedido</a>
                            <a href="#">Factura</a>
                            <a href="#">Promoción</a>
                            <a href="#">Reseña</a>
                            <a href="#">Bitácora</a>
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
    
     <!-- CARRUSEL -->
    <section>
        <div class="container h-100 d-flex align-items-center justify-content-center">
            <h2>Carrusel</h2>
        </div>
    </section>
    
     <!-- QUIENES SOMOS -->
     <section>
        <div class="container h-100 d-flex align-items-center justify-content-center">
            <h2>Quiénes Somos</h2>
        </div>
    </section>
     
      <!-- MISION Y VISION -->
     <section>
        <div class="container h-100 d-flex align-items-center justify-content-center">
            <h2>Visión y Misión</h2>
        </div>
    </section>
      
    <!-- CONTACTANOS -->
    <section style="background-color: #fff3dc">
        <div class="container h-100 align-items-center justify-content-center">
            <h1 class="text-center mb-4">Contáctanos</h1>
            <div class="row align-items-center shadow" style="background-color: #FFF">

                <div class="col-md-6 text-center mt-4 mt-md-0" style="border-bottom: 2px solid #000">
                    <h1>¿Tienes dudas, sugerencias o quieres hacer un pedido especial?</h1>
                    <h4>Estamos aquí para ayudarte. Completa el formulario y compártenos tu experiancia con nosotros.</h4> 
                    <h4>¡Tu mensaje es importante para seguir mejorando! <i class="fa-solid fa-handshake"></i></h4>

                    <img src="img/Logo.png" class="img-fluid rounded" alt="Logo" style="max-width: 40%; height: auto;">
                    <img src="img/TaKabronTitle.png" alt="Logo" style="max-width: 45%; height: auto;">
                </div>
                
                
                <div class="col-md-6 text-right" style="border-left: 2px solid #000">
                    <h2><i class="fa-solid fa-building text-secondary"></i> Nombre de la empresa</h2>
                    <h4><i class="fas fa-pepper-hot mb-2"></i> Ta-Kabrón <i class="fa-solid fa-hand-point-left text-muted"></i></h4>
                    <h2><i class="fas fa-map-marker-alt text-danger"></i> Dirección</h2>
                    <h4>3ra Avenida 5-9, Zona 1, Ciudad de Guatemala <i class="fa-solid fa-hand-point-left text-muted mb-2"></i></h4>
                    <h2><i class="fa-solid fa-phone text-success"></i> Teléfono</h2>
                    <h4>+502 1234-5678 <i class="fa-solid fa-hand-point-left text-muted mb-2"></i></h4>
                    <h2><i class="fa-solid fa-envelope"></i> Correo Electrónico</h2>
                    <h4>correo@takabron.com <i class="fa-solid fa-hand-point-left text-muted"></i></h4>
                    <h4>pedidos@takabron.com <i class="fa-solid fa-hand-point-left text-muted"></i></h4> 
                </div>
                
            </div>
        </div>
    </section>
       
      <!-- FOOTER -->
     <footer>
        <div class="container text-center py-3">
           <p>&copy; 2025 Ta'Kabrón</p>
        </div>
    </footer>

</body>
</html>
