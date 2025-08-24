
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ta'Kabron</title>
        <link rel="stylesheet" href="styles/Menu.css">
    </head>
    <body>

        <div class="title">
            <picture class="title__img">
                <img src="img/menuLogo.png" alt="Logo"/>
            </picture>
            <h1 class="title__title">NUESTRO MENÚ</h1>
        </div>

        <section class="combo">

            <ul class="combo__filters">
                <div class="combo__filter">
                    <a href="">Familiar</a>
                </div>
                <div class="combo__filter">
                    <a href="">Duo</a>
                </div>
                <div class="combo__filter">
                    <a href="">Individual</a>
                </div>
                <div class="combo__filter">
                    <a href="">Unitario</a>
                </div>
            </ul>

            <div class="combo__list">
                <c:forEach var="combo" items="${combos}">
                    <div class="combo__item">
                        <picture class = "combo__img">
                            <img src="Validar?codigoCombo=${combo.getCodigoCombo()}" loading="lazy" alt="Combo"/>
                        </picture>
                        <h3>${combo.getNombreCombo()}</h3>
                        <p>${combo.getDescripcionCombo()}</p>
                    </div>
                </c:forEach>
            </div>
        </section> 


    </body>
</html>
