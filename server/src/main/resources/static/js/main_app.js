$(document).ready(function () {
    getNodeList();

    $("#searchButton").click(function () {
        getNodeList();
    });

    function getNodeList() {
        $.ajax({
            url: "http://localhost:8080/node/list",
            type: "GET",
            dataType: "text",
            success: function (data) {
                setNodeListTable(data);
                alert(data);
            },
            error: function (xhr, status, errorThrown) {
                alert("Error!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }
        });
    }

    function setNodeListTable(data) {
        $("#listField").html("");
        var jsonData = JSON.parse(data);
        var appendData = "";
        for (i = 0; i < jsonData.length; i++) {
            appendData += "<tr class='node-row' id='" + jsonData[i].id + "'><td>" + jsonData[i].id + "</td><td>" + jsonData[i].model + "</td><td>" + jsonData[i].name + "</td>"
            appendData += "<td></tr>"
        }
        $("#listField").append(appendData);

        $("tr.node-row").mouseover(function () {
            this.style.cursor = "pointer";
        });

        $("tr.node-row").click(function () {
            var id = this.id;
            getNodeItems(id);
        });
    }

    function getNodeItems(nodeId) {
        $.ajax({
            url: "http://localhost:8080/node/" + nodeId + "/items",
            type: "GET",
            dataType: "text",
            success: function (data) {
                setNodeData(nodeId, data);
                alert(data);
            },
            error: function (xhr, status, errorThrown) {
                alert("Error!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }
        });
    }

    function setNodeData(nodeId, data) {
        $("#nodeField").html("");
        var jsonData = JSON.parse(data);
        var appendData = "<h2 class='text-center'>Node Id : " + nodeId + "</h2>";
        appendData += "<div class='col-sm-8 text-center'><img class='img-thumbnail' src='images/edison.png'/></div>";
        appendData += "<div class='col-sm-4'><ul class='list-group'>";
        appendData += "<div class='list-group-item active'>Item List</div>";
        for (i = 0; i < jsonData.length; i++) {
            appendData += "<li class='list-group-item node-item-li' id='" + jsonData[i].id + "'>Item type : " + jsonData[i].itemType + "</br>";
            appendData += "Item ID : " + jsonData[i].id + "</br></br></li>";
        }
        appendData = appendData + "</ul></div>";
        $("#nodeField").append(appendData);

        $("li.node-item-li").mouseover(function () {
            this.style.cursor = "pointer";
        });

        $("li.node-item-li").click(function () {
            var itemId = this.id;
            getNodeItem(itemId);
        });
    }

    function getNodeItem(itemId) {
        $.ajax({
            url: "http://localhost:8080/node/item/" + itemId,
            type: "GET",
            dataType: "text",
            success: function (data) {
                alert(data);
                setItemData(itemId, data)
            },
            error: function (xhr, status, errorThrown) {
                alert("Error!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }
        });
    }

    function setItemData(itemId, data) {
        $("#itemField").html("");
        var jsonData = JSON.parse(data);
        if(jsonData.itemType == 'LED'){
            setItemLed(itemId, jsonData);
        }
        else if(jsonData.itemType = 'Temperature Sensor'){
            setItemSensor(itemId, jsonData);
        }
    }

    function setItemLed(itemId, jsonData){
        var appendData = "<h2 class='text-center'>Item Id : " + itemId + "</h2>";
        appendData += "<div class='col-sm-8 text-center'><img class='img-thumbnail' src='images/led.jpg'/></div>";
        appendData += "<div class='col-sm-4'><ul class='list-group'>";
        appendData += "<div class='list-group-item active'>Item Information</div>";
        appendData += "<li class='list-group-item'>Node Id : " + jsonData.nodeId + "</li>";
        appendData += "<li class='list-group-item'>Item type : " + jsonData.itemType + "</li>";
        appendData += "<li class='list-group-item'>Item ID : " + jsonData.id + "</li>";
        appendData += "<li class='list-group-item status-info'>Status : " + "<p class='"+itemId+"'>"+jsonData.status + "</p></li>";
        appendData += "<li class='list-group-item'><button type='button' class='btn btn-primary on-button'>On</button><button type='button' class='btn btn-primary off-button'>Off</button></li>";
        appendData = appendData + "</ul></div>";
        $("#itemField").append(appendData);
        $(".on-button").click(function () {
            sendOperation(itemId, "on");
        });
        $(".off-button").click(function () {
            sendOperation(itemId, "off");
        });
    }

    function setItemSensor(itemId, jsonData){
        var appendData = "<h2 class='text-center'>Item Id : " + itemId + "</h2>";
        appendData += "<div class='col-sm-8 text-center'><img class='img-thumbnail' src='images/sensor.jpg'/></div>";
        appendData += "<div class='col-sm-4'><ul class='list-group'>";
        appendData += "<div class='list-group-item active'>Item Information</div>";
        appendData += "<li class='list-group-item'>Node Id : " + jsonData.nodeId + "</br>";
        appendData += "Item type : " + jsonData.itemType + "</br>";
        appendData += "Item ID : " + jsonData.id + "</br>";
        appendData += "Sensor Value : " + "55" + "</br>";
        appendData = appendData + "</ul></div>";
        $("#itemField").append(appendData);
    }

    function sendOperation(itemId, operation){
        $.ajax({
            url: "http://localhost:8080/node/item/" + itemId + "/" + operation,
            type: "GET",
            dataType: "text",
            success: function (data) {
                $("."+itemId).html(operation)
            },
            error: function (xhr, status, errorThrown) {
                alert("Error!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }
        });
    }
});