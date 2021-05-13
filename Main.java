import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    Joueur J1 = new Joueur(true, "J1", Affichage.ANSI_CYAN);
    Joueur J2 = new Joueur(false, "J2", Affichage.ANSI_RED);
    Joueur[] joueurs = { J1, J2 };

    Plateau plateau = new Plateau(10);
    plateau.configurationInitiale(J1, J2);

    int tour = 0;
    boolean jeu = true;
    boolean coupLegal;
    String input;
    Coup coupJoueur;
    Joueur joueurActif;

    while (jeu) {

      Affichage.afficher(plateau);
      joueurActif = joueurs[tour % 2];
      coupLegal = false;
      ArrayList<Coup> coupsLegaux;
      ArrayList<Coup> coupsForces = new ArrayList<Coup>();
      boolean prisePossible = false;
      Piece pieceDeplacee;

      coupsLegaux = joueurActif.calculerCoupsLegaux(plateau);
      for (Coup coup : coupsLegaux) {
        if (coup.prise) {
          coupsForces.add(coup);
          prisePossible = true;
        }
      }

      while (!coupLegal) {

        input = Affichage.demanderCoup(joueurActif);

        if (input.equals("?")) {
        if (prisePossible) {
            Affichage.listerCoups(coupsForces);
          } else {
            Affichage.listerCoups(coupsLegaux);
          }
        } else {

          coupJoueur = Affichage.ConversionInputCoup(input, plateau.taille);

          if (coupJoueur == null) {
            Affichage.ecrire("Format invalide , réessayez. Exemple : a3b4");
          } else {

            pieceDeplacee = plateau.getPiece(coupJoueur.x1, coupJoueur.y1);
            if (pieceDeplacee == null) {
              Affichage.ecrire("Il n'y a pas de pas de pièce à cette emplacement. Réessayez");
            } else if (plateau.getPiece(coupJoueur.x1, coupJoueur.y1).joueur != joueurActif) {
              Affichage.ecrire("C'est au tour de " + joueurActif + " de jouer. Réessayez");
            } else {

              int i = 0;
              while (i < coupsLegaux.size() && !coupLegal) {
                if (coupJoueur.equals(coupsLegaux.get(i))) {
                  coupJoueur = coupsLegaux.get(i);
                  coupLegal = true;
                }
                i++;
              }
              if (!coupLegal) {
                Affichage.ecrire("Coup invalide, réessayez. (Entrer \"?\" pour la listes des coups possibles)");
              } else if (prisePossible && !coupJoueur.prise) {
                Affichage
                    .ecrire("Vous avez la possibilté de faire une prise, vous devez donc jouer un tel coup. Réessayez");
              } else {
                plateau.deplacerPiece(coupJoueur);
              }
            }
          }
        }

      }
      tour++;
    }

    Affichage.quitter();

  }

}
