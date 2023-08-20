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
 *
 * PUNTO 1 EJERCICIO 2
 * Con el mismo archivo de entrada, generar el archivo lineasImpares.txt con solo las l ´ ´ıneas
impares del texto.
 */
public class Ejercicio2 {
    public static void main(String[] args) {
         String nombreArchivoEntrada = "src/main/java/Tp0/entrada.txt";
         String nombreArchivoSalida = "src/main/java/Tp0/espaciosImpares.txt";
         
         String linea = null;
         
         try {
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);

            FileWriter escritorArchivo = new FileWriter(nombreArchivoSalida);

            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);


            int i = 1;
            String salida = "";
            while ((linea = bufferLectura.readLine()) != null) {
                    if(i %2 != 0){
                       salida = salida +linea+"\n";
                    }
                    i++;
                }
            
            bufferEscritura.write( salida);
            
            
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

