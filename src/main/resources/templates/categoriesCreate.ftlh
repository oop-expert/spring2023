<!doctype html>
<html>
<head>
    <title>BuyProduct</title>
</head>
<body>
    <h1>BuyProduct </h1><hr>
    <div>
        <h1>Добавление категории</h1>
        <form action="/categories/create" method="post">
            <textarea type="text" name="name" placeholder="Введите название категории"></textarea><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить категорию</button>
        </form>
    </div>
</body>
</html>