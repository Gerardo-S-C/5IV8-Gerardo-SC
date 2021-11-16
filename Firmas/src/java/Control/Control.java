/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.*;
import java.security.KeyPair;

/**
 *
 * @author illum
 */
public class Control {
    private CifrarRSA cifrarRSA;
    private Firma firma;
    private Validar validar;

    public Control() {
        try {
            cifrarRSA = new CifrarRSA();
            firma = new Firma();
            validar = new Validar();
        } catch (Exception e) {
            
        }
    }

    public void generarClave(String password) {
        cifrarRSA.GeneradorRSA(password);
    }

    public void firmarArchivo(String rutaArchivo, String rutaFirma, String passwordKeyPair) throws Exception {
        KeyPair parDeClaves = cifrarRSA.getClaves().get(passwordKeyPair);
        if (parDeClaves == null) {
            throw new Exception("Clave inexistente.");
        } else {
            firma.firmarArchivo(rutaArchivo, parDeClaves.getPrivate(), rutaFirma);
        }
    }

    public boolean validarFirma(String rutaArchivo, String rutaFirma, String rutaClavePublica) throws Exception {
        return validar.ValidarFirma(rutaArchivo, rutaFirma, rutaClavePublica);
    }
    
    public void exportarClavePublica(String rutaClavePublica, String passwordKeyPair) throws Exception {
        cifrarRSA.ExportarClavePublica(rutaClavePublica, passwordKeyPair);
    }
}
