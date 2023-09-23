console.log("main")
var buttonClick = function(buttonName){
    $.ajax({
        url: "buttonLog",
        type: "get", //send it through get method
        data: {
            buttonName: buttonName,
        },
        success: function(response) {
            //Do Something
        },
        error: function(xhr) {
            //Do Something to handle error
        }
    });
}

