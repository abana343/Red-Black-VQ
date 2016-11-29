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
    public double[] A;    
    public boolean[] B;
    private int d;
    private int tamañoArbol;
    
    
    public VQKDTree(String fileName, int tMax, double alfaInicial, double alfaFinal, int h)
    {        
        super(fileName,tMax,alfaInicial,alfaFinal);
        this.h = h;
        
        this.r = new Random();        
        this.dataSet = new ArrayList<Vector>();       
        this.tamañoArbol();
        this.A = new double[tamañoArbol];        
        this.B = new boolean[tamañoArbol];
        
        this.cargarDataSet();
        this.tamaño = this.dataSet.size();
        this.d = 2;
    }
    
    
    public void initB()
    {
        for (int i = 0; i <tamañoArbol; i++)
        {
            this.B[i]= false;
        }
    }
    /**
     * 
     * @return cantidad de nodos del A.
     */
    public void tamañoArbol()
    {
        tamañoArbol =(int) Math.pow(2, (double) h) - 1; //remplazar por (2^h)-1               
        System.out.println("h= "+h +"tamaño= "+tamañoArbol);
    }
    
    
    @Override
    public void entrenar()
    {          
        int k = 0;        
        this.initB();
        for (int t = 0; t < this.tMax; t++)
        {
            int i = 0; // posición del árbol que actualmente esta ciendo analizada.
            Vector x = this.dataSet.get(r.nextInt(this.tamaño));// se selecciona un elemento al azar del dataset 
            for (int j = 0; j < this.h; j++)
            {
                k = j%this.d;// asignamos la dimencion que se trabajara en esta iteracion.
                double xk = x.obtenerValor(k);//le aignamos un valor respecto a la dimención
                if(!this.B[i])// si el valor no esta inicializado
                {
                    this.A[i] = xk;//le aignamos un valor respecto a la dimención
                    this.B[i] = true;//y lo activamos como inicializado.                    
                    break;
                }
                
                if(this.A[i] >= xk)//lo cambie a mayor o igual 
                {                
                    i = 2*i+1;
                }
                else
                {
                    i = 2*i+2;
                }                
                //generamos la migracion del punto A[padre].
                this.A[(i-1)/2] =this.A[(i-1)/2]+ this.alfa*(xk-this.A[(i-1)/2]);    
                //System.out.print(""+j+" ");                
            }
            //Actualizamos valor de aprendizaje.
            this.alfa -= this.alfaInicial/this.tMax;            
        }        
                      
    }                        
}
