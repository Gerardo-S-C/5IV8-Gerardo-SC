/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


/**
 *
 * @author illum
 */
public class CifrarRSA {

    private HashMap<String, KeyPair> claves;
    private KeyPairGenerator generador;
    
    public CifrarRSA () throws NoSuchAlgorithmException{
        generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(1024);
        claves = new HashMap<>(100);
    }
    public void GeneradorRSA(String password){
        claves.put(password, generador.generateKeyPair());
    }
    
    public void ExportarClavePublica(String rutaClavePublica, String passwordKeyPair) throws Exception{
        KeyPair parDeClaves = claves.get(passwordKeyPair);
        if (parDeClaves == null) {
            throw new Exception("Clave inexsitente.");
        } else {
            FileOutputStream fos = new FileOutputStream(rutaClavePublica);
            fos.write(parDeClaves.getPublic().getEncoded());
            fos.close();
        }
    }

    public HashMap<String, KeyPair> getClaves() {
        return claves;
    }

    public void setClaves(HashMap<String, KeyPair> claves) {
        this.claves = claves;
    }

    public KeyPairGenerator getGenerador() {
        return generador;
    }

    public void setGenerador(KeyPairGenerator generador) {
        this.generador = generador;
    }
}
