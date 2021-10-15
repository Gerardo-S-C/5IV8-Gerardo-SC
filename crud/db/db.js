const mysql = require('mysql');

const conexion = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'illuminova',
    database: 'crud_node'

})

conexion.connect((error)=>{
    if(error){
        console.error('El error de conexión es: '+error);
        return
    }
    console.log('Conexión exitosa');
})

module.exports = conexion;