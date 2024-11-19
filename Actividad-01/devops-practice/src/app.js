const express = require('express');
const app = express();

app.get('/', (req,res) => {
    res.send('Hello, World!');
});

app.get('/multi/:id/:uid', (req,res) => {
    res.send('Producto: ' + req.params.id*req.params.uid);
});

module.exports = app;


if (require.main === module) {
    const port = process.env.PORT || 3000; 
    app.listen(port, () => {
        console.log(`Server running on port ${port}`);
    });
}