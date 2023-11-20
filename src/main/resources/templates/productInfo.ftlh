<!doctype html>
<html>
<head>
    <title>BuyProduct</title>
</head>
<body>
<h1>BuyProduct</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"><br><br>
</#list>
<b>Название товара: </b>${product.title}<br>
<b>Описание товара: </b>${product.description}<br>
<b>Цена товара: </b>${product.price}<br>
<b>Автор: </b> <a href="/user/${product.user.id}">${product.user.name}</a><br>

</body>
</html>