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
 * PUNTO 1 EJERCICIO 3
 * Generar un archivo de texto que contenga 100 numeros reales (double o float) generados ´
aleatoriamente con valores entre -100 y 100.
 */
public class Ejercicio3 {
 static final int CANTNUMEROS = 100;
    static final int MAX_VALOR = 100;
    static final int MIN_VALOR = -100;
    static final String NOMBRE_ARCHIVO = "src/main/java/Tp0/randoms.txt";

 private static void generarArchivo(){
        try{
            BufferedWriter buff = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO));
            String salida = "";
            for (int i = 0; i < CANTNUMEROS ; i++){
                int num = (int)(MIN_VALOR + (Math.random() * (MAX_VALOR-MIN_VALOR)));
                salida = salida +num +"\n";
                System.out.println(num);
            }
             buff.write( salida);
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

