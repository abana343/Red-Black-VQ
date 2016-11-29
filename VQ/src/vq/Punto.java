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
public class Punto
{
    public double x;
    public double y;

    public Punto(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
    
    //modificar clases para que sea generica para k-dimenciones.
    public double obtenerValorPorDimencion(int k)
    {
        switch(k)
        {
            case 0:
                return this.x;
            case 1:
                return this.y;
        }
        
        return -1;
    }

    @Override
    public String toString()
    {
        return "Punto{" + "x= " + x + ", y= " + y + '}';
    }
    
    
}
