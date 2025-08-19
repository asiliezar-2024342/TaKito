<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cargando...</title>
        <meta http-equiv="refresh" content="4;url=<%= request.getAttribute("jspFinal")%>">
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
        <head>
            <title>Cargando...</title>
            <meta http-equiv="refresh" content="5;url=<%= request.getAttribute("jspFinal")%>">
        </head>
        <!DOCTYPE html>
        <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
            </head>
            <body>


                <div class="logo">
                    <img class="logo__image" src="img/Logo.png" alt="Logo de la aplicación">
                </div>

                <div>
                    <h1>¡Te lo juro, Ta'Kabron!</h1>
                </div>

                <style>

                    *{
                        box-sizing: border-box;
                    }


                    html{
                        animation: output 0.8s both;
                        animation-delay: 5s;
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

                        animation: change-color 2.5s both;
                        animation-delay: 0.5s;
                    }

                    .logo {
                        width: 15em;
                        height: 15em;
                        margin: 0;
                        top: 0;
                        position: relative;
                        animation: move-to-bottom 2.5s forwards;

                    }

                    .logo__image{
                        user-select: none;
                        width: 100%;
                        height: 100%;
                        object-fit: cover;
                    }

                    h1{
                        font-size: 2.5em;
                        color: transparent;
                        margin: 0;
                        position: relative;
                        top: 0;
                        animation: title-input 1s forwards;
                        animation-delay: 2.5s;
                    }

                    @keyframes move-to-bottom {
                        0% {
                            transform: translateY(-290%);
                        }
                        50% {
                            transform: translateY(100%) scaleY(0.2);
                        }

                        100%{
                            transform: translateY(0) scaleY(1);
                        }
                    }

                    @keyframes change-color {
                        0% {
                            background-color: #fff;
                        }
                        50% {
                            background-color: #FEC201;
                        }

                        100% {
                            background-color: #fff;
                        }
                    }

                    @keyframes title-input {
                        100% {
                            color: #0077B6;
                            scale: 1.2;
                        }
                    }

                    @keyframes output {
                        0%{
                            opacity: 1;
                        }
                        100%{
                            opacity: 0;
                        }
                    }


                </style>

            </body>
        </html>
    </html>
