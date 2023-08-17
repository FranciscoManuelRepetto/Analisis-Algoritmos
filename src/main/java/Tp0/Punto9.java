/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tp0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Punto9 {
    
    public static void main(String[] args) {
        String nombreArchivoEntradaA = "src/main/java/Tp0/entradaA.txt";
        String nombreArchivoEntradaB = "src/main/java/Tp0/entradaB.txt";
        
        char[] arrA = null,arrB = null;
        ordenarArchivo(nombreArchivoEntradaA, arrA,true);
        ordenarArchivo(nombreArchivoEntradaA, arrA,false);
         
    }

    
    //ORDENAR ARCHIVO 
    public static void ordenarArchivo(String nombreArchivoEntrada, char[] arr, boolean metodo) {
        /*Parametros 
            nombreArchivoEntrada = direccion del archivo a ordenar
            arr = arreglo de los elementos del archivo
            metodo = flag para ordenar segun quicksort y mergesort
        */
        try {
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);

            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            Scanner archivo = new Scanner(bufferLectura);

            arr = (archivo.next()).toCharArray();
            
            if(metodo)
            quicksort(arr, 0, (arr.length));
            else
            mergeSort(arr, 0, (arr.length));

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

    }
    
    
    // ----------QUICKSORT----------
    public static void swap (char[] arr, int i, int j)
    {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    // Partición usando el esquema de partición de Lomuto
    public static int partition(char[] a, int start, int end)
    {
        // Elija el elemento más a la derecha como un pivote de la array
        char pivot = a[end];
 
        // los elementos menores que el pivote serán empujados a la izquierda de `pIndex`
        // elementos más que el pivote serán empujados a la derecha de `pIndex`
        // elementos iguales pueden ir en cualquier dirección
        int pIndex = start;
 
        // cada vez que encontramos un elemento menor o igual que el pivote,
        // `pIndex` se incrementa, y ese elemento se colocaría
        // antes del pivote.
        for (int i = start; i < end; i++)
        {
            if (a[i] <= pivot)
            {
                swap(a, i, pIndex);
                pIndex++;
            }
        }
 
        // intercambiar `pIndex` con pivote
        swap(a, end, pIndex);
 
        // devuelve `pIndex` (índice del elemento pivote)
        return pIndex;
    }
 
    // Rutina de clasificación rápida
    public static void quicksort(char[] a, int start, int end)
    {
        // condición base
        if (start >= end) {
            return;
        }
 
        // reorganizar los elementos a través del pivote
        int pivot = partition(a, start, end);
 
        // recurre en un subarray que contiene elementos menores que el pivote
        quicksort(a, start, pivot - 1);
 
        // se repite en el subarray que contiene más elementos que el pivote
        quicksort(a, pivot + 1, end);
    }
    
        //----------MERGE SORT----------
    public static void merge(char array[], int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        char L[] = new char[n1];
        char M[] = new char[n2];

        // fill the left and right array
        for (int i = 0; i < n1; i++) {
            L[i] = array[p + i];
        }
        for (int j = 0; j < n2; j++) {
            M[j] = array[q + 1 + j];
        }

        // Maintain current index of sub-arrays and main array
        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        // Until we reach either end of either L or M, pick larger among
        // elements L and M and place them in the correct position at A[p..r]
        // for sorting in descending
        // use if(L[i] >= <[j])
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = M[j];
                j++;
            }
            k++;
        }

        // When we run out of elements in either L or M,
        // pick up the remaining elements and put in A[p..r]
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = M[j];
            j++;
            k++;
        }
    }

    // Divide the array into two sub arrays, sort them and merge them
    public static void mergeSort(char array[], int left, int right) {
        if (left < right) {

            // m is the point where the array is divided into two sub arrays
            int mid = (left + right) / 2;

            // recursive call to each sub arrays
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge the sorted sub arrays
            merge(array, left, mid, right);
        }
    }
}
