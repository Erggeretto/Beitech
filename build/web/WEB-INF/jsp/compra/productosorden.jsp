<%-- 
    Document   : productosOrden
    Created on : 30-oct-2017, 13:06:56
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
        <div ng-class="explicacionCss.explica"> Por favor seleccione los productos y la cantidad para comprar </div>
        <form action="compras/ordena.htm" method="post">
            <table>
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td >Productos registrados</td>
                    <td ng-bind="productos.length"></td>
                    <td><input type="submit" name="Shopping" /></td>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="productos" ng-style="{color:($odd?'blue':'green')}">
                    <td><label for="idProductos{{productos.idProducto}}">{{productos.nombre}}</label></td>
                    <td><input type="number" max="5" min="0" id="cantidad{{productos.idProducto}}" 
                               name="cantidad{{productos.idProducto}}" ng-model="cantidad" /></td>
                    <td><input type="number" max="5" id="precio{{productos.idProducto}}" 
                               name="precio{{productos.idProducto}}" ng-model="precio" /></td>
                    <td><input id="idProducto{{productos.idProducto}}" name="idProducto{{productos.idProducto}}" 
                               type="hidden" ng-model="compra/ordena{{productos.idProducto}}">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>