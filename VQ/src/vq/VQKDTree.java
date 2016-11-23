/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author vayal
 */
public class VQKDTree extends VectorQuantization
{
    
    public int h;
    public double[] arbol;
    public double[] arbol2;
    public boolean[] comprobacion;
    private int dimencion;
    private int tamañoArbol;
    
    
    public VQKDTree(String fileName, int tMax, double alfaInicial, double alfaFinal, int h)
    {        
        super(fileName,tMax,alfaInicial,alfaFinal);
        this.h = h;
        
        this.r = new Random();        
        this.dataSet = new ArrayList<Punto>();       
        this.tamañoArbol();
        this.arbol = new double[tamañoArbol];
        this.arbol2 = new double[tamañoArbol];
        this.comprobacion = new boolean[tamañoArbol];
        this.init();
        this.cargarDataSet();
        this.tamaño = this.dataSet.size();
        this.dimencion = 2;
    }
    
    
    public void init()
    {
        for (int i = 0; i <tamañoArbol; i++)
        {
            this.comprobacion[i]= false;
        }
    }
    /**
     * 
     * @return cantidad de nodos del arbol.
     */
    public void tamañoArbol()
    {
        tamañoArbol = 0;
        for (int i = 0; i < h; i++)
        {
            tamañoArbol += (int) Math.pow(2, i);
        }        
        System.out.println("h= "+h +"tamaño= "+tamañoArbol);
    }
    
    
    @Override
    public void entrenar()
    {        
        //asignamos un valor al azar a la raíz.
        Punto a = this.dataSet.get(r.nextInt(this.tamaño));
        this.arbol[0] = a.x;
        this.arbol2[0] = a.y;
        this.comprobacion[0] = true;
        double dato=0;
        double dato2=0;
        
        for (int t = 0; t < tMax; t++)
        {
            int posicion=0;
            Punto x = this.dataSet.get(r.nextInt(this.tamaño));
                        
            for (int j = 0; j < this.h; j++)
            {
                //por mientras para elegir la dimension seleccionada
                int i = j%this.dimencion;                
                if(i==0)
                {
                    dato=x.x;
                    dato2=x.y;
                }
                else
                {
                    dato=x.y;
                    dato2=x.x;
                }                
                
                if(this.arbol[posicion] <= dato)
                {
                    migracionKD(posicion, dato);    
                    int izq = (posicion*2)+1;
                    if(izq<this.tamañoArbol && !this.comprobacion[izq])
                    {                           
                        if(i==0)
                        {
                            this.arbol[izq] = this.dataSet.get(r.nextInt(this.tamaño)).x;
                            this.arbol2[izq] = this.dataSet.get(r.nextInt(this.tamaño)).y;
                        }
                        else
                        {
                            this.arbol[izq] = this.dataSet.get(r.nextInt(this.tamaño)).y;
                            this.arbol2[izq] = this.dataSet.get(r.nextInt(this.tamaño)).x;
                        }
                        this.comprobacion[izq]= true;
                        break;
                    }
                    posicion= izq;
                }
                else
                {
                    migracionKD(posicion, dato);   
                    int der = (posicion+1)*2;
                    if(der<this.tamañoArbol && !this.comprobacion[der])
                    {
                        if(i==0)
                        {
                            this.arbol[der] = this.dataSet.get(r.nextInt(this.tamaño)).x;
                            this.arbol2[der] = this.dataSet.get(r.nextInt(this.tamaño)).y;
                        }
                        else
                        {
                            this.arbol[der] = this.dataSet.get(r.nextInt(this.tamaño)).y;
                            this.arbol2[der] = this.dataSet.get(r.nextInt(this.tamaño)).x;
                        }
                        this.comprobacion[der]= true;
                        break;
                    }
                    posicion = der;
                }
                
            }
            //migracionKD(posicion, dato);
        }
        
        //Actualizamos valor de aprendizaje.
        this.alfa -= this.alfaInicial/this.tMax;
    }
    
    private void migracionKD(int posicion, double dato)
    {
        double valor = this.arbol[posicion];
        this.arbol[posicion] = valor +this.alfa*(dato-valor);
        //this.prototipos.get(bmu).x= aux.x + this.alfa*(pivote.x-aux.x); 
    } 
    
    
    
    
}
