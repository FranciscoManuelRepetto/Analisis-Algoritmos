package ejemplos_InOut;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Podemos usar una instancia de la clase Scanner para poder leer datos de forma más comoda que con
 * un Reader "pelado". Recordamos que un reader solo nos provee operaciones para leer o un byte o
 * una linea completa (hasta el siguiente \n o \r ).
 *
 * Scanner nos permite encapsular un objeto Reader, un Stream (como System.in) o un String y obtener a
 * partir de ese objeto secuencialmente datos de tipos primitivos o incluso substrings separados sin
 * que necesitemos controlar manualmente como se reconocen esos datos.
 *
 * https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
 * @author jpinero
 */
public class Ejemplo_3_Scanner {
    static final int CANTNUMEROS = 10;
    static final int MAX_VALOR = 1000000;
    static final String NOMBRE_ARCHIVO = "src/ejemplo_InOut/numeros.txt";

    private static void leerArchivo_1(){
    	try{
    		BufferedReader buff = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
    		Scanner s = new Scanner(buff);
    		for (int i = 0; i < CANTNUMEROS  ; i++){
    			//System.out.println( Integer.parseInt(buff.readLine()));
    			System.out.println(s.nextInt());
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

    private static void leerArchivo_2(){
    	try{
    		BufferedReader buff = new BufferedReader(new FileReader("src/ejemplo_InOut/ejemplo_scanner"));
    		Scanner s = new Scanner(buff);
    		//Si falla la lectura del float, cambiar el '.' por coma, cuestion de configuracion de idiomas.
    		s = new Scanner("123141   4243.433     unaPalabra\no muchas palabras seguidas.");

    		System.out.println("Scanner nos da herramientas comodas para leer datos primitivos de distinto tipo");
    		System.out.println(s.nextInt() +" " + s.nextFloat() + " " + s.next() + " ");
    		System.out.println(s.nextLine());
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
    	leerArchivo_1();
    	leerArchivo_2();
    }
}