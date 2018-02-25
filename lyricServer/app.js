const express = require('express')
const app = express()

var PythonShell = require('python-shell');

app.get('/', (req, res) => {
    res.send('Hello World!')
})

app.get('/rhyme/:word', (req, res) => {
    console.log(req.params);
    var options = {
        args: [req.params.word]
    };
    var pyshell = new PythonShell('suggestions.py', options);

    // sends a message to the Python script via stdin
    pyshell.send('hello');

    pyshell.on('message', function (message) {
        // received a message sent from the Python script (a simple "print" statement)
        console.log(message);
        res.send(message)
    });

    // end the input stream and allow the process to exit
    pyshell.end(function (err, code, signal) {
        if (err) throw err;
        console.log('The exit code was: ' + code);
        console.log('The exit signal was: ' + signal);
        console.log('finished');
        console.log('finished');
    });


})

app.listen(3000, () => console.log('Example app listening on port 3000!'))