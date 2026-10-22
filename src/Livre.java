
import java.util.List;

public class Livre {
    private String id;
    private String titre;
    private List<String> themes;

    public Livre(String id, String titre, List<String> themes) {
        this.id = id;
        this.titre = titre;
        this.themes = themes;
    }

    public String getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public List<String> getThemes() {
        return themes;
    }
    @Override
    public String toString() {
        return id + " : " + titre + " " + themes;
    }
}