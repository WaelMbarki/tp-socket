import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bibliotheque {
    private String nom;
    private List<Livre> livres;

    public Bibliotheque(String nom) {
        this.nom = nom;
        this.livres = new ArrayList<>();
        initialiserCatalogue();
    }

    // catalogue initial
    private void initialiserCatalogue() {
        this.livres.add(new Livre("L1", "Programmation Java", Arrays.asList("JAVA", "POO")));
        this.livres.add(new Livre("L2", "Réseaux informatiques", Arrays.asList("RESEAUX", "TCP")));
        this.livres.add(new Livre("L3", "Systèmes distribués", Arrays.asList("JAVA", "DISTRIBUE")));
        this.livres.add(new Livre("L4", "Bases de données", Arrays.asList("SQL", "DONNEES")));
        this.livres.add(new Livre("L5", "Architecture logicielle", Arrays.asList("ARCHITECTURE", "POO")));
    }
    public String getNom() {
        return nom;
    }
    public List<Livre> getLivres() {
        return Collections.unmodifiableList(livres);
    }
}
