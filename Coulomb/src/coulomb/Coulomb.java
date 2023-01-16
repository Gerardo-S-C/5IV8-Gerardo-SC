/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coulomb;

import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author illum
 */
public class Coulomb {

    /**
     * @param args the command line arguments
     */

    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int tot_cargas, coord_vec, Q;
        int i, j;
        System.out.print("Ingrese la cantidad de cargas a evaluar: ");
        tot_cargas=sc.nextInt();
        int[][] coordenadas = new int[tot_cargas][3];//para limitar a 3 variables

        try{
            //para llenar las posiciones de las cargas en xyz
            for(i=0; i<tot_cargas; i++){
                OUTER:
                for (j=0; j<3; j++) {
                    String pos="";
                    switch (j) {
                        case 0:
                            pos="Z";
                            break;
                        case 1:
                            pos="Y";
                            break;
                        case 2:
                            pos="X";
                            break;
                        default:
                            break;
                    }
                    System.out.println("Ingrese las coordenadas "+pos+" de la carga "+(i+1));
                    coord_vec=sc.nextInt();
                    coordenadas[i][j]=coord_vec;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        int[] carga = new int[tot_cargas];//guarda el valor de las cargas por particula
        int cargaINT;
        System.out.println("Ingrese la carga de cada particula: ");
        for(i=0; i<tot_cargas; i++){
            cargaINT=sc.nextInt();
            carga[i]=cargaINT;
        }
        
        System.out.println("Las coordenadas de cada particula con su carga son: ");
        //asocia el valor de cada carga desde la primera hasta la ultima ingresada
        for(i=0; i<tot_cargas; i++){
            System.out.print("q"+(i+1)+": ");
            for(j=0; j<3; j++){
                String vector="";
                switch (j) {
                        case 0:
                            vector="k ";
                            break;
                        case 1:
                            vector="j ";
                            break;
                        case 2:
                            vector="i ";
                            break;
                        default:
                            break;
                    }
                System.out.print(coordenadas[i][j]+""+vector);
            }
            System.out.print("  ->  "+carga[i]+"*10^(-6) C");
            System.out.println("");
        }
        
        System.out.println("NOTA: Las magnitudes estan en microCoulombs...");
        System.out.println("Ingrese ahora la carga sobre la cual se va a calcular"
                + " la fuerza electrica");
        
        Q=sc.nextInt()-1;
        System.out.println("La carga elegida fue q"+(Q+1));
        int tempX, tempY, tempZ;
        tempX=coordenadas[Q][0];
        tempY=coordenadas[Q][1];
        tempZ=coordenadas[Q][2];
        //System.out.println("Carga de prueba: "+coordenadas[Q][0]+" "+coordenadas[Q][1]+" "+coordenadas[Q][2]);
        //System.out.println("Carga de prueba: "+temp[0][0]+" "+temp[0][1]+" "+temp[0][2]);
        int [][] temp = {{tempX, tempY, tempZ}};//carga Q sobre la que se evaluara despues
        System.out.println("Carga temporal para las ecuaciones de vectores: ");
        for(i=0; i<1; i++){
            for(j=0; j<3; j++){
                System.out.print(temp[i][j]+" ");
            }
            System.out.println("\n");
        }
        //metodo para calcular la distancia de Q al resto de particulas con otra
        //iteracion diferente a la previa, pues esta solo asigna el Q temporal
        //compraracion de arreglos para que no evalue la misma particula
        int[][]Rv = new int [coordenadas.length][coordenadas[0].length];
        for(i=0; i<tot_cargas; i++){
            for(j=0; j<3; j++){
                Rv[i][j]=temp[0][j]-coordenadas[i][j];
                System.out.print(Rv[i][j]+" ");
            }
            System.out.println("");
        }
        //operaciones para sacar el vector unitario
        //igualando (0,0,0)=0 para que sume 0 y no afecte
        double vecU = 0;
        double vecU0 = 0;
        double raiz = 0;
        double[] vectoresU = new double [tot_cargas];
        for(i=0; i<tot_cargas; i++){
            for(j=0; j<3; j++){
                if(Rv[i][j]==vecU0){
                    //System.out.println("Rv = (0,0,0)");
                    vecU=vecU+vecU0;
                    raiz=1;
                }else{
                    vecU = Math.pow(Rv[i][j],2)+vecU;
                    raiz = Math.sqrt(vecU);
                }
                System.out.println("Total de ["+i+","+j+"] "+vecU);
                System.out.println("La raiz en ["+i+"] es: "+raiz);
                vectoresU[i] = raiz;
            }
            vecU=0;
        }

        //repasemos los valores que tenemos hasta ahora que necesitamos para la
        //formula de la ley de coulomb de forma vectorial
        //k=9*10^9
        //Q=la carga a elegir
        //q=resto de cargas a evaluar
        //magnitud^2 del vector= vectoresU^2
        //vector unitario=Rv/vectoresU
        //por lo tanto, ya podemos sustituir y realizar las operaciones
        double[] Respuesta = new double[3];
        double k = 9*Math.pow(10, 9);
        double micro = 1*Math.pow(10, -6);
        double cargaQ = Math.round(k*(carga[Q]*micro));
        double cargaq = 0;
        double [][] resultadoTEMP = new double[tot_cargas][3];
        System.out.println("constante de carga Q evaluada: "+cargaQ);
        for(i=0; i<tot_cargas; i++){
            if(carga[Q]==carga[i]){
                carga[i]=0;
            }else{
                cargaq=carga[i];
            }
            System.out.println("Carga en q"+(i+1)+": "+cargaq);
            System.out.println("La raiz en r"+Q+(i+1)+" es: "+vectoresU[i]);
            for(j=0; j<3; j++){
                resultadoTEMP[i][j] = ((cargaq)*Rv[i][j])/(Math.pow(vectoresU[i], 3));
                System.out.println("Resultado TEMP: "+resultadoTEMP[i][j]);
            }
        }
        //para obtener la suma de los valores independientes de cada particula evaluada
        //con la particula Q
        double sumtotZ=0;
        double sumtotY=0;
        double sumtotX=0;
        double[] FIN = new double[3];
        System.out.println("VECTOR FINAL: ");
        for(i=0; i<tot_cargas; i++){
            sumtotZ+=resultadoTEMP[i][0];
            sumtotY+=resultadoTEMP[i][1];
            sumtotX+=resultadoTEMP[i][2];
        }
        /*
        System.out.println("La suma en X es de: "+sumtotX);
        System.out.println("La suma en Y es de: "+sumtotY);
        System.out.println("La suma en Z es de: "+sumtotZ);
        */
        
        System.out.println("La fuerza en forma vectorial (Z, Y, X) es: ");
        double I,J,K,mag;
        I=cargaQ*sumtotX;J=cargaQ*sumtotY;K=cargaQ*sumtotZ;
        System.out.println("("+I+")i + ("+J+")j + ("+K+")k");
        mag=Math.sqrt(Math.pow(I, 2)+Math.pow(J, 2)+Math.pow(K, 2));
        System.out.println("Y su magnitud es de: "+mag+"N");
    }
    
}
