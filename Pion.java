import java.util.ArrayList;

public class Pion extends Piece {

  public Pion(Joueur joueur) {
    super(joueur);
    this.symbole = joueur.symbole_pion;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();
    int x2;
    int y2;
    for (int i = -1; i <= 1; i += 2) {
      x2 = x + i;
      if (joueur.blanc) {
        y2 = y + 1;
      } else {
        y2 = y - 1;
      }

      if (0 <= x2 && x2 < plateau.taille && 0 <= y2 && y2 < plateau.taille) {
        if (plateau.getPiece(x2, y2) == null) {
          coupsLegaux.add(new Coup(x, y, x2, y2, false));
        }
      }

    }

    return coupsLegaux;
  }

  public ArrayList<Coup> calculerCoupsForces(Plateau plateau) {
    ArrayList<Coup> coupsForces = new ArrayList<Coup>();
    int x3;
    int y3;
    int x2;
    int y2;

    for (int i = -1; i <= 1; i += 2) {
      x3 = x + i;
      x2 = x +2*i;
      if (joueur.blanc) {
        y3 = y + 1;
        y2 = y + 2*i;
      } else {
        y3 = y - 1;
        y2 = y -2*i;
      }

      if (0 <= x3 && x3 < plateau.taille && 0 <= y3 && y3 < plateau.taille) {
        if (plateau.getPiece(x3, y3).joueur != null) {
          if (plateau.getPiece(x3, y3).joueur != Piece.joueur){
          coupsForces.add(new Coup(x, y, x2, y2, true));
          }
        }
      }
    }

    }

    return coupsForces;
  }

}
