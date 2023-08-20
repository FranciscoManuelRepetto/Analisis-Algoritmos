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
public class AVL {
    //Atributos
    private NodoAVL raiz;
    
    //Constructor
    public AVL(){
        raiz=null;
    }
    
    //Operaciones principales
    public boolean pertenece(Comparable elem){
       boolean pertenece=perteneceAux(this.raiz,elem);
       return pertenece;
    }
    
    private boolean perteneceAux(NodoAVL nodo,Comparable elem){
        boolean pertenece=false;
        if(nodo!=null){
            if(elem.compareTo(nodo.getElem())== 0){
                /*Si elem es igual al elemento del nodo,
                entonces elem pertence a AVL*/
                pertenece=true;
            }else if(elem.compareTo(nodo.getElem())<0){
                /*Si el elemento es menor al elemento del nodo
                entonces lo busca en los hijos izquierdos*/
                pertenece=perteneceAux(nodo.getIzquierdo(),elem);
            }else{
                /*Si el elemento es mayor al elemento del nodo
                entonces lo busca en los hijos derechos*/
                pertenece=perteneceAux(nodo.getDerecho(),elem);
            }
        }
        return pertenece;
    }
    
    
    public boolean insertar(Comparable elem){
        boolean exito = true;
        if(this.raiz==null){
            this.raiz=new NodoAVL(elem,null,null);
        }else{
        exito=insertarAux(this.raiz,elem,null);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoAVL n,Comparable elem,NodoAVL padre){
        /*Precondicion n no es nulo*/
        boolean exito=true;
        if((elem.compareTo(n.getElem())==0)){
            /*Reportar error: Elemento repetido */
            exito=false;
        }else{
            if(elem.compareTo(n.getElem())< 0){
                /*Elemento es menor que n.getElem()
                Si tiene HI baja a la izquierda, sino agrega elemento*/
                if(n.getIzquierdo()!=null){
                    exito=insertarAux(n.getIzquierdo(),elem,n);
                }else{
                    n.setIzquierdo(new NodoAVL(elem,null,null));
                }
            }else{
                /*Elemento es mayor que n.getElem()
                si tiene HD baja a la derecha, sino agrega elemento*/
                if(n.getDerecho()!=null){
                    exito=insertarAux(n.getDerecho(),elem,n);
                }else{
                    n.setDerecho(new NodoAVL(elem,null,null));
                }
            }
        }
        /*Actualiza la altura de cada nodo a la vuelta y balancea el arbol*/
            n.recalcularAltura();
            NodoAVL aux;
            aux=balancear(n);
            if(aux!=null)
                /*Si hubo rotaciones, enlaza el nuevo subArbol y actualiza las alturas*/
                reenlace(aux,padre);
            
        return exito;
    }
    
    private int calcularBalance(NodoAVL nodo){
        int balance=0;
        if(nodo.getIzquierdo()!=null && nodo.getDerecho()!=null){
            balance=(nodo.getIzquierdo().getAltura())-(nodo.getDerecho().getAltura());
        }else{
            /*Calcula balance del nodo con un hijo nulo: altura -1*/
            if(nodo.getIzquierdo()!=null)
                /*Es +1 porque -(-1)= 1 */
                balance=nodo.getIzquierdo().getAltura()+1;
            if(nodo.getDerecho()!=null)
                balance=-1-nodo.getDerecho().getAltura();
        }
        return balance;
    }
    
    private NodoAVL balancear(NodoAVL nodo) {
        NodoAVL aux = null;
        /*Calcula el balance del nodo*/
        int balance = calcularBalance(nodo);
        /*Si el balance es 0, entonces el arbol esta perfectamente baleanceado
          Si el balance es 1, entonces el arbol esta levemente inclinado hacia la izquierda
          Si el balance es -1, entonces el arbol esta levemente inclinado hacia la derecha*/
        if (balance == 2) {
            /*Si el balance es 2, el arbol esta inclinado hacia la izquierda*/
            /*Calcula balance del hijo izquierdo*/
            int balHijoIzq = -1;
            if (nodo.getIzquierdo() != null) {
                balHijoIzq = calcularBalance(nodo.getIzquierdo());
            }
            if (balHijoIzq >= 0) {
                /*El hijo izquierdo esta inclinado hacia la izquierda o tiene balance 0,
                    aplica rotacion simple a derecha*/
                aux = rotarDerecha(nodo);
            } else {
                /*El hijo izquierdo esta inclinado hacia la derecha, por lo tanto,
                    aplica rotacion doble izquierda-derecha*/
                aux = rotarIzquierdaDerecha(nodo);
            }
        } else if (balance == -2) {
            /*Si el balance es -2, el arbol esta inclinado hacia la derecha*/
            /*Calcula balance del hijo Derecho*/
            int balHijoDer = -1;
            if (nodo.getDerecho() != null) {
                balHijoDer = calcularBalance(nodo.getDerecho());
            }
            if (balHijoDer <= 0) {
                /*El hijo derecho esta inclinado hacia la derecha o tiene balance 0,
                    aplica rotacion simple a izquierda*/
                aux = rotarIzquierda(nodo);
            } else {
                /*El hijo derecho esta inclinado hacia la izquierda, por lo tanto,
                    aplica rotacion doble derecha-izquierda*/
                aux = rotarDerechaIzquierda(nodo);
            }
        }
        return aux;
    }

    private NodoAVL rotarIzquierda(NodoAVL r) {
        NodoAVL h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        /*Despues de rotar, recalculo las alturas*/
        r.recalcularAltura();
        h.recalcularAltura();
        /*Retorna la nueva raiz del subArbol*/
        return h;
    }

    private NodoAVL rotarDerecha(NodoAVL r){
        NodoAVL h=r.getIzquierdo();
        NodoAVL temp=h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        /*Despues de rotar, recalculo las alturas*/
        r.recalcularAltura();
        h.recalcularAltura();
        /*Retorna la nueva raiz del subArbol */
        return h;
    }
    
    private NodoAVL rotarIzquierdaDerecha(NodoAVL r2){
        /*r1 pivot hijo del nodo desbalanceado
          r2 pivot nodo desbalanceado*/
        NodoAVL r1=r2.getIzquierdo(),aux;
        aux=rotarIzquierda(r1);
        reenlace(aux,r2);
        aux=rotarDerecha(r2);
        /*Retorna la nueva raiz del subArbol */
        return aux;
    }
    
    private NodoAVL rotarDerechaIzquierda(NodoAVL r2){
        /*r1 pivot hijo del nodo desbalanceado
          r2 pivot nodo desbalanceado*/
        NodoAVL r1=r2.getDerecho(),aux;
        aux=rotarDerecha(r1);
        reenlace(aux,r2);
        aux=rotarIzquierda(r2);
        /*Retorna la nueva raiz del subArbol */
        return aux;
    }
    
    private void reenlace(NodoAVL nodo,NodoAVL padre){
        if(padre!=null){
            if(padre.getElem().compareTo(nodo.getElem())<0)
                padre.setDerecho(nodo);
            else
                padre.setIzquierdo(nodo);
        }else{
            this.raiz=nodo;
        }
    }
    
    public boolean eliminar(Comparable elem){
       boolean elimino;
       elimino=eliminarAux(elem,this.raiz,null);
       return elimino;
    }
    
    private boolean eliminarAux(Comparable elem,NodoAVL nodo,NodoAVL padre){
        boolean exito=false;
        if(nodo!=null){
            /*Pregunto si es nodo.getElem() es que hay que eliminar*/
            if(elem.compareTo(nodo.getElem()) == 0){
                /*Llamo metodo que diferencia casos*/
                diferenciarCasos(nodo,padre);
                /*Si encontro el nodo, entonces se va a poder eliminar*/
                exito=true;
            }else{
                /*Si no es el elemento buscado, lo busco en los hijos*/
                /*Recorre la rama correspondiente*/
                if(elem.compareTo(nodo.getElem())<0){
                    if(nodo.getIzquierdo()!=null)
                        /*Recorre hijo izquierdo*/
                        exito=eliminarAux(elem,nodo.getIzquierdo(),nodo);
                } else {
                    if (nodo.getDerecho() != null) /*Recorre los hijos derecho*/ {
                        exito = eliminarAux(elem, nodo.getDerecho(), nodo);
                    }
                }
                if (exito) {
                    /*Actualizo la altura del nodo y balancea el arbol*/
                    nodo.recalcularAltura();
                    NodoAVL aux;
                    aux = balancear(nodo);
                    if (aux != null) {
                        reenlace(aux, padre);
                    }
                }
            }
        }
        return exito;
    }
    
    private void diferenciarCasos(NodoAVL nodo,NodoAVL padre){
        /*Metodo que diferencia los casos eliminar segun la
        cantidad de hijos del nodo*/
        if(nodo.getIzquierdo()!=null && nodo.getDerecho()!=null){
            /*LLamo al caso 3*/
            caso3(nodo.getDerecho(),padre,nodo);
        }else if(nodo.getIzquierdo()!=null || nodo.getDerecho()!=null){
            /*Llamo al caso 2*/
            caso2(nodo,padre);
        }else{
            /*Llamo al caso 1*/
            caso1(nodo,padre);
        }
    }
    
    private void caso1(NodoAVL nodo,NodoAVL padre){
        /*Si no tiene hijos*/
        if(padre==null)
            /*Si el nodo es la raiz sin hijos*/
            this.raiz=null;
        else{
            /*Reconoce al hijo que tiene que setear a null
            y lo setea*/
            if(nodo.getElem().compareTo(padre.getElem())<0)
                padre.setIzquierdo(null);
            else{
                padre.setDerecho(null);
            }
        }
        /*LA ALTURA SE RECALCULA RECURSIVAMENTE CUANDO VUELVE*/
        
    }
    
    private void caso2(NodoAVL nodo,NodoAVL padre){
        boolean ladoIzq=false;
        /*Si tiene un hijo*/
        if(padre==null){
            /*Si el nodo es la raiz, la nueva raiz
            es el hijo del nodo*/
            if(nodo.getIzquierdo()!=null)
                this.raiz=nodo.getIzquierdo();
            else
                this.raiz=nodo.getDerecho();
        }else{
            /*Reconoce el lado del padre que tiene que setear*/
            if(padre.getIzquierdo().getElem().compareTo(nodo.getElem()) == 0)
                ladoIzq=true;
            /*Reconoce donde esta el nieto del padre*/
            if(nodo.getIzquierdo()!=null){
                /*El nieto esta del lado izquierdo del nodo*/
                if(ladoIzq)
                    /*Setea el lado izquierdo del padre con 
                    el nieto*/
                    padre.setIzquierdo(nodo.getIzquierdo());
                else
                    /*Setea el lado derecho del padre con 
                    el nieto*/
                    padre.setDerecho(nodo.getIzquierdo());
            }else{
                /*El nieto esta del lado derecho del nodo*/
                if(ladoIzq)
                    /*Setea el lado izquierdo del padre con 
                    el nieto*/
                    padre.setIzquierdo(nodo.getDerecho());
                else
                    /*Setea el lado derecho del padre con 
                    el nieto*/
                    padre.setDerecho(nodo.getDerecho());
            }
            /*NO ES NECESARIO ACTUALIZAR LA ALTURA DE LOS NIETOS PORQUE SIGUEN SIENDO HOJAS*/
        }
    }

    private void caso3(NodoAVL candi, NodoAVL padre, NodoAVL elim) {
        /*Busco el candidato del nodo a elimnar*/
        /*Candidato: hijo menor del subarbol derecho*/
        if (candi.getIzquierdo() != null) {
            /*Recorre hasta el ultimo hijo izquierdo*/
            caso3(candi.getIzquierdo(), padre, elim);
        } else {
            eliminarAux(candi.getElem(), elim, padre);
            /*Inserta el ultimo hijo izquierdo*/
            candi.setIzquierdo(elim.getIzquierdo());
            candi.setDerecho(elim.getDerecho());
            /*Actualizo la altura del candidato*/
            candi.recalcularAltura();
            if (padre != null) {
                /*Verifica que nodo elim no sea la raiz*/
                if (padre.getElem().compareTo(elim.getElem()) > 0) {
                    padre.setIzquierdo(candi);
                } else {
                    padre.setDerecho(candi);
                }
                /*La altura del padre se actualiza y balancea a la vuelta de la recursicion*/
            } else /*Si es la raiz, entonces setea la raiz*/ {
                this.raiz = candi;
            }
        }
    }
    
    public Lista listar(){
        Lista list=new Lista();
        listarAux(list,this.raiz);
        return list;
    }
    
    private void listarAux(Lista list,NodoAVL nodo){
        /*Metodo que devuelve una lista de todos los elementos
        del ABB en un recorrido Inorden*/
        if(nodo!=null){
            /*Recorre hasta al ultimo hijo izquierdo*/
            listarAux(list,nodo.getIzquierdo());
            /*Agrega el elemento en la lista*/
            list.insertar(nodo.getElem(), list.longitud()+1);
            /*Recorre a hasta el ultimo hijo derecho*/
            listarAux(list,nodo.getDerecho());
        }
    }
    
    public Lista listarRango(Comparable elemMinimo,Comparable elemMaximo){
        Lista list=new Lista();
        if(elemMinimo.compareTo(elemMaximo)<=0){
            listarRangoAux(elemMinimo,elemMaximo,this.raiz,list);
        }
        return list;
    }
    
    private void listarRangoAux(Comparable elemMinimo, Comparable elemMaximo, NodoAVL nodo, Lista list) {
        if (nodo != null) {
            /*Recorro el arbol en inOrden para buscar los mayores o iguales a elemMinimo
                Y menores o iguales a elemMaximo*/
            if (elemMinimo.compareTo(nodo.getElem()) < 0 && nodo.getIzquierdo() != null) {
                listarRangoAux(elemMinimo, elemMaximo, nodo.getIzquierdo(), list);
            }
            /*Pregunto si el elemento del nodo es mayor o igual a elemMinimo
                    Y si el elemento es menor o igual a elemMaximo*/
            if (elemMinimo.compareTo(nodo.getElem()) <= 0 && elemMaximo.compareTo(nodo.getElem()) >= 0) {
                /*Si se cumple entonces lo inserto en la lista*/
                list.insertar(nodo.getElem(), list.longitud() + 1);
            }
            if (elemMaximo.compareTo(nodo.getElem()) > 0 && nodo.getDerecho() != null) {
                listarRangoAux(elemMinimo, elemMaximo, nodo.getDerecho(), list);
            }

        }
    } 
    
    public Comparable minimoElem(){
        /*Devuelve el minimo elemento del arbol*/
        NodoAVL aux;
        Comparable elem=null;
        if(this.raiz!=null){
            aux=minimoElemAux(this.raiz);
            elem=aux.getElem();
        }
        return elem;
    }
    
    private NodoAVL minimoElemAux(NodoAVL nodo){
        NodoAVL aux;
        if(nodo.getIzquierdo()!=null)
            /*Recorre hasta el ultimo hijo izquierdo*/
            aux=minimoElemAux(nodo.getIzquierdo());
        else
            /*Inserta el ultimo hijo izquierdo*/
            aux=nodo;
        return aux;
    }
    
    public boolean eliminarMinimo(){
        boolean exito;
        if(this.raiz==null)
            exito=false;
        else{
            eliminarMinimoAux(this.raiz,null);
            exito=true;
        }
        return exito;
    }
    
    private void eliminarMinimoAux(NodoAVL nodo,NodoAVL padre){
        /*Recorro el arbol hasta el ultimo hijo izquierdo*/
        if(nodo.getIzquierdo()!=null){
            eliminarMinimoAux(nodo.getIzquierdo(),nodo);
                /*Actualizo la altura del nodo y balancea el arbol*/
                nodo.recalcularAltura();
                NodoAVL aux;
                aux = balancear(nodo);
                if (aux != null) {
                    reenlace(aux, padre);
                }
        }else{
            if(padre!=null){
                if(nodo.getDerecho()==null)
                    /*Si el padre no tiene nieto, seteo su hijo a null*/
                    padre.setIzquierdo(null);
                else
                    /*Si el padre tiene nieto, seteo a su hijo por su nieto*/
                    padre.setIzquierdo(nodo.getDerecho());
            }else{
                if(nodo.getDerecho()!=null)
                    /*Si el nodo es la raiz y tiene un hijo mas grande,
                    el hijo es la nueva raiz*/
                    this.raiz=nodo.getDerecho();
                else
                    /*Si el nodo es la raiz, la seteo en null*/
                    this.raiz=null;
            }                
        }
    }
    
    public Comparable maximoElem(){
        /*Devuelve el maximo elemento del arbol*/
        NodoAVL aux;
        Comparable elem=null;
        if(this.raiz!=null){
            aux=maximoElemAux(this.raiz);
            elem=aux.getElem();
        }
        return elem;
    }
    
    private NodoAVL maximoElemAux(NodoAVL nodo){
        NodoAVL aux;
        if(nodo.getDerecho()!=null)
            /*Recorre hasta el ultimo hijo derecho del arbol*/
            aux=maximoElemAux(nodo.getDerecho());
        else
            /*Inserta el ultimo hijo derecho*/
            aux=nodo;
            
        return aux;
    }  

    public void vaciar(){
        this.raiz=null;
    }
    
    public boolean esVacio(){
        boolean vacio=true;
        if(this.raiz!=null)
            vacio=false;
        return vacio;
    }
    
    public AVL clone(){
        AVL clon=new AVL();
        clon.raiz=cloneAux(this.raiz);
        return clon;
    }
    
    private NodoAVL cloneAux(NodoAVL nodo){
        /*Recorre los elementos de los hijos de la raiz*/
        NodoAVL clon=null;
        if(nodo!=null){
            clon=new NodoAVL(nodo.getElem(),cloneAux(nodo.getIzquierdo()),cloneAux(nodo.getDerecho()));
        }
        return clon;
    }
    
    
    public String toString(){
        String arbol="null";
        if(this.raiz!=null)
            arbol=toStringAux(this.raiz);
        return arbol;
    }
    
    private String toStringAux(NodoAVL nodo){
        String arbol="";
        if(nodo!=null){
            NodoAVL hijoI=nodo.getIzquierdo(),hijoD=nodo.getDerecho();
            if(hijoI!=null)
                arbol+=nodo.getElem()+" HI: "+hijoI.getElem();
            else
                arbol+=nodo.getElem()+" HI: null";
            if(hijoD!=null)
                arbol+=" HD: "+hijoD.getElem();
            else
                arbol+=" HD: null";
            arbol+=" Altura: "+nodo.getAltura();
            arbol+="\n";
            arbol+=toStringAux(nodo.getIzquierdo());
            arbol+=toStringAux(nodo.getDerecho());
        }
        return arbol;
    }
    
}
