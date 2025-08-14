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

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <link rel="stylesheet" href="styles/Principal.css"/> 
</head>
<body>
    <!-- HEADER -->
    <div class="barra-container position-relative">
  
        <div class="barra-naranja d-flex justify-content-end align-items-center">
            <div class="iconos-derecha d-flex align-items-center">
              <i class="fas fa-user-circle"></i>
              <div class="position-relative ml-3">
                <i class="fas fa-shopping-cart"></i>
                <span class="badge">0</span>
              </div>
            </div>
        </div>

        <img src="img/Imagen.png" alt="Logo Ta'Kabrón" class="logo-flotante" />

        <div class="barra-clara">
            <nav class="nav-right ml-auto d-flex">
              <a href="#"><i class="fas fa-home"></i> Inicio</a>
              <a href="#"><i class="fas fa-pepper-hot"></i> Ta'Kabrón</a>
              <a href="#"><i class="fas fa-utensils"></i> Ordenar</a>
              <a href="#"><i class="fas fa-gift"></i> Promociones</a>
            </nav>
        </div>
    </div>
    
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

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
