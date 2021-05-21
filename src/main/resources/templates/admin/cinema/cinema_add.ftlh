<#import "../admin_layout.ftlh" as a>

<@a.admin style="cinema/cinema_style.ftlh" script="cinema/cinema_script.ftlh">

    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Add Cinema</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Cinema</li>
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
                            <a class="nav-link" id="custom-tabs-one-profile-tab" data-toggle="pill"
                               href="#custom-tabs-one-profile" role="tab" aria-controls="custom-tabs-one-profile"
                               aria-selected="false">Halls</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="custom-tabs-one-messages-tab" data-toggle="pill"
                               href="#custom-tabs-one-messages" role="tab" aria-controls="custom-tabs-one-messages"
                               aria-selected="false">Address</a>
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
                                        <!-- Cinema Title -->
                                        <div class="form-group">
                                            <label>Cinema Name</label>
                                            <input type="text" name="name" class="form-control requiredField"
                                                   placeholder="Enter cinema name ...">
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-6">
                                        <!-- Logo Image -->
                                        <div class="form-group">
                                            <label for="exampleInputFile">Logo Image</label>
                                            <div class="input-group">
                                                <div class="custom-file">
                                                    <input type="file" name="logoImage"
                                                           class="custom-file-input requiredField"
                                                           id="exampleInputFile">
                                                    <label class="custom-file-label" for="exampleInputFile">Select the
                                                        logo image ...</label>
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

                                <!-- Conditions -->
                                <div class="form-group">
                                    <label>Conditions</label>
                                    <textarea class="form-control" rows="3" name="conditions"
                                              placeholder="Enter filmDomain description ..."></textarea>
                                </div>

                            </div>
                            <#--Hall tab-->
                            <div class="tab-pane fade" id="custom-tabs-one-profile" role="tabpanel"
                                 aria-labelledby="custom-tabs-one-profile-tab">
                                <div class="callout callout-warning">
                                    <h5>First finish creating a cinema please!</h5>

                                    <p>You will be able to create and add a hall for this cinema after saving.
                                        To do this, after saving, go to the "Cinema List" page and click "Edit" for the required cinema.</p>
                                </div>
                                <#--<table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width: 10px">#</th>
                                        <th>Task</th>
                                        <th>Progress</th>
                                        <th style="width: 82px"></th>
                                        <th style="width: 95px"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1.</td>
                                        <td>Update software</td>
                                        <td>
                                            <div class="progress progress-xs">
                                                <div class="progress-bar progress-bar-danger" style="width: 55%"></div>
                                            </div>
                                        </td>
                                        <td>
                                            <a class="btn btn-info btn-sm" href="#">
                                                <i class="fas fa-pencil-alt">
                                                </i>
                                                Edit
                                            </a>
                                        </td>
                                        <td>
                                            <a class="btn btn-danger btn-sm" href="#">
                                                <i class="fas fa-trash">
                                                </i>
                                                Delete
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>-->
                            </div>
                            <#--Address tab-->
                            <div class="tab-pane fade" id="custom-tabs-one-messages" role="tabpanel"
                                 aria-labelledby="custom-tabs-one-messages-tab">

                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>City</label>
                                            <select name="city" class="select2 requiredField"
                                                    data-placeholder="To select multiple types at once, hold down the CTRL"
                                                    style="width: 100%;">
                                                <option value="Odessa">Odessa</option>
                                                <option value="Dnepr">Dnepr</option>
                                                <option value="Dnepr">Kiev</option>
                                                <option value="Dnepr">Kharkov</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Street</label>
                                            <input type="text" name="street" class="form-control"
                                                   placeholder="Enter street ...">
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Build</label>
                                            <input type="text" name="build" class="form-control"
                                                   placeholder="Enter build ...">
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->

                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Main Phone</label>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                                </div>
                                                <input type="text" name="mainPhone" class="form-control"
                                                       data-inputmask='"mask": "(999) 999-9999"' data-mask>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Additional Phone</label>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                                </div>
                                                <input type="text" name="additionalPhone" class="form-control"
                                                       data-inputmask='"mask": "(999) 999-9999"' data-mask>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Email address</label>
                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">@</span>
                                                </div>
                                                <input type="email" name="email" class="form-control"
                                                       id="exampleInputEmail1" placeholder="Enter email">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->

                                <div class="form-group">
                                    <label>Map Coordinate</label>
                                    <input type="text" name="mapCoordinate" class="form-control"
                                           placeholder="Enter map coordinate ...">
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
                        <button type="submit" class="btn btn-primary" <#--onclick="validateForm()"-->>Save</button>
                    </div>
                </form>
            </div>
            <!-- /.card -->
        </div>
        <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</@a.admin>