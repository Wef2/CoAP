var coap        = require('coap')
  , server      = coap.createServer()

server.on('request', function(req, res) {
  res.end('Test')
})

server.listen(function() {
  var req = coap.request('coap://localhost')

  req.on('response', function(res) {
    res.pipe(process.stdout)
    res.on('end', function() {
      process.exit(0)
    })
  })

  req.end()
})
