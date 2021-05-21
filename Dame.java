import java.util.ArrayList;

public class Dame extends Piece {

  public Dame(Joueur joueur) {
    super(joueur);
    this.symbole = joueur.symbole_dame;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();
    int x2;
    int y2;
    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {
        x2 = x + i;
        y2 = y + j;
        while (plateau.getPiece(x2, y2) == null) {
          while (0 <= x2 && 10 >= x2 && 0 <= y2 && 10 >= y2) {
            coupsLegaux.add(new Coup(x, y, x2, y2));
            x2 += i;
            y2 += j;
          }
        }
      }
    }
    return new ArrayList<Coup>();
  }

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
        while (plateau.getPiece(x2, y2) != null) {
          while (plateau.getPiece(x2, y2).joueur != this.joueur) {

          }

        }
      }
    }
    return coupsForces;
  }

}
