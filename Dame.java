import java.util.ArrayList;

public class Dame extends Piece {

  public Dame(Joueur joueur) {
    super(joueur);
    this.symbole = joueur.symboleDame;
  }

  /**
   * Methode calculant la liste des coups légaux offerts au joueur actif, les
   * coups légaux sont ceux permettant un déplacement simple de la dame, sans
   * prise.
   */
  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();

    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {
        int x2 = x + i;
        int y2 = y + j;
        boolean pieceAtteinte = false;
        while (plateau.dansPlateau(x2, y2) && !pieceAtteinte) {
          if (plateau.getPiece(x2, y2) == null) {
            coupsLegaux.add(new Coup(x, y, x2, y2));
          } else {
            pieceAtteinte = true;
          }
          x2 += i;
          y2 += j;
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
        boolean pieceAtteinte = false;
        while (plateau.dansPlateau(x3, y3) && !pieceAtteinte) {
          if (plateau.getPiece(x3, y3) instanceof Piece) {
            pieceAtteinte = true;
          } else {
            x3 += i;
            y3 += j;
          }
        }
        if (pieceAtteinte) {
          if (plateau.getPiece(x3, y3).joueur != this.joueur) {
            int x2 = x3 + i;
            int y2 = y3 + j;
            pieceAtteinte = false;
            while (plateau.dansPlateau(x2, y2) && !pieceAtteinte) {
              if (plateau.getPiece(x2, y2) == null) {
                coupsForces.add(new Coup(x, y, x2, y2, x3, y3));
              } else {
                pieceAtteinte = true;
              }
              x2 += i;
              y2 += j;
            }
          }
        }
      }

    }
    return coupsForces;
  }

}
