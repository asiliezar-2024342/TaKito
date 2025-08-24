<%-- 
    Document   : ResenaCliente
    Created on : Aug 20, 2025, 11:51:51 AM
    Author     : angel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ta'Kabron</title>
        <link rel="stylesheet" href="styles/Resena2.css">
    </head>
    <body>

        <section class="resena">

            <form class="resena__form" action="Controlador?menu=ResenaCliente&accion=Agregar" method="POST">
                <div>
                    <div class="resena__title-container">
                        <h2>¿Tienes una sugerencia o comentario?<h2>
                                <h3>Cuéntanos para mejorar tu experiencia.</h3>
                                <p>Para nosotros es importante que compartas tu opinión.</p>
                                </div>

                                <div class="resena__input-container" >
                                    <label>Tipo*</label>
                                    <div>
                                        <label>
                                            <input type="radio" name="txtTipo" value="Sucursal" required
                                                   ${resena.tipo == 'Sucursal' ? 'checked' : ''}> Sucursal
                                        </label>

                                        <label>
                                            <input type="radio" name="txtTipo" value="Empleado" 
                                                   ${resena.tipo == 'Empleado' ? 'checked' : ''}> Empleado
                                        </label>

                                        <label>
                                            <input type="radio" name="txtTipo" value="Producto" 
                                                   ${resena.tipo == 'Producto' ? 'checked' : ''}> Producto
                                        </label>
                                    </div>
                                </div>
                                <div class="resena__input-container">
                                    <label class="resena__input-label">
                                        Asunto*
                                        <input type="text" required placeholder="Falta de atención al cliente" name="txtTitulo">
                                    </label>
                                </div>
                                <div class="resena__input-container" >
                                    <label class="resena__input-label">
                                        Comentario
                                        <textarea name="txtComentario" style="resize: none;" placeholder="He recibido..."></textarea>
                                    </label>
                                </div>
                                <div class="resena__input-container" >
                                    <label>Calificación*</label>

                                    <div class="resena__rating">
                                        <input required type="radio" id="r5" name="txtCalificacion" value="5"
                                               ${resena.calificacionResena == 5 ? 'checked' : ''}/>
                                        <label for="r5">★</label>

                                        <input type="radio" id="r4" name="txtCalificacion" value="4"
                                               ${resena.calificacionResena == 4 ? 'checked' : ''}/>
                                        <label for="r4">★</label>

                                        <input type="radio" id="r3" name="txtCalificacion" value="3"
                                               ${resena.calificacionResena == 3 ? 'checked' : ''}/>
                                        <label for="r3">★</label>

                                        <input type="radio" id="r2" name="txtCalificacion" value="2"
                                               ${resena.calificacionResena == 2 ? 'checked' : ''}/>
                                        <label for="r2">★</label>

                                        <input type="radio" id="r1" name="txtCalificacion" value="1"
                                               ${resena.calificacionResena == 1 ? 'checked' : ''}/>
                                        <label for="r1">★</label>
                                    </div>
                                </div>
                                <div class="resena__input-container">
                                    <select name="txtSucursal" required>
                                        <c:forEach var="sucursal" items="${sucursales}">
                                            <option value="${sucursal.getCodigoSucursal()}" >${sucursal.getNombreSucursal()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                </div>

                                <div class="resena__input-container">
                                    <button type="submit" name="accion" value="Agregar" title="Enviar">
                                        <img src="img/taco-feliz.png" alt="Agregar">
                                        <strong>Enviar</strong>
                                        <img src="img/enviar.png" alt="enviar"/>
                                    </button>
                                </div>
                                </form>
                                </section>
                                <section class ="history">
                                    <h2>Historial de reseñas de usuario</h2>
                                    <div class="history__content">
                                        <div style="display:flex; flex-direction: column; align-items: center; justify-content: center;">
                                            <img src="Validar?codigoUsuario=${usuarioActual.getCodigoUsuario()}" loading="lazy" alt="Foto de usuario"/>
                                            <h3>${nombreCliente} ${apellidoCliente}</h3>
                                        </div>
                                        <div>
                                            <table>
                                                <thead>
                                                    <tr> 
                                                        <th>Título</th>
                                                        <th>Comentario</th>
                                                        <th>Calificación</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="resenaUsuario" items="${resenasUsuarios}">
                                                        <tr>

                                                            <td>${resenaUsuario.getTituloResena()}</td>
                                                            <td>${resenaUsuario.getComentarioResena()}</td>
                                                            <td>
                                                                <div class="comment__stars">
                                                                    <c:forEach var="i" begin="1" end="5">
                                                                        <c:choose>
                                                                            <c:when test="${i <= resenaUsuario.calificacionResena}">
                                                                                <span class="comment__star comment__star-filled">★</span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span class="comment__star">★</span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </div>
                                                            </td>

                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </section>

                                <section class="comments">
                                    <h2>Historial de reseñas de otros usuarios</h2>
                                    <c:forEach var="resenaOtro" items="${resenasOtros}">
                                        <div class="comments__comment">
                                            <img src="Validar?codigoUsuario=${resenaOtro.getCodigoUsuario()}" loading="lazy" alt="Foto de usuario"/>
                                            <div class="comments__content">

                                                <h4>${resenaOtro.getTituloResena()}</h4>
                                                <p>${resenaOtro.getComentarioResena()}</p>

                                                <div class="comment__stars">
                                                    <c:forEach var="i" begin="1" end="5">
                                                        <c:choose>
                                                            <c:when test="${i <= resenaOtro.calificacionResena}">
                                                                <span class="comment__star comment__star-filled">★</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="comment__star">★</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </div>

                                            </div>
                                        </div>
                                    </c:forEach>
                                </section>
                                </body>
                                </html>
