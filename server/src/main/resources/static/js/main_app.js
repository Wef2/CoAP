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
      var appendData = "";
      for(i=0; i<jsonData.length; i++){
        appendData += "<tr class='node-row' id='"+jsonData[i].id+"'><td>"+jsonData[i].id+"</td><td>"+jsonData[i].model+"</td><td>"+jsonData[i].name+"</td>"
        appendData += "<td></tr>"
      }
      $("#listField").append(appendData);

      $("tr.node-row").mouseover(function(){
        this.style.cursor = "pointer";
      });

      $("tr.node-row").click(function(){
        var id = this.id;
        getNodeItems(id);
      });
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
      $("#dataField").html("");
      var jsonData = JSON.parse(data);
      var appendData = "<h2 class='text-center'>Node Id : "+nodeId+"</h2>";
      appendData += "<div class='col-sm-8'><img src='images/edison.png'/></div>";
      appendData += "<div class='col-sm-4'><ul class='list-group'>";
      appendData += "<div class='list-group-item active'>Item List</div>";
      for(i=0; i<jsonData.length; i++){
        appendData += "<li class='list-group-item'>Item type : "+jsonData[i].itemType + "</br>";
        appendData += "Item ID : "+jsonData[i].id + "</br></br></li>";
      }
      appendData = appendData + "</ul></div>";
      $("#dataField").append(appendData);
  }

  function getNodeItem(itemId){
        $.ajax({
          url : "http://localhost:8080/node/item"+itemId,
          type : "GET",
          dataType : "text",
          success : function(data) {
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
});
