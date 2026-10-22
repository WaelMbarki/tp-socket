import java.util.List;

public interface ServiceBibliotheque {
    List<Livre> listerLivres();
    List<Livre> rechercherParTheme(String theme);
}