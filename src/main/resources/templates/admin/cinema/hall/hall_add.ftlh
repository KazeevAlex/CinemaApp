<#import "../../admin_layout.ftlh" as a>

<@a.admin style="cinema/cinema_style.ftlh" script="cinema/cinema_script.ftlh">

    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Add Hall</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Hall</li>
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
            <div class="card card-primary card-tabs">
                <div class="card-header p-0 pt-1">
                    <ul class="nav nav-tabs" id="custom-tabs-one-tab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="custom-tabs-one-home-tab" data-toggle="pill"
                               href="#custom-tabs-one-home" role="tab" aria-controls="custom-tabs-one-home"
                               aria-selected="true">Main Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="custom-tabs-one-settings-tab" data-toggle="pill"
                               href="#custom-tabs-one-settings" role="tab" aria-controls="custom-tabs-one-settings"
                               aria-selected="false">Seo Block</a>
                        </li>
                    </ul>
                </div>
                <!-- /.card-header -->
                <!-- form start -->
                <form method="post" action="add" enctype="multipart/form-data" id="filmForm"
                      onsubmit="return validateForm()">
                    <div class="card-body">
                        <div class="tab-content" id="custom-tabs-one-tabContent">

                            <#--Main Informaition tab-->
                            <div class="tab-pane fade show active" id="custom-tabs-one-home" role="tabpanel"
                                 aria-labelledby="custom-tabs-one-home-tab">

                                <div class="row">
                                    <div class="col-md-6">
                                        <!-- Hall Title -->
                                        <div class="form-group">
                                            <label>Hall Name</label>
                                            <input type="text" name="name" class="form-control requiredField"
                                                   placeholder="Enter cinema name ...">
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-6">
                                        <!-- Logo Image -->
                                        <div class="form-group">
                                            <label for="exampleInputFile">Hall Schema Image</label>
                                            <div class="input-group">
                                                <div class="custom-file">
                                                    <input type="file" name="schemaImage"
                                                           class="custom-file-input requiredField"
                                                           id="exampleInputFile">
                                                    <label class="custom-file-label" for="exampleInputFile">Select the
                                                        hall schema image ...</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->

                                <div class="row">
                                    <div class="col-md-6">
                                        <!-- Top Banner Image -->
                                        <div class="form-group">
                                            <label for="exampleInputFile">Top Banner Image</label>
                                            <div class="input-group">
                                                <div class="custom-file">
                                                    <input type="file" name="topBannerImage"
                                                           class="custom-file-input requiredField"
                                                           id="exampleInputFile">
                                                    <label class="custom-file-label" for="exampleInputFile">Select the
                                                        top banner image ...</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-6">
                                        <!-- Gallery -->
                                        <div class="form-group">
                                            <label for="exampleInputFile">Gallery Images</label>
                                            <div class="input-group">
                                                <div class="custom-file">
                                                    <input type="file" name="galleryImages"
                                                           class="custom-file-input requiredField" id="exampleInputFile"
                                                           multiple>
                                                    <label class="custom-file-label" for="exampleInputFile">To select
                                                        multiple files, hold down the CTRL or SHIFT key while selecting
                                                        ...</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->

                                <!-- Description -->
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea class="form-control" rows="3" name="description"
                                              placeholder="Enter filmDomain description ..."></textarea>
                                </div>

                            </div>
                            <#--Seo tab-->
                            <div class="tab-pane fade" id="custom-tabs-one-settings" role="tabpanel"
                                 aria-labelledby="custom-tabs-one-settings-tab">
                                <div class="form-group">
                                    <label>URL</label>
                                    <input type="text" name="seoUrl" class="form-control"
                                           placeholder="Enter SEO URL ...">
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <input type="text" name="seoTitle" class="form-control"
                                           placeholder="Enter SEO Title ...">
                                </div>
                                <div class="form-group">
                                    <label>Keywords</label>
                                    <input type="text" name="seoKeywords" class="form-control"
                                           placeholder="Enter SEO keywords ...">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="seoDescription" class="form-control" rows="3"
                                              placeholder="Enter SEO description ..."></textarea>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- /.card-body -->

                    <div class="card-footer">

                        <#--                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
            <!-- /.card -->
        </div>
        <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</@a.admin>