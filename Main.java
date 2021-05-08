public class Main {

  public static void main(String[] args) {

    Joueur J1 = new Joueur("J1", Affichage.ANSI_RED);
    Joueur J2 = new Joueur("J2", Affichage.ANSI_CYAN);
    Joueur[] joueurs = { J1, J2 };

    Plateau plateau = new Plateau(10);
    plateau.configurationInitiale(J1, J2);

    int tour = 0;
    boolean jeu = true;
    boolean coupLegal;
    int[][] coup;
    Joueur joueurActif;

    while (jeu) {

      Affichage.afficher(plateau);
      joueurActif = joueurs[tour % 2];
      coupLegal = false;
      while (!coupLegal) {
        Affichage.demanderCoup(joueurActif);

        coupLegal = true;
      }
      tour++;
    }

    Affichage.quitter();

  }

}
