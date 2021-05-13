import java.util.ArrayList;

public class MaClassePrincipale {

  public static void main(String[] args) {

    boolean jeu = true;

    while (jeu) {

      Joueur J1 = new Joueur(true, "J1", Affichage.ANSI_CYAN);
      Joueur J2 = new Joueur(false, "J2", Affichage.ANSI_RED);
      Joueur[] joueurs = { J1, J2 };

      Plateau plateau = new Plateau(10);
      plateau.configurationInitiale(J1, J2);

      boolean partie = true;
      int tour = 0;
      boolean coupLegal;
      String commande;
      Coup coupJoueur;
      Joueur joueurActif;

      while (partie) {

        Affichage.effaceEcran();
        Affichage.afficher(plateau);
        joueurActif = joueurs[tour % 2];
        coupLegal = false;
        boolean prisePossible = false;
        Piece pieceDeplacee;

        ArrayList<Coup> coupsLegaux = joueurActif.calculerCoupsLegaux(plateau);
        ArrayList<Coup> coupsForces = new ArrayList<Coup>();

        for (Coup coup : coupsLegaux) {
          if (coup.prise) {
            coupsForces.add(coup);
            prisePossible = true;
          }
        }

        while (!coupLegal) {

          commande = Affichage.demanderCommande(joueurActif);

          if (commande.equals("?")) {
            Affichage.aide();
          } else if (commande.equals("!")) {
            if (prisePossible) {
              Affichage.listerCoups(coupsForces);
            } else {
              Affichage.listerCoups(coupsLegaux);
            }
          } else if (commande.equals("*")) {
            Affichage.afficher(plateau);
          } else if (commande.equals("hist")) {

          } else if (commande.equals("recommencer")) {
            coupLegal = true;
            partie = false;
          } else if (commande.equals("quitter")) {
            coupLegal = true;
            partie = false;
            jeu = false;
          } else {

            coupJoueur = Affichage.ConversionInputCoup(commande, plateau.taille);

            if (coupJoueur == null) {
              Affichage.erreur("Format invalide. Exemple : b3 a4    ");
            } else {

              pieceDeplacee = plateau.getPiece(coupJoueur.x1, coupJoueur.y1);
              if (pieceDeplacee == null) {
                Affichage.erreur("Il n'y a pas de pas de pièce à cette emplacement.");
              } else if (pieceDeplacee.joueur != joueurActif) {
                Affichage.erreur("C'est au tour de " + joueurActif + " de jouer.");
              } else if (plateau.getPiece(coupJoueur.x2, coupJoueur.y2) instanceof Piece) {
                Affichage.erreur("Il y a déjà une pièce à cet emplacement.");
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
                  Affichage.erreur("Coup invalide (Entrer \"?\" pour la listes des coups possibles).");
                } else if (prisePossible && !coupJoueur.prise) {
                  Affichage.erreur("Vous avez la possibilté de faire une prise, vous devez donc jouer un tel coup.");
                } else {
                  plateau.deplacerPiece(coupJoueur);
                }
              }
            }
          }

        }

        tour++;
      }

    }

    Affichage.quitter();

  }

}
