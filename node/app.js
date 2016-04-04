var serverAddress = '117.17.102.81'
var serverPort = '5555';

const coap    = require('coap')
    , server  = coap.createServer()
    , initReq = coap.request({
      host: serverAddress,
      port: serverPort,
      method: 'GET'
})

server.listen(function() {
  console.log('IoT Node Server Started')
  console.log('IoT Node Server Address : ', server._address)
  console.log('IoT Node Server Port : ', server._port)

  var payload = {
    id: "1",
    model: "Intel Edison",
    name: "IoT Node 1",
    address: server._address,
    port: server._port,
    item:{
      id: "1-L-1",
      type: "LED",
      status: "off"
    }
  }

  initReq.write(JSON.stringify(payload));
  initReq.on('response', function(res) {
        res.pipe(process.stdout)
  })
  initReq.end()
})


server.on('request', function(req, res) {

  if (req.headers['Observe'] !== 0)
    console.log("Message From Server")
    console.log("Server Address : " + req.rsinfo.address)
    console.log("Server Port : " + req.rsinfo.port)
    console.log("Payload : " + req.payload.toString());
    return res.end(req.payload.toString() + "Success")

  var interval = setInterval(function() {
    res.write("Success")
  }, 1000)

  res.on('finish', function(err) {
    clearInterval(interval)
  })
})
