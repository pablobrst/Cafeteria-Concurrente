# Cafetería Concurrente y Distribuida
 
Simulación completa de una cafetería mediante **programación concurrente y distribuida en Java**, con hasta **9.000 hilos ejecutándose simultáneamente**.
 
---
 
## Descripción
 
El sistema modela el funcionamiento real de una cafetería donde tres tipos de actores (clientes, cocineros y vendedores) operan de forma concurrente accediendo a recursos compartidos. Implementa el patrón **productor-consumidor en dos niveles**, sincronización avanzada con semáforos, locks y monitores, y acceso remoto al estado del sistema mediante **Java RMI**.
 
---
 
## Arquitectura del sistema
 
### Patrón Productor-Consumidor (2 niveles)
 
```
[Cocineros] → producen → [Despensa] → consumen → [Vendedores]
                                                       ↓
                                               producen en
                                                       ↓
[Clientes]  → consumen ←←←←←←←←←←←← [Mostrador]
```
 
### Actores y escala
 
| Actor | Cantidad | Ciclo de vida |
|-------|----------|---------------|
| Clientes | 8.000 | Finito: parque → mostrador → caja → consumición |
| Cocineros | 500 | Infinito: descanso → cocina → despensa → descanso |
| Vendedores | 500 | Infinito: descanso → despensa → mostrador → descanso |
 
---
 
## Mecanismos de sincronización
 
### Semáforos (`java.util.concurrent.Semaphore`)
 
| Área | Aforo máximo | Tipo |
|------|-------------|------|
| Mostrador (cola) | 20 | FIFO |
| Mostrador (atención) | 5 | Normal |
| Caja | 10 | FIFO |
| Área de consumición | 30 | FIFO |
| Cocina | 100 | Normal |
| Despensa (cocineros) | 50 | Normal |
| Despensa (vendedores) | 50 | Normal |
 
### ReentrantLock + Condition
Usado en el Mostrador para que los clientes esperen a que haya suficientes productos sin bloquear el sistema. El método `await()` libera el lock mientras espera; `signalAll()` despierta a todos los clientes cuando el vendedor repone stock.
 
### Monitores (`synchronized` + `wait/notifyAll`)
Usados en `ControlPausa` para pausar y reanudar todos los hilos del sistema de forma coordinada sin perder el estado.
 
### Mutex binario
Semáforo con permiso único en `Caja` (para actualizar ingresos totales) y `Despensa` (para modificar el stock), garantizando exclusión mutua en las secciones críticas.
 
---
 
## Comunicación remota (Java RMI)
 
El sistema expone su estado en tiempo real a través de **Java RMI en el puerto 1099**. Un cliente remoto independiente puede conectarse y visualizar el estado de todas las áreas sin estar en la misma JVM.
 
```java
// Servidor registra los objetos
Naming.rebind("//localhost/mostrador", mostrador);
Naming.rebind("//localhost/despensa", despensa);
 
// Cliente remoto los consulta
MostradorRemote mostrador = (MostradorRemote) Naming.lookup("//localhost/mostrador");
```
 
Interfaces remotas implementadas: `ParqueRemote`, `MostradorRemote`, `CajaRemote`, `AreaConsumicionRemote`, `CocinaRemote`, `DespensaRemote`, `SalaDescansoRemote`, `ControlPausaRemote`.
 
---
 
## Estructura del proyecto
 
```
CafeteriaConcurrente/
├── src/
│   ├── com/mycompany/cafeteria/
│   │   ├── Cafeteria.java          # Main: inicializa todo y lanza RMI
│   │   ├── ControlPausa.java       # Pausa/reanuda todos los hilos
│   │   ├── LogCafeteria.java       # Sistema de logs con timestamp
│   │   ├── Pedido.java             # DTO pedido cliente
│   │   ├── LlevarMostrador.java    # DTO transporte vendedor→mostrador
│   │   └── TransporteDespensa.java # DTO transporte cocinero→despensa
│   ├── Hilos/
│   │   ├── PadreHilos.java         # Fábrica de hilos (crea los 9.000)
│   │   ├── Clientes.java
│   │   ├── Cocineros.java
│   │   └── Vendedores.java
│   ├── ObjCompartido/
│   │   ├── Parque.java
│   │   ├── Mostrador.java          # Lock + Condition + Semáforos
│   │   ├── Caja.java
│   │   ├── Area_de_consumición.java
│   │   ├── Cocina.java
│   │   ├── Despensa.java
│   │   └── Sala_de_descanso.java
│   ├── InterfaceRemote/            # Interfaces RMI
│   ├── Interface/
│   │   ├── Interfaz.java           # GUI servidor (Swing, actualización 1s)
│   │   └── InterfazCliente.java    # GUI cliente remoto
│   └── RMICliente/
│       └── RMICliente.java         # Punto de entrada cliente remoto
└── README.md
```
 
---
 
## Cómo ejecutar
 
### Requisitos
- Java JDK 11 o superior
- Apache NetBeans (recomendado)
### Servidor
1. Clona el repositorio:
   ```bash
   git clone https://github.com/pablobrst/Cafeteria-Concurrente.git
   ```
2. Abre el proyecto en NetBeans
3. Ejecuta `Cafeteria.java` — arranca el servidor RMI en el puerto 1099 y la interfaz gráfica
4. Verás la GUI actualizándose en tiempo real con el estado de todas las áreas
### Cliente remoto
1. Con el servidor ya corriendo, ejecuta `RMICliente.java`
2. Se abrirá una segunda ventana conectada al servidor que muestra el estado y permite pausar/reanudar la simulación remotamente
---
 
## Sistema de logs
 
Todas las acciones quedan registradas en `evolucion_cafeteria.txt` con timestamp:
 
```
[09/06/2025 14:32:01] El Cliente C-0042 va al parque
[09/06/2025 14:32:06] El Cliente C-0042 va hacia la cafeteria
[09/06/2025 14:32:09] El Cocinero B-0003 entra en la cocina
[09/06/2025 14:32:14] El Vendedor V-0011 dejó 4 cafés y 7 rosquillas en el mostrador
```
 
---
 
## Autor
 
**Pablo Brañas**  
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white)](https://github.com/pablobrst)
