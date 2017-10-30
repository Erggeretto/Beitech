<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="app">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
        <script src="js/script.js"></script>
	<link href="css/style.css" rel="stylesheet" />
        <title>Compras Beitech</title>
    </head>

    <body ng-controller="IndiceControlador">
        <ng-include src="urlHead"></ng-include>
        <div style="padding:0 0 0 4em;"><a ng-href="{{urldisponibles}}">Productos Disponibles</a></div><br />
        <div style="padding:0 0 0 4em;"><a ng-href="{{urlproductos}}">Fijar Precios</a></div><br />
        <div style="padding:0 0 0 4em;"><a ng-href="{{urlordenes}}">Ordenar Compra</a></div><br />
        <div style="padding:0 0 0 4em;"><a ng-href="{{urllistado}}">Listar Compras</a></div><br />
        <div style="padding:0 0 0 4em;"><a ng-href="{{urllistames}}">Listar Compras Mes</a></div><br />
        <ng-include src="urlFoot"></ng-include>
    </body>
</html>
