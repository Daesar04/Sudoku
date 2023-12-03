# Aplicación Sudoku

## Desarrolladores
- Javier Sáez García
- David Escribano Arias

## Descripción de la Aplicación
Esta aplicación de Sudoku cuenta con diversas funcionalidades para proporcionar una experiencia de juego completa y desafiante. A continuación se detallan las características principales:

### 1. Generación del Tablero
El programa es capaz de generar tableros de Sudoku válidos de diferentes niveles de dificultad (fácil, medio, difícil, etc.). Esto implica la creación de un tablero inicial con algunos números predeterminados y la garantía de que el tablero no tenga múltiples soluciones.

### 2. Interfaz de Usuario
La aplicación cuenta con una interfaz gráfica de usuario que permite a los jugadores interactuar con el tablero de Sudoku. La interfaz incluye una cuadrícula 9x9 donde los jugadores pueden ingresar números y realizar otras acciones.

### 3. Validación de Jugadas
El programa verifica constantemente si las jugadas de los usuarios son válidas según las reglas del Sudoku. Esto incluye la comprobación de filas, columnas y regiones (bloques 3x3) para asegurarse de que no haya números repetidos.

### 4. Ayudas y Pistas
La aplicación ofrece funciones de ayuda, como resaltar los números que están en la posición equivocada, para ayudar a los jugadores a avanzar cuando están atascados.

### 5. Detección de Victoria
El programa verifica si el jugador ha completado el tablero correctamente y muestra un mensaje de victoria cuando todas las casillas están llenas.

### 6. Niveles de Dificultad
Ofrece la opción de seleccionar diferentes niveles de dificultad para adaptarse a las preferencias del jugador, desde tableros fáciles para principiantes hasta tableros más desafiantes para jugadores experimentados.

## Patrón de Diseño Utilizado

### Singleton Modificado
En este proyecto, hemos implementado una variante del patrón de diseño Singleton para gestionar las instancias de las clases `Interfaz`, `InterfazDificultad`, `InterfazVictoria` y `Juego_Sudoku`. 

#### Implementación:
- **Método `getInstancia`**: 
  Este método sigue el enfoque tradicional del patrón Singleton. 
  ```java
  public static Interfaz getInstancia() {
      if(instancia == null) {
          instancia = new Interfaz();
      }
      return instancia;
  }

- **Método `crearNuevaInstancia`**:
   Esta es una extensión del patrón Singleton convencional. En nuestro juego, es necesario restablecer o reiniciar el estado del juego para comenzar una nueva partida de Sudoku.
   Este método anula la instancia existente y crea una nueva, garantizando que el estado del juego se reinicie completamente para la nueva partida.
   ```java
   public static Interfaz crearNuevaInstancia() {
     instancia = null;
     return getInstancia();
   }

#### Razón de la Modificación:
   La necesidad de esta adaptación surge de la naturaleza del juego de Sudoku. Cada partida es independiente y requiere un estado de interfaz nuevo y limpio. El método `crearNuevaInstancia` permite a los jugadores iniciar una nueva partida sin retener ningún estado de partidas anteriores, lo cual es crucial para la experiencia de juego.

## Esquema UML de clases

![Esquema UML](https://cdn.discordapp.com/attachments/1039570431507706038/1180541089480978452/Diagrama_en_blanco_2.png?ex=657dcb91&is=656b5691&hm=1ede694ca2b29c66523f0ebd8becf2d965890f2f52b8e8edcd7acb59cad18b8c&)


## Boceto

![Boceto](https://cdn.discordapp.com/attachments/1039570431507706038/1158765794382069880/image.png?ex=6537ce3e&is=6525593e&hm=330103b1495f2c1825a5c6490a5bff3c3138a320c84bb27ca464a93d8b09ba78&)


## Interfaz Final

![Interfaz Final](https://media.discordapp.net/attachments/1039570431507706038/1180498832954503269/image.png?ex=657da436&is=656b2f36&hm=5ad00e8d0b94c80bd88d2ba349f8d3db9cd6014a783be95a8c89331d1fe778d7&=&format=webp&quality=lossless&width=1108&height=902)
