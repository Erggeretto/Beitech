<%-- 
    Document   : disponibles
    Created on : 30-oct-2017, 13:11:22
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
        <title>Listado de Productos Disponibles por Cliente</title>
    </head>
    <body ng-controller="IndiceControlador">
        <ng-include src="urlHead"></ng-include>
        <div ng-class="titulosCss.titulo"> Listado de Productos Disponibles por Cliente </div>
        <div ng-class="explicacionCss.explica"> Por favor seleccione un solo Cliente y sus productos disponibles </div>
        <form action="inventario/disponibles.htm" method="post">
            <legend>Clientes</legend>
            <label for="cliente" ng-repeat="cliente" >
                <label for="cliente{{cliente.idCliente}}">{{cliente.name}}</label><input id="cliente{{cliente.idCliente}}" name="cliente{{cliente.idCliente}}" type="radio" ng-model="cliente.idCliente" /><br />               
            </label>
            <legend>Productos</legend>
            <label for="producto" ng-repeat="cliente" >
                <label for="cliente{{producto.idProducto}}">{{producto.name}}</label><input id="cliente{{cliente.idCliente}}" name="cliente{{producto.idProducto}}" type="checkbox" ng-model="producto.idProducto" /><br />
            </label>
            <input type="submit" name="Asignar"  />
      </fieldset> 
        </form>
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>
