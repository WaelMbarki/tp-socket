import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ServeurBibliothequeTCP {
    public static void main(String[] args) {
        int port = 5000;

        // Une seule bibliothèque et une seule instance de GestionBibliotheque, créées au démarrage
        Bibliotheque bibliotheque = new Bibliotheque("Bibliothèque universitaire");
        GestionBibliotheque gestion = new GestionBibliotheque(bibliotheque);
        ProtocoleBibliotheque protocole = new ProtocoleBibliotheque(gestion);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré, en attente de connexions sur le port " + port);

            // Le serveur traite les clients
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    // Evite qu'un client silencieux bloque indéfiniment le serveur
                    socket.setSoTimeout(10000);
                    traiterClient(socket, protocole);
                } catch (IOException e) {
                    // Une erreur avec un client n'arrête pas le serveur
                    System.out.println("Erreur avec un client : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }

    // Une connexion = une requête + une réponse, puis fermeture de la connexion
    private static void traiterClient(Socket socket, ProtocoleBibliotheque protocole) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        System.out.println("Client connecté : " + socket.getRemoteSocketAddress());

        String requete = in.readLine();
        if (requete == null) {
            // Fin du flux : différent d'une ligne vide
            System.out.println("Le client a fermé la connexion sans envoyer de requête");
            return;
        }
        System.out.println("Requête reçue de " + socket.getRemoteSocketAddress() + " : " + requete);

        String reponse = protocole.traiter(requete);

        out.write(reponse + "\n");
        out.flush();
        System.out.println("Réponse envoyée : " + reponse);
        // La socket est fermée par le try-with-resources de l'appelant
    }
}