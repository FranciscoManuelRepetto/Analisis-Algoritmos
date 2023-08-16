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
 *
 * @author Usuario
 */
public class Ejercicio1 {
    public static void main(String[] args) {
         String nombreArchivoEntrada = "src/main/java/Tp0/entrada.txt";
         String nombreArchivoSalida = "src/main/java/Tp0/sinEspacios.txt";
         
         String linea = null;
         
         try {
            /* FileReader es una clase que como indica el nombre, nos permite
             * leer texto desde un archivo.
             * https://docs.oracle.com/javase/7/docs/api/java/io/FileReader.html
             */
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);
            /* De la misma manera, nos interesa poder escribir en un archivo, no
             * solo por la salida estandar (consola).
             * https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html
             */
            FileWriter escritorArchivo = new FileWriter(nombreArchivoSalida);

            /* Si bien no es obligatorio, se recomienda fuertemente:
             * Usar buffer para la entrada/salida de un archivo de texto
             * Mejorar la performance de las operaciones de
             * lectura y escritura, pero también es más robusto-
             * https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html
             * https://docs.oracle.com/javase/7/docs/api/java/io/BufferedWriter.html
             */
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);

            /* Mientras el buffer de lectura tenga algo por leer desde el archivo,
             * imprimimos cada linea por pantalla y la grabamos tal cual en el archivo.
             */
            
            while ((linea = bufferLectura.readLine()) != null) {
                if(!linea.trim().isEmpty()){
                     linea = linea.replaceAll(" ","");
                     bufferEscritura.write(linea+"\n");
                }
               
            }

             /* Para evitar que los archivos corran riesgo de quedar corruptos,
             * es conveniente cerrarlos. Cerrando el buffer que envuelve un archivo
             * este se ocupa de cerrar tambien su correspondiente archivo.
             */
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

