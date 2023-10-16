<!doctype html>
<html>
<head>
    <title>BuyProduct</title>
</head>
<body>
    <h1>BuyProduct</h1><hr>
    <h4>Товары в наличии:</h4>
    <#list products as product>
     <div>
         <p><b>${product.title}</b> ${product.price} | <a href="/product/${product.id}">Подробнее...</a></p>
     </div>
    </#list>
    <hr>
    <h3>Создать новый товар</h3>
    <form action="/product/create" method="post">
        Название товара: <input type="text" name="title"/> <br><br>
        Описание товара: <input type="text" name="description"/> <br><br>
        Цена товара: <input type="text" name="price"/> <br><br>
        <input type="submit" value="Добавить товар">
    </form>
</body>
</html>