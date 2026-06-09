
package com.mycompany.peclparadigmasprogramacion;

import java.io.IOException;

public class Empaquetador extends Thread{
    //String
    private String nombre;
    private String estado="Esperando";
        
    //Horno y Almacen    
    private Horno horno; 
    private Almacen almacen;
        
    //Enteros
    private int paquete,galletas=0;
        
    public Empaquetador(String nombre, Horno horno, Almacen almacen){
        this.nombre=nombre;
        this.horno = horno;
        this.almacen = almacen;
    }//Constructor
    
    /////////////////////////////////////////////////////////
    //////////// Funciones para el almacén //////////////////
    /////////////////////////////////////////////////////////
    
    public void empaquetar() throws InterruptedException, IOException{
        //Coge galletas del horno de 20 en 20 durante 5 
        //  tandas,luego las empaqueta y va a guardarlas
        //  en el almacén.
            
        while(paquete<5){
            estado = horno.cogerGalletas(nombre);
            galletas+=20;
            paquete++;
        }//while
            
        paquete=0;
        estado="Almacenando";
        guardarEnAlmacen();
    }//fin
       
    public void guardarEnAlmacen(){
        //Trata de almacenar galletas al almacén que les
        //  toma entre 2 y 4 segundos.
            
        try{
            sleep((int)(2000+2000*Math.random()));
            almacen.alamcenarGalletas(galletas,nombre);
            estado="Esperando";
            galletas=0;
        }catch(Exception ex){}
    }//fin
        
        /////////////////////////////////////////////////////////
        ///////---////// Ejecución del hilo //////////---////////
        /////////////////////////////////////////////////////////
        
    public void run(){
        //Se dedica infinitamente a empaquetar galletas
        //  y llevarlas al horno
            
        while (true){
            try {
                empaquetar();
            } catch (Exception ex) {}
        }//while
    }//fin
        
        /////////////////////////////////////////////////////////
        /////////// Funciones para la interfaz //////////////////
        /////////////////////////////////////////////////////////
        
    public int estadoEmpaquetado(){
        //Devuelve como esta de lleno el paquete de galletas
            
        return paquete;
        }//fin
        
    public String darEstado(){
        //Devuelve que estan haciendo los empaquetadores
            
        return estado;
        }//fin
            
    }//fin clase empaquetadores
