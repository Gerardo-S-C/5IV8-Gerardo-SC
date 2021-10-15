const { application, json } = require('express');
const express = require('express');
const index = express();

index.set('view engine','ejs');

index.use(express.urlencoded({extended:false}));
index.use(express(json));

index.use('/',require('./router'));

index.listen(5000,()=>{
    console.log('SERVER corriendo en http://localhost:5000');
})