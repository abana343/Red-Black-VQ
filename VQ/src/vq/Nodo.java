/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vq;

/**
 *
 * @author vayal
 */
public class Nodo implements Comparable<Double>
{
    public Nodo Izquierdo;
    public Nodo Derecho;
    public double valor;
    public double constante;

    public Nodo(double valor, double con)
    {
        this.valor = valor;
        this.constante=con;
    }        
            
    @Override
    public int compareTo(Double comparacion)
    {
        if(this.valor == comparacion)
        {
            return 0;//retorna cero si los valores son iguales.
        }
        else
        {
            if(this.valor < comparacion)
            {
                return -1;// retorna -1 si el valor es menor al comparable.
            }            
        }
        return 1;//retorna 1 si el valor es mayor al comparable.
    }
    
}
