/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tp0.Punto1;

import static Tp0.Punto1.Ejercicio3.CANTNUMEROS;
import static Tp0.Punto1.Ejercicio3.MAX_VALOR;
import static Tp0.Punto1.Ejercicio3.MIN_VALOR;
import static Tp0.Punto1.Ejercicio3.NOMBRE_ARCHIVO;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * PUNTO 1 EJERCICIO 4
 * Generar un archivo de texto con cadenas aleatorias de 10 caracteres alfanumericos (0-9, a-z, ´
A-Z).
 */
public class Ejercicio4 {

    
 private static void generarArchivo(){
          String nombreArchivoSalida = "src/main/java/Tp0/caraterAlfanumerico.txt";
     try{
            FileWriter escritorArchivo = new FileWriter(nombreArchivoSalida);
            
            BufferedWriter buff = new BufferedWriter(escritorArchivo);

        int valoresRandom = 10;
        String salida = "";

        for (int i = 0; i < valoresRandom; i++) {
            int valorRandom = 0;
            switch((int)(Math.random()*3)){
                case 0:
                    valorRandom = (int) (48 + (Math.random() * (57 - 48)));  //De 0 -1
                    break;
                case 1:
                    valorRandom =  (int) (65 + (Math.random() * (90 - 65))); //De A-Z
                    break;
                case 2:
                    valorRandom =  (int) (97 + (Math.random() * (122 - 97))); //De a-z
                    break;
            }

            char caracterAscii = (char) valorRandom;
           salida = salida +caracterAscii+"\n";
        }
         buff.write(salida);
        buff.close();
     } catch (FileNotFoundException ex) {
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
