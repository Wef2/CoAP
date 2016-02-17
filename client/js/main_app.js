$(document).ready(function(){
  getNodeList();

  $("#searchButton").click(function(){
    getNodeList();
  });

  function getNodeList(){
        $.ajax({
          url : "http://localhost:8080/node/list",
          type : "GET",
          dataType : "text",
          success : function(data) {
            setNodeListTable(data);
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

  function setNodeListTable(data){
      $("#listField").html("");
      var jsonData = JSON.parse(data);
      for(i=0; i<jsonData.length; i++){
        var appendData = "<tr><td>"+jsonData[i].id+"</td><td>"+jsonData[i].model+"</td><td>"+jsonData[i].name+"</td>"
        appendData = appendData + "<td></tr>"
        $("#listField").append(appendData);
      }
  }

  function getNodeItems(nodeId){
        $.ajax({
          url : "http://localhost:8080/node/"+nodeId+"/items",
          type : "GET",
          dataType : "text",
          success : function(data) {
            setNodeData(nodeId, data);
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

  function setNodeData(nodeId, data){
      var jsonData = JSON.parse(data);
      for(i=0; i<jsonData.length; i++){
        var appendData = "<li class='list-group-item'>"+jsonData[i].itemType + "</br>"
        appendData = appendData + "ID : "+jsonData[i].id + "</br></br>"
        appendData = appendData + "<button type='button' class='btn btn-default led-control'>More Info</button></li>"
        $("#dataField").append(appendData);
      }
  }

});
