<%-- 
    Document   : PrincipalContent
    Created on : Aug 18, 2025, 10:11:22 PM
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ta'Kabron</title>
        <link rel="stylesheet" href="styles/Principal.css"/> 
        <link rel="stylesheet" href="styles/contactanos.css"/> 
        <link rel="stylesheet" href="styles/visionMision.css"/> 
    </head>
    <body>

        <!-- CARRUSEL -->
        <div class="carusel" style="height: 75dvh; width: 100dvw;">
            <iframe style="height: 100%; width: 100%; box-sizing: border-box; border:none;" src="Carrusel.jsp" name="myFrame"></iframe>
        </div>

        <!-- QUIENES SOMOS -->
        <section id="quienes-somos" class="quienes-somos">
          <div class="qs-container">

            <!-- Imagen / logo -->
            <div class="qs-imagen">
              <img src="img/Logo.png" alt="Logo Ta'Kabrón">
            </div>

            <!-- Texto -->
            <div class="qs-texto">
              <h2><span class="icono"></span> ¿Quiénes Somos?</h2>

              <p>
                En <strong>Ta’Kabrón</strong> somos más que un restaurante, somos una familia apasionada 
                por compartir la auténtica esencia de la comida mexicana. Nuestro objetivo es ofrecerte 
                una experiencia única llena de sabor, tradición y un ambiente acogedor.
              </p>

              <p>
                Desde nuestros inicios hemos buscado innovar en cada platillo, respetando las recetas 
                tradicionales pero agregando nuestro toque especial que nos hace 
                <em>¡Inconfundibles!</em>
              </p>

              <p>
                Para nosotros lo más importante eres tú, y por eso cada día trabajamos con dedicación y 
                entusiasmo para brindarte calidad, sabor y alegría en cada visita.
              </p>

              <blockquote>
                <p>"No vendemos solo comida, ofrecemos momentos felices."</p>
                <footer>Restaurante Ta’Kabrón</footer>
              </blockquote>
            </div>

          </div>
        </section>


        <!-- MISION Y VISION -->
        <section>
            <div class="containermi">
                <h2 class="titulo-seccion">Visión y Misión</h2>

                <div class="feature">
                    <h3>Visión</h3>
                    <img class="image" src="./img/vision.png" alt="Calidad">
                    <p class="texto">
                        Ser reconocidos como el restaurante mexicano líder en Guatemala,
                        distinguiéndonos por el sabor auténtico de nuestros platillos,
                        la innovación constante y el compromiso con la comunidad y el medio ambiente.
                    </p>
                </div>

                <div class="feature">
                    <h3>Misión</h3>
                    <img class="image" src="./img/mision.png" alt="Calidad">
                    <p class="texto">
                        Brindar a nuestros clientes una experiencia única de comida mexicana,
                        ofreciendo productos frescos y de calidad, con un servicio cercano y amable
                        que refleje nuestra pasión por la gastronomía y el trabajo en equipo.
                    </p>
                </div>
            </div>
        </section>

        <!-- CONTACTANOS -->
        <section class="contact">
            <div class="contact__container">
                <h1>¿Tienes dudas?</h1>
                <h2>Contáctanos</h2>
                <div class="contact__row">
                    <div class="contact__1">
                        <a href="https://web.whatsapp.com" target="_blank">
                            <img src="img/wha-icon.png" alt="alt"/>
                        </a>
                    </div>
                    <div class="contact__2">
                        <a href="https://www.facebook.com" target="_blank">
                            <img src="img/face-icon.png" alt="alt"/>
                        </a>
                    </div>
                    <div class="contact__3">
                        <a href="https://www.instagram.com" target="_blank">
                            <img src="img/ig-icon.png" alt="alt"/>
                        </a>
                    </div> 
                </div>
                <div class="contact__info">
                    <div class="contact__horario">
                        <h3>Horarios de atención</h3>
                        <p>Lunes - Domingo</p>
                        <p>Atendemos las 24hrs. ¡Que esperas para disfrutar!</p>
                    </div>
                    <div class="contact__direccion">
                        <h3>Dirección</h3>
                        <p>2da Avenida zona 18 3ra calle, Ciudad de Guatemala</p>
                        <p>¿Que esperas para visitarnos?</p>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>