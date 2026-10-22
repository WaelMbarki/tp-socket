import java.util.ArrayList;
import java.util.List;

public class GestionBibliotheque implements ServiceBibliotheque {
    private Bibliotheque bibliotheque;

    public GestionBibliotheque(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    //  lister les livres
    public List<Livre> listerLivres() {
        return new ArrayList<>(bibliotheque.getLivres());
    }

    // livres associés à un thème
    public List<Livre> rechercherParTheme(String theme) {
        if (theme == null || theme.trim().isEmpty()) {
            throw new IllegalArgumentException("Theme invalide : null, vide ou composé d'espaces");
        }
        String recherche = theme.trim();

        List<Livre> resultat = new ArrayList<>();
        for (Livre livre : bibliotheque.getLivres()) {
            for (String t : livre.getThemes()) {
                // thème entier, sans tenir compte de la casse
                if (t.equalsIgnoreCase(recherche)) {
                    resultat.add(livre);
                    break;
                }
            }
        }
        return resultat;
    }
}
