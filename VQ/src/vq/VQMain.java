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
        In in = new In(filename);          
        
        VectorQuantization vq = new VectorQuantization(filename,10000, 1, 0, 5);            
        
        vq.entrenar();//ejecuta el entrenamiento de VQ
        
        dibujar(vq);
    }

    /**
     * esta metodo sirve para inicializar el linzo para dibujar. cambiar metodo
     * @param vector 
     */
    private static void dibujar(VectorQuantization vector)
    {
        StdDraw.show(0);                                      

        //Dibujamos todos los puntos
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        
        vector.draw();
        
        StdDraw.show(0);
        StdDraw.show(40);       
    }
    
}
