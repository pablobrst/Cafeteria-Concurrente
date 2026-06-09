
package com.mycompany.peclparadigmasprogramacion;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;


public class Repostero extends Thread{
    //String
    private String nombre,estado;
       
    //Enteros
    private int tanda,hornadas,galletasGeneradas,id;
    private int ejecutarse = 1;
    private int cafe = (int)(3+2*Math.random());
        
    //Hornos
    private Horno horno1,horno2,horno3;
      
    //Booleanos
    private boolean buscar=true;
      
    //Archivo log
    private FileWriter archivo;
      
    //Bloqueo
    private Semaphore bloqueo = new Semaphore(1);
        
    public Repostero(String nombre,int id, Horno horno1, Horno horno2, Horno horno3,FileWriter archivo){
        this.nombre = nombre;
        this.id = id;
        this.archivo=archivo;
        this.horno1 = horno1;
        this.horno2 = horno2;
        this.horno3 = horno3;
            
    }//constructor  
    
    public void hacerTanda() throws IOException{
        // Prepara una tanda de entre 37 y 45 galletas que 
        //  tarda entre 2 y 4 segundos para despues buscar
        //  un horno donde hornearlas.
        //Si ha hecho tantas hornadas como las necesarias
        //  para tomar un descanso(variable cafe) espera
        //  para servirse un cafe(0 a 2 s) y cuando lo tiene se lo
        //  toma tardando entre 3 y 6 segundos.
            
        try{
            estado="Produciendo ("+hornadas+"/"+cafe+")";
            tanda= ((int)(37+8*Math.random()));
            sleep((int)(2000+2000*Math.random())); 
            hornadas++;
              
            while(buscar){
                if(!horno1.hornoLleno()){
                    buscar=false;
                    horno1.dejarGalletas(tanda,id);
                }else{
                    if(!horno2.hornoLleno()){
                        buscar=false;
                        horno2.dejarGalletas(tanda,id);
                    }else{
                        if(!horno3.hornoLleno()){
                            buscar=false;
                            horno3.dejarGalletas(tanda,id);
                        }//if3
                    }//if2
                }//if1
            }//while
            buscar=true;
            galletasGeneradas+=tanda;
               
            estado="Produciendo ("+hornadas+"/"+cafe+")";
            if(hornadas==cafe){
                    
                hornadas=0;
                estado="Pausa para el cafe";
                System.out.println("Repostero"+(id+1)+" esperando la cafetera "+LocalTime.now()+"\n");
                archivo.write("Repostero"+(id+1)+" esperando la cafetera "+LocalTime.now()+"\n");
                        
                horno1.pausaCafe(id);
                       
                System.out.println("Repostero"+(id+1)+" tomándose el cafe "+LocalTime.now()+"\n");
                archivo.write("Repostero"+(id+1)+" tomándose el cafe "+LocalTime.now()+"\n");
                sleep((int)(3000+3000*Math.random()));//descansan tomandose su café
            }//if
        }catch(InterruptedException ex){}
    }//fin
        
    /////////////////////////////////////////////////////////
    //////////////// Ejecucion del hilo /////////////////////
    /////////////////////////////////////////////////////////
       
    public void run(){
        //Mientras el cliente no lo detiene 
        //  ejecutará hacerTanda().
         
        while(true){
            try {
                estado="detenido";
                bloqueo.acquire(ejecutarse);
                hacerTanda();
                bloqueo.release();
            } catch (Exception ex) {}
            
        }//while
    }//fin
        
    /////////////////////////////////////////////////////////
    ///////////// Metodos para la interfaz //////////////////
    /////////////////////////////////////////////////////////
     
    public String darEstado(){
        //Devuelve que esta haciendo el hilo actualmente
         
        return estado;
    }//fin
      
    public void detenerse() throws InterruptedException {
        //Detiene el hilo
        
        ejecutarse = 2; 
        bloqueo.acquire(bloqueo.availablePermits());
    }//fin
    
    public void reanudarse() {
        //Reanudael hilo
        
        ejecutarse=1;
        bloqueo.release(2);  
    }//fin
    
    /////////////////////////////////////////////////////////
    ///////////// Metodos getter y setter  //////////////////
    /////////////////////////////////////////////////////////

    public String getNombre() {
        return nombre;
    }
    
    public int getID(){
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public int getTanda() {
        return tanda;
    }

    public int getHornadas() {
        return hornadas;
    }

    public int getGalletasGeneradas() {
        return galletasGeneradas;
    }

    public int getEjecutarse() {
        return ejecutarse;
    }

    public int getCafe() {
        return cafe;
    }

    public Horno getHorno1() {
        return horno1;
    }

    public Horno getHorno2() {
        return horno2;
    }

    public Horno getHorno3() {
        return horno3;
    }

    public boolean isBuscar() {
        return buscar;
    }

    public FileWriter getArchivo() {
        return archivo;
    }
    
}//fin clase repostero
