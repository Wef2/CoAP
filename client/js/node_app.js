$(document).ready(function(){
  function setData(){
        var id = 1;
        $.ajax({
          url : "http://localhost:8080/node/"+id,
          type : "GET",
          dataType : "text",
          success : function(data) {
            setNodeData(data);
            alert(data);
          },
          error : function(xhr, status, errorThrown) {
            alert("Error!");
            console.log("Error: " + errorThrown);
            console.log("Status: " + status);
            console.dir(xhr);
          }
        });
  }

  function setNodeData(data){
      var jsonData = JSON.parse(data);
      $("#dataField").html("<tr><td>"+jsonData.id+"</td><td>"+jsonData.info+"</td></tr>");
  }

  setData();

  $(".led-control").click(function(){
    window.location = "http://localhost:3000/led.html";
  });
  $("#temperature-control").click(function(){
    window.location = "http://localhost:3000/sensor.html";
  });

});
