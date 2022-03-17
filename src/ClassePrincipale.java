package src;
import java.util.ArrayList;
import java.util.Random;

public class ClassePrincipale {

  public static void jouer(String[] args) {

    boolean jeu = true;

    while (jeu) {

      Joueur J1 = new Joueur(true, "J1", Affichage.ANSI_CYAN, Options.J1_ORDI);
      Joueur J2 = new Joueur(false, "J2", Affichage.ANSI_RED, Options.J2_ORDI);
      Joueur[] joueurs = { J1, J2 };
      Random generateur = new Random();
      Affichage.nouvellePartie(J1, J2);

      Plateau plateau = new Plateau(Options.TAILLE_PLATEAU);
      plateau.configurationInitiale(J1, J2);

      boolean partie = true;
      int tour = 0;

      boolean rafle = false;
      ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();
      ArrayList<Coup> coupsForces = new ArrayList<Coup>();
      ArrayList<Coup> coupsLegauxEtForces;

      while (partie) {

        Affichage.afficher(plateau);
        Joueur joueurActif = joueurs[tour % 2];
        Joueur joueurInactif = joueurs[(tour + 1) % 2];

        if (!rafle) {
          coupsLegaux = joueurActif.calculerCoupsLegaux(plateau);
          coupsForces = joueurActif.calculerCoupsForces(plateau);
        }
        coupsLegauxEtForces = new ArrayList<Coup>();
        coupsLegauxEtForces.addAll(coupsLegaux);
        coupsLegauxEtForces.addAll(coupsForces);

        boolean prisePossible = !coupsForces.isEmpty();
        boolean actionLegale = false;

        if (coupsLegauxEtForces.isEmpty()) {
          Affichage.fin(plateau, tour, joueurActif + " ne peux plus bouger, " + joueurInactif + " remporte la partie.");
          actionLegale = true;
          partie = false;
        }

        while (!actionLegale) {

          String commande = "";
          if (!joueurActif.ordi) {
            commande = Affichage.demanderCommande(joueurActif);
          }

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
            Affichage.listerCoups(plateau.historiqueCoups);
          } else if (commande.equals("abandon")) {
            actionLegale = true;
            partie = false;
            Affichage.fin(plateau, tour, joueurInactif + " remporte la victoire par abandon de " + joueurActif + ".");
          } else if (commande.equals("quitter")) {
            actionLegale = true;
            partie = false;
            jeu = false;
          } else {

            Coup coupJoueur;
            boolean coupAleatoire = false;
            if (joueurActif.ordi || (Options.COUP_ALEATOIRE && commande.equals(""))) {
              if (coupsForces.isEmpty()) {
                coupJoueur = coupsLegaux.get(generateur.nextInt(coupsLegaux.size()));
              } else {
                coupJoueur = coupsForces.get(generateur.nextInt(coupsForces.size()));
              }
              coupAleatoire = true;
            } else if (coupsForces.size() == 1 && commande.equals("")) {
              coupJoueur = coupsForces.get(0);
            } else if (coupsForces.isEmpty() && coupsLegaux.size() == 1 && commande.equals("")) {
              coupJoueur = coupsLegaux.get(0);
            } else {
              coupJoueur = Affichage.ConversionInputCoup(commande, plateau.taille);
            }

            if (coupJoueur == null) {
              Affichage.ecrire("Format invalide. Entrer '?' pour afficher l'aide.");
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
                while (i < coupsLegauxEtForces.size() && !actionLegale) {
                  if (coupJoueur.equals(coupsLegauxEtForces.get(i))) {
                    coupJoueur = coupsLegauxEtForces.get(i);
                    actionLegale = true;
                  }
                  i++;
                }

                if (!actionLegale) {
                  Affichage.erreur("Coup invalide (Entrer \"!\" pour la listes des coups possibles).");
                } else if (prisePossible && !coupJoueur.prise) {
                  Affichage.erreur("Vous avez la possibilté de faire une prise, vous devez donc jouer un tel coup.");
                  actionLegale = false;
                } else {
                  // Prise
                  if (coupJoueur.prise) {
                    joueurInactif.enleverPiece(plateau.getPiece(coupJoueur.x3, coupJoueur.y3));
                  }
                  // Coup
                  plateau.jouerCoup(coupJoueur);
                  Affichage.coupJoue(coupJoueur, joueurActif, coupAleatoire);
                  // Promotion
                  Piece piecePromue = plateau.promotion(pieceDeplacee, joueurActif);
                  if (piecePromue != null) {
                    Affichage.promotion(pieceDeplacee, piecePromue);
                    pieceDeplacee = piecePromue;
                  }
                  // Rafle
                  if (coupJoueur.prise && !pieceDeplacee.calculerCoupsForces(plateau).isEmpty()) {
                    rafle = true;
                    Affichage.rafle(joueurActif, pieceDeplacee);
                    coupsLegaux.clear();
                    coupsForces = pieceDeplacee.calculerCoupsForces(plateau);
                    coupsLegauxEtForces.clear();
                  } else {
                    if (rafle) {
                      Affichage.finRafle(joueurActif);
                      rafle = false;
                    }
                    tour++;
                  }
                }
              }
            }
          }

        }

        if (joueurInactif.listePieces.isEmpty()) {
          partie = false;
          Affichage.fin(plateau, tour, joueurActif + " remporte la victoire.");
        }

      }

      boolean commandeValide = false;
      while (!commandeValide && jeu) {
        String commande = Affichage.demanderCommandeFin();
        if (commande.equals("?")) {
          Affichage.aideFin();
        } else if (commande.equals("!")) {
          commandeValide = true;
          Affichage.effacerEcran();
        } else if (commande.equals("quitter")) {
          jeu = false;
          commandeValide = true;
        } else if (commande.equals("hist")) {
          Affichage.listerCoups(plateau.historiqueCoups);
        }
      }
    }

    Affichage.quitter();

  }

}
