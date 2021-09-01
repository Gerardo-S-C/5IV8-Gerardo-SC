/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teclado.numerico;

import java.util.Hashtable;
import java.util.Set;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author illum
 */
public class Operaciones {

    String cadena="";
    
    public String concatenar(String num){
        
        this.cadena=this.cadena+num; 
    return this.cadena;
    }
    
    public Hashtable<String, String> obtenerEquivalencias() {
        Hashtable<String, String> equivalencias = new Hashtable<>();
        equivalencias.put("A", "2");
        equivalencias.put("B", "22");
        equivalencias.put("C", "222");
        equivalencias.put("D", "3");
        equivalencias.put("E", "33");
        equivalencias.put("F", "333");
        equivalencias.put("G", "4");
        equivalencias.put("H", "44");
        equivalencias.put("I", "444");
        equivalencias.put("J", "5");
        equivalencias.put("K", "55");
        equivalencias.put("L", "555");
        equivalencias.put("M", "6");
        equivalencias.put("N", "66");
        equivalencias.put("O", "666");
        equivalencias.put("P", "7");
        equivalencias.put("Q", "77");
        equivalencias.put("R", "777");
        equivalencias.put("S", "7777");
        equivalencias.put("T", "8");
        equivalencias.put("U", "88");
        equivalencias.put("V", "888");
        equivalencias.put("W", "9");
        equivalencias.put("X", "99");
        equivalencias.put("Y", "999");
        equivalencias.put("Z", "9999");
        return equivalencias;
    }   
    public  String asciiNumero(String ascii) {
    Hashtable<String, String> equivalencias = obtenerEquivalencias();
    return equivalencias.getOrDefault(ascii, "");
    }
    
    public  String Numeroascii(String numeroBuscado){
    Hashtable<String,String> equivalencias=obtenerEquivalencias();
    Set<String> claves=equivalencias.keySet();
    
    for(String clave:claves){
        String numeros=equivalencias.get(clave);
        if(numeros.equals(numeroBuscado)){
            return clave;
        }
    }
    return "";
    }
    
    public  String codificar(String original){
    
        StringBuilder codificado = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
        String charComoCadenaYEnMayusculas = String.valueOf(original.charAt(i)).toUpperCase();
        String equivalencia = asciiNumero(charComoCadenaYEnMayusculas);
        codificado
                .append(equivalencia)
                .append(" ");
    }
    return codificado.toString();
    }
    
    public  String decodificado(String dif){
        StringBuilder decodificado = new StringBuilder();
        String[] numeros = dif.split(" ");
        for(String numeroActual: numeros){
            String equivalencia=Numeroascii(numeroActual);
            decodificado.append(equivalencia);
        }
       return decodificado.toString();
    }
    
    public String main(){
        Scanner sc = new Scanner(System.in);
        String codificado;
        System.out.println("Ingresa un texto y lo convertiré: ");
        String textoDeUsuario = sc.nextLine();
        codificado = decodificado(textoDeUsuario);
        System.out.println("El texto convertido es: ");
        JOptionPane.showMessageDialog(null,codificado);
        
        return codificado;
    }
    
}
