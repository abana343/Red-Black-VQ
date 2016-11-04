/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

/**
 *
 * @author vayal
 */
public class VQMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String filename = args[0];               
        
        VectorQuantization vq = new VectorQuantization(filename,10000, 1, 0, 5);            
        
        vq.entrenar();//ejecuta el entrenamiento de VQ
        
        vq.draw();
    }
    
    public static void Ejecutar(String filename, int tmax, int aI, int aF, int k)
    {        
        
        VectorQuantization vq = new VectorQuantization(filename,tmax, aI, aF, k);            
        
        vq.entrenar();//ejecuta el entrenamiento de VQ
        
        vq.draw();
    }

    /**
     * esta metodo sirve para inicializar el linzo para dibujar. cambiar metodo
     * @param vector 
     */
     
}
