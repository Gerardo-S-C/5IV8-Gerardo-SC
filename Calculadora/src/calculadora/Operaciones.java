/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author illum
 */
public class Operaciones {
    
    private String cadena;
    private double resultado;
    private boolean suma;
    private boolean resta;
    private boolean multiplicacion;
    private boolean division;
    private boolean raiz;
    private boolean seno;
    private boolean coseno;
    private boolean tangente;
       
    
    public Operaciones(){
        
        cadena="";
        suma=false;
        resta=false;
        multiplicacion=false;
        division=false;
        raiz=false;
        seno=false;
        coseno=false;
        tangente=false;
    }
    
    public String concatenar(String num){
        
        this.cadena=this.cadena+num; 
    return this.cadena;
    }
    
    public void suma(String cadena){
        this.resultado=Double.parseDouble(cadena);
        suma=true;
        this.cadena="";
    }
    public void resta(String cadena){
        this.resultado=Double.parseDouble(cadena);
        resta=true;
        this.cadena="";
    }
    public void multiplicacion(String cadena){
        this.resultado=Double.parseDouble(cadena);
        multiplicacion=true;
        this.cadena="";
    }
    public void division(String cadena){
        this.resultado=Double.parseDouble(cadena);
        division=true;
        this.cadena="";
    }
    public void seno(String cadena){
        this.resultado=Double.parseDouble(cadena);
        seno=true;
        this.cadena="";
    }
    public void coseno(String cadena){
        this.resultado=Double.parseDouble(cadena);
        coseno=true;
        this.cadena="";
    }
    public void tangente(String cadena){
        this.resultado=Double.parseDouble(cadena);
        tangente=true;
        this.cadena="";
    }
    
    
    public double resultado(String numero){
        
        if(suma==true){
            resultado=resultado+Double.parseDouble(numero);
        }
        else if(resta==true){
            resultado=resultado-Double.parseDouble(numero);
        }
        else if(multiplicacion==true){
            resultado=resultado*Double.parseDouble(numero);
        }
        else if(division==true){
            resultado=resultado/Double.parseDouble(numero);
        }
        else if(seno==true){
            double seno=Math.toRadians(resultado);
            resultado=Math.sin(seno);
        }
        else if(coseno==true){
            double cos=Math.toRadians(resultado);
            resultado=Math.cos(cos);
        }
        else if(tangente==true){
            double tangente=Math.toRadians(resultado);
            resultado=Math.tan(tangente);
        }
        cadena="";
        suma=false;
        resta=false;
        multiplicacion=false;
        division=false;
        raiz=false;
        seno=false;
        coseno=false;
        tangente=false;
        
        return resultado;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
    
}
