var app = angular.module("app", []);
app.value("appName","'Compras Beitech'");
app.controller("IndiceControlador", ['$scope','$log','$http',function($scope,$log,$http)  {
    $scope.urlHead="head.html";
    $scope.urlFoot="foot.html";
    $scope.urldisponibles = "inventario/disponibles.htm";
    $scope.urlproductos = "costo/productos.htm";
    $scope.urlordenes = "compra/clientesorden.htm";
    $scope.urllistado = "historico/listado.htm";
    $scope.urllistames = "historico/listadomes.htm";
    $scope.tituloCss={
        titulo:true
    }
    $scope.explicacionCss={
        explica:true
    }
    $log.debug("Acabamos de crear el $scope");
}]);