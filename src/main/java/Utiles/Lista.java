 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

/**
 *
 * @author repetto.francisco
 */
public class Lista {
   
    //Atributos//
    private Nodo cab;
    private int longitud=0;
    
    //Constructor//
    public Lista(){
        this.cab=null;
        this.longitud=0;
    }
    
    //Metodos//
    public boolean insertar(Object nuevoElem,int pos){
        //Metodo para inserta un nuevo en la posicion pos
        boolean exito=true;
        
        if(pos<1||pos>this.longitud+1)
            /*Error de posicion invalida*/
            exito=false;
        else{
            if(pos==1){
                /*Crea un nuevo nodo y se enlaza en la cabecera*/
                this.cab=new Nodo(nuevoElem,this.cab);
            }else{
                /*Avanza hasta el elemento en posicion pos-1*/
                Nodo aux=this.cab;
                int i=1;
                while(i<pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                Nodo nuevo=new Nodo(nuevoElem,aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longitud++;
            }
        return exito;
    }
    
    public boolean eliminar(int pos){
        //Metodo para eliminar un elemento en la posicio pos
        boolean exito=true;
        if(pos<1||pos>this.longitud)
            /*Error de posicion invalida*/
            exito=false;
        else{
            if(pos==1){
               this.cab=this.cab.getEnlace();
            }else{
                /*Avanza hasta el elemento en posicion pos-1*/
                Nodo aux=this.cab;
                int i=1;
                while(i<pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            this.longitud--;
        }

    return exito;  
    }
    
    
    public Object recuperar(int pos){
     /*Metodo para devolver el elemento en la posicion pos*/
     Object elem=null;
     if(pos<1||pos>this.longitud+1)
         /*Error de posicion invalida*/
         elem=null;
     else{
         Nodo aux=this.cab;    
         int i=1;
         while(i<pos){
                aux=aux.getEnlace();
                i++;
            }
         if(aux!=null)
         elem=aux.getElem();
        }   
     return elem;
    }
    
    public int localizar(Object elem){
     /*Metodo para devolver la posicion del objeto elem*/
     int pos=-1;
     Nodo aux=this.cab;
     int i=1;
     while(aux!=null){
         if(aux.getElem().equals(elem)){
             /*Encuentra el elemento entonces a pos le asigno la posicion
               Y aux le asigno null para cortar while*/
             pos=i;
             aux=null;
         }else{
             /*Avanza el aux y el contador aumenta en uno*/
             aux=aux.getEnlace();
             i++;
         }
     }
     return pos;
    }
    
    public int longitud(){
        /*Metodo para devolver la longitud de la lista*/
        int longitud;
        longitud=this.longitud;
        return longitud;
    }
    
    public boolean esVacia(){
        /*Metodo para ver si la lista esta vacia o no*/
        boolean vacia=false;
        if(this.cab==null)
            vacia=true;
        return vacia;
    }
    
    public void vaciar(){
        /*Metodo para vaciar la lista*/
        this.cab=null;
        longitud=0;
    }
    
    public Lista clone(){
        Lista clon=new Lista();
        if(this.cab==null)
            clon.cab=null;
        else{
            clon.cab=new Nodo(this.cab.getElem(),null);
            Nodo aux,aux2;
            /*Aux apunta al primer nodo de la lista y despues al nodo siguiente*/
            aux=this.cab;
            aux=aux.getEnlace();
            /*Aux2 apunta al primer nodo de la lista clon*/
            aux2=clon.cab;
            /*Aux avanza hasta pasarse de la ultima posicion*/
            while(aux!=null){
                aux2.setEnlace(new Nodo(aux.getElem(),null));
                aux=aux.getEnlace();
                aux2=aux2.getEnlace();
            }
            clon.longitud=this.longitud;
        }
        return clon;
        
    }
    
    public String toString(){
        /*Metodo para mostrar todo los elementos de la lista*/
        String elem="";
        Nodo aux=this.cab;
     if(aux==null)
         elem=null;
     else{
         elem+="[";
         while(aux!=null){
         elem+=aux.getElem().toString();
         aux=aux.getEnlace();
         if(aux!=null)
             elem+=",";
         }
         elem+="]";
     }
     return elem;
    }
    
    /*EJERCICIO PRACTICA PARA PARCIAL*/
    
    /*Agregar a TDA Lista el método agregarElemento(Object nuevo, int x),
    que recorre la lista una sola vez y agregando el elemento nuevo en la
    primer posición y lo agrega cada x posiciones. Ejemplo: Sea la lista
    [1,2,3,4,5,6,7], nuevo= 0 y x = 2 modifica la lista para que quede
    [0,1,2,0,3,4,0,5,6,0,7]*/
    
    public boolean agregarElemento(Object elem,int x){
        boolean puesto;
        if(x<1||x>this.longitud+1)
            /*Ingreso una posicion invalida*/
            puesto=false;
        else{
            int i=0;
            this.cab=new Nodo(elem,this.cab);
            Nodo aux=this.cab,nuevo;
            while(aux!=null){
                if(i==x){
                    /*Busco las posiciones multiplos de x*/
                    nuevo=new Nodo(elem,aux.getEnlace());
                    aux.setEnlace(nuevo);
                    i=-1;
                }
                aux=aux.getEnlace();
                i++;
            }
            puesto=true;
        }
        return puesto;
    }
    
    
}
