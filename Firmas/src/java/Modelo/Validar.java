/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author illum
 */
public class Validar {
    public boolean ValidarFirma(String Archivo, String Firma, String ClavePublica) throws Exception {
        byte[] datos = LeerArchivo(Archivo);
        byte[] firma = LeerArchivo(Firma);
        PublicKey clavePublica = LeerClavePublicaDesdeArchivo(ClavePublica);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(clavePublica);
        signature.update(datos);

        return signature.verify(firma);
    }

    public byte[] LeerArchivo(String ruta) throws Exception {
        return Files.readAllBytes(Paths.get(ruta));
    }

    public PublicKey LeerClavePublicaDesdeArchivo(String ruta) throws Exception {
        byte[] keyBytes = LeerArchivo(ruta);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}
