//validar que ningun campo este vacio

function validar(){
    
    var texto= document.getElementById("texto").value;
    var password= document.getElementById("password").value;
    var AES="";
    var cifrad=document.getElementById("cifrar");
    //alert(texto);
    //valido el texto
    if(texto==""){
        alert("El mensaje a no puede ir vacio");
    }else{
        var buscar=document.getElementsByName("AES");
        for(var i=0; i<buscar.length; i++){
            if(buscar[i].checked)
                AES=buscar[i].value;
        }
        //alert("Se seleccionó "+ AES);

        //valido el campo para validar la contraseña
        //dependiendo el tamaño del cifrado
        if(AES==""){
            alert("seleccione el tipo de cifrado primero");
        }else if(AES=="AES 128"){
            if(password.length!=16){
                alert("La llave debe tener 16 caracteres, tienes hasta ahora "+ password.length );
            }
            else{
                if(cifrad.value=="Cifrar"){
                //alert("vas bien");
                cifrar(texto,password);
                }else{
                    alert("algo hizo mal");
                }
            }
        }else if(AES=="AES 192"){
            if(password.length!=24){
                alert("La llave debe tener 24 caracteres, tienes hasta ahora "+ password.length );
            }
            else{
                if(cifrad.value=="Cifrar"){
                    //alert("vas bien");
                    cifrar(texto,password);
                    }else{
                        alert("algo hizo mal");
                    }
            }
        }else if(AES=="AES 256"){
            if(password.length!=32){
                alert("La llave debe tener 32 caracteres, tienes hasta ahora "+ password.length );
            }
            else{
                if(cifrad.value=="Cifrar"){
                    //alert("vas bien");
                    cifrar(texto,password);
                    }else{
                        alert("algo hizo mal");
                    }
            }
        }
    }

}
function validarDES(){
    
    var destexto= document.getElementById("destexto").value;
    var password= document.getElementById("password").value;
    var AES="";
    var descifrad=document.getElementById("descifrar").value;

    //alert(texto);
        var buscar=document.getElementsByName("AES");
        for(var i=0; i<buscar.length; i++){
            if(buscar[i].checked)
                AES=buscar[i].value;
        }
        //alert("Se seleccionó "+ AES);

        //valido el campo para validar la contraseña
        //dependiendo el tamaño del cifrado
        if(AES==""){
            alert("seleccione el tipo de descifrado de su archivo primero");
        }else if(AES=="AES 128"){
            if(password.length!=16){
                alert("La llave debe tener 16 caracteres, tienes hasta ahora "+ password.length );
            }
            else{
                alert(descifrad);
                if(descifrad=="Descifrar"){
                    //alert("vas bien");
                    descifrar(destexto,password);
                }else{
                    alert("algo hizo mal");
                }
            }
        }else if(AES=="AES 192"){
            if(password.length!=24){
                alert("La llave debe tener 24 caracteres, tienes hasta ahora "+ password.length );
            }
            else{
                //alert("vas bien");
                descifrar(destexto,password);
            }
        }else if(AES=="AES 256"){
            if(password.length!=32){
                alert("La llave debe tener 32 caracteres, tienes hasta ahora "+ password.length );
            }
            else{
                //alert("vas bien");
                descifrar(destexto,password);
            }
        }

}

function cifrar(texto,password){
    var cifrado = CryptoJS.AES.encrypt(texto, password);
    //alert(cifrado);
    alert("La descarga de su archivo cifrado iniciara en breve");
    download("AES.txt",cifrado);
}

function descifrar(destexto,password){
    //muestro el txt cifrado en el campo destexto y hago la misma validacion de campos
    var descifrado = CryptoJS.AES.decrypt(destexto,password);
    document.getElementById('destexto1').innerHTML=descifrado;
    var descifrar = descifrado.toString(CryptoJS.enc.Utf8);
    document.getElementById('destexto2').innerHTML=descifrar;
    alert("Su archivo ha sido descifrado");
    download("AESdescifrado.txt",descifrar);
}

function download(filename, text) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);
  
    element.style.display = 'none';
    document.body.appendChild(element);
  
    element.click();
  
    document.body.removeChild(element);
}