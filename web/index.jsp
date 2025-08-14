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
        <title>Access Page</title>
        
        <link rel="stylesheet" href="./styles/index.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    </head>
    
    <body>
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

            <img src="./img/Logo.png" alt="Logo Ta'Kabrón" class="logo-flotante" />
            <img src="./img/TaKabronTitle.png" alt="Titulo Ta'Kabrón" class="titulo-flotante">

            <div class="barra-clara">
                
            </div>
        </div>
        
        <div class="container mt-4 col-lg-4">
            <div class="caja-login">
                <div class="letras">
                    <div class="card-body">
                        <form class="form-sign" action="Validar" method="POST">
                            <div class="form-group text-center">
                                <h3>Iniciar Sesión</h3><br>
                            </div>

                            <div class="form-group">
                                <label class="atributo"><strong>Usuario</strong></label>
                                <input type="text" name="txtUser" class="form-control">
                            </div>

                            <div class="form-group">
                                <br><label class="atributo"><strong>Contraseña</strong></label>
                                <input type="password" name="txtPass" class="form-control">
                            </div><br>

                            <input type="submit" value="Ingresar" name="accion" class="btn btn-primary btn-block">
                            <br><br>
                            <div class="text-center">
                                <label><a href="#">¿No tienes cuenta? Registrate aquí <-</a></label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>