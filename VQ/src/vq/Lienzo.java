/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

import java.awt.Color;
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
    
    public void DibujarLinea(double x,double y,double x2, double y2, Color color, double grosor)
    {        
        StdDraw.setPenRadius(grosor);
        StdDraw.setPenColor(color);            
        StdDraw.line(x,y,x2,y2);
    }
    
    public void DibujarPunto(double x,double y, Color color, double grosor)
    {        
        StdDraw.setPenRadius(grosor);
        StdDraw.setPenColor(color);            
        StdDraw.point(x, y);
    }
    
    public void DibujarPuntoRedBlue(double x,double y)
    {
        DibujarPunto(x, y, Color.RED, .03);
        DibujarPunto(x, y, Color.BLUE, .02);
    }
    
    
    /**
     * Dibuja el árbol con datos del nivel anterior.
     * @param posicion posicion del dato en el arbol.
     * @param dim dimencion que le toca dibujar
     * @param datoPadre dato padre para dibujar el punto.
     */
    public void drawVQKDTree(int posicion,boolean dim,double datoPadre)
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
            DibujarPuntoRedBlue(this.listaDouble[posicion], datoPadre);           
        }
        else
        {
            DibujarPuntoRedBlue(datoPadre, this.listaDouble[posicion]);            
        }
    }
    
    /**
     * dibuja el árbol con datos al azar.
     * @param posicion posicion del dato en el arbol
     * @param dim dimencion que le toca dibujar.
     */
    public void drawVQKDTreeAzar(int posicion,boolean dim)
    {
        int izq = (posicion*2)+1;
        int der = (posicion+1)*2;        
        if(izq<this.tamañoLista && this.listaBoolean[izq])
        {
            this.drawVQKDTreeAzar(izq,!dim);
        }
        if(izq<this.tamañoLista && this.listaBoolean[der])
        {
            this.drawVQKDTreeAzar(der,!dim);
        }                
        
        if(dim)
        {
            DibujarPuntoRedBlue(this.listaDouble[posicion], this.listaDouble2[posicion]);            
        }
        else
        {
            DibujarPuntoRedBlue(this.listaDouble2[posicion], this.listaDouble[posicion]);            
        }
    }
    
    
    /**
     * inicializa las listas
     * @param valores árbol
     * @param comprobacion lista de comprobación
     * @param valores2 lista con balores para el árbol al azar.
     */
    public void drawVQKDTreeAzar(double[] valores,boolean[] comprobacion,double[] valores2)
    {
        this.listaDouble = valores;  
        this.listaBoolean=comprobacion;
        this.listaDouble2=valores2;
        this.tamañoLista =listaDouble.length;        
        
        this.drawVQKDTreeAzar(0, true);               
        
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
    public void drawLista(ArrayList<Vector> lista)            
    {                        
        if (lista.isEmpty()) return;                      
        for (Vector punto : lista)
        {
            DibujarPunto(punto.obtenerValor(0), punto.obtenerValor(1), Color.BLACK, .01);            
        }
        
    }
    
    public void drawListaColor(ArrayList<Vector> lista)            
    {                
        if (lista.isEmpty()) return;                      
        for (Vector punto : lista)
        {
            DibujarPuntoRedBlue(punto.obtenerValor(0), punto.obtenerValor(1));            
        }
        
    }
    
    public void LimpiarLienzo()
    {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);   
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
    }

    void DibujarVQKDTreeLinea(double[] valores,boolean[] comprobacion)
    {        
        this.listaDouble = valores;  
        this.listaBoolean=comprobacion;                
        this.tamañoLista =listaDouble.length;        
        
        this.DibujarVQKDTreeLinea(0, true,0,1,0,1,0); 
    }

    private void DibujarVQKDTreeLinea(int i, boolean dim, double Xmin, double Xmax, double Ymin, double Ymax, double datoPadre)
    {
        int izq = (i*2)+1;
        int der = (i*2)+2;      
        double valor = this.listaDouble[i];
        if(izq<this.tamañoLista && this.listaBoolean[izq])
        {
            if(dim)
            {
                this.DibujarVQKDTreeLinea(izq,!dim,Xmin,valor,Ymin,Ymax,valor);//pasa dim y
            }
            else
            {
                this.DibujarVQKDTreeLinea(izq,!dim,Xmin,Xmax,Ymin,valor,valor);
            }
        }
        if(der<this.tamañoLista && this.listaBoolean[der])
        {
            if(dim)
            {
                this.DibujarVQKDTreeLinea(der,!dim,valor,Xmax,Ymin,Ymax,valor);//pasa dim y
            }
            else
            {
                this.DibujarVQKDTreeLinea(der,!dim,Xmin,Xmax,valor,Ymax,valor);
            }                
        }    
        
        if(dim)
        {            
            DibujarPuntoRedBlue(valor, datoPadre);            
            DibujarLinea(valor,Ymin,valor,Ymax, Color.RED, .005);                
        }
        else
        {            
            DibujarPuntoRedBlue(datoPadre,valor);             
            DibujarLinea(Xmin,valor,Xmax,valor, Color.BLUE, .005);                     
        }
    }
}
