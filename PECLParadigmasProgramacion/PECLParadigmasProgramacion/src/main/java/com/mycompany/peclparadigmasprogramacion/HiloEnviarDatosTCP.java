
package com.mycompany.peclparadigmasprogramacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloEnviarDatosTCP extends Thread{
        //Hilo que tras establecer una conexion con cliete sin cerrar
        //  dicha conexion empieza de manera indefinida a mandar 
        //  datos a la interfaz cliente.
    
        //Booleanos
        private boolean conectar = true;
        
        //Concurrencia
        private Repostero repostero0, repostero1, repostero2, repostero3, repostero4;
        private Horno horno1, horno2, horno3;
        private Almacen almacen;
        
        //TCP
        private ServerSocket servidor;
        private Socket conexion;
        private DataOutputStream salida;
        private DataInputStream entrada;
        
        public HiloEnviarDatosTCP(Repostero repostero0, Repostero repostero1, Repostero repostero2, 
            Repostero repostero3, Repostero repostero4, Horno horno1, Horno horno2, Horno horno3,
            Almacen almacen) {
        // Inicialización de los objetos pasados como parámetros
        
        this.repostero0 = repostero0;
        this.repostero1 = repostero1;
        this.repostero2 = repostero2;
        this.repostero3 = repostero3;
        this.repostero4 = repostero4;
        this.horno1 = horno1;
        this.horno2 = horno2;
        this.horno3 = horno3;
        this.almacen = almacen;
        
    }//constructor
                
        /////////////////////////////////////////////////////////
        //////////////// Ejecucion del hilo /////////////////////
        /////////////////////////////////////////////////////////
        
        public void run(){
            //Inicia un servidor, espera que se conecte y envia datos
            // infinitamente a cliente para cargar su interfaz distribuida por TCP.
            
            
            try{
                servidor = new ServerSocket(1500); //Creamos un ServerSocket en el Puerto 1500
                while(true){
                    conexion = servidor.accept(); //Esperamos una conexión
                    salida = new DataOutputStream(conexion.getOutputStream());
                    entrada = new DataInputStream(conexion.getInputStream());

                    while(conectar){
                        conectar = entrada.readBoolean();
                       //Salidas de galletas horneadas por cada respostero
                        salida.writeInt(repostero0.getGalletasGeneradas());
                        salida.writeInt(repostero1.getGalletasGeneradas());
                        salida.writeInt(repostero2.getGalletasGeneradas());
                        salida.writeInt(repostero3.getGalletasGeneradas());
                        salida.writeInt(repostero4.getGalletasGeneradas());

                        // Obtener los desperdicios de cada horno
                        int[] desperdicios1 = horno1.getDesperdicios();
                        int[] desperdicios2 = horno2.getDesperdicios();
                        int[] desperdicios3 = horno3.getDesperdicios();

                        // Sumar y escribir los desperdicios para cada uno de los 5 índices
                        int totalDesperdiciadas1 = desperdicios1[0] + desperdicios2[0] + desperdicios3[0];
                        salida.writeInt(totalDesperdiciadas1);

                        int totalDesperdiciadas2 = desperdicios1[1] + desperdicios2[1] + desperdicios3[1];
                        salida.writeInt(totalDesperdiciadas2);

                        int totalDesperdiciadas3 = desperdicios1[2] + desperdicios2[2] + desperdicios3[2];
                        salida.writeInt(totalDesperdiciadas3);

                        int totalDesperdiciadas4 = desperdicios1[3] + desperdicios2[3] + desperdicios3[3];
                        salida.writeInt(totalDesperdiciadas4);

                        int totalDesperdiciadas5 = desperdicios1[4] + desperdicios2[4] + desperdicios3[4];
                        salida.writeInt(totalDesperdiciadas5);

                        //Salidas horneadas por horno
                        salida.writeInt(horno1.getGalletasHorneadas());
                        salida.writeInt(horno2.getGalletasHorneadas());
                        salida.writeInt(horno3.getGalletasHorneadas());

                        //Salidas galletas en almacen
                        salida.writeInt(almacen.getCantidad());

                        //Salidas galletas consumidas
                        salida.writeInt(almacen.getGalletasConsumidas());

                        //Salidas progress bar
                        salida.writeBoolean(horno1.horneando);
                        salida.writeBoolean(horno2.horneando);
                        salida.writeBoolean(horno3.horneando);

                        salida.flush(); 
                        sleep(1000);
                    }//while
                    
                    salida.close();
                    entrada.close();
                    conexion.close();
                    
                    conectar=true;
                    
                }//while2
            }catch(Exception ex){}
            
        }//fin
    
    }//fin clase hilo carga interfaz cliente
