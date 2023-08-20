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
public class NodoAVL {
      //Atributos//
 private Comparable elem=null;
 private NodoAVL izquierdo=null;
 private NodoAVL derecho=null;
 private int altura=0;
 
 //Constructores//
 public NodoAVL(Comparable elemento,NodoAVL zurdo,NodoAVL diestro){
     /*Constructor de Nodo Arbol*/
     elem=elemento;
     izquierdo=zurdo;
     derecho=diestro;
     altura=0;
 }
 
 //Observadores//
 public Comparable getElem(){
     return this.elem;
 }
 
 public NodoAVL getIzquierdo(){
     return this.izquierdo;
 }
 
 public NodoAVL getDerecho(){
     return this.derecho;
 }
 
 public int getAltura(){
     return this.altura;
 }
 
 //Modificadores//
 public void setElem(Comparable elemento){
    this.elem=elemento;
 }
 
 public void setIzquierdo(NodoAVL zurdo){
     this.izquierdo=zurdo;
 }
 
 public void setDerecho(NodoAVL diestro){
     this.derecho=diestro;
 }
 
 public void recalcularAltura(){
        if(this.izquierdo!=null && this.derecho!=null){
            this.altura=Math.max(this.izquierdo.getAltura(),this.derecho.getAltura())+1;
        }else{ 
            if(this.izquierdo!=null){
                this.altura= this.izquierdo.getAltura()+1;
            }else{ 
                if(this.derecho!=null){
                    this.altura=this.derecho.getAltura()+1;
                }else{
                    this.altura = 0;
                }
            }
        }
    }
 }



