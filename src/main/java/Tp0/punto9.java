package Tp0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * PUNTO 9
 * Se leen dos listas de numeros enteros, A y B de 100 y 60 elementos, respectivamente. Se
desea resolver mediante procedimientos las siguientes tareas:
    a) Ordenar aplicando un metodo de ordenaci ´ on distinto a cada una de las listas ´ A y B
    b) Crear una lista C a partir de la mezcla de las listas A y B ya ordenadas.
    c) Mostrar la lista C
 */
public class Punto9 {
    static final int MAX_VALOR = 57;
    static final int MIN_VALOR = 48;
   static final String NOMBRE_ARCHIVO_A ="src/ejemplos_InOut/entradaA.txt";
   static final String NOMBRE_ARCHIVO_B= "src/ejemplos_InOut/entradaB.txt";
   static final char []a= new char[100];
   static final char[] b= new char[60];
    
    
    public static void main(String[] args) {
        
        creacionInicializacion();
        ordenarArchivo(NOMBRE_ARCHIVO_A,true);
        ordenarArchivo(NOMBRE_ARCHIVO_B,false);
        generarArchivoC();
         
    }
    
    
    
    private static void creacionInicializacion(){
        /*Creamos los archivos A y B*/
        try{
            //generamos el archivo de salida
            FileWriter escritorArchivoA = new FileWriter(NOMBRE_ARCHIVO_A);
            FileWriter escritorArchivoB = new FileWriter(NOMBRE_ARCHIVO_B);
            

            BufferedWriter buffA = new BufferedWriter(escritorArchivoA); 
            BufferedWriter buffB = new BufferedWriter(escritorArchivoB);
                /*Ahora llenamos el archivo A con 100 elementos numericos*/
                
                for (int i =1; i < 101 ; i++){
                    char num = (char)(MIN_VALOR + (Math.random() * (MAX_VALOR-MIN_VALOR)));
                    buffA.write(num+"\n");
                }
            buffA.close();
                
                
                /*Y AHORa el B con 60 elementos numericos*/
            for (int i = 1; i < 61 ; i++){
    			char num = (char)(MIN_VALOR + (Math.random() * (MAX_VALOR-MIN_VALOR)));
    			buffB.write(num+"\n");
    		}
    		buffB.close();

    	
        } catch (IOException ex) {
            Logger.getLogger(Punto9.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generarArreglo(String nombreArchivoEntrada, int i, char []arr){
        try {
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            while ((linea = bufferLectura.readLine()) != null) {
                arr[i]= (linea.trim().charAt(0));
                i++;
            }
            bufferLectura.close();
            
         } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }
    
    
    //ORDENAR ARCHIVO 
    public static void ordenarArchivo(String nombreArchivoEntrada, boolean metodo) {
        /*Parametros 
            nombreArchivoEntrada = direccion del archivo a ordenar
            metodo = flag para ordenar segun quicksort y mergesort
        
        */           
            if(metodo){
                generarArreglo(nombreArchivoEntrada,0,a);
                quicksort(a, 0, (a.length-1)); //ordeno el metodo a con quicksort
                modificarArchivo(nombreArchivoEntrada, a);
            }
             else{
                generarArreglo(nombreArchivoEntrada,0,b);
                mergeSort(b, 0, (b.length-1));
                modificarArchivo(nombreArchivoEntrada, b);
            }

    }
    
    private static void modificarArchivo(String nombreArchivoEntrada, char []arr){
        try {
            //Abro el archivo
             FileWriter escritorArchivo = new FileWriter(nombreArchivoEntrada);

            
            //Guardamos en el archivo original
           BufferedWriter buffA = new BufferedWriter(escritorArchivo); 
                //Guardamos en el archivo original
                for (int j =0; j < arr.length ; j++){
                    buffA.write(arr[j]+"\n");
                }
            buffA.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Punto9.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*GENERANDO ARCHIVO C*/
    
   private static void generarArchivoC(){
    
        try {
            //Creo el archivo C asi despues lo puedo modificar
          FileWriter  escritorArchivoC = new FileWriter("src/ejemplos_InOut/archivoC.txt");
            //generamos el arreglo C
            char []c= new char [a.length+b.length];
            
            //lo cargamos primero con A y dps con B
            for (int i = 0; i < a.length+b.length; i++) {
                if(i<a.length)
                    c[i]=a[i];
                else
                    c[i]=b[i-a.length];
            }     

            //Reutilizo el merge que use para ordenar el archivo C
            merge(c, 0, (a.length-1), c.length-1);
            modificarArchivo("src/ejemplos_InOut/archivoC.txt", c);
            mostrarArreglo(c);
            
        } catch (IOException ex) {
            Logger.getLogger(Punto9.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   private static void mostrarArreglo(char []arr){
       for (char c: arr){
           System.out.println(c);
       }
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*---------------------------------------------METODOS DE ORDENAMIENTO-------------------------------------------*/
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
            if (a[i] - '0' <= pivot - '0')
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

        // completa el arreglo de la derecha y el de la izquierda
        for (int i = 0; i < n1; i++) {
            L[i] = array[p + i];
        }
        for (int j = 0; j < n2; j++) {
            M[j] = array[q + 1 + j];
        }

        //Mantiene el puntero de los sub-arreglos y del arreglo principal
        int i, j, k;
        i = 0;
        j = 0;
        k = p;

  
        //Comparamos el elemento i de L con el elemento j de M y lo colocamos ordenadamente en el arreglo
        
        while (i < n1 && j < n2) {
            if (L[i] - '0' <= M[j] - '0') {
                array[k] = L[i];
                i++;
            } else {
                array[k] = M[j];
                j++;
            }
            k++;
        }

        //Cuando terminamos con los elementos ya sea de L o M
        //Agarramos el sobrante y lo ponemos en A
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

    //Divide el arrglo en dos subarreglos, los ordena y los vuelve a unir
    public static void mergeSort(char array[], int left, int right) {
        if (left < right) {

            //mid es el punto en donde el arreglo es dividido en dos sub arreglos
            int mid = (left + right) / 2;

            //llamo rescursivamente a cada uno para ordenarlos
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            //Y los uno
            merge(array, left, mid, right);
        }
    }
}

