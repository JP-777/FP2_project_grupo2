import java.util.*;

public class adivinanzas{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego de las adivinanzas");
        int modo = menu();
        if (modo == 1) {
            admin();
        } else {
            usuario();
        }
    }

    public static int menu() {
        //podra elegir si ser usuario o ser admin
        System.out.println("Elige un modo: 1. Admin 2. Usuario");
        int modo = scanner.nextInt();
        return modo;
    }

    public static void mostrar_juego(){
        //toda la logica del juego
        String[] preguntas = {"¿Qué es blanco y negro y se lee al revés?", "¿Qué sube y baja pero nunca se mueve?"};
        String[] respuestas = {"Una cebra", "Una escalera"};
        int intentos = 3;

        for (int i = 0; i < preguntas.length; i++) {
            System.out.println(preguntas[i]);
            String respuesta = scanner.next();

            if (respuesta.equalsIgnoreCase(respuestas[i])) {
                System.out.println("¡Correcto!");
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("Incorrecto. Te quedan " + intentos + " intentos.");
                } else {
                    System.out.println("Has perdido todos tus intentos.");
                    break;
                }
            }
        }
    }

    public static int punto_error() {
        //puntuar si es correcto o no, si es correcto se pasa al siguiente pregunta
        // de lo contrario se resta un intento
        int puntos = 0;
        String[] respuestas = {"Una cebra", "Una escalera"};
        for (int i = 0; i < respuestas.length; i++) {
            System.out.println("Escribe tu respuesta:");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase(respuestas[i])) {
                puntos++;
            } else {
                puntos--;
            }
        }
        return puntos;
    }

    public static void usuario() {
        //el usuario podra escoger cuantas preguntas responder
        System.out.println("¿Cuántas preguntas quieres responder?");
        int numPreguntas = scanner.nextInt();
        for (int i = 0; i < numPreguntas; i++) {
            mostrar_juego();
        }
        menu_finalizar();
    }

    public static void admin() {
        // podra editar, eliminar y añadir preguntas
        System.out.println("Modo Admin: 1. Añadir pregunta 2. Eliminar pregunta 3. Editar pregunta");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        switch (opcion) {
            case 1:
                System.out.println("Escribe la nueva pregunta:");
                String nuevaPregunta = scanner.nextLine();
                System.out.println("Escribe la respuesta:");
                String nuevaRespuesta = scanner.nextLine();
                // Aquí se añadiría la nueva pregunta y respuesta a la base de datos o lista
                System.out.println("Pregunta añadida: " + nuevaPregunta + " Respuesta: " + nuevaRespuesta);
                break;
            case 2:
                System.out.println("Escribe el número de la pregunta a eliminar:");
                int numEliminar = scanner.nextInt();
                // Aquí se eliminaría la pregunta correspondiente de la base de datos o lista
                System.out.println("Pregunta eliminada número: " + numEliminar);
                break;
            case 3:
                System.out.println("Escribe el número de la pregunta a editar:");
                int numEditar = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                System.out.println("Escribe la nueva pregunta:");
                String preguntaEditada = scanner.nextLine();
                System.out.println("Escribe la nueva respuesta:");
                String respuestaEditada = scanner.nextLine();
                // Aquí se editaría la pregunta y respuesta en la base de datos o lista
                System.out.println("Pregunta editada número: " + numEditar + " Nueva pregunta: " + preguntaEditada + " Nueva respuesta: " + respuestaEditada);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static int time() {
        // tiempo que se demora en la partida
        // "si tabajamos con base de datos podemos poner mejores puntuaciones"
        // Simular tiempo de juego en segundos
        int tiempo = (int) (Math.random() * 100); // Generar un tiempo aleatorio
        System.out.println("Tiempo total de la partida: " + tiempo + " segundos");
        return tiempo;
    }

    public static void menu_finalizar() {
        //podra finalizar el juego
        //podra volver al meno principal
        System.out.println("¿Deseas finalizar el juego? 1. Sí 2. No");
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            System.out.println("Gracias por jugar. ¡Hasta la próxima!");
        } else {
            int modo = menu();
            if (modo == 1) {
                admin();
            } else {
                usuario();
            }
        }
    }
    

}