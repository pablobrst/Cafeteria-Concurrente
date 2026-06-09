
package com.mycompany.peclparadigmasprogramacion;

import static java.awt.Color.ORANGE;
import static java.awt.Color.WHITE;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Timer;


public class PantallaConcurrencia extends javax.swing.JFrame {    
    //reposteros
    private Repostero repostero0, repostero1, repostero2, repostero3, repostero4;
    
    //Horno y almacen
    private Horno horno1, horno2, horno3;
    private Almacen almacen;
    
    //Empaquetadores
    private Empaquetador empaquetador1, empaquetador2, empaquetador3;
    
    //Archivo log
    private final FileWriter archivo;
    
    public PantallaConcurrencia(Repostero repostero0, Repostero repostero1, Repostero repostero2, Repostero repostero3, Repostero repostero4, 
        Horno horno1, Horno horno2, Horno horno3, Almacen almacen,Empaquetador empaquetador1, Empaquetador empaquetador2,
        Empaquetador empaquetador3,FileWriter archivo) throws IOException {
        //Carga la interfaz de servidor, inicializa el archivo log,
        //  crea el recurso compartido por empaquetadores ,almacén,
        //  inicializa los hilos( repostero,horno y empaqueador),
        //  inicia el hilo envio de datos a cliente por TCP 
        //  y inicia los hilos que detectan mediante tcp solo si
        //  se ha dado la orden de detener reposteros desde cliente.
        
        //Reposteros
        this.repostero0 = repostero0;
        this.repostero1 = repostero1;
        this.repostero2 = repostero2;
        this.repostero3 = repostero3;
        this.repostero4 = repostero4;
        //Hornos
        this.horno1 = horno1;
        this.horno2 = horno2;
        this.horno3 = horno3;
        this.almacen = almacen;
        //Empaquetadores
        this.empaquetador1 = empaquetador1;
        this.empaquetador2 = empaquetador2;
        this.empaquetador3 = empaquetador3;
        //Archivo Log
        this.archivo = archivo; // El archivo de log se pasa al constructor
                
        initComponents();
        this.setResizable(false);
        
        Timer timer = new Timer(100, e -> cargarTextos()); 
        timer.start(); // Iniciar el temporizador
        
    }//Constructor del Jframe Pantalla concurrencia
    
    public void cargarTextos() {
        //Carga en la interfaz del servidor todos 
        //  los datos relacionados con los hilos,
        //  recursos compartidos y sus estados y
        //  es lanzado por el temporizador cada 100 ms.

        String estado = "";
        int paquetes=0;
        ph3.setValue(100);
        ph2.setValue(100);
        ph1.setValue(100);

        //cafetera
        estado = horno1.haciendoCafe();
        cafetera.setText(String.valueOf(estado));

        //colacafetera
        estado=horno1.darColaCafetera();
        CC.setText(String.valueOf(estado));

        // Repostero 0
        estado = repostero0.darEstado();
        r1.setText(String.valueOf(estado));

        // Repostero 1
        estado = repostero1.darEstado();
        r2.setText(String.valueOf(estado));

        // Repostero 2
        estado = repostero2.darEstado();
        r3.setText(String.valueOf(estado));

        // Repostero 3
        estado = repostero3.darEstado();
        r4.setText(String.valueOf(estado));

        // Repostero 4
        estado = repostero4.darEstado();
        r5.setText(String.valueOf(estado));

        // Empaquetador 1
        estado = empaquetador1.darEstado();
        E1.setText(String.valueOf(estado));
        paquetes =  empaquetador1.estadoEmpaquetado();
        pe1.setForeground(ORANGE);
        pe1.setValue(((paquetes*100)/5));

        // Empaquetador 2
        estado = empaquetador2.darEstado();
        E2.setText(String.valueOf(estado));
        paquetes =  empaquetador2.estadoEmpaquetado();
        pe2.setForeground(ORANGE);
        pe2.setValue(((paquetes*100)/5));

        // Empaquetador 3
        estado = empaquetador3.darEstado();
        E3.setText(String.valueOf(estado));
        paquetes =  empaquetador3.estadoEmpaquetado();
        pe3.setForeground(ORANGE);
        pe3.setValue(((paquetes*100)/5));

        //Horno1
        int go=horno1.galletasDentro();
        go1.setText(String.valueOf(go));
        if(horno1.horneando){
            ph1.setForeground(ORANGE);
        }else{
            ph1.setForeground(WHITE);
        }//if

        //horno2
        go=horno2.galletasDentro();
        go2.setText(String.valueOf(go));
        if(horno2.horneando){
            ph2.setForeground(ORANGE);
        }else{
            ph2.setForeground(WHITE);
        }//if

        //Horno3
        go=horno3.galletasDentro();
        go3.setText(String.valueOf(go));
        if(horno3.horneando){
            ph3.setForeground(ORANGE);
        }else{
            ph3.setForeground(WHITE);
        }//if

        //Almacen
        int Ga=almacen.galletasAlmacenadas();
        ga.setText(String.valueOf(Ga));
    }//fin
   
    public static void main(String args[]) throws IOException {
        // main
        //Crea el recurso compartido almacén asi como todos los hilos 
        //  (reposteros,hornos,empaquetadores y hilos relacionados con cliente)
        //  posteriormente pone en ejecucion dichos hilos y lanza la interfaz servidor
        
        //Archivo Log
        FileWriter archivo = new FileWriter("evolucionGalletas.txt");
        //Crear las instancias de Almacen, Hornos, y Empaquetadores
        Almacen almacen = new Almacen(archivo);  
        Horno horno1 = new Horno("horno1",archivo);
        Horno horno2 = new Horno("horno2",archivo);
        Horno horno3 = new Horno("horno3",archivo);
        //iniciar los Hornos
        horno1.start();
        horno2.start();
        horno3.start();
        // Crear los reposteros
        Repostero repostero0 = new Repostero("Repostero 1", 0, horno1, horno2, horno3,archivo);
        Repostero repostero1 = new Repostero("Repostero 2", 1, horno1, horno2, horno3,archivo);
        Repostero repostero2 = new Repostero("Repostero 3", 2, horno1, horno2, horno3,archivo);
        Repostero repostero3 = new Repostero("Repostero 4", 3, horno1, horno2, horno3,archivo);
        Repostero repostero4 = new Repostero("Repostero 5", 4, horno1, horno2, horno3,archivo);
        //iniciar los reposteros
        repostero0.start();
        repostero1.start();
        repostero2.start();
        repostero3.start();
        repostero4.start();
        //crear empaquetadores
        Empaquetador empaquetador1 = new Empaquetador("empaquetador1", horno1, almacen);
        Empaquetador empaquetador2 = new Empaquetador("empaquetador2", horno2, almacen);
        Empaquetador empaquetador3 = new Empaquetador("empaquetador3", horno3, almacen);
        // Iniciar los empaquetadores
        empaquetador1.start();
        empaquetador2.start();
        empaquetador3.start();  
        // Crear e iniciar hilo que envia datos a la interfaz cliente
        HiloEnviarDatosTCP hiloServidor = new HiloEnviarDatosTCP(repostero0, repostero1,
        repostero2,repostero3, repostero4, horno1, horno2, horno3, almacen);
        //iniciar envio de datos
        hiloServidor.start();
        //Crear hilo que detiene reposteros en funcion de cliente
        DetenerReposteros detenerR1 = new DetenerReposteros(1501,repostero0);
        DetenerReposteros detenerR2 = new DetenerReposteros(1502,repostero1);
        DetenerReposteros detenerR3 = new DetenerReposteros(1503,repostero2);
        DetenerReposteros detenerR4 = new DetenerReposteros(1504,repostero3);
        DetenerReposteros detenerR5 = new DetenerReposteros(1505,repostero4);
        //Iniciar hilos que detienen repostero por cliente
        detenerR1.start();
        detenerR2.start();
        detenerR3.start();
        detenerR4.start();
        detenerR5.start();
        
        //Iniciamos Interfaz servidor
        new PantallaConcurrencia(repostero0, repostero1, repostero2, repostero3,
            repostero4,horno1, horno2, horno3, almacen,empaquetador1, empaquetador2,
            empaquetador3,archivo).setVisible(true);
          
    }//fin Main
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar2 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cafetera = new javax.swing.JTextField();
        CC = new javax.swing.JTextField();
        r1 = new javax.swing.JTextField();
        r4 = new javax.swing.JTextField();
        r2 = new javax.swing.JTextField();
        r3 = new javax.swing.JTextField();
        r5 = new javax.swing.JTextField();
        go2 = new javax.swing.JTextField();
        go3 = new javax.swing.JTextField();
        go1 = new javax.swing.JTextField();
        ga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        E1 = new javax.swing.JTextField();
        E2 = new javax.swing.JTextField();
        E3 = new javax.swing.JTextField();
        pe2 = new javax.swing.JProgressBar();
        pe1 = new javax.swing.JProgressBar();
        pe3 = new javax.swing.JProgressBar();
        ph1 = new javax.swing.JProgressBar();
        ph2 = new javax.swing.JProgressBar();
        ph3 = new javax.swing.JProgressBar();
        ConsumirGalletas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Cafetera");

        jLabel4.setText("REPOSTERO 3");

        jLabel5.setText("REPOSTERO 2");

        jLabel6.setText("REPOSTERO 4");

        jLabel7.setText("REPOSTERO 5");

        jLabel9.setText("Horneando");

        jLabel10.setText("HORNO 2");

        jLabel11.setText("HORNO 3");

        jLabel12.setText("HORNO 1");

        jLabel13.setText("ALMACEN");

        jLabel16.setText("Numero de galletas");

        cafetera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeteraActionPerformed(evt);
            }
        });

        CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CCActionPerformed(evt);
            }
        });

        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        r4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r4ActionPerformed(evt);
            }
        });

        r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2ActionPerformed(evt);
            }
        });

        go1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go1ActionPerformed(evt);
            }
        });

        ga.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaActionPerformed(evt);
            }
        });

        jLabel8.setText("REPOSTERO 1");

        jLabel17.setText("Horneando");

        jLabel18.setText("Horneando");

        jLabel19.setText("Numero de galletas");

        jLabel20.setText("Numero de galletas");

        jLabel14.setText("EMPAQUETADOR 1");

        jLabel15.setText("EMPAQUETADOR 3");

        jLabel21.setText("EMPAQUETADOR 2");

        ConsumirGalletas.setText("Consumir galletas (-100)");
        ConsumirGalletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsumirGalletasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(pe1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(go1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)))
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(go2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel19)
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pe2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(19, 19, 19)))
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(ph3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(E3)
                                                        .addComponent(jLabel20)
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(go3)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pe3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(ConsumirGalletas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                                .addComponent(ga, javax.swing.GroupLayout.Alignment.LEADING))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jLabel13)))
                        .addContainerGap(122, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(r1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cafetera, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CC)))
                        .addGap(104, 104, 104))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CC)
                    .addComponent(cafetera, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(r1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(r3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(r2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(r4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(go2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(go1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(go3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ph3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ph2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ph1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel21)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pe1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pe2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pe3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ga, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConsumirGalletas)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cafeteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeteraActionPerformed

    }//GEN-LAST:event_cafeteraActionPerformed

    private void CCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CCActionPerformed

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r1ActionPerformed

    private void r4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r4ActionPerformed

    private void r2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r2ActionPerformed

    private void ConsumirGalletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsumirGalletasActionPerformed
        //Cada vez que se pulsa se consumen 100 galletas y se 
        //  actualiza la interfaz galletas consumidas.
        
        try {
            almacen.comerGalletas();
            ga.setText(String.valueOf(almacen.galletasAlmacenadas()));
        } catch (Exception ex) {}
        
    }//GEN-LAST:event_ConsumirGalletasActionPerformed

    private void go1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_go1ActionPerformed

    private void gaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CC;
    private javax.swing.JButton ConsumirGalletas;
    private javax.swing.JTextField E1;
    private javax.swing.JTextField E2;
    private javax.swing.JTextField E3;
    private javax.swing.JTextField cafetera;
    private javax.swing.JTextField ga;
    private javax.swing.JTextField go1;
    private javax.swing.JTextField go2;
    private javax.swing.JTextField go3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar pe1;
    private javax.swing.JProgressBar pe2;
    private javax.swing.JProgressBar pe3;
    private javax.swing.JProgressBar ph1;
    private javax.swing.JProgressBar ph2;
    private javax.swing.JProgressBar ph3;
    private javax.swing.JTextField r1;
    private javax.swing.JTextField r2;
    private javax.swing.JTextField r3;
    private javax.swing.JTextField r4;
    private javax.swing.JTextField r5;
    // End of variables declaration//GEN-END:variables
}//fin Pantalla del servidor carga concurrencia y envia datos  a cliente.
