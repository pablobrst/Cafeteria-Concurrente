
package com.mycompany.peclparadigmasprogramacion;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;


 public class Almacen{
    //Enteros
    private final int capacidadMax = 1000;
    private int cantidad,galletasConsumidas=0;
        
    //Archivo log
    FileWriter archivo;
        
    //Semaforos
    Semaphore lleno = new Semaphore(capacidadMax);
    Semaphore em = new Semaphore(1);
        
    /////////////////////////////////////////////////////////
    /////////////// Metodos concurrentes ////////////////////
    /////////////////////////////////////////////////////////
        
    public Almacen(FileWriter archivo){
        this.archivo = archivo;
    }//constructor
        
    public void alamcenarGalletas(int caja,String nombre){
        //Da paso a un empaquetador a almacenr su caja,
        //  cuando termina entra el siguiente.
        
        try{
            lleno.acquire(caja);
            em.acquire();
            cantidad+=caja;
            System.out.println(nombre+" ha almacenado "+caja+" galletas, almacén lleno al "+((cantidad/capacidadMax)*100)+"% "+LocalTime.now()+"\n");
            archivo.write(nombre+" ha almacenado "+caja+" galletas, almacén lleno al "+((cantidad/capacidadMax)*100)+"% "+LocalTime.now()+"\n");
            em.release();
        }catch(Exception ex){}
    }//fin
        
    /////////////////////////////////////////////////////////
    ///////////// Metodos para la interfaz //////////////////
    /////////////////////////////////////////////////////////
        
    public void comerGalletas() throws InterruptedException, IOException{
        //Consume 100 galletas del almacen si las dispone,
        //  sino no consumira ninguna.
            
        em.acquire();
        if(cantidad>=100){
            System.out.println("Se han consumido 100 galletas, almacén lleno al "+((cantidad/capacidadMax)*100)+"% "+LocalTime.now()+"\n");
            archivo.write("Se han consumido 100 galletas, almacén lleno al "+((cantidad/capacidadMax)*100)+"% "+LocalTime.now()+"\n");
            galletasConsumidas += 100;
            cantidad-=100;
            lleno.release(100);
        }//if                
        em.release();  
    }//fin
        
    public int galletasConsumidas(){
        //Devuelve las galletas que se han consumido
        
        return galletasConsumidas;
    }//fin
        
    public int galletasAlmacenadas(){
        //Devuelve las galletas que se han almacenado
            
        return cantidad;
    }//fin
    
    /////////////////////////////////////////////////////////
    ///////////// Metodos getter y setter  //////////////////
    /////////////////////////////////////////////////////////

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getGalletasConsumidas() {
        return galletasConsumidas;
    }

    public FileWriter getArchivo() {
        return archivo;
    }
    
}//fin recurso compartido almacén
