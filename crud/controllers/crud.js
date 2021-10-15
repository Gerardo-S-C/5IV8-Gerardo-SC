const conexion = require('../db/db')

exports.save=(req,res)=>{
    const nombre = req.body.nombre;
    const edad = req.body.edad;
    const raza = req.body.rol;
    console.log(nombre+" - "+edad+" - "+raza);

    conexion.query('INSERT INTO perro SET ?',{nombre:nombre, edad:edad, raza_idraza:raza}, (error, results)=>{
        if(error){
            console.log(error);
        }else{
            res.redirect('/');
        }
    })

}

exports.update=(req,res)=>{
    const id=req.body.id;
    const nombre = req.body.nombre;
    const edad = req.body.edad;
    const raza = req.body.rol;
    conexion.query('UPDATE perro SET ? WHERE idperro= ?', [{nombre:nombre, edad:edad, raza_idraza:raza}, id], (error,results)=>{
        if(error){
            console.log(error);
        }else{
            res.redirect('/');
        }
    })
}