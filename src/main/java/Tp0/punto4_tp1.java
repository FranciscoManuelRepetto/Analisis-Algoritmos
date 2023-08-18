/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos_InOut;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author male_
 */
public class punto4_tp1 {
     static final int CANTNUMEROS = 1000;
     static final int MAX_VALOR = 1000;
    static final int MIN_VALOR = 1;
    static final String NOMBRE_ARCHIVO = "src/ejemplos_InOut/numerosSinRepetir.txt";
    static ArbolAVL a = new ArbolAVL();
    
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
