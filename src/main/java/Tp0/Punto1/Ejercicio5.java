/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp0.Punto1;
import Utiles.AVL;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * PUNTO 1 EJERCICIO 5
 * Generar un archivo con numeros aleatorios de los n ´ umeros del 1 al 1000 sin que los elementos ´
se repitan.
 */
public class Ejercicio5 {
     static final int CANTNUMEROS = 1000;
     static final int MAX_VALOR = 1000;
    static final int MIN_VALOR = 1;
    static final String NOMBRE_ARCHIVO = "src/ejemplos_InOut/numerosSinRepetir.txt";
    static AVL a = new AVL();
    
  private static void generarArchivo(){
    	try{
    		BufferedWriter buff = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO));
    		for (int i = 0; i < CANTNUMEROS ; i++){
    			Comparable num =(int)(MIN_VALOR + (Math.random() * (MAX_VALOR-MIN_VALOR)));
                        if(a.insertar(num)){
                            buff.write( num +"\n");
                        }
    			System.out.println(num);
    		}
    		buff.close();

    	}
    	catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        }
        catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }
 
    public static void main(String[] args) {
        generarArchivo();
    }

}
