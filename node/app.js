const coap  = require('coap')
    , req   = coap.request('coap://localhost:5683')

req.on('response', function(res) {
  res.pipe(process.stdout)
})

req.end()
