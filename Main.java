public class Main {

  public static void main(String[] args) {

    Joueur J1 = new Joueur(1, "J1", Affichage.ANSI_CYAN);
    Joueur J2 = new Joueur(2, "J2", Affichage.ANSI_RED);
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
        coup = Affichage.demanderCoup(joueurActif);

        coupLegal = true;
      }
      tour++;
    }

    Affichage.quitter();

  }

}
