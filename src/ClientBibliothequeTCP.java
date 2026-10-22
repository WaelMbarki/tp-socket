import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Usage : java ClientBibliothequeTCP [adresseServeur] [port]
public class ClientBibliothequeTCP {
    public static void main(String[] args) {
        String adresse = "127.0.0.1";
        int port = 5000;
        if (args.length >= 1) {
            adresse = args[0];
        }
        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Port invalide : " + args[1]);
                return;
            }
        }

        // Le client demande d'abord la requête à l'utilisateur
        Scanner clavier = new Scanner(System.in);
        System.out.print("Requête (LISTE ou RECHERCHE;theme) : ");
        String requete = clavier.nextLine();

        try (Socket socket = new Socket(adresse, port);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            System.out.println("Connexion réussie au serveur " + adresse + ":" + port);

            out.write(requete + "\n");
            out.flush();

            String reponse = in.readLine();
            if (reponse == null) {
                // Fermeture sans réponse : échec de communication, jamais affiché comme OK;0
                System.out.println("Echec de communication : connexion fermée sans réponse");
            } else {
                System.out.println("Réponse du serveur : " + reponse);
            }
        } catch (ConnectException e) {
            System.out.println("Echec de connexion : serveur inaccessible (" + adresse + ":" + port + ")");
        } catch (IOException e) {
            System.out.println("Erreur de communication : " + e.getMessage());
        }
    }
}