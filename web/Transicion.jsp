<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="3;url=<%= request.getAttribute("jspFinal")%>">
    </head>
    <body>

        <div class="ingredient" style="--quantity: 5">
            <div class="ingredient__image-container" style="--position: 1">
                <img src="img/icon_aguacate.png" alt="Aguacate">
            </div>
            <div class="ingredient__image-container" style="--position: 2">
                <img src="img/icon_burrito.png" alt="Burrito">
            </div>
            <div class="ingredient__image-container" style="--position: 3">
                <img src="img/icon_chile.png" alt="Chile">
            </div>
            <div class="ingredient__image-container" style="--position: 4">
                <img src="img/icon_chilli.png" alt="Jalapeño">
            </div>
            <div class="ingredient__image-container" style="--position: 5">
                <img src="img/icon_taco.png" alt="Taco">
            </div>
        </div>

        <div class="logo">
            <img class="logo__image" src="img/Logo.png" alt="Logo de la aplicación">
        </div>

        <style>
            * {
                box-sizing: border-box;
            }

            html {
                animation: output 0.8s both;
                animation-delay: 2.8s;
            }

            body {
                margin: 0;
                padding: 0;
                height: 100dvh;
                width: 100dvw;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }

            .logo {
                width: 15em;
                height: 15em;
                position: relative;
                animation: scale 2.5s linear both;
            }

            .logo__image {
                user-select: none;
                width: 100%;
                height: 100%;
                object-fit: cover;
                position: absolute;
            }

            .ingredient {
                position: absolute;
                width: 20em;
                height: 20em;
                animation: rotate 8s infinite;
            }

            .ingredient__image-container {
                --radius: 15em;
                position: absolute;
                width: 5em;
                height: 5em;
                top: 50%;
                left: 50%;
                margin: -2.5em;
                transform: rotate(calc(360deg / var(--quantity) * var(--position))) translateX(var(--radius));
            }

            .ingredient__image-container img {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            @keyframes scale {
                0% {
                    transform: scale(1);
                }
                25% {
                    transform: scale(1.1);
                }
                50% {
                    transform: scale(1.2);
                }
                75% {
                    transform: scale(1.1);
                }
                100% {
                    transform: scale(1.2);
                }
            }

            @keyframes rotate {
                0% {
                    transform: rotate(0);
                }
                100% {
                    transform: rotate(360deg);
                }
            }

            @keyframes output {
                0% {
                    opacity: 1;
                }
                100% {
                    opacity: 0;
                }
            }

            @media screen and (max-width: 600px) {
                .ingredient {
                    width: 8em;
                    height: 8em;
                }

                .ingredient__image-container {
                    --radius: 8em;
                    width: 4em;
                    height: 4em;
                    margin: -2em;
                }

                .logo {
                    width: 10em;
                    height: 10em;
                }
            }
        </style>

    </body>
</html>
