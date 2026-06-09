/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peclparadigmasprogramacion;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author TrendingPC
 */
public class DetenerReposteros extends Thread{
        //Hilo que establece conexion TCP con Cliente para recibir 
        //  la orden booleana de detener a un repostero con id predeterminado.
        
        //TCP
        private ServerSocket servidor;
        private Socket conexion;
        private DataInputStream entrada;
        
        //Booleanos
        private boolean Señal,detenido0 =false;
        
        //Enteros
        private int puerto;
        
        //Repostero
        private Repostero repostero;
        
        public DetenerReposteros(int puerto,Repostero repostero){
            this.puerto=puerto;
            this.repostero=repostero;
        }//constructor
        
        /////////////////////////////////////////////////////////
        //////////////// Ejecucion del hilo /////////////////////
        /////////////////////////////////////////////////////////
                
        public void run(){
            //Crea un Servidor en el puerto predeterminado y en funcion del 
            //  id que se haya dado mandara la orden de detener o reanudar 
            //  a un repostero o a otro cuando recibe la señal.
            
            try {
                ServerSocket servidor = new ServerSocket(puerto);
                Socket conexion;
                DataInputStream entrada;
                
                while(true){
                    conexion = servidor.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    
                    Señal=entrada.readBoolean();
                    if(detenido0) {
                        detenido0 = false;
                        repostero.reanudarse();
                    } else {
                        detenido0 = true;
                        repostero.detenerse(); 
                    } // if1.1

                    entrada.close();
                    conexion.close();
                }//while true
            }catch(Exception ex){}
        }//fin
    }//fin clase hilo que detiene reposteros
