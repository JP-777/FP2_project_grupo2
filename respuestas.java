import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Respuestas{
    private List<String> respuestas;

    // Constructor que inicializa y carga las respuestas desde el archivo
    public Respuestas() {
        respuestas = new ArrayList<>();
        cargarRespuestas("respuestas.txt");
    }

    // Método para cargar las respuestas desde un archivo
    private void cargarRespuestas(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                respuestas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener las respuestas
    public List<String> getRespuestas() {
        return respuestas;
    }

    // Métodos adicionales si se requiere modificar o agregar respuestas
    public void modRespuestas(List<String> nuevasRespuestas) {
        respuestas = nuevasRespuestas;
    }

    public void addRespuestas(List<String> nuevasRespuestas) {
        respuestas.addAll(nuevasRespuestas);
    }
    
    public static void mostrarRespuestas(String[] args) {
        Respuestas respuestasObj = new Respuestas();

        // Imprimir las respuesta
        for (String respuestas : respuestasObj.getRespuestas()) {
            System.out.println(respuestas);
        }
    }
}