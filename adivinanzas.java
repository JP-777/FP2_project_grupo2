import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class adivinanzas {
    private Preguntas pregObj;
    private Respuestas respObj;
    Scanner sc = new Scanner(System.in);
    public long tiempo;
    public long tiempototal;
    public List<Estadistica> estadisticas;

    public adivinanzas() {
        pregObj = new Preguntas();
        respObj = new Respuestas();
        estadisticas = new ArrayList<>();
    }

    public static void main(String[] args) {
        adivinanzas juego = new adivinanzas();
        juego.usuario();
    }

    public void estadisticas() {
        System.out.println("Pregunta\tTiempo (s)\tResultado");
        for (Estadistica est : estadisticas) {
            String resultado = est.correcta ? "Optimo" : "Refuerza aprendizaje";
            System.out.println(est.pregunta + "\t" + est.tiempo + "\t" + resultado);
        }
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
