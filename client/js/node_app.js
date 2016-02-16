$(document).ready(function(){

  var id = 1;

  function getJSON(){
        $.ajax({
          url : "http://localhost:8080/node/"+id+"/items",
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
      $(".nodeInfo").html("Node Id : "+id);
      for(i=0; i<jsonData.length; i++){
        var appendData = "<li class='list-group-item'>"+jsonData[i].itemType + "</br>"
        appendData = appendData + "ID : "+jsonData[i].id + "</br></br>"
        appendData = appendData + "<button type='button' class='btn btn-default led-control'>More Info</button></li>"
        $("#dataField").append(appendData);
      }
  }

  getJSON();

  $(".led-control").click(function(){
    window.location = "http://localhost:3000/led.html";
  });
  $("#temperature-control").click(function(){
    window.location = "http://localhost:3000/sensor.html";
  });

});
