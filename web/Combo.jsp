<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Ta'Kabron</title>
        <link rel="stylesheet" href="styles/CRUD.css">
    </head>
    <body>
        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Precio</th>
                            <th>Categoría</th>
                            <th>Estado</th>
                            <th>Foto</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="combo" items="${combos}">
                            <tr>
                                <td>${combo.getCodigoCombo()}</td>
                                <td>${combo.getNombreCombo()}</td>
                                <td>${combo.getDescripcionCombo()}</td>
                                <td>${combo.getPrecioCombo()}</td>
                                <td>${combo.getCategoria()}</td>
                                <td>${combo.getEstado()}</td>
                                <td>
                                    <img src="Validar?codigoCombo=${combo.getCodigoCombo()}" width="110" height="150" loading="lazy" alt="Foto de combo"/>
                                </td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=Combo&accion=Editar&codigoCombo=${combo.getCodigoCombo()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=Combo&accion=ConfirmarEliminar&codigoCombo=${combo.getCodigoCombo()}">
                                        <img src="img/Eliminar.png" alt="Eliminar">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="form-section">
                <div class="form-layout">
                    <div class="form-container">
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=Combo" method="POST" enctype="multipart/form-data">
                            <div>
                                <div class="form-row">
                                    <img src="img/titulo.png" alt="Nombre">
                                    <label><strong>Nombre: </strong></label>
                                    <input type="text" value="${combo.getNombreCombo()}" name="txtNombreCombo">
                                </div>
                                <div class="form-row">
                                    <img src="img/comentario.png" alt="Descripcion">
                                    <label><strong>Descripción: </strong></label>
                                    <input type="text" value="${combo.getDescripcionCombo()}" name="txtDescripcion">
                                </div>
                                <div class="form-row">
                                    <img src="img/precio.png" alt="Comentario">
                                    <label><strong>Precio:  </strong></label>
                                    <input type="number" step="0.01" placeholder="Ingresa un número decimal." value="${combo.getPrecioCombo()}" name="txtPrecioCombo">
                                </div>
                                <div class="form-row">
                                    <img src="img/clasificacion.png" alt="Categoria">
                                    <label><strong>Categoría: </strong></label>
                                    <select name="txtCategoria" class="select">
                                        <option value="Familiar" ${combo.categoria == 'Familiar' ? 'selected' : ''}>Familiar</option>
                                        <option value="Duo" ${combo.categoria == 'Duo' ? 'selected' : ''}>Duo</option>
                                        <option value="Individual" ${combo.categoria == 'Individual' ? 'selected' : ''}>Individual</option>
                                        <option value="Unitario" ${combo.categoria == 'Unitario' ? 'selected' : ''}>Unitaria</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <img src="img/icon-estado.jpg" alt="Estado">
                                    <label><strong>Estado: </strong></label>
                                    <input type="text" value="${combo.getEstado()}" name="txtEstado" value="Activo" readonly>
                                </div>
                                <div class="form-row">
                                    <img src="img/camara.png" alt="Foto">
                                    <label><strong>Foto: </strong></label>
                                    <input type="file" value="" name="txtFoto" accept="image/*">
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit" class="btn-icon Agregar" name="accion" value="Agregar" title="Agregar">
                                    <img src="img/Agregar.png" alt="Agregar">
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>



        <section class="crud">
            <div class="crud__table">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Cantidad</th>
                            <th>Combo</th>
                            <th>Producto</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detalleCombo" items="${detalleCombos}">
                            <tr>
                                <td>${detalleCombo.getCodigoDetalleCombo()}</td>
                                <td>${detalleCombo.getCantidad()}</td>
                                <td>${detalleCombo.getCodigoCombo()}</td>
                                <td>${detalleCombo.getCodigoProducto()}</td>
                                <td>
                                    <a class="btn-icon Editar" title="Editar" href="Controlador?menu=DetalleCombo&accion=Editar&codigoDetalleCombo=${detalleCombo.getCodigoDetalleCombo()}">
                                        <img src="img/Actualizar.png" alt="Editar">
                                    </a>
                                    <a class="btn-icon Eliminar" title="Eliminar"  href="Controlador?menu=DetalleCombo&accion=ConfirmarEliminar&codigoDetalleCombo=${detalleCombo.getCodigoDetalleCombo()}">
                                        <img src="img/Eliminar.png" alt="Eliminar">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="form-section">
                <div class="form-layout">
                    <div class="form-container">
                        <form class="flex-column" style="flex-direction: row;" action="Controlador?menu=DetalleCombo" method="POST">
                            <div>
                                <div class="form-row">
                                    <img src="img/cantidad.png" alt="Cantidad">
                                    <label><strong>Cantidad: </strong></label>
                                    <input type="number" value="${detalleCombo.getCantidad()}" name="txtCantidad" required>
                                </div>
                                <div class="form-row">
                                    <img src="img/combo.png" alt="Combo">
                                    <label><strong>Combo: </strong></label>
                                    <input type="number" value="${detalleCombo.getCodigoCombo()}" name="txtCombo" required>
                                </div>
                                <div class="form-row">
                                    <img src="img/producto.png" alt="Producto">
                                    <label><strong>Producto:  </strong></label>
                                    <input type="number" value="${detalleCombo.getCodigoProducto()}" name="txtProducto" required>
                                </div>
                            </div>
                            <div class="flex-column">
                                <button type="submit" class="btn-icon Actualizar" name="accion" value="Actualizar" title="Actualizar">
                                    <img src="img/Actualizar.png" alt="Actualizar">
                                </button>
                                <button type="submit" class="btn-icon Agregar" name="accion" value="Agregar" title="Agregar">
                                    <img src="img/Agregar.png" alt="Agregar">
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>