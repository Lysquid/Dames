import java.util.ArrayList;
import java.util.Random;

public class MaClassePrincipale {

  public static void main(String[] args) {

    boolean jeu = true;

    while (jeu) {

      Joueur J1 = new Joueur(true, "J1", Affichage.ANSI_CYAN, true);
      Joueur J2 = new Joueur(false, "J2", Affichage.ANSI_RED, true);
      Joueur[] joueurs = { J1, J2 };
      Random generateur = new Random();

      Plateau plateau = new Plateau(10);
      plateau.configurationInitiale(J1, J2);

      boolean partie = true;
      int tour = 0;

      while (partie) {

        // Affichage.effaceEcran();
        Affichage.afficher(plateau);
        Joueur joueurActif = joueurs[tour % 2];
        Joueur joueurInactif = joueurs[(tour + 1) % 2];

        ArrayList<Coup> coupsLegaux = joueurActif.calculerCoupsLegaux(plateau);
        ArrayList<Coup> coupsForces = joueurActif.calculerCoupsForces(plateau);
        ArrayList<Coup> coupsLegauxEtForces = new ArrayList<Coup>();
        coupsLegauxEtForces.addAll(coupsLegaux);
        coupsLegauxEtForces.addAll(coupsForces);

        boolean prisePossible = !coupsForces.isEmpty();
        boolean coupLegal = false;

        if (coupsLegauxEtForces.isEmpty()) {
          Affichage.fin(plateau, joueurActif + " ne peux plus bouger, " + joueurInactif + " remporte la partie.");
          coupLegal = true;
        }

        while (!coupLegal) {

          String commande = Affichage.demanderCommande(joueurActif);

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

          } else if (commande.equals("abandon")) {
            coupLegal = true;
            partie = false;
            Affichage.fin(plateau, joueurInactif + " remporte la victoire par abandon de " + joueurActif + ".");
          } else if (commande.equals("quitter")) {
            coupLegal = true;
            partie = false;
            jeu = false;
          } else {

            Coup coupJoueur;
            if (joueurActif.ordi && commande.equals("")) {
              if (coupsForces.isEmpty()) {
                coupJoueur = coupsLegaux.get(generateur.nextInt(coupsLegaux.size()));
              } else {
                coupJoueur = coupsForces.get(generateur.nextInt(coupsForces.size()));
              }
              Affichage.coupOrdi(coupJoueur);
            } else {
              coupJoueur = Affichage.ConversionInputCoup(commande, plateau.taille);
            }

            if (coupJoueur == null) {
              Affichage.erreur("Format invalide. Exemple : b3 a4    ");
            } else {

              Piece pieceDeplacee = plateau.getPiece(coupJoueur.x1, coupJoueur.y1);
              if (coupJoueur.surCaseBlanche()) {
                Affichage.erreur("Le jeu se joue sur les cases noires.");
              } else if (pieceDeplacee == null) {
                Affichage.erreur("Il n'y a pas de pas de pièce à cette emplacement.");
              } else if (pieceDeplacee.joueur != joueurActif) {
                Affichage.erreur("C'est au tour de " + joueurActif + " de jouer.");
              } else if (plateau.getPiece(coupJoueur.x2, coupJoueur.y2) instanceof Piece) {
                Affichage.erreur("Il y a déjà une pièce à cet emplacement.");
              } else {

                int i = 0;
                while (i < coupsLegauxEtForces.size() && !coupLegal) {
                  if (coupJoueur.equals(coupsLegauxEtForces.get(i))) {
                    coupJoueur = coupsLegauxEtForces.get(i);
                    coupLegal = true;
                  }
                  i++;
                }

                if (!coupLegal) {
                  Affichage.erreur("Coup invalide (Entrer \"!\" pour la listes des coups possibles).");
                } else if (prisePossible && !coupJoueur.prise) {
                  Affichage.erreur("Vous avez la possibilté de faire une prise, vous devez donc jouer un tel coup.");
                  coupLegal = false;
                } else {
                  if (coupJoueur.prise) {
                    joueurInactif.enleverPiece(plateau.getPiece(coupJoueur.x3, coupJoueur.y3));
                  }
                  plateau.jouerCoup(coupJoueur);
                  if (coupJoueur.prise && !joueurActif.calculerCoupsForces(plateau).isEmpty()) {
                    // rafle
                  } else {
                    tour++;
                  }
                }
              }
            }
          }

        }

        if (joueurInactif.listePieces.isEmpty()) {
          partie = false;
          Affichage.fin(plateau, joueurActif + " remporte la victoire.");
        }

      }

      boolean commandeValide = false;
      while (!commandeValide) {
        String commande = Affichage.demanderCommandeFin();
        if (commande.equals("?")) {
          Affichage.aideFin();
        } else if (commande.equals("r")) {
          commandeValide = true;
        } else if (commande.equals("quitter")) {
          jeu = false;
          commandeValide = true;
        }
      }
    }

    Affichage.quitter();

  }

}
