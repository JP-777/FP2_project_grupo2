import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class preguntas {
    private List<String> preguntas;
    
    private Respuestas respObj;

    // Constructor que inicializa y carga las preguntas y respuestas desde los archivos
    public preguntas() {
        preguntas = new ArrayList<>();
        respObj = new Respuestas();
        cargarAdivinanzas("Adivinanzas.txt");
    }

    // Método para cargar las preguntas desde un archivo
    private void cargarAdivinanzas(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                preguntas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar las preguntas en un archivo
    private void guardarAdivinanzas(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String pregunta : preguntas) {
                bw.write(pregunta);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener las preguntas
    public List<String> getPreguntas() {
        return preguntas;
    }

    // Método para modificar una pregunta y su respuesta específica por índice
    public void modPreguntas(int indice, String nuevaPregunta, String nuevaRespuesta) {
        if (indice-1 >= 0 && indice-1 < preguntas.size()) {
            System.out.println("Editando pregunta: " + preguntas.get(indice-1));
            preguntas.set(indice-1, nuevaPregunta);
            respObj.modRespuesta(indice-1, nuevaRespuesta);
            guardarAdivinanzas("Adivinanzas.txt");
            respObj.guardarRespuestas("respuestas.txt");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    // Método para agregar preguntas y sus respuestas
    public void addPreguntas(List<String> nuevasPreguntas, List<String> nuevasRespuestas) {
        if (nuevasPreguntas.size() == nuevasRespuestas.size()) {
            preguntas.addAll(nuevasPreguntas);
            respObj.addRespuestas(nuevasRespuestas);
            guardarAdivinanzas("Adivinanzas.txt");
            respObj.guardarRespuestas("respuestas.txt");
        } else {
            System.out.println("La cantidad de preguntas y respuestas no coincide.");
        }
    }

    // Método para eliminar una pregunta y su respuesta específica por índice
    public void eliminarPreguntas(int indice) {
        if (indice >= 0 && indice < preguntas.size()) {
            preguntas.remove(indice-1);
            respObj.eliminarRespuesta(indice-1);
            guardarAdivinanzas("Adivinanzas.txt");
            respObj.guardarRespuestas("respuestas.txt");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    // Método para mostrar las preguntas y sus respuestas
    public void mostrarPreguntas() {
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println((i + 1)+")  Pregunta: " + preguntas.get(i));
            System.out.println((i + 1) +")  Respuesta: " + respObj.getRespuestas().get(i));
        }
    }
}