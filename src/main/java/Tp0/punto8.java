/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp0;

/**
 *
 * @author male_
 */
public class punto8 {
    /* Dadas las notas de los estudiantes de un colegio en el primer curso 
    de un bachillerato en las diferentes asignaturas (5 por comodidad*/
    
    private static int [][]estudianteNotas;
    //fila representa al estudiante
    //columna representa la asignatura
    
    
    //a) Calcular la media de cada estudiante
 private static void calcularMediaCadaEstudiante(){
    for (int i = 0; i < estudianteNotas.length; i++) { // para cada estudiante
        int suma = 0; // variable para acumular la suma de las notas
        for (int j = 0; j < estudianteNotas[i].length; j++) { // para cada asignatura
          suma += estudianteNotas[i][j]; // sumar la nota a la suma
        }
      int media =(suma / estudianteNotas[i].length); // calcular la media dividiendo la suma por el número de notas
       System.out.println("La media del Estudiante " + (i+1) + " es: " + media); // mostrar la media por pantalla
    }
}

// b) calcular la media de cada asignatura
private static void calcularMediaCadaAsignatura(){
    for (int j = 0; j < estudianteNotas[0].length; j++) { // para cada asignatura
      int suma = 0; // variable para acumular la suma de las notas
         for (int i = 0; i < estudianteNotas.length; i++) { // para cada estudiante
              suma += estudianteNotas[i][j]; // sumar la nota a la suma
        }
     double media = suma / estudianteNotas.length; // calcular la media dividiendo la suma por el número de estudiantes
     System.out.println("La media de la asignatura " + (j+1) + " es: " + media); // mostrar la media por pantalla
    }
}
 
//c) calcular la media total de la clase
private static void calcularMediaClase(){
    int sumaTotal = 0; // variable para acumular la suma de todas las notas
    for (int i = 0; i < estudianteNotas.length; i++) { // para cada estudiante
        for (int j = 0; j < estudianteNotas[i].length; j++) { // para cada asignatura
            sumaTotal += estudianteNotas[i][j]; // sumar la nota a la suma total
        }
    }
    int mediaClase = sumaTotal / (estudianteNotas.length * estudianteNotas[0].length); // calcular la media dividiendo la suma total por el número de alumnos y el número de asignaturas
    System.out.println("La media de la clase es: " + mediaClase); // mostrar la media por pantalla
}


        /*------------------------------------------ACLARACIONES PUNTO D------------------------------------------*/
//d) Ordenar el listado de alumnos por orden decreciente de notas medias individuales que inlcuyen todas las materias

//Decidimos usar metodos basicos de ordenamiento que aprendimos en desarrollo porque consideramos 
//que no vamos a trabajar con una gran cantidad de datos 
//De estos metodos basicos decidimos utilizar el burbuja mejorado que es mas eficiente que el burbuja normal
//y el metodo de inserccion
//ambos son de los mas eficientes al trabajar con poco volumen de datos



private static void ordenarEstudiantesMetodoBurbujaMejorado(int [] a){
       int i=0,j,temp;
       boolean intercambio=true;
           while (intercambio&&i<a.length-1){
               intercambio=false;
              for (j=0;j<a.length-1-i;j++){
                  if (a[j+1]>a[j]){
                      temp=a[j];
                      a[j]=a[j+1];
                      a[j+1]=temp;
                      intercambio=true;
                  }
              }       
           i++;
           }
      mostrarArreglo(a);     
   }

private static void ordenarEstudiantesMetodoInserccion(int []a){
  int p,j,temp;
     for (p=1;p<a.length;p++){
         temp=a[p];
         j=p;
         while (j>0&&temp>a[j-1]){
             a[j]=a[j-1];
             j--;
         }
         a[j]=temp;
     }
     mostrarArreglo(a);
 }

private static int [] guardarMediaEstudiantes(){
   //Metodo utilizado por los metodos de ordenamiento
   //Se guarda las notas medias individuales de cada estudiante en un arreglo para asi poder ordenar
   
    int [] mediaEstudiantes = new int [estudianteNotas.length];
    //ahora calculo la media de cada estudiante
      for (int i = 0; i < estudianteNotas.length; i++) { // para cada estudiante
        int suma = 0; // variable para acumular la suma de las notas
        for (int j = 0; j < estudianteNotas[i].length; j++) { // para cada nota
          suma += estudianteNotas[i][j]; // sumar la nota a la suma
        }
      int media =(suma / estudianteNotas[i].length); // calcular la media dividiendo la suma por el número de notas
      mediaEstudiantes[i]=media;
     }
      return mediaEstudiantes;
  }

 private static void mostrarArreglo(int []a){
       for (int i: a){
           System.out.println(i);
       }
   }



    public static void main(String[] args) {
        System.out.println("Mostrando media de cada estudiante");
        calcularMediaCadaEstudiante();
        System.out.println("Mostrando media de cada asignatura");
        calcularMediaCadaAsignatura();
        System.out.println("Mostrando media de la clase");
        calcularMediaClase();
        System.out.println("Ordenando estudiantes por metodo burbuja mejorado");
        ordenarEstudiantesMetodoBurbujaMejorado(guardarMediaEstudiantes());
        System.out.println("Ordenando estudiantes por metodo inserccion");
        ordenarEstudiantesMetodoInserccion(guardarMediaEstudiantes());
    }

}    


    

