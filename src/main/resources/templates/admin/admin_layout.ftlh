<#macro admin style="share/style.ftlh" script="share/script.ftlh">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Admin</title>
        <#include "${style}">

    </head>
<#--    <body class="hold-transition sidebar-mini layout-fixed">-->
    <body class="dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
    <div class="wrapper">
        <#include "share/header.ftlh">
        <#include "share/left_side.ftlh">

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <#nested >
        </div>

        <#include "share/footer.ftlh">
    </div>

    <#include "${script}">
    </body>
    </html>
</#macro>