<#import "../admin_layout.ftlh" as a>

<@a.admin style="film/film_style.ftlh" script="film/film_script.ftlh">

    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Add Film</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Film</li>
                        <li class="breadcrumb-item active">Add</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">

            <!-- general form elements -->
            <div class="card card-primary" xmlns="http://www.w3.org/1999/html">
                <div class="card-header">
                    <h3 class="card-title">Main Information</h3>
                </div>
                <!-- /.card-header -->
                <!-- form start -->
                <form method="post" action="../edit/${filmId}" enctype="multipart/form-data" id="filmForm"
                      onsubmit="return validateForm()">
                    <div class="card-body">

                        <div class="row">
                            <div class="col-md-6">
                                <!-- Movie Title -->
                                <div class="form-group">
                                    <label>Film Name</label>
                                    <input type="text" name="name" class="form-control requiredField"
                                           value="${film.getName()}" placeholder="Enter movie name ...">
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <!-- Main Image -->
                                <div class="form-group">
                                    <label for="exampleInputFile">Main Image</label>
                                    <div class="input-group">
                                        <div class="custom-file">
                                            <input type="file" name="mainImage" class="custom-file-input"
                                                   id="exampleInputFile">
                                            <label class="custom-file-label" for="exampleInputFile">
                                                To leave the main image unchanged, leave this field blank...</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->

                        <div class="row">
                            <div class="col-md-6">
                                <!-- Film Type -->
                                <div class="form-group">
                                    <label>Film Type</label>
                                    <select name="type" class="select2 requiredField" multiple="multiple"
                                            data-placeholder="To select multiple types at once, hold down the CTRL"
                                            style="width: 100%;">
                                        <#list types as type>
                                            <#if filmTypes?seq_contains(type)>
                                                <option selected value="${type}">${type.getAlias()}</option>
                                            <#else>
                                                <option value="${type}">${type.getAlias()}</option>
                                            </#if>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <!-- Trailer Link -->
                                <div class="form-group">
                                    <label>Trailer Link</label>
                                    <input type="text" name="trailerLink" class="form-control"
                                           value="${film.getTrailerLink()}" placeholder="Enter trailer link ...">
                                </div>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->

                        <!-- Description -->
                        <div class="form-group">
                            <label>Description</label>
                            <textarea class="form-control" rows="3" name="description"
                                       placeholder="Enter filmDomain description ...">${film.getDescription()}</textarea>
                        </div>

                        <!-- Gallery -->
                        <div class="form-group">
                            <label for="exampleInputFile">Gallery Images</label>
                            <div class="input-group">
                                <div class="custom-file">
                                    <input type="file" name="galleryImages" class="custom-file-input"
                                           id="exampleInputFile" multiple>
                                    <label class="custom-file-label" for="exampleInputFile">To select multiple files,
                                        hold down the CTRL or SHIFT key while selecting ...</label>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.card-body -->

                    <br>
                    <!-- SEO Block -->
                    <div class="card card-secondary" id="accordion">
                        <div class="card-header">
                            <h4 class="card-title w-100">
                                <a class="d-block w-100 collapsed" data-toggle="collapse" href="#collapseOne"
                                   aria-expanded="false">
                                    SEO Block
                                </a>
                            </h4>
                        </div>
                        <!-- /.card-header -->
                        <div id="collapseOne" class="collapse" data-parent="#accordion" style="">
                            <div class="card-body">
                                <div class="form-group">
                                    <label>URL</label>
                                    <input type="text" name="seoUrl" class="form-control"
                                           value="${film.getSeoBlock().getUrl()}" placeholder="Enter SEO URL ...">
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <input type="text" name="seoTitle" class="form-control"
                                           value="${film.getSeoBlock().getTitle()}" placeholder="Enter SEO Title ...">
                                </div>
                                <div class="form-group">
                                    <label>Keywords</label>
                                    <input type="text" name="seoKeywords" class="form-control"
                                           value="${film.getSeoBlock().getKeywords()}" placeholder="Enter SEO keywords ...">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="seoDescription" class="form-control" rows="3"
                                              placeholder="Enter SEO description ...">${film.getSeoBlock().getDescription()}</textarea>
                                </div>
                            </div>
                            <!-- /.card-body -->
                        </div>
                    </div>
                    <!-- /.seo block -->

                    <div class="card-footer">

                        <#--                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="../delete/${film.getId()}" class="btn btn-primary">Delete</a>
                        <a href="../list?size=8" class="btn btn-primary">Back to List</a>
                    </div>
                </form>
            </div>
            <!-- /.card -->
        </div>
        <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</@a.admin>