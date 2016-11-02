/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.Random;

/**
 * 2d
 * @author vayal
 */
public class VectorQuantization 
{
    // es el arreglo de datos que se ingresan
    public ArrayList<Punto>  dataSet;
    //tiempo maximo que se debe demorar el algoritmo
    public int tMax;
    // lista de prototipos.
    public ArrayList<Punto> prototipos;
    //constante de movimiento, la cual establece de donde parte alfa.
    public double alfaInicial;
    //constante de movimiento, la cual especifica hasta donde llega el alfa     
    public double alfaFinal;
    //variable de movimiento, la cual establece cuanto se debe mover un prototipo
    public double alfa;
    //numero de prototipos que se deben encontrar.
    public int k;
    //ramdom. para elegir valores al azar.
    public Random r;    
    //cantidad de elementos del dataset.
    public int tamaño;
    // semilla aleatoria.
    protected int seed;    
    //Nombre del archivo para el dataset.
    private String fileName;

    

    /**
     * 
     * @param tMax
     * @param alfaInicial
     * @param alfaFinal
     * @param k 
     */
    public VectorQuantization(String fileName,int tMax, double alfaInicial, double alfaFinal, int k)
    {
        this.tMax = tMax;
        this.alfaInicial = alfaInicial;
        this.alfa = alfaInicial;
        this.alfaFinal = alfaFinal;
        this.k = k;
        this.fileName = fileName;
        System.out.println(""+this.alfaInicial/this.tMax);
        this.r = new Random();
        this.prototipos = new ArrayList<Punto>();
        this.dataSet = new ArrayList<Punto>();
        
        this.newDataSet();
    }          
    
    /**
     * 
     * @param seed 
     */        
    public void setSeed(int seed)
    {
        this.seed = seed;
    }
    
    
    private void newDataSet()
    {
        In in = new In(this.fileName);  
        while (!in.isEmpty()) {//cargar dataset.
            double x = in.readDouble();
            double y = in.readDouble();    
            this.insertar(x, y);//inserta punto 2D a la estructura de datos
        }   
    }
    /**
     * 
     * @param x
     * @param y 
     */
    private void insertar(double  x, double y)
    {           
        Punto nuevoPunto = new Punto(x, y);
        this.dataSet.add(nuevoPunto);                       
    }
    
    /**
     * selecciona la colección de elementos que se utilizara para como prototipos del
     * problema.
     */
    public void inicializar()
    {
        int index=0;
        this.tamaño = this.dataSet.size();
        for (int i = 0; i < k; i++)//para cada prototipo
        {            
            index = r.nextInt(this.tamaño); //seleccionar al azar un elemento del conjunto de datos.
            this.prototipos.add(this.dataSet.get(index));//asignar el elemento a un prototipo.
        }
    }
    
    
    public ArrayList<Punto> entrenar()
    {
        this.inicializar();
        Punto pivote;
        int index=0;
        int bmu =0;
        double bmuDistancia=Double.MAX_VALUE;
        while(this.alfa>=0)
        {
            // Elegimos el pivote de la iteracion.
            index = r.nextInt(this.tamaño);
            pivote = this.dataSet.get(index);
            
            // Buscamos el BMU
            double distancia =0;
            for (int j = 0; j < k; j++)
            {
                distancia = distanciaEntreDosPuntoAlCuadrado(pivote, this.prototipos.get(j));
                
                if(distancia<bmuDistancia)
                {
                    bmuDistancia=distancia;
                    bmu = j;
                }
                
            }
            
            // Migramos el BMU a la nueva posición.
            Punto aux=  this.prototipos.get(bmu);
            this.prototipos.get(bmu).x= aux.x + this.alfa*(pivote.x-aux.x);
            this.prototipos.get(bmu).y= aux.y + this.alfa*(pivote.y-aux.y);
            
            // Decrementamos alfa.
            this.alfa -= this.alfaInicial/this.tMax;
                                 
        }
                
        return this.prototipos;
    }
    
    public double distanciaEntreDosPuntoAlCuadrado(Punto pivote, Punto posibleBMU)            
    {
        double x = pivote.x-posibleBMU.x;
        double y = pivote.y-posibleBMU.y;
        x= x*x;
        y= y*y;        
        return x+y;        
    }         

    // draw all of the points to standard draw
    public void draw() {
        // draw the rectangle
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
        if (this.dataSet.isEmpty()) return;              
        
        for (Punto punto : dataSet)
        {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            StdDraw.point(punto.x,punto.y);
        }
        
        for (Punto punto : prototipos)
        {            
            
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);            
            StdDraw.point(punto.x,punto.y);
            
            StdDraw.setPenRadius(.02);            
            StdDraw.setPenColor(StdDraw.BLUE);            
            StdDraw.point(punto.x,punto.y);
        }
        
        System.out.println("alfa "+this.alfa);
        
    }
    
    
}
