<!-- jQuery -->
<script src="/static/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- bs-custom-file-input -->
<script src="/static/admin/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>

<!-- Select2 -->
<script src="/static/admin/plugins/select2/js/select2.full.min.js"></script>

<!-- AdminLTE App -->
<script src="/static/admin/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/static/admin/dist/js/demo.js"></script>

<!-- Page specific script -->
<script>

    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()

        //Initialize Select2 Elements
        $('.select2bs4').select2({
            theme: 'bootstrap4'
        })

        //bs-custom-file-input
        bsCustomFileInput.init();
    });

    function validateForm() {
        var isValid = true;

        var elements = document.getElementById('filmForm').getElementsByClassName('requiredField');

        for (var i = 0; i < elements.length; i++) {
            if (elements[i].value.length < 1) {
                isValid = false;
            }
        }

        if (!isValid) {
            alert('Please fill all required fields');
        }

        return isValid;
    }
</script>