package ejemplos_InOut;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Este otro ejemplo esta para mostrar como generar 10000 valores enteros, menores
 * a 1,000,000 de forma aleatoria, y los guardar en un archivo, de a uno por linea.
 *
 * https://docs.oracle.com/javase/7/docs/api/java/util/Random.html
 *
 * Otro enfoque es usar math.random() que les devuelve un double entre 0 y 1, y luego
 * "desnormalizar" los datos hacia el rango que les interese.
 * Ejemplo para generar doubles entre 5 y 10.
 * int techo = 10; int piso = 5;
 * double aleatorio = piso + (Math.random() * (techo - piso));
 *
 */
public class Ejemplo_2_randomInts {
    static final int CANTNUMEROS = 10000;
    static final int MAX_VALOR = 1000000;
    static final String NOMBRE_ARCHIVO = "src/ejemplo_InOut/numeros.txt";

    private static void generarArchivo(){
    	try{
    		BufferedWriter buff = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO));
    		Random generador = new Random();
    		for (int i = 0; i < CANTNUMEROS ; i++){
    			int num = generador.nextInt(MAX_VALOR);
    			buff.write( num +"\n");
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

    private static int[] leerArchivo(){
    	int [] arreglo = new int[CANTNUMEROS];
    	try{
    		BufferedReader buff = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
    		for (int i = 0; i < CANTNUMEROS  ; i++){
    			// De cada linea debemos obtener la informacion que nos sirva, numeros, palabras aisladas...etc
    			arreglo[i] = Integer.parseInt(buff.readLine());

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
    	return arreglo;
    }

    private static void mostrarLosNMasGrandesDelArchivo(int n,int[] arreglo){
        Arrays.sort(arreglo);//Reemplazar esta llamada por sus propios metodos de ordenamiento.
        int tope = Math.max(0, arreglo.length - n);
        System.out.println("Los "+ n + " números más grandes son:");
        for (int i = arreglo.length-1; i > tope; i--){
        	System.out.println(arreglo[i]);
        }
    }

    public static void main(String[] args) {
    	/** Como nos interesa medir la eficiencia de nuestros algoritmos, realizaremos mediciones
    	 * de eficiencia de los metodos que nos interese, partiendo del supuesto de que tenemos
    	 * datos cargados en memoria, para suprimir el sesgo que nos traeria la E/S (por más que
    	 * sea lineal, involucra operaciones muy lentas, y que varian muchisimo de un sistema a
    	 * otro.
    	 *
    	 * Podemos ver como varia el tiempo de ejecución nuestro algoritmo en función del tamaño
    	 * de entrada.
    	 */
    	generarArchivo(); //Una vez que ya tenemos un archivo generado, no hace falta generar uno nuevo.
    	int [] arregloGenerado = leerArchivo();
    	long inicio = System.nanoTime(); // Tomamos la hora del sistema en nanosegundos

    	mostrarLosNMasGrandesDelArchivo(CANTNUMEROS,arregloGenerado);

    	long fin = System.nanoTime();
    	System.out.println("Se tardo: " + (fin - inicio) + "nanosegundos en obtener los numeros mas grandes");
    }
}