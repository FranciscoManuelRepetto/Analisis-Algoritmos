/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tp0;

import utiles.TecladoIn;

/**
 *
 * PUNTO 3
 * Realizar un programa que nos pida un numero n y nos diga cuantos numeros primos existen 
entre 1 y n.
 */
public class Punto3 {
    public static void main(String[] args) {
        int n = 0;
        System.out.println("Escriba un numero n para obtener cantidad de numeros primos entre n y 1:");
        n = TecladoIn.readInt();
        while(n <= 1){
            System.out.println("Tiene que ser mayor a 1");
            n = TecladoIn.readInt();
        }
        
        int j = 0;
        for (int i = 2; i < n; i++) {
            if(esPrimo(i)){
                j++;
            }
        }
        System.out.println("Entre "+n+" y 1 existen "+j+" numeros primos");
        
    }
    

    static boolean esPrimo(int num) {
        boolean esPrimo;
        if (num <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }

        // Verificar divisibilidad desde 2 hasta la raíz cuadrada del número
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // El número es divisible, por lo que no es primo
            }
        }

        return true; // Si no es divisible por ningún número, es primo
    }
}
