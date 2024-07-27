import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class adivinanzas {
    private preguntas pregObj;
    private Respuestas respObj;
    Scanner sc = new Scanner(System.in);
    public long tiempo;
    public long tiempototal;
    public List<Estadistica> estadisticas;

    public adivinanzas() {
        pregObj = new preguntas();
        respObj = new Respuestas();
        estadisticas = new ArrayList<>();
    }

    public static void main(String[] args) {
        adivinanzas juego = new adivinanzas();
        juego.menu();
    }

    public void menu(){
        System.out.println("Elija ser un usuario o ser modo administrador");
        String menu = sc.next();
        if ("usuario".equals(menu)) {
            usuario();
        }
        else{
            modadmind();
        }
    }

    public void estadisticas() {
        System.out.println("Pregunta\tTiempo (s)\tResultado");
        for (Estadistica est : estadisticas) {
            String resultado = est.correcta ? "Optimo" : "Refuerza aprendizaje";
            System.out.println(est.pregunta + "\t" + est.tiempo + "\t" + resultado);
        }
        menu();
    }

    public int puntyerror(int indice, List<String> resp, int eleccion, int puntos) {
        if (indice >= 0 && indice < resp.size() && eleccion >= 0 && eleccion < resp.size()) {
            String pregunta = resp.get(indice);
            String respuesta = resp.get(eleccion);
            if (pregunta.equals(respuesta)) {
                puntos = puntos + 1;
                estadisticas.add(new Estadistica(pregunta, true, tiempo));
            } else {
                estadisticas.add(new Estadistica(pregunta, false, tiempo));
            }
        } else {
            System.out.println("Invalid index or choice");
        }
        return puntos;
    }

    public void modadmind() {
        int opcion;
        do {
            System.out.println("Modo Administrador");
            System.out.println("1. Mostrar preguntas y respuestas");
            System.out.println("2. Modificar pregunta y respuesta");
            System.out.println("3. Agregar preguntas y respuestas");
            System.out.println("4. Eliminar pregunta y respuesta");
            System.out.println("5. Jugar");
            System.out.println("6. Salir");
            opcion = sc.nextInt();
            sc.nextLine(); // consumir la nueva línea

            switch (opcion) {
                case 1:
                    pregObj.mostrarPreguntas();
                    break;
                case 2:
                    System.out.println("Ingrese el índice de la pregunta que desea modificar:");
                    int indiceModificar = sc.nextInt();
                    sc.nextLine(); // consumir la nueva línea
                    System.out.println("Ingrese la nueva pregunta:");
                    String nuevaPregunta = sc.nextLine();
                    System.out.println("Ingrese la nueva respuesta:");
                    String nuevaRespuesta = sc.nextLine();
                    pregObj.modPreguntas(indiceModificar, nuevaPregunta, nuevaRespuesta);
                    break;
                case 3:
                    System.out.println("Ingrese las preguntas a agregar, separadas por un salto de línea. Escriba 'fin' para terminar:");
                    List<String> agregarPreguntas = new ArrayList<>();
                    List<String> agregarRespuestas = new ArrayList<>();
                    String pregunta;
                    while (!(pregunta = sc.nextLine()).equalsIgnoreCase("fin")) {
                        agregarPreguntas.add(pregunta);
                        System.out.println("Ingrese la respuesta para la pregunta: " + pregunta);
                        String respuesta = sc.nextLine();
                        agregarRespuestas.add(respuesta);
                    }
                    pregObj.addPreguntas(agregarPreguntas, agregarRespuestas);
                    break;
                case 4:
                    System.out.println("Ingrese el índice de la pregunta que desea eliminar:");
                    int indiceEliminar = sc.nextInt();
                    sc.nextLine(); // consumir la nueva línea
                    pregObj.eliminarPreguntas(indiceEliminar);
                    break;
                case 5:
                    System.out.println("Cantidad de preguntas para jugar:");
                    int preg = sc.nextInt();
                    sc.nextLine(); // consumir la nueva línea
                    iniciar(preg);
                    break;
                case 6:
                    System.out.println("Saliendo del modo administrador.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }


    public void usuario() {
        System.out.println("Elija la cantidad de preguntas (no mas de 10)");
        int cant = sc.nextInt();
        iniciar(cant);
    }

    public void iniciar(int cant) {
        int[] numran = new int[cant];
        Random rand = new Random();
        for (int i = 0; i < cant; i++) {
            numran[i] = rand.nextInt(cant);
        }
        List<String> pregseleccionadas = mipreguntas(cant, numran);
        List<String> respseleccionadas = mirespuestas(cant, numran);
        int puntos = 0;

        for (int indice = 0; indice < pregseleccionadas.size(); indice++) {
            String pregunta = pregseleccionadas.get(indice);
            System.out.println(pregunta);
            long tiemxpreg = System.currentTimeMillis() / 1000;
            for (int resp = 0; resp < respseleccionadas.size(); resp++) {
                System.out.println((resp + 1) + ") " + respseleccionadas.get(resp));
            }

            int eleccion = sc.nextInt() - 1;
            puntos = puntyerror(indice, respseleccionadas, eleccion, puntos);
            System.out.println("Puntos: " + puntos);
            long fin = System.currentTimeMillis() / 1000;
            System.out.println("Tiempo por pregunta: " + (fin - tiemxpreg));
            tiempo = (fin - tiemxpreg);
            tiempototal = tiempo + tiempototal;
        }
        System.out.println("Tiempo total: " + tiempototal);
        
        estadisticas();
        
    }

    public List<String> mipreguntas(int cant, int[] numran) {
        List<String> adivinanzas = new ArrayList<>();
        List<String> copiaPreguntas = new ArrayList<>(pregObj.getPreguntas());

        for (int i = 0; i < cant; i++) {
            if (copiaPreguntas.isEmpty()) {
                break;
            }
            int indiceAleatorio = numran[i];
            if (indiceAleatorio >= 0 && indiceAleatorio < copiaPreguntas.size()) {
                String preguntaSeleccionada = copiaPreguntas.get(indiceAleatorio);
                adivinanzas.add(preguntaSeleccionada);
                copiaPreguntas.remove(indiceAleatorio);
            }
        }
        
        return adivinanzas;
    }

    public List<String> mirespuestas(int cant, int[] numran) {
        List<String> adivinanzas = new ArrayList<>();
        List<String> copiaRespuestas = new ArrayList<>(respObj.getRespuestas());

        for (int i = 0; i < cant; i++) {
            if (copiaRespuestas.isEmpty()) {
                break;
            }
            int indiceAleatorio = numran[i];
            if (indiceAleatorio >= 0 && indiceAleatorio < copiaRespuestas.size()) {
                String preguntaSeleccionada = copiaRespuestas.get(indiceAleatorio);
                adivinanzas.add(preguntaSeleccionada);
                copiaRespuestas.remove(indiceAleatorio);
            }
        }

        return adivinanzas;
    }
}
