<#import "../admin_layout.ftlh" as a>

<@a.admin style="cinema/cinema_style.ftlh" script="cinema/cinema_script.ftlh">

    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Edit Cinema</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Cinema</li>
                        <li class="breadcrumb-item active">Edit</li>
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
                <form method="post" action="../edit/${cinemaId}" enctype="multipart/form-data" id="filmForm"
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
                                                   value="${cinema.getName()}" placeholder="Enter cinema name ...">
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
                                                           class="custom-file-input"
                                                           id="exampleInputFile">
                                                    <label class="custom-file-label" for="exampleInputFile">
                                                        To leave the logo image unchanged, leave this field blank...
                                                    </label>
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
                                                           class="custom-file-input"
                                                           id="exampleInputFile">
                                                    <label class="custom-file-label" for="exampleInputFile">
                                                        To leave the top banner image unchanged, leave this field blank...
                                                    </label>
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
                                                           class="custom-file-input" id="exampleInputFile"
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
                                              placeholder="Enter filmDomain description ...">${cinema.getDescription()}</textarea>
                                </div>

                                <!-- Conditions -->
                                <div class="form-group">
                                    <label>Conditions</label>
                                    <textarea class="form-control" rows="3" name="conditions"
                                              placeholder="Enter filmDomain description ...">${cinema.getConditions()}</textarea>
                                </div>

                            </div>
                            <#--Hall tab-->
                            <div class="tab-pane fade" id="custom-tabs-one-profile" role="tabpanel"
                                 aria-labelledby="custom-tabs-one-profile-tab">

                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width: 10px">Id</th>
                                        <th>Hall Name</th>
                                        <th>Number of Rows</th>
                                        <th>Number of Seats</th>
                                        <th>Number of Active Sessions</th>
                                        <th style="width: 82px"></th>
                                        <th style="width: 95px"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>48</td>
                                        <td>Best Hall</td>
                                        <td>12</td>
                                        <td>150</td>
                                        <td>7</td>
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
                                </table>
                                <a href="/admin/hall/add" class="btn btn-info">Add Hall</a>
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
                                                <#assign cities = ["Odessa", "Dnepr", "Kiev", "Kharkov"]>
                                                <#list cities as city>
                                                    <#if city == cinema.getAddress().getCity()>
                                                        <option selected value="${city}">${city}</option>
                                                    <#else>
                                                        <option value="${city}">${city}</option>
                                                    </#if>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Street</label>
                                            <input type="text" name="street" class="form-control"
                                                   value="${cinema.getAddress().getStreet()}"
                                                   placeholder="Enter street ...">
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Build</label>
                                            <input type="text" name="build" class="form-control"
                                                   value="${cinema.getAddress().getBuild()}"
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
                                                       value="${cinema.getAddress().getMainPhone()}"
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
                                                       value="${cinema.getAddress().getAdditionalPhone()}"
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
                                                       value="${cinema.getAddress().getEmail()}"
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
                                           value="${cinema.getAddress().getMapCoordinate()}"
                                           placeholder="Enter map coordinate ...">
                                </div>

                            </div>
                            <#--Seo tab-->
                            <div class="tab-pane fade" id="custom-tabs-one-settings" role="tabpanel"
                                 aria-labelledby="custom-tabs-one-settings-tab">
                                <div class="form-group">
                                    <label>URL</label>
                                    <input type="text" name="seoUrl" class="form-control"
                                           value="${cinema.getSeoBlock().getUrl()}"
                                           placeholder="Enter SEO URL ...">
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <input type="text" name="seoTitle" class="form-control"
                                           value="${cinema.getSeoBlock().getTitle()}"
                                           placeholder="Enter SEO Title ...">
                                </div>
                                <div class="form-group">
                                    <label>Keywords</label>
                                    <input type="text" name="seoKeywords" class="form-control"
                                           value="${cinema.getSeoBlock().getKeywords()}"
                                           placeholder="Enter SEO keywords ...">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="seoDescription" class="form-control" rows="3"
                                              placeholder="Enter SEO description ...">${cinema.getSeoBlock().getDescription()}</textarea>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- /.card-body -->

                    <div class="card-footer">

                        <#--                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="../delete/${cinemaId}" class="btn btn-primary">Delete</a>
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