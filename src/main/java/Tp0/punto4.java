/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp0;

import java.util.Scanner;

/**
 *
 *PUNTO 4
 * Realizar un juego para adivinar un numero: Generar un n ´ umero entero en forma aleatoria ´
dentro de un rango, y luego, ir pidiendo numeros indicando ´ mayor o menor segun sea mayor ´
o menor con respecto a n. El proceso termina cuando el usuario acierta.
 */
public class Punto4 {
    public static void main(String[] args) {
       int numeroRandom= (int)(1 + (Math.random() * (100-1)));
        Scanner sc= new Scanner(System.in);
        int nroUsuario=-1;
        
       while(nroUsuario!=numeroRandom){
           System.out.println("Ingrese un numero entre 1 y 100");
           nroUsuario=sc.nextInt();
           
           if(nroUsuario<0 || nroUsuario>100)
               System.out.println("No ingreso un numero valido");
           else{
               if(nroUsuario>numeroRandom)
                   System.out.println("El numero a adivinar es menor que "+nroUsuario);
               else
                   System.out.println("El numero a adivinar es mayor que "+nroUsuario);
           }
               
        }
       
        System.out.println("Felicidades! El numero a adivinar era "+nroUsuario);
       
    }
    
}
