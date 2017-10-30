<%-- 
    Document   : productos
    Created on : 30-oct-2017, 13:10:06
    Author     : Erggeretto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
        <script src="js/script.js"></script>
	<link href="css/style.css" rel="stylesheet" />
        <title>Listado de Productos</title>
    </head>
    <body ng-controller="IndiceControlador">
        <ng-include src="urlHead"></ng-include>
        <div ng-class="titulosCss.titulo"> Listado de Productos </div>
        <div ng-class="explicacionCss.explica"> Por favor escriba los precios para asignar </div>
        <form action="compras/productoorden.htm" method="post">
            <table>
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Precio Unidad</th>
                    <th>Accion</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td colspan="3">Productos registrados</td>
                    <td ng-bind="productos.length"></td>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="productos" ng-style="{color:($odd?'blue':'green')}">
                    <td><label for="idProductos{{productos.idProductos}}">{{productos.nombre}}</label></td>
                    <td><input id="idProductos{{productos.idProductos}}" name="idProductos{{productos.idProductos}}" 
                               type="text" ng-model="costo/precio{{productos.name}}">
                        <td><input id="idProductos{{productos.price}}" name="idProductos{{productos.price}}" 
                               type="text" ng-model="costo/precio{{productos.price}}">
                        <button ng-click="selecciona(productos.idProductos)">Asignar</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>
