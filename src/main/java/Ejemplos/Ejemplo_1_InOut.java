package ejemplos_InOut;
//import java.io.*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta clase esta hecha para dar un primer ejemplo rapido de como funcionan
 * algunas clases provistas por Java para el manejo de entrada/salida desde archivos
 * de texto.
 *
 * En otras clases del mismo paquete veremos como generar archivos con caracteristicas
 * particulares, para generar conjuntos de valores random.
 */
public class Ejemplo_1_InOut {

    public static void main(String[] args) {
        /** Para abrir un archivo, tanto en modo lectura como en modo escritura,
         * necesitamos que el archivo exista en el sistema de archivos (en el lugar
         * desde el que lo estamos tratando de abrir).
         * En caso de que el archivo no exista, o que nuestro manejo del archivo
         * provoque fallas, errores, o cualquier tipo de excepción, debemos manejar
         * esas condiciones.
         *
         * NOTA: Si en vez de ejecutarlo desde un IDE como netbeans o eclipse, lo ejecutan por
         * consola, quiten de la ruta de los archivos el "src/ejemplo_InOut/" porque va a fallar
         * la creacion y apertura de archivos al apuntar a rutas que no existan.
         */
        String nombreArchivoEntrada = "src/Ejemplos/entrada.txt";
        String nombreArchivoSalida = "src/Ejemplos/salida.txt";
        //¿Que pasa si tratamos de leer un archivo que no existe?
        //nombreArchivoEntrada = "noexiste.txt";

        /** Mientras manipulamos archivos de text, será común que operemos con
         * strings que representaran lineas de texto.
         */
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
                System.out.println(linea);
                bufferEscritura.write(linea +"\n");
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