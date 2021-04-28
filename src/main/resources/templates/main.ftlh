<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <div>Добавить фильм</div>
    <form method="post" action="main" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Название фильма">
        <input type="text" name="description" placeholder="Описание фильма">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>

    <div>Поиск</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}" placeholder="Название фильма">
        <button type="submit">Найти</button>
    </form>
    <div>Список фильмов</div>
    <#list films as film>
    <div>
        <b>${film.id}</b>
        <span>${film.name}</span>
        <i>${film.description}</i>
        <strong>${film.authorName}</strong>
        <div>
            <#if film.filename??>
                <img src="/img/${film.filename}">
            </#if>
        </div>
    </div>
    <#else>
    No films
    </#list>
</@c.page>