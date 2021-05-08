import java.util.Scanner;

public class Affichage {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String SYMBOLE_COIN = "■";
    private static final String SYMBOLE_CASE = "▐█▌";

    private static Scanner scanner = new Scanner(System.in);

    public static void afficher(Plateau plateau) {

        effaceEcran();
        String affichage = "";
        String bordure_ligne = "";

        bordure_ligne += ANSI_BLACK + SYMBOLE_COIN;
        for (int i = 0; i < plateau.taille; i++) {
            bordure_ligne += " " + (i + 1) % 10;
        }
        bordure_ligne += " " + SYMBOLE_COIN + ANSI_RESET;

        affichage += bordure_ligne + "\n";
        for (int y = 0; y < plateau.taille; y++) {
            affichage += ANSI_BLACK + (y + 1) % 10 + ANSI_RESET;
            if (y % 2 == 1) {
                affichage += " ";
            }
            for (int x = 0; x < plateau.taille; x++) {
                if ((y + x) % 2 == 0) {
                    affichage += ANSI_BLACK + SYMBOLE_CASE + ANSI_RESET;
                } else {
                    Piece piece = plateau.getPiece(x, y);
                    if (piece instanceof Piece) {
                        affichage += piece.toString();
                    } else {
                        affichage += " ";
                    }
                }
            }
            if (y % 2 == 0) {
                affichage += " ";
            }
            affichage += ANSI_BLACK + (y + 1) % 10 + ANSI_RESET + "\n";
        }
        affichage += bordure_ligne + "\n";
        System.out.println(affichage);
    }

    public static void effaceEcran() {
        /**
         * Fonction fournie sur discord pour effacer l'écran
         */
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

    public static int[][] demanderCoup(Joueur joueur) {
        boolean formatLegal = false;
        int[][] coup = new int[2][2];
        String[] coord = new String[2];
        while (!formatLegal) {
            System.out.print(joueur.toString() + " > ");

            try {
                coord[0] = String.valueOf(scanner.nextInt());
                coord[1] = String.valueOf(scanner.nextInt());
                for (int i = 0; i < 2; i++) {
                    if (coord[i].length() < 2) {
                        coord[i] = "0" + coord[i];
                    }
                    for (int j = 0; j < 2; j++) {
                        coup[i][j] = Math.floorMod(Character.getNumericValue(coord[i].charAt(j)) - 1, 10);
                    }
                }
                // System.out.println(coup[0][0] + "" + coup[0][1] + " " + coup[1][0] + "" +
                // coup[1][1]);
                formatLegal = true;
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Format invalide , réesseyez. Exemple : 72 63");
            }

        }
        return coup;
    }

    public static void quitter() {
        scanner.close();
    }

}
