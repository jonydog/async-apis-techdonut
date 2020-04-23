const http = require('http')

const server = http.createServer((req, res) => {

    console.log(req.url)
	if ((req.url === '/hello') && req.method === 'GET') {
        setTimeout(
            ()=>{
                res.statusCode = 200
                res.end('Hello')
            },
            1500
        )
        return
	}
	res.statusCode = 404
	res.end('Not found')
})

const port = process.env.PORT || '9090'
server.listen(port,()=>{
    console.log(`Server listening on port ${port}`)
})
