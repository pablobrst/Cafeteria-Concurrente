
package com.mycompany.peclparadigmasprogramacion;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;

public class Horno extends Thread{
        //String
        private String nombre,cafetera;
        
        //Arrays
        private String[] colaCafetera = new String[5];
        private int[] desperdicios = new int[5];
        
        //Booleanos
        boolean horneando = false;
        
        //Enteros        
        private int cantidad,galletasHorneadas=0;
        private int nCola =0;
        private final int capacidadMax = 200;
                
        //Archivo log
        private FileWriter archivo;
        
        //Semáforos
        private Semaphore vacio = new Semaphore(capacidadMax);
        private Semaphore lleno = new Semaphore(0);
        private Semaphore em = new Semaphore(1);
        private Semaphore emCafetera = new Semaphore(1);
        
        public Horno(String nombre,FileWriter archivo){
            this.nombre=nombre;
            this.archivo = archivo;
            this.cafetera = "Ningun repostero descansando";

            for(int i=0;i<colaCafetera.length;i++){
                    colaCafetera[i]="";
            }//for
        }//constructor hilo horno
                
        public synchronized boolean hornoLleno(){
            //Devuelve si el horno esta lleno
            
            if(cantidad>=capacidadMax){
                return true;
            }//if
            return false;
        }//fin
        
        public synchronized boolean hornoVacio(){
            //Devuelve si el horno esta vacío
            
            if(cantidad==0){
                
                return true;
            }//if
            return false;
        }//fin
        
        ////////////////////////////////////////////////////////
        ///////////////// Metodos Cafetera //////////////////////
        /////////////////////////////////////////////////////////
        
        public synchronized void anotarseEnCafetera(int id){
            //De manera sincronizada los hilos se apuntan
            //  en cola cafetera para prepararse el café
            
            colaCafetera[nCola]="Repostero"+(id+1);
            nCola++;
            if(nCola==colaCafetera.length){
                nCola=0;
            }//if
        }//fin
        
        public String siguienteEnCola(){
            //Busca el siguiente repostero 
            //  en la cola de la cafetera
            
            for(int i =0;i<colaCafetera.length;i++){
                if(colaCafetera[i]!=null){
                    String repostero = colaCafetera[i];
                    colaCafetera[i]=null;
                    return repostero;
                }//if
            }//for
            return "Ningun repostero en cola";            
        }//fin
        
        public  void pausaCafe(int id) throws InterruptedException, IOException{
            //De manera asincrona empieza la funcion,
            //  se anotan de manera sincronizada 
            //  en la cola de la cafetera y en orden
            //   se sirven un café en 2 segundos
            
            anotarseEnCafetera(id);
            emCafetera.acquire();
            try{
                String siguiente = siguienteEnCola();
                cafetera=siguiente;
                System.out.println("Repostero"+(id+1)+" esta en la cafetera "+LocalTime.now()+"\n");
                archivo.write("Repostero"+(id+1)+" esta en la cafetera "+LocalTime.now()+"\n");
                sleep(2000);
            }catch(InterruptedException ex){}
            emCafetera.release();
            
        }//fin

        /////////////////////////////////////////////////////////
        /////////// Metodos consumidor/productor ////////////////
        /////////////////////////////////////////////////////////
        
        public void dejarGalletas(int tanda,int id) throws InterruptedException, IOException{
            //Acceden un repostero con su tanda de galletas 
            //  y su id a anotar sus desperdicios, despúes
            //  si el semáforo les da permisos meten las 
            //  galletas al horno y dan paso al siguiente.
            //Si el horno esta lleno comienza el horneado de 
            //  8 segundos y da paso a que los empaquetadores
            //  vacíen el horno.
            
            em.acquire();   //exclusion mutua
            tanda = anotarDesperdicios(tanda,id);
            
            vacio.acquire(tanda);//caben las galletas?
            cantidad+=tanda;//mete las galletas
            
            System.out.println("Repostero"+(id+1)+" ha dejado "+tanda+" "+nombre+" lleno al "+cantidad/2+"% "+LocalTime.now()+"\n");
            archivo.write("Repostero"+(id+1)+" ha dejado "+tanda+" "+nombre+" lleno al "+cantidad/2+"% "+LocalTime.now()+"\n");
            
            if(hornoLleno()){//se llena 
                horneando=true;
                System.out.println(nombre+" horneando, "+nombre+" lleno al 100%"+LocalTime.now()+"\n");
                archivo.write(nombre+" horneando, "+nombre+" lleno al 100%"+LocalTime.now()+"\n");
                sleep(8000);//hornea todas las galletas
                horneando=false;
                galletasHorneadas+=200;
                lleno.release(200);//se pueden retirar
                
            }//if
            
            em.release();
        }//fin
        
        public String cogerGalletas(String nombreE) throws InterruptedException, IOException{
            //accede el único empaquetador que hay en el horno
            //  con su nombre,si el semaforo le da permiso 
            //  saca 20 galletas tardando entre 0,5 y 1 segundos.
            // Cuando lo vacíe se le da paso
            //  a los reposteros a rellenar otra hornada.
            
            lleno.acquire(20);//liberamos 20 espacios 
            cantidad-=20;//sacamos las 20 galletas
            System.out.println(nombreE+" ha recogido 20 galletas, "+nombre+" lleno al "+cantidad/2+"% "+LocalTime.now()+"\n");
            archivo.write(nombreE+" ha recogido 20 galletas, "+nombre+" lleno al "+cantidad/2+"% "+LocalTime.now()+"\n");
            sleep((int)(Math.random()*500+500));
            
            if(hornoVacio()){//se vacia 
                vacio.release(200);//se pueden hornear
            } //if
            return "Empaquetando";
        }//fin
        
        public int anotarDesperdicios(int tanda,int id) throws IOException{
            //Si la tanda que se va a introducir más la 
            //  cantidad supera la capacidad máxima se 
            //  anotan las galletas que se van a desperdiciar
            //  y se devuelven las que se van a guardar.
            
            if((tanda+cantidad)>capacidadMax && cantidad<capacidadMax){
                desperdicios[id] +=(tanda+cantidad-capacidadMax);
                System.out.println("Repostero"+(id+1)+" ha desperdiciado "+(tanda+cantidad-capacidadMax)+" galletas "+LocalTime.now()+"\n");
                archivo.write("Repostero"+(id+1)+" ha desperdiciado "+(tanda+cantidad-capacidadMax)+" galletas "+LocalTime.now()+"\n");
               return tanda-(tanda+cantidad-capacidadMax);
            }//if
            return tanda;
        }//fin
        
        /////////////////////////////////////////////////////////
        ///////////// Metodos para la interfaz //////////////////
        /////////////////////////////////////////////////////////
        
        public int darDesperdicios(int id){
            //Devuelve los desperdicios de un repostero con su id.
            
            return desperdicios[id];
        }//fin
                
        public int galletasDentro(){
            //Devuelve las galletas que estan dentro
            //  de el horno actualmente.
            
            return cantidad;
        }//fin
        
        public String darColaCafetera(){
            //Comprueba que reposteros estan esperando para la 
            //  cafetera y los devuelve en una frase
            
            String esperando = "";
            for(int i=0;i<colaCafetera.length;i++){
                if(colaCafetera[i]!=null){
                    esperando+=colaCafetera[i];
                    esperando+=" ";                   
                }//if
            }//for
            return esperando;
        }//fin
        
        public String haciendoCafe(){
            //Devuelve el nombre del repostero que está
            //  haciendose un café
            
            return cafetera;
        }//fin
        
    /////////////////////////////////////////////////////////
    ///////////// Metodos getter y setter  //////////////////
    /////////////////////////////////////////////////////////

    public String getNombre() {
        return nombre;
    }

    public String getCafetera() {
        return cafetera;
    }

    public String[] getColaCafetera() {
        return colaCafetera;
    }

    public int[] getDesperdicios() {
        return desperdicios;
    }

    public boolean isHorneando() {
        return horneando;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getGalletasHorneadas() {
        return galletasHorneadas;
    }

    public int getnCola() {
        return nCola;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public FileWriter getArchivo() {
        return archivo;
    }
        
    }//fin clase horno
