package src;
import java.util.ArrayList;

public class Pion extends Piece {

  public Pion(Joueur joueur) {
    super(joueur);
    this.symbole = joueur.symbolePion;
  }

  /**
   * Methode calculant la liste des coups légaux offerts au joueur actif, les
   * coups légaux sont ceux permettant un déplacement simple du pion, sans prise.
   */
  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();
    for (int i = -1; i <= 1; i += 2) {
      int x2 = x + i;
      int y2;
      if (joueur.blanc) {
        y2 = y + 1;
      } else {
        y2 = y - 1;
      }

      if (plateau.dansPlateau(x2, y2)) {
        if (plateau.getPiece(x2, y2) == null) {
          coupsLegaux.add(new Coup(x, y, x2, y2));
        }
      }

    }

    return coupsLegaux;
  }

  /**
   * Methode calculant la liste des coups forcés du joueur actif, les coups forcés
   * sont ceux où la prise d'un pion de l'équipe adverse est obligatoire.
   */
  public ArrayList<Coup> calculerCoupsForces(Plateau plateau) {
    ArrayList<Coup> coupsForces = new ArrayList<Coup>();

    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {

        int x3 = x + i;
        int y3 = y + j;
        int x2 = x3 + i;
        int y2 = y3 + j;

        if (plateau.dansPlateau(x2, y2)) {
          if (plateau.getPiece(x2, y2) == null && plateau.getPiece(x3, y3) != null) {
            if (plateau.getPiece(x3, y3).joueur != this.joueur) {
              coupsForces.add(new Coup(x, y, x2, y2, x3, y3));
            }
          }
        }
      }
    }

    return coupsForces;
  }

}
