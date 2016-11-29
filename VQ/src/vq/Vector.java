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
public class Vector
{
    public double[] ListaDouble;
    public int dimenciones;

    /**
     * Inicializa el vector con valores en cero y con k dimenciones
     * @param dimenciones 
     */
    public Vector(int k)
    {
        this.dimenciones = k;
        this.ListaDouble =new double[this.dimenciones];
        
    }        
    
    /**
     * inicializa el vector con un nuevo vector pasado.
     * @param vector vaector con el cual se inicializara este uevo vector.
     */
    public Vector(double[] vector)
    {
        this.ListaDouble = vector;
        this.dimenciones = this.ListaDouble.length;
    }
    
    /**
     * Crea un vector de 2 dimenciones pasando ambos parametros.
     * @param x
     * @param y 
     */     
    public Vector(double x, double y)
    {
        this.dimenciones = 2;
        this.ListaDouble =new double[this.dimenciones];
        this.ListaDouble[0]= x;
        this.ListaDouble[1]= y;
        
    }
        
    /**
     *obtiene el valor de la dimencion establecida, recuerde que la lista comienza de cero su dimencion es 
     * dimencion -1
    */
    public double obtenerValor(int dimencion)
    {
        return ListaDouble[dimencion];
    }
    
    /**
     *
     *Modifica el valor de la dimencion establecida, recuerde que la lista comienza de cero su dimencion es 
     * dimencion -1    
     * @param dimencio
     * @param valor 
     */
    public void modificarValor(int dimencio, double valor)
    {
        this.ListaDouble[dimencio] = valor;
    }
}
