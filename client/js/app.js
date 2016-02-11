$(document).ready(function(){
  $("#searchButton").click(function(){
        $.ajax({
          url : "http://localhost:8080/node/list",
          type : "GET",
          dataType : "text",
          success : function(data) {
            setNodeTable(data);
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

  function setNodeTable(data){
      var jsonData = JSON.parse(data);
      $("#dataField").html("");
      for(i=0; i<jsonData.length; i++){
        $("#dataField").append("<tr><td>"+jsonData[i].id+"</td><td>"+jsonData[i].info+"</td></tr>");
      }
  }

});
