<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="get" action="/main" class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-12">
            <input class="form-control" type="text" name="filter" value="${filter?ifExists}"
                   placeholder="Название фильма">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Найти</button>
        </div>
    </form>

    <a class="btn btn-primary my-3" data-bs-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="false" aria-controls="collapseExample">
        Добавить фильм
    </a>
    <div class="collapse" id="collapseExample">
        <form class="mb-3" method="post" action="main" enctype="multipart/form-data">
            <div class="mb-3">
                <input class="form-control" type="text" name="name" placeholder="Название фильма">
            </div>
            <div class="mb-3">
                <input class="form-control" type="text" name="description" placeholder="Описание фильма">
            </div>
            <div class="input-group mb-3">
                <input type="file" name="file" class="form-control" id="inputGroupFile02">
                <#--                <label class="input-group-text" for="inputGroupFile02">Upload</label>-->
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Добавить</button>
        </form>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list films as film>
            <div class="card my-3 me-2" style="width: 18rem;">
                <div class="mt-1">
                    <#if film.filename??>
                        <img class="card-img-top" src="/img/${film.filename}">
                    </#if>
                </div>
                <div class="m-2">
                    <h5 class="card-title">${film.name}</h5>
                    <p class="card-text">${film.description}</p>
                </div>
                <div class="card-footer">
                    ${film.authorName}
                </div>
            </div>
        <#else>
            No films
        </#list>
    </div>
</@c.page>