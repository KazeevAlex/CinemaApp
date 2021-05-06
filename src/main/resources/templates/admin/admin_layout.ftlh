<#macro admin>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Admin</title>
        <#include "share/style.ftlh">
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
    <div class="wrapper">
        <#include "share/header.ftlh">
        <#include "share/left_side.ftlh">

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <#nested >
        </div>

        <#include "share/footer.ftlh">
        <#include "share/script.ftlh">
    </div>
    </body>
    </html>
</#macro>