<%-- 
    Document   : index
    Created on : 14 jul 2025, 10:40:58
    Author     : Javier Paredes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
        
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
                    <h2>Iniciar Sesión</h2>
                </div>
                <form class="form" action="Validar" method="POST">
                    <div class="input-group">
                        <label>Correo</label>
                        <input type="text" name="txtUser" required>
                    </div>
                    <div class="input-group">
                        <label>Contraseña</label>
                        <input type="password" name="txtPass" required>
                    </div>
                    <button type="submit" class="btn-primary" value="Ingresar" name="accion">Iniciar Sesión</button>
                </form>
                <div class="form-footer">
                    <p>¿No tienes cuenta? <a href="registro.jsp">Registrarse</a></p>
                </div>
            </div>
        </div>
    </body>
</html>