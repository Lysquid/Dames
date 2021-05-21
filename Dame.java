import java.util.ArrayList;

public class Dame extends Piece {

  public Dame(Joueur joueur) {
    super(joueur);
    this.symbole = joueur.symbole_dame;
  }

  /**
   * Methode calculant la liste des coups légaux offerts au joueur actif, les
   * coups légaux sont ceux permettant un déplacement simple de la dame, sans
   * prise.
   */
  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();
    int x2;
    int y2;
    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {
        x2 = x + i;
        y2 = y + j;
        while (plateau.getPiece(x2, y2) == null) {
          while (plateau.dansPlateau(x2, y2)) {
            coupsLegaux.add(new Coup(x, y, x2, y2));
            x2 += i;
            y2 += j;
          }
        }
      }
    }
    return new ArrayList<Coup>();
  }

  /**
   * Methode calculant la liste des coups forcés du joueur actif, les coups forcés
   * sont ceux où la prise d'un pion de l'équipe adverse est obligatoire.
   */
  public ArrayList<Coup> calculerCoupsForces(Plateau plateau) {
    ArrayList<Coup> coupsForces = new ArrayList<Coup>();
    int x2;
    int y2;
    int x3;
    int y3;
    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {
        x2 = x + i;
        y2 = y + j;
        x3 = x + 2 * i;
        y3 = x + 2 * j;
        while (plateau.dansPlateau(x2, y2)) {
          while (plateau.getPiece(x2, y2) == null) {
            x2 += i;
            y2 += j;
            x3 += i;
            y3 += j;
          }
          if (plateau.getPiece(x2, y2).joueur != this.joueur) {
            if (plateau.getPiece(x3, y3) == null) {
              coupsForces.add(new Coup(x, y, x2, y2, x3, y3));
            }
          }
        }
      }
    }
    return coupsForces;
  }

}
