var address;
var port;

var coap    = require('coap')
    , server  = coap.createServer()
    , initReq = coap.request({
      host: 'localhost',
      port: '5555',
      method: 'GET'
    })


server.on('request', function(req, res) {
  if (req.headers['Observe'] !== 0)
    return res.end(new Date().toISOString() + '\n')

  var interval = setInterval(function() {
    res.write(new Date().toISOString() + '\n')
  }, 1000)

  res.on('finish', function(err) {
    clearInterval(interval)
  })
})

server.listen(function() {
  console.log('Node Server Started')
  console.log('Node Server Address : ', server._address)
  console.log('Node Server Port : ', server._port)

  initReq.on('response', function(res) {
        res.pipe(process.stdout)
  })

  initReq.end()
})
