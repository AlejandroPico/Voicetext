package main;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class VoiceRecognition {
    public static void main(String[] args) {
        try {
            // Configuración del reconocimiento de voz
            Configuration configuration = new Configuration();

            // Establecer el modelo de acústica
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

            // Establecer el modelo de diccionario
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

            // Establecer el lenguaje de gramatical (opcional)
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            // Crear el reconocedor de voz en vivo
            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

            // Comenzar el reconocimiento
            System.out.println("Iniciando el reconocimiento de voz. Di algo...");
            recognizer.startRecognition(true);

            SpeechResult result;

            // Mantener el reconocimiento activo
            while ((result = recognizer.getResult()) != null) {
                System.out.println("Texto reconocido: " + result.getHypothesis());

                // Puedes incluir comandos específicos para realizar acciones basadas en el reconocimiento
                if (result.getHypothesis().equalsIgnoreCase("salir")) {
                    System.out.println("Finalizando el reconocimiento de voz...");
                    break;
                }
            }

            // Detener el reconocimiento
            recognizer.stopRecognition();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}