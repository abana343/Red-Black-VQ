/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

import edu.princeton.cs.algs4.In;

/**
 *
 * @author vayal
 */
public class VQMain {

    /**
     * @param args the command line arguments
     */
    
    public static Lienzo lienzo;
    public static void main(String[] args) {
        // TODO code application logic here        
        VQInterface hola = new VQInterface();
        hola.addVentana(StdDraw.getFrame());        
        hola.setVisible(true);        
        VQMain.lienzo = new Lienzo();
    }
    
    public static void EjecutarVQ(String filename, int tmax, int aI, int aF, int k)
    {        
        
        VectorQuantization vq = new VectorQuantization(filename,tmax,aF , aI, k);            
        
        vq.entrenar();//ejecuta el entrenamiento de VQ
        
        //vq.draw();
        lienzo.drawLimpiar();
        lienzo.drawLista(vq.dataSet);
        lienzo.drawListaColor(vq.prototipos);
    }

    public static void EjecutarVQKD(String filename, int tmax, int aI, int aF, int h)
    {        
        
        VQKDTree vq = new VQKDTree(filename,tmax,aF , aI, h);            
        
        vq.entrenar();//ejecuta el entrenamiento de VQ
        
        lienzo.drawLimpiar();
        VQMain.lienzo.drawLista(vq.dataSet);
        VQMain.lienzo.drawVQKDTree(vq.arbol, vq.comprobacion);
        //vq.draw();
//        vq.draw(0, true,0);
        
        vq=null;
    }

    public static void EjecutarVQKDAzr(String filename, int tmax, int aI, int aF, int h)
    {        
        
        VQKDTree vq = new VQKDTree(filename,tmax,aF , aI, h);            
        
        vq.entrenar();//ejecuta el entrenamiento de VQ
        
        
        lienzo.drawLimpiar();
        VQMain.lienzo.drawLista(vq.dataSet);
        VQMain.lienzo.drawVQKDTree(vq.arbol, vq.comprobacion,vq.arbol2);
        //vq.draw();
//        vq.draw(0, true,0);
        
        vq=null;
    }    
     
}
