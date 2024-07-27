import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Preguntas{
    private List<String> preguntas;

    // Constructor que inicializa y carga las preguntas desde el archivo
    public Preguntas() {
        preguntas = new ArrayList<>();
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

    // Método para obtener las preguntas
    public List<String> getPreguntas() {
        return preguntas;
    }

    // Métodos adicionales si se requiere modificar o agregar preguntas
    public void modPreguntas(List<String> nuevasPreguntas) {
        preguntas = nuevasPreguntas;
    }

    public void addPreguntas(List<String> nuevasPreguntas) {
        preguntas.addAll(nuevasPreguntas);
    }
    
    public static void mostrarPreguntas(String[] args) {
        Preguntas preguntasObj = new Preguntas();

        // Imprimir las preguntas
        for (String pregunta : preguntasObj.getPreguntas()) {
            System.out.println(pregunta);
        }
    }
}

