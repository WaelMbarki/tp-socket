import java.util.List;

public class ProtocoleBibliotheque {
    private GestionBibliotheque gestion;

    public ProtocoleBibliotheque(GestionBibliotheque gestion) {
        this.gestion = gestion;
    }

    // Reçoit une requête (sans \n) et retourne la réponse (sans \n)
    public String traiter(String requete) {
        // Ligne absente ou vide
        if (requete == null || requete.trim().isEmpty()) {
            return "ERR;REQUETE_INVALIDE";
        }

        // limit = -1 : garde les champs vides finaux ("RECHERCHE;" donne 2 champs)
        String[] champs = requete.split(";", -1);
        String commande = champs[0].trim();

        // Commande vide (ex : ";JAVA")
        if (commande.isEmpty()) {
            return "ERR;REQUETE_INVALIDE";
        }

        switch (commande) {
            case "LISTE":
                // LISTE ne prend aucun argument
                if (champs.length != 1) {
                    return "ERR;REQUETE_INVALIDE";
                }
                return construireReponse(gestion.listerLivres());

            case "RECHERCHE": {
                // RECHERCHE exige exactement un thème non vide
                if (champs.length != 2) {
                    return "ERR;REQUETE_INVALIDE";
                }
                String theme = champs[1].trim();
                if (theme.isEmpty() || theme.contains(",") || theme.contains(":")) {
                    return "ERR;REQUETE_INVALIDE"; // vide ou caractère réservé
                }
                try {
                    return construireReponse(gestion.rechercherParTheme(theme));
                } catch (IllegalArgumentException e) {
                    return "ERR;REQUETE_INVALIDE";
                }
            }

            default:
                return "ERR;COMMANDE_INCONNUE";
        }
    }

    // Format : OK;nombre;identifiant:titre;identifiant:titre;...
    private String construireReponse(List<Livre> livres) {
        StringBuilder sb = new StringBuilder("OK;" + livres.size());
        for (Livre l : livres) {
            sb.append(";").append(l.getId()).append(":").append(l.getTitre());
        }
        return sb.toString();
    }
}