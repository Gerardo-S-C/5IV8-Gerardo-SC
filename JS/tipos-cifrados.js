var cesar = cesar || (function() {
    var proceso = function (txt, desp, action) {
        var replace = (function() {
            //en el cuerpo principal de la funcion del callback
            //debemos de empezar a definir los elementos necesarios para
            //el cifrado:
            //abc
            var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
            var l = abc.length;
            //debemos de retornar posicion del caracter
            return function(c) {
                //variable para iterar en mi arreglo del abc
                var i = abc.indexOf(c.toLowerCase());
                //asegurarse que este dentro de mi rango
                if (i != -1) {
                    var pos = i;
                    if (action) {
                        //cifrar (avanza)
                        pos += desp;
                        //nuestro limite es el tamaño del abc; por lo tanto
                        //tenemos que darle vueltas y vueltas y vueltas sobre
                        //el mismo tamaño
                        pos -= (pos >= l)?l:0;
                    } else {
                        //descifrar (retrocede)
                        pos -= desp;
                        pos += (pos < 0)?l:0;
                    }
                    return abc[pos];
                }
                 //retornar el caracter o la poscion del caracter
                return c;
            };
        })();
        //necesito una expresion regular para mi abc
        var re = (/([a-z])/ig);
        //alert(re);
        //esta es la funcion que se encarga del reemplazo
        //acorde a la poscion que esta obteniendo respecto del caracter
        //para recorrer el abc
        return String(txt).replace(re, function (match) {
            return replace(match);
        });
    };

    //hay que definir la accion a realizar en el algoritmo
    return {
            encode: function(txt, desp) {
            return proceso(txt, desp, true);
        },
            decode: function(txt, desp) {
            return proceso(txt, desp, false);
        }
    };
})();

//ahora vamos a realizar la correspondiente funcion

//codificar o cifrar
function cifrar(){
    document.getElementById("resultado").innerHTML = 
    cesar.encode(document.getElementById("cadena").value, 3);
}

//decodificar o descifrar
function descifrar(){
    document.getElementById("resultado").innerHTML = 
    cesar.decode(document.getElementById("cadena").value, 3);
}


function PhraseToArray(Frase)
  {
   var Out = [];
   for(var i = 0; i < Frase.length; i++)
   {
    var CodeNumber = Frase.charCodeAt(i);
    if(CodeNumber >= 97 && CodeNumber <= 122)
    {
     CodeNumber = CodeNumber - 32;
    }
    if(CodeNumber == 209 || CodeNumber == 241)
    {
     Out.push(15);
    }
    if(CodeNumber == 32)
    {
     Out.push(32);
    }
    else{
     if(CodeNumber-64 < 15)
     {
      Out.push(CodeNumber-64);
     }
     else if(CodeNumber-64 >= 15 && CodeNumber-64 < 28){
      Out.push(CodeNumber-63);
     }
    }
   }
   return Out;
}
function Encriptar(ModeEnc)
  {
   var GetPhrase = document.getElementById('InputText').value;
   var GetPass = document.getElementById('PassWord').value;
   var Codes = [];
   if(GetPhrase.length < 1 || GetPass.length < 1)
   {
    alert('La frase/contraseña no puede estar en blanco')
    return;
   }
   var PassData = PhraseToArray(GetPass);
   var PhraseData = PhraseToArray(GetPhrase);
   var SpaceCount = 0;
   if (ModeEnc == true)
   {
    for(var i = 0; i < PhraseData.length; i++)
    {
     if(PhraseData[i] == 32)
     {
      Codes.push(32);
      SpaceCount += 1;
     }else{
      Codes.push((PassData[(i - SpaceCount) % PassData.length] + PhraseData[i]) % 27);
     }
    }
   }else{
    for(var i = 0; i < PhraseData.length; i++)
    {
     if(PhraseData[i] == 32)
     {
      Codes.push(32);
      SpaceCount += 1;
     }else{
      var Value = PhraseData[i] - PassData[(i - SpaceCount) % PassData.length];
      if (Value < 1)
      {
       Value += 27;
      }
     Codes.push(Value % 27);
     }
    }
   }
   return Codes;
}

  //Devolver resultado
function RebuildString(Codigos)
  {
   var Salida = ""
   for(var i = 0; i < Codigos.length; i++)
   {
    if (Codigos[i] == 15 )
    {
     Salida += String.fromCharCode(209);
    }
    if (Codigos[i] == 32)
    {
     Salida += String.fromCharCode(32);
    }
    if (Codigos[i] == 0)
    {
     Salida += String.fromCharCode(90);
    }
    if(Codigos[i] < 15 && Codigos [i] > 0)
     Salida += String.fromCharCode(Codigos[i]+64);
    else if(Codigos[i] > 15 && Codigos[i] < 28){
     Salida += String.fromCharCode(Codigos[i]+63);
    }
   }
   document.getElementById('Result').value = Salida;
}
