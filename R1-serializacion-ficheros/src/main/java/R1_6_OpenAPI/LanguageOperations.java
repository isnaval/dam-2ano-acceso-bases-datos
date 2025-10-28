package src.main.java.R1_6_OpenAPI;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageOperations {

    public List<Language> buscarPorNombre(List<Language> idiomas, String nombre) {
        String nombreLower = nombre.toLowerCase();
        return idiomas.stream()
                .filter(lang -> lang.getEnglish() != null &&
                        lang.getEnglish().toLowerCase().contains(nombreLower))
                .collect(Collectors.toList());
    }

    public Language buscarPorCodigo(List<Language> idiomas, String codigo) {
        return idiomas.stream()
                .filter(lang -> lang.getAlpha2() != null &&
                        lang.getAlpha2().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Language> filtrarPorLetra(List<Language> idiomas, char letra) {
        return idiomas.stream()
                .filter(lang -> lang.getEnglish() != null &&
                        lang.getEnglish().toUpperCase().startsWith(String.valueOf(letra).toUpperCase()))
                .collect(Collectors.toList());
    }

    public void mostrarTodos(List<Language> idiomas) {
        System.out.println("\n=== TODOS LOS IDIOMAS (" + idiomas.size() + ") ===");
        for (int i = 0; i < idiomas.size(); i++) {
            System.out.println((i + 1) + ". " + idiomas.get(i));
        }
    }
}
