import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

class Respuestas{
    private List<String> respuestas;

    // Constructor que inicializa y carga las respuestas desde el archivo
    public Respuestas() {
        respuestas = new ArrayList<>();
        cargarRespuestas("respuestas.txt");
    }

    // Método para cargar las respuestas desde un archivo
    public void cargarRespuestas(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                respuestas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar las respuestas en un archivo
    public void guardarRespuestas(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String respuesta : respuestas) {
                bw.write(respuesta);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener las respuestas
    public List<String> getRespuestas() {
        return respuestas;
    }

    // Método para modificar una respuesta específica por índice
    public void modRespuesta(int indice, String nuevaRespuesta) {
        if (indice-1 >= 0 && indice-1 < respuestas.size()) {
            respuestas.set(indice-1, nuevaRespuesta);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    // Método para agregar respuestas
    public void addRespuestas(List<String> nuevasRespuestas) {
        respuestas.addAll(nuevasRespuestas);
    }

    // Método para eliminar una respuesta específica por índice
    public void eliminarRespuesta(int indice) {
        if (indice-1 >= 0 && indice-1 < respuestas.size()) {
            respuestas.remove(indice-1);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }
}