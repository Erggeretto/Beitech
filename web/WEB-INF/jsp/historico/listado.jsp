<%-- 
    Document   : listado
    Created on : 30-oct-2017, 13:12:45
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
        <div ng-class="explicacionCss.explica"> Por favor seleccione un cliente para ver historico y las fechas </div>
        <form action="historico/listadoorden.htm" method="get">
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
                <tr ng-repeat="orden" ng-style="{color:($odd?'blue':'green')}">
                    <td><label for="idCliente{{orden.idCliente}}">{{orden.nombre}}</label></td>
                    <td><input id="idCliente{{orden.idCliente}}" name="idCliente{{orden.idCliente}}" 
                               type="hidden" ng-model="historico/listadoorden{{orden.idCliente}}">
                        <input type="date" id="fechaIni" name="fechaIni" ng-model="orden.date"
                                placeholder="yyyy-MM-dd" min="2013-01-01" />
                        <input type="date" id="fechaFin" name="fechaFin" ng-model="orden.date"
                                placeholder="yyyy-MM-dd" min="2013-01-01" />
                        <button ng-click="selecciona(orden.idCliente)">Ver Historico</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>