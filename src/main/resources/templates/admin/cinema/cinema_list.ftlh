<#import "../admin_layout.ftlh" as a>
<#import "../share/pager.ftlh" as p>
<@a.admin style="cinema/cinema_style.ftlh" script="cinema/cinema_script.ftlh">

    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Cinema List</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Cinema</li>
                        <li class="breadcrumb-item active">List</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">

            <@p.pager url page>

                <div class="row row-cols-1 row-cols-md-4 g-4">
                    <#list page.content as cinema>
                        <div class="col">
                            <div class="card ">
                                <#if cinema.getTopBanner??>
                                    <img class="card-img-top" src="/img/${cinema.getTopBanner()}">
                                <#else >
                                    <svg class="bd-placeholder-img card-img-top" width="100%" height="140"
                                         xmlns="http://www.w3.org/2000/svg" role="img"
                                         aria-label="Placeholder: Image cap"
                                         preserveAspectRatio="xMidYMid slice" focusable="false"><title>
                                            Placeholder</title>
                                        <rect width="100%" height="100%" fill="#868e96"></rect>
                                        <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
                                    </svg>
                                </#if>
                                <div class="card-body text-center">
                                    <h4 class="card-title" style="float: none;">${cinema.getName()}</h4>
                                    <#--<p class="card-text">This is a longer card with supporting text below as a natural
                                        lead-in to additional content. This content is a little bit longer.</p>-->
                                    <br>
                                    <a href="/admin/cinema/edit/${cinema.getId()}" class="btn btn-primary">Edit</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </@p.pager>
        </div>
        <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</@a.admin>