import java.util.ArrayList;
import java.util.Scanner;

final public class Affichage {

  public static final boolean COULEUR_ACTIVEE = false;

  public static final String ANSI_RESET = COULEUR_ACTIVEE ? "\u001B[0m" : "";
  public static final String ANSI_BLACK = COULEUR_ACTIVEE ? "\u001B[30m" : "";
  public static final String ANSI_RED = COULEUR_ACTIVEE ? "\u001B[31m" : "";
  public static final String ANSI_GREEN = COULEUR_ACTIVEE ? "\u001B[32m" : "";
  public static final String ANSI_YELLOW = COULEUR_ACTIVEE ? "\u001B[33m" : "";
  public static final String ANSI_BLUE = COULEUR_ACTIVEE ? "\u001B[34m" : "";
  public static final String ANSI_PURPLE = COULEUR_ACTIVEE ? "\u001B[35m" : "";
  public static final String ANSI_CYAN = COULEUR_ACTIVEE ? "\u001B[36m" : "";
  public static final String ANSI_WHITE = COULEUR_ACTIVEE ? "\u001B[37m" : "";

  private static final String SYMBOLE_COIN = "■";
  private static final String SYMBOLE_CASE = "▐█▌";

  private static Scanner scanner = new Scanner(System.in);

  public static void afficher(Plateau plateau) {

    String affichage = "";
    String bordure_ligne = "";

    bordure_ligne += ANSI_BLACK + SYMBOLE_COIN;
    for (int i = 0; i < plateau.taille; i++) {
      bordure_ligne += " " + coordToChar(i, true);
    }
    bordure_ligne += " " + SYMBOLE_COIN + ANSI_RESET;

    affichage += "\n" + bordure_ligne + "\n";
    for (int y = plateau.taille - 1; y >= 0; y--) {
      affichage += ANSI_BLACK + coordToChar(y, false) + ANSI_RESET;
      if (y % 2 == 0) {
        affichage += " ";
      }
      for (int x = 0; x < plateau.taille; x++) {
        if ((y + x) % 2 == 0) {
          Piece piece = plateau.getPiece(x, y);
          if (piece instanceof Piece) {
            affichage += piece;
          } else {
            affichage += " ";
          }
        } else {
          affichage += ANSI_BLACK + SYMBOLE_CASE + ANSI_RESET;
        }
      }
      if (y % 2 == 1) {
        affichage += " ";
      }
      affichage += ANSI_BLACK + coordToChar(y, false) + ANSI_RESET + "\n";
    }
    affichage += bordure_ligne + "\n";
    System.out.println(affichage);
  }

  /**
   * Fonction fournie sur discord pour effacer l'écran
   */
  public static void effaceEcran() {
    try {
      String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else if (os.contains("Linux")) {
        new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
      }
    } catch (final Exception e) {
      System.out.println("Erreur : " + e);
    }
  }

  public static String demanderCommande(Joueur joueur) {

    System.out.print(joueur + " > ");
    return scanner.nextLine();
  }

  /**
   * Methode convertissant l'input de l'utilisateur en un objet coup.
   */
  public static Coup ConversionInputCoup(String commande, int taille) {

    int[] coords = new int[4];

    int i = 0;
    int nbCoordTrouvees = 0;
    int coord;
    while (i < commande.length()) {
      coord = charToCoord(commande.charAt(i), (nbCoordTrouvees % 2 == 0));

      if (0 <= coord && coord <= taille) {
        if (nbCoordTrouvees < 4) {
          coords[nbCoordTrouvees] = coord;
        }
        nbCoordTrouvees++;
      }

      i++;
    }

    if (nbCoordTrouvees == 4) {
      return new Coup(coords[0], coords[1], coords[2], coords[3]);
    } else {
      return null;
    }
  }

  public static int charToCoord(char caractere, boolean lettre) {
    int coord = (int) caractere;
    if (lettre) {
      coord -= 97; // lettre
    } else {
      coord -= 48; // chiffre
    }
    return coord;
  }

  public static char coordToChar(int coord, boolean lettre) {
    if (lettre) {
      coord += 97;
    } else {
      coord += 48;
    }
    return (char) (coord);
  }

  public static void quitter() {
    scanner.close();
  }

  public static void erreur(String texte) {
    System.out.println(texte + " Réessayez");
  }

  public static void listerCoups(ArrayList<Coup> listeCoups) {
    for (Coup coup : listeCoups) {
      System.out.println(coup);
    }
  }

  /**
   * Methode permettant d'indiquer au joueur quelles valeurs rentrer dans la
   * console afin qu'il puisse joueur correctement.
   */
  public static void aide() {
    System.out.println();
    System.out.println("Jeu de Dames");
    System.out.println("Commandes :");
    System.out.println("?               Affiche l'aide");
    System.out.println("!               Liste les coups possibles");
    System.out.println("*               Affiche à nouveau le plateau");
    System.out.println("hist            Affiche l'historique des coups joués");
    System.out.println("abandon         Abandonné la partie");
    System.out.println("quitter         Quitte le jeu");
    System.out.println("Entrez votre coup dans le format [position initiale] [poisition finale]");
    System.out.println("Exemple : b3 a4");
    System.out.println();
  }

  public static void fin(Plateau plateau, int tour, String texte) {
    afficher(plateau);
    System.out.println("Fin de la partie");
    System.out.println(texte);
    System.out.println("La partie a été jouée en " + tour + " coups.");
    System.out.println();
    aideFin();
  }

  public static void aideFin() {
    System.out.println("Commandes :");
    System.out.println("r               Recommencer une partie");
    System.out.println("quitter         Quitte le jeu");
  }

  public static String demanderCommandeFin() {
    System.out.print("> ");
    return scanner.nextLine();
  }

  public static void coupOrdi(Coup coupJoueur) {
    System.out.println("ordi: " + coupJoueur);
  }

}
