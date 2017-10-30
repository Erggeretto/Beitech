<%-- 
    Document   : listadoorden
    Created on : 30-oct-2017, 13:12:59
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
        <title>Resumen Orden de Compra</title>
    </head>
    <body ng-controller="IndiceControlador">
        <ng-include src="urlHead"></ng-include>
        <div ng-class="titulosCss.titulo"> Resumen Orden de Compra para {{cliente.name}}</div>
        <div ng-class="explicacionCss.explica"> Despliegue de ordenes por fecha </div>
        <table>
            <thead>
            <tr>
                <th>Orden</th>
                <th>Precio</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <td >Compras registradas</td>
                <td ng-bind="ordenDetalle.length"></td>
            </tr>
            </tfoot>
            <tbody>
            <tr ng-repeat="ordenDetalle" ng-style="{color:($odd?'blue':'green')}">
                <td><label for="idProductos{{ordenDetalle.idProducto}}">{{ordenDetalle.nombre}}</label></td>
                <td><input type="number" max="5" min="0" id="cantidad{{ordenDetalle.idProducto}}" 
                           name="cantidad{{ordenDetalle.idProducto}}" ng-model="ordenDetalle" /></td>
                <td><input type="number" max="5" id="precio{{ordenDetalle.idProducto}}" 
                           name="precio{{ordenDetalle.idProducto}}" ng-model="ordenDetalle" /></td>
            </tr>
            </tbody>
        </table>
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>