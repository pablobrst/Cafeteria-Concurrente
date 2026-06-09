
package com.mycompany.peclparadigmasprogramacion;

import static java.awt.Color.ORANGE;
import static java.awt.Color.WHITE;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Clientes extends javax.swing.JFrame {
    
    //Booleanos
    private boolean ejecutarR1 = true, ejecutarR2 = true, ejecutarR3 = true, ejecutarR4 = true, ejecutarR5 = true;
    
    //HiloRecibirDatos
    HiloRecibirDatosTCP hiloTCP =  new HiloRecibirDatosTCP();

    public Clientes() throws IOException {
        //Inicia la interfaz Cliente y lanza un hilo que recibe
        //  los datos de servidor de manera distribuida por TCP.
        
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        hiloTCP.start();
    }//constructor
    
    public class HiloRecibirDatosTCP extends Thread{
        //Booleanos
        boolean conectar = true;
        
        //TCP
        Socket conexion;
        DataInputStream entrada;
        DataOutputStream salida;
        
        //Enteros
        int valor1,valor2,valor3,valor4,valor5,total1, total2, total3, total4, total5;
        
        /////////////////////////////////////////////////////////
        //////////////// Ejecucion del hilo /////////////////////
        /////////////////////////////////////////////////////////
        
        public void run(){
            //Crea una conexion con el serverSocket (servidor) y 
            //  recibe de manera infinita los datos cargandolos 
            //  en la interfaz posteriormente.
            
            try{
            conexion = new Socket(InetAddress.getLocalHost(),1500);
            entrada = new DataInputStream(conexion.getInputStream()); 
            salida = new DataOutputStream(conexion.getOutputStream());
            
            ph3.setValue(100);
            ph2.setValue(100);
            ph1.setValue(100);

            while(true){
                    salida.writeBoolean(conectar);
                    // Entradas de las galletas horneadas por cada respostero
                    valor1 = entrada.readInt();
                    gg1.setText(String.valueOf(valor1));

                    valor2 = entrada.readInt();
                    gg2.setText(String.valueOf(valor2));

                    valor3 = entrada.readInt();
                    gg3.setText(String.valueOf(valor3));

                    valor4 = entrada.readInt();
                    gg4.setText(String.valueOf(valor4));

                    valor5 = entrada.readInt();
                    gg5.setText(String.valueOf(valor5));

                    // Entradas de las galletas desperdiciadas por cada repostero
                    total1 = entrada.readInt();
                    gd1.setText(String.valueOf(total1));

                    total2 = entrada.readInt();
                    gd2.setText(String.valueOf(total2));

                    total3 = entrada.readInt();
                    gd3.setText(String.valueOf(total3));

                    total4 = entrada.readInt();
                    gd4.setText(String.valueOf(total4));

                    total5 = entrada.readInt();
                    gd5.setText(String.valueOf(total5));

                    //Entradas de galletas horneadas por horno
                    gh1.setText(String.valueOf(entrada.readInt()));
                    gh2.setText(String.valueOf(entrada.readInt()));
                    gh3.setText(String.valueOf(entrada.readInt()));

                    //Entrada galletas almacenadas
                    ga.setText(String.valueOf(entrada.readInt()));

                    //Entrada galletas almacenadas
                    gc.setText(String.valueOf(entrada.readInt()));

                    // Entradas progress bar
                    if(entrada.readBoolean()){
                        ph1.setForeground(ORANGE);
                    }else{
                        ph1.setForeground(WHITE);
                    }//if1
                    if(entrada.readBoolean()){
                        ph2.setForeground(ORANGE);
                    }else{
                        ph2.setForeground(WHITE);
                    }//if2
                    if(entrada.readBoolean()){
                        ph3.setForeground(ORANGE);
                    }else{
                        ph3.setForeground(WHITE);
                    }//if3
                    
                }//whie
            }catch(Exception ex){}
        }//fin
        
        public void finalizarConexion(){
            //finaliza la conexion con servidor
            
            conectar = false;
        }//fin
        
    }//fin hilo recibe y carga datos de interfaz
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gg2 = new javax.swing.JTextField();
        gg3 = new javax.swing.JTextField();
        gg4 = new javax.swing.JTextField();
        gg5 = new javax.swing.JTextField();
        gg1 = new javax.swing.JTextField();
        gd1 = new javax.swing.JTextField();
        gd2 = new javax.swing.JTextField();
        gd3 = new javax.swing.JTextField();
        gd4 = new javax.swing.JTextField();
        gd5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PausaR2 = new javax.swing.JButton();
        PausaR1 = new javax.swing.JButton();
        PausaR3 = new javax.swing.JButton();
        PausaR4 = new javax.swing.JButton();
        PausaR5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        gh2 = new javax.swing.JTextField();
        gh1 = new javax.swing.JTextField();
        gh3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ga = new javax.swing.JTextField();
        gc = new javax.swing.JTextField();
        ph1 = new javax.swing.JProgressBar();
        ph2 = new javax.swing.JProgressBar();
        ph3 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gg2.setText("0");
        gg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gg2ActionPerformed(evt);
            }
        });

        gg3.setText("0");
        gg3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gg3ActionPerformed(evt);
            }
        });

        gg4.setText("0");
        gg4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gg4ActionPerformed(evt);
            }
        });

        gg5.setText("0");
        gg5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gg5ActionPerformed(evt);
            }
        });

        gg1.setText("0");
        gg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gg1ActionPerformed(evt);
            }
        });

        gd1.setText("0");
        gd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gd1ActionPerformed(evt);
            }
        });

        gd2.setText("0");
        gd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gd2ActionPerformed(evt);
            }
        });

        gd3.setText("0");
        gd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gd3ActionPerformed(evt);
            }
        });

        gd4.setText("0");
        gd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gd4ActionPerformed(evt);
            }
        });

        gd5.setText("0");
        gd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gd5ActionPerformed(evt);
            }
        });

        jLabel1.setText("Repostero 1");

        jLabel2.setText("Repostero 2");

        jLabel3.setText("Repostero 3");

        jLabel4.setText("Repostero 4");

        jLabel5.setText("Repostero 5");

        jLabel6.setText("Galletas generadas");

        jLabel7.setText("Galletas desperdiciadas");

        PausaR2.setText("Pausar");
        PausaR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PausaR2ActionPerformed(evt);
            }
        });

        PausaR1.setText("Pausar");
        PausaR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PausaR1ActionPerformed(evt);
            }
        });

        PausaR3.setText("Pausar");
        PausaR3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PausaR3ActionPerformed(evt);
            }
        });

        PausaR4.setText("Pausar");
        PausaR4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PausaR4ActionPerformed(evt);
            }
        });

        PausaR5.setText("Pausar");
        PausaR5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PausaR5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Galletas generadas");

        jLabel9.setText("Horneando");

        jLabel11.setText("Horno 1");

        jLabel12.setText("Horno 2");

        jLabel13.setText("Horno 3");

        gh2.setText("0");
        gh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gh2ActionPerformed(evt);
            }
        });

        gh1.setText("0");
        gh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gh1ActionPerformed(evt);
            }
        });

        gh3.setText("0");
        gh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gh3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Galletas almacenadas");

        jLabel14.setText("Galletas consumidas");

        jLabel15.setText("Almacén");

        ga.setText("0");
        ga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaActionPerformed(evt);
            }
        });

        gc.setText("0");
        gc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcActionPerformed(evt);
            }
        });

        jButton1.setText("Finalizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ga)
                    .addComponent(gg1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(gg5, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(gg4, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(gg3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(gg2)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gh1)
                    .addComponent(gh2)
                    .addComponent(gh3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(gc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gd1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(PausaR1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gd5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(PausaR5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gd4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(PausaR4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gd3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(PausaR3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gd2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(PausaR2))
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 84, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ph3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gd1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PausaR1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gd2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PausaR2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gd3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PausaR3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gd4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PausaR4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gd5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PausaR5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gg1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gg2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gg3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gg4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(gg5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(gh1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ph1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(gh2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ph2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(gh3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ph3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(ga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gg2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gg2ActionPerformed

    private void gg3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gg3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gg3ActionPerformed

    private void gg4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gg4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gg4ActionPerformed

    private void gg5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gg5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gg5ActionPerformed

    private void gg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gg1ActionPerformed

    private void gd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gd1ActionPerformed

    private void gd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gd2ActionPerformed

    private void gd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gd3ActionPerformed

    private void gd4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gd4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gd4ActionPerformed

    private void gd5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gd5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gd5ActionPerformed

    private void gh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gh2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gh2ActionPerformed

    private void gh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gh1ActionPerformed

    private void gh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gh3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gh3ActionPerformed

    private void gaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gaActionPerformed

    private void gcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gcActionPerformed

    private void PausaR5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PausaR5ActionPerformed
        //Si es pulsado por primera vez no hace nada, 
        //  la segunda vez el boton establecera una conexion
        //  con un hilo que ejercera de servidor para enviarle
        //  un booleano que indica si detener o reanudar un repostero.
        
        //TCP  
        Socket conexion;
        DataOutputStream salida;

        try{
            conexion = new Socket(InetAddress.getLocalHost(),1505);
            salida = new DataOutputStream(conexion.getOutputStream()); //Abrimos los canales de E/S

            if(ejecutarR5){
                ejecutarR5=false;
                PausaR5.setText("Reanudar");
                PausaR5.setBackground(ORANGE);
            }else{ 
                ejecutarR5=true;
                PausaR5.setText("Pausar");
                PausaR5.setBackground(WHITE);
            }//if1
            salida.writeBoolean(ejecutarR5);
            salida.close();
            conexion.close();
            }catch(Exception ex){}               
        
    }//GEN-LAST:event_PausaR5ActionPerformed

    private void PausaR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PausaR1ActionPerformed
        //Si es pulsado por primera vez no hace nada, 
        //  la segunda vez el boton establecera una conexion
        //  con un hilo que ejercera de servidor para enviarle
        //  un booleano que indica si detener o reanudar un repostero.
        
        //TCP   
        Socket conexion;
        DataOutputStream salida;
        
        try{
            conexion = new Socket(InetAddress.getLocalHost(),1501);
            salida = new DataOutputStream(conexion.getOutputStream()); //Abrimos los canales de E/S
            
            if(ejecutarR1){
                ejecutarR1=false;
                PausaR1.setText("Reanudar");
                PausaR1.setBackground(ORANGE);
            }else{ 
                ejecutarR1=true;
                PausaR1.setText("Pausar");
                PausaR1.setBackground(WHITE);
            }//if1
            salida.writeBoolean(ejecutarR1);
            salida.close();
            conexion.close();
                }catch(Exception ex){}
        
    }//GEN-LAST:event_PausaR1ActionPerformed

    private void PausaR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PausaR2ActionPerformed
        //Si es pulsado por primera vez no hace nada, 
        //  la segunda vez el boton establecera una conexion
        //  con un hilo que ejercera de servidor para enviarle
        //  un booleano que indica si detener o reanudar un repostero.
        
        //TCP
        Socket conexion;
        DataOutputStream salida;

        try{
            conexion = new Socket(InetAddress.getLocalHost(),1502);
            salida = new DataOutputStream(conexion.getOutputStream()); //Abrimos los canales de E/S

            if(ejecutarR2){
                ejecutarR2=false;
                PausaR2.setText("Reanudar");
                PausaR2.setBackground(ORANGE);
            }else{
                ejecutarR2=true;
                PausaR2.setText("Pausar");
                PausaR2.setBackground(WHITE);
            }//if1
            salida.writeBoolean(ejecutarR2);
            salida.close();
            conexion.close();
        }catch(Exception ex){}
        
    }//GEN-LAST:event_PausaR2ActionPerformed

    private void PausaR3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PausaR3ActionPerformed
        //Si es pulsado por primera vez no hace nada, 
        //  la segunda vez el boton establecera una conexion
        //  con un hilo que ejercera de servidor para enviarle
        //  un booleano que indica si detener o reanudar un repostero.
        
        //TCP
        Socket conexion;
        DataOutputStream salida;

        try{
            conexion = new Socket(InetAddress.getLocalHost(),1503);
            salida = new DataOutputStream(conexion.getOutputStream()); //Abrimos los canales de E/S

            if(ejecutarR3){
                ejecutarR3=false;
                PausaR3.setText("Reanudar");
                PausaR3.setBackground(ORANGE);
            }else{
                ejecutarR3=true;
                PausaR3.setText("Pausar");
                PausaR3.setBackground(WHITE);
            }//if1
            salida.writeBoolean(ejecutarR3);
            salida.close();
            conexion.close();
        }catch(Exception ex){}
        
    }//GEN-LAST:event_PausaR3ActionPerformed

    private void PausaR4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PausaR4ActionPerformed
        //Si es pulsado por primera vez no hace nada, 
        //  la segunda vez el boton establecera una conexion
        //  con un hilo que ejercera de servidor para enviarle
        //  un booleano que indica si detener o reanudar un repostero.
        
        //TCP
        Socket conexion;
        DataOutputStream salida;

        try{
            conexion = new Socket(InetAddress.getLocalHost(),1504);
            salida = new DataOutputStream(conexion.getOutputStream()); //Abrimos los canales de E/S

            if(ejecutarR4){
                ejecutarR4=false;
                PausaR4.setText("Reanudar");
                PausaR4.setBackground(ORANGE);
            }else{
                ejecutarR4=true;
                PausaR4.setText("Pausar");
                PausaR4.setBackground(WHITE);
            }//if1
            salida.writeBoolean(ejecutarR4);
            salida.close();
            conexion.close();
        }catch(Exception ex){}
        
    }//GEN-LAST:event_PausaR4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Detiene la ejecucion del programa
        
        hiloTCP.finalizarConexion();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        try {
            new Clientes().setVisible(true);
        }catch(IOException ex){}
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PausaR1;
    private javax.swing.JButton PausaR2;
    private javax.swing.JButton PausaR3;
    private javax.swing.JButton PausaR4;
    private javax.swing.JButton PausaR5;
    private javax.swing.JTextField ga;
    private javax.swing.JTextField gc;
    private javax.swing.JTextField gd1;
    private javax.swing.JTextField gd2;
    private javax.swing.JTextField gd3;
    private javax.swing.JTextField gd4;
    private javax.swing.JTextField gd5;
    private javax.swing.JTextField gg1;
    private javax.swing.JTextField gg2;
    private javax.swing.JTextField gg3;
    private javax.swing.JTextField gg4;
    private javax.swing.JTextField gg5;
    private javax.swing.JTextField gh1;
    private javax.swing.JTextField gh2;
    private javax.swing.JTextField gh3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar ph1;
    private javax.swing.JProgressBar ph2;
    private javax.swing.JProgressBar ph3;
    // End of variables declaration//GEN-END:variables
}
