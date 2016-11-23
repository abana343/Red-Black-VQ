/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

import java.util.ArrayList;

/**
 *
 * @author vayal
 */
public class Lienzo
{
    
    public double[] listaDouble;
    public double[] listaDouble2;
    public boolean [] listaBoolean;    
    public int tamañoLista;
        

    public Lienzo()
    {
    }
    
    /**
     * Dibuja el árbol con datos del nivel anterior.
     * @param posicion posicion del dato en el arbol.
     * @param dim dimencion que le toca dibujar
     * @param dato dato padre para dibujar el punto.
     */
    public void drawVQKDTree(int posicion,boolean dim,double dato)
    {
        int izq = (posicion*2)+1;
        int der = (posicion+1)*2;
        if(izq<this.tamañoLista && this.listaBoolean[izq])
        {
            this.drawVQKDTree(izq,!dim,this.listaDouble[posicion]);
        }
        if(izq<this.tamañoLista && this.listaBoolean[der])
        {
            this.drawVQKDTree(der,!dim,this.listaDouble[posicion]);
        }                
        if(dim)
        {
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);            
            StdDraw.point(this.listaDouble[posicion],dato);
            
            StdDraw.setPenRadius(.02);            
            StdDraw.setPenColor(StdDraw.BLUE);            
            StdDraw.point(this.listaDouble[posicion],dato);
        }
        else
        {
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);            
            StdDraw.point(dato,this.listaDouble[posicion]);

            StdDraw.setPenRadius(.02);            
            StdDraw.setPenColor(StdDraw.BLUE);            
            StdDraw.point(dato,this.listaDouble[posicion]);
        }
    }
    
    /**
     * dibuja el árbol con datos al azar.
     * @param posicion posicion del dato en el arbol
     * @param dim dimencion que le toca dibujar.
     */
    public void drawVQKDTree(int posicion,boolean dim)
    {
        int izq = (posicion*2)+1;
        int der = (posicion+1)*2;        
        if(izq<this.tamañoLista && this.listaBoolean[izq])
        {
            this.drawVQKDTree(izq,!dim);
        }
        if(izq<this.tamañoLista && this.listaBoolean[der])
        {
            this.drawVQKDTree(der,!dim);
        }                
        if(dim)
        {
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);            
            StdDraw.point(this.listaDouble[posicion],this.listaDouble2[posicion]);
            
            StdDraw.setPenRadius(.02);            
            StdDraw.setPenColor(StdDraw.BLUE);            
            StdDraw.point(this.listaDouble[posicion],this.listaDouble2[posicion]);
        }
        else
        {
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);            
            StdDraw.point(this.listaDouble2[posicion],this.listaDouble[posicion]);

            StdDraw.setPenRadius(.02);            
            StdDraw.setPenColor(StdDraw.BLUE);            
            StdDraw.point(this.listaDouble2[posicion],this.listaDouble[posicion]);
        }
    }
    
    
    /**
     * inicializa las listas
     * @param valores árbol
     * @param comprobacion lista de comprobación
     * @param valores2 lista con balores para el árbol al azar.
     */
    public void drawVQKDTree(double[] valores,boolean[] comprobacion,double[] valores2)
    {
        this.listaDouble = valores;  
        this.listaBoolean=comprobacion;
        this.listaDouble2=valores2;
        this.tamañoLista =listaDouble.length;        
        
        this.drawVQKDTree(0, true);               
        
    }
    
    
    /**
     * inicializa las dos listas y dibuja el arbolcon datos anteriores.
     * @param valores arbol.
     * @param lista2 lista de comprobacion.
     */
    public void drawVQKDTree(double[] valores,boolean[] lista2)
    {
        this.listaDouble = valores;  
        this.listaBoolean=lista2;        
        this.tamañoLista =listaDouble.length;        
        
        this.drawVQKDTree(0, true,0);               
        
    }
    
    /**
     * Dibuja la lista completa con puntos negros.
    */     
    public void drawLista(ArrayList<Punto> lista)            
    {                        
        if (lista.isEmpty()) return;                      
        for (Punto punto : lista)
        {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            StdDraw.point(punto.x,punto.y);
        }
        
    }
    
    public void drawListaColor(ArrayList<Punto> lista)            
    {                
        if (lista.isEmpty()) return;                      
        for (Punto punto : lista)
        {
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);            
            StdDraw.point(punto.x,punto.y);
            
            StdDraw.setPenRadius(.02);            
            StdDraw.setPenColor(StdDraw.BLUE);            
            StdDraw.point(punto.x,punto.y);
        }
        
    }
    
    public void drawLimpiar()
    {
        StdDraw.clear();
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
    }
}
