(function(){
    $(document).ready(function () {
        console.log("DataTable")
        var table =$('#logTable').DataTable();
        table
            .order( [ 0, 'desc' ] )
            .draw();
        $('.dataTables_length').addClass('bs-select');


        $("#refresh").click(function(){
            location.reload();
        });


        $("#cowrite").click(function(){
            table.search( "cowrite" ).draw();

            // window.location.replace("/cowrite");
        });


    });


})();


