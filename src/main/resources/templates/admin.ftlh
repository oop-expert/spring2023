<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1>
<hr>
<h4>Панель администратора</h4>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Имя</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.name}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактировние</a></th>
            <th><a href="/user/${user.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>

    <a href="/categories/create">Добавить категорию</a>


</table>
    <h1>Категории товаров</h1>
        <#list categories as category>
            <div>
                <p><b>
                <form action="/categories/delete/${category.id}" method="post">
                    ${category.name} |
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Удалить категорию">
                </form></b>
            </div>
        </#list>

    <h1>Комментарии</h1>
        <#list commentaries as commentary>
            <div>
                <b>
                <form action="/admin/comment/ban/${commentary.id}" method="post">
                    ${commentary.text} |
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Удалить комментарий">
                </form>
                </b>
            </div>
        </#list>
</body>
</html>