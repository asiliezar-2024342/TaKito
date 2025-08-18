<%-- 
    Document   : registro.jsp
    Created on : 18 ago 2025, 00:01:32
    Author     : Javier Paredes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrate</title>
        
        <link rel="stylesheet" href="./styles/index.css">
    </head>
    
    <body>
        <div class="decorative-elements">
            <div class="food-icon burrito-1"></div>
            <div class="food-icon burrito-2"></div>
            <div class="food-icon burrito-3"></div>
            <div class="food-icon taco-1"></div>
            <div class="food-icon taco-2"></div>
            <div class="food-icon taco-3"></div>
            <div class="food-icon aguacate-1"></div>
            <div class="food-icon aguacate-2"></div>
            <div class="food-icon aguacate-3"></div>
        </div>

        <div class="container">
            <div class="form-container">
                <div class="form-header">
                    <h2>Registro</h2>
                </div>
                <form class="form" action="Controlador?menu=Usuario" method="POST">
                    <div class="input-group">
                        <label>Correo</label>
                        <input type="email" value="" name="txtCorreoUsuario" required>
                    </div>
                    <div class="input-group">
                        <label>Contraseña</label>
                        <input type="password" value="" name="txtContrasenaUsuario" required>
                    </div>
                    <button type="submit" name="accion" value="Agregar" class="btn-primary">Registrarse</button>
                </form>
                <div class="form-footer">
                    <p>¿Ya tienes cuenta? <a href="index.jsp">Iniciar Sesión</a></p>
                </div>
            </div>
        </div>
    </body>
</html>