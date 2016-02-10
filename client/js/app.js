$(document).ready(function(){
  $("#searchButton").click(function(){
        $.ajax({
          url : "http://localhost:8080/greeting",
          type : "GET",
          dataType : "text",
          success : function(data) {
            addToTable(data);
            alert(data);
          },
          error : function(xhr, status, errorThrown) {
            alert("Error!");
            console.log("Error: " + errorThrown);
            console.log("Status: " + status);
            console.dir(xhr);
          }
        });
  });

  function addToTable(data){
      var jsonData = JSON.parse(data);
      console.log(jsonData);
      $("#dataField").append("<tr><td>"+jsonData.id+"</td><td>"+jsonData.content+"</td></tr>");
  }

});
