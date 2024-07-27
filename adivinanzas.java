import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;

public class adivinanzas {
    private Preguntas pregObj;
    private Respuestas respObj;
    Scanner sc = new Scanner(System.in);
    public long tiempo;
    public adivinanzas() {
        pregObj = new Preguntas();
        respObj = new Respuestas();
    }

    public static void main(String[] args) {
        adivinanzas juego = new adivinanzas();
        juego.iniciar();
    }

    public int estadisticas() {
        return 0;
    }

    public int puntyerror(int indice, List<String> resp, int eleccion, int puntos) {
        if (indice >= 0 && indice < resp.size() && eleccion >= 0 && eleccion < resp.size()) {
            String pregunta = resp.get(indice);
            String respuesta = resp.get(eleccion);
            if (pregunta.equals(respuesta)) {
                puntos = puntos + 1;
            }
        } else {
            System.out.println("Invalid index or choice");
        }
        return puntos;
    }

    public void modadmind() {
        // Implementation of admin mode
    }

    public void usuario() {
        // Implementation of user mode
    }

    public void iniciar() {
        int cant = 3;
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
            System.out.println(fin - tiemxpreg);
            tiempo = (fin - tiemxpreg) + tiempo;
        }
        System.out.println(tiempo);
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
