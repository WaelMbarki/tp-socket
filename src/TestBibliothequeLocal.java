import java.util.List;

public class TestBibliothequeLocal {
    public static void main(String[] args) {
        Bibliotheque bib = new Bibliotheque("Bibliothèque universitaire");
        GestionBibliotheque gestion = new GestionBibliotheque(bib);

        System.out.println("Test 1: liste de tous les livres ");
        afficher(gestion.listerLivres());

        System.out.println("Test 2: recherche de JAVA");
        afficher(gestion.rechercherParTheme("JAVA"));

        System.out.println("Test 3: recherche de java");
        afficher(gestion.rechercherParTheme(" java "));

        System.out.println("Test 4: recherche de HISTOIRE");
        afficher(gestion.rechercherParTheme("HISTOIRE"));

        System.out.println("Test 5: recherche avec un thème vide");
        try {
            gestion.rechercherParTheme("");
            System.out.println("erreur: aucune exception levée");
        } catch (IllegalArgumentException e) {
            System.out.println("exception : " + e.getMessage());
        }
    }

    private static void afficher(List<Livre> livres) {
        if (livres.isEmpty()) {
            System.out.println("liste vide");
        }
        for (Livre l : livres) {
            System.out.println(l);
        }
        System.out.println("nombre de livres : " + livres.size());
        System.out.println();
    }
}