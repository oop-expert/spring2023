<!doctype html>
<html>
<head>
    <title>BuyProduct</title>
</head>
<body>
    <h1>BuyProduct </h1><hr>
    <div>
        <h1>Добавление комментария</h1>
        <form action="/comment/add" method="post">
            <textarea type="text" name="text" placeholder="Введите комментарий"></textarea><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить комментарий</button>
        </form>
    </div>
</body>
</html>