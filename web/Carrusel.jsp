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
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="styles/Carrusel.css"/> 
    </head>
    <body>
         <!-- CARRUSEL -->
    <section class="carrusel">
  <!-- Botones ocultos -->
  <input type="radio" name="slider" id="slide1" checked>
  <input type="radio" name="slider" id="slide2">
  <input type="radio" name="slider" id="slide3">
  <input type="radio" name="slider" id="slide4">
  <input type="radio" name="slider" id="slide5">
  <input type="radio" name="slider" id="slide6">

  <!-- Contenedor de imágenes -->
  <div class="carrusel-container">
    <div class="carrusel-item"><img src="img/slide1.jpg" alt="Imagen 1"></div>
    <div class="carrusel-item"><img src="img/slide2.jpg" alt="Imagen 2"></div>
    <div class="carrusel-item"><img src="img/slide3.jpg" alt="Imagen 3"></div>
    <div class="carrusel-item"><img src="img/slide4.jpg" alt="Imagen 4"></div>
    <div class="carrusel-item"><img src="img/slide5.jpg" alt="Imagen 5"></div>
    <div class="carrusel-item"><img src="img/slide6.jpg" alt="Imagen 6"></div>
  </div>

  <!-- Botones de navegación (puntos) -->
  <div class="navegacion">
    <label for="slide1"></label>
    <label for="slide2"></label>
    <label for="slide3"></label>
    <label for="slide4"></label>
    <label for="slide5"></label>
    <label for="slide6"></label>
  </div>
</section>

        



    </body>
</html>
