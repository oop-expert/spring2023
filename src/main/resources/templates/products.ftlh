<!doctype html>
<html>
<head>
    <title>BuyProduct</title>
</head>
<body>
    <h1>BuyProduct</h1><hr>
    <h4>Товары в наличии:</h4>
    <form action="/" method="get">
        Поиск по названию товара: <input type="text" name="title"><br>
        <input type="submit" value="Поиск">
    </form>
    <#list products as product>
     <div>
         <p><b>${product.title}</b> ${product.price} | <a href="/product/${product.id}">Подробнее...</a></p>
     </div>
        <#else>
        <h3>Товаров нет</h3>
    </#list>
    <hr>
    <h3>Создать новый товар</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Название товара: <input type="text" name="title"/> <br><br>
        Описание товара: <input type="text" name="description"/> <br><br>
        Цена товара: <input type="text" name="price"/> <br><br>
        Фотография 1: <input type="file" name="file1"><br><br>
        Фотография 2: <input type="file" name="file2"><br><br>
        Фотография 3: <input type="file" name="file3"><br><br>
        <input type="submit" value="Добавить товар">
    </form>
</body>
</html>