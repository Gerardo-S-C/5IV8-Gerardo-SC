const express = require('express');
const router = express.Router();

const conexion = require('./db/db');


//Muestra los registros
router.get('/', (req,res)=>{
    conexion.query('SELECT * FROM perro    INNER JOIN raza    ON perro.raza_idraza=raza.idraza', (error, results)=>{
        if(error){
            throw error;
        }else{
            res.render('index',{results:results});
        }
    })
})

//Crea los registros
router.get('/create', (req,res)=>{
    res.render('create');
})

//para editar los registros
router.get('/edit/:id', (req,res)=>{
    const id= req.params.id;
    conexion.query('SELECT * FROM perro WHERE idperro=?',[id], (error, results)=>{
        if(error){
            throw error;
        }else{
            res.render('edit', {perro:results[0]})
        }
    })
})

//para eliminar el registro
router.get('/delete/:id', (req,res)=>{
    const id= req.params.id;
    conexion.query('DELETE FROM perro WHERE idperro = ?',[id],(error,results)=>{
        if(error){
            throw error;
        }else{
            res.redirect('/');
        }
    })
})

const crud = require('./controllers/crud');
router.post('/save', crud.save);
router.post('/update', crud.update);

module.exports = router;