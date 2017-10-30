<%-- 
    Document   : clientesorden
    Created on : 30-oct-2017, 15:23:26
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
        <title>Listado de Clientes</title>
    </head>
    <body ng-controller="IndiceControlador">
        <ng-include src="urlHead"></ng-include>
        <div ng-class="titulosCss.titulo"> Listado de Clientes </div>
        <div ng-class="explicacionCss.explica"> Por favor seleccione un cliente para iniciar compra </div>
        <form action="compras/productoorden.htm" method="post">
            <table>
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Seleccionar</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td colspan="3">Clientes registrados</td>
                    <td ng-bind="clientes.length"></td>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="clientes" ng-style="{color:($odd?'blue':'green')}">
                    <td><label for="idCliente{{clientes.idCliente}}">{{clientes.nombre}}</label></td>
                    <td><input id="idCliente{{clientes.idCliente}}" name="idCliente{{clientes.idCliente}}" 
                               type="hidden" ng-model="compra/productoorden{{clientes.idCliente}}">
                        <button ng-click="selecciona(clientes.idCliente)">Shopping</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>
