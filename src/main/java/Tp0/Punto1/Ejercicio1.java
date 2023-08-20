/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tp0.Punto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *PUNTO 1 EJERCICIO 1 
 * Utilizando un archivo de entrada que contenga un texto con varias palabras y varias l´ıneas de
texto y uno o mas espacios en blanco entre ellas, generar ´ sinEspacios.txt con el mismo texto
pero eliminando todos los espacios en blanco
 */
public class Ejercicio1 {
    public static void main(String[] args) {
         String nombreArchivoEntrada = "src/main/java/Tp0/entrada.txt";
         String nombreArchivoSalida = "src/main/java/Tp0/sinEspacios.txt";
         
         String linea = null;
         
         try {
            /* FileReader es una clase que como indica el nombre, nos permite
             * leer texto desde un archivo. */
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);
            /* De la misma manera, nos interesa poder escribir en un archivo, no
             * solo por la salida estandar (consola).*/
            FileWriter escritorArchivo = new FileWriter(nombreArchivoSalida);

            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);

            /* Mientras el buffer de lectura tenga algo por leer desde el archivo,
             * imprimimos cada linea por pantalla y la grabamos tal cual en el archivo.
             */
            String salida ="";
            while ((linea = bufferLectura.readLine()) != null) {
                if(!linea.trim().isEmpty()){
                     linea = linea.replaceAll(" ","");
                     salida = salida +linea+"\n";
                }        
            }
           bufferEscritura.write(salida);


            bufferLectura.close();
            bufferEscritura.close();
        }
        catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        }
        catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }
    
         
}


         


