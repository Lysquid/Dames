import java.util.ArrayList;

public class Pion extends Piece {

  public Pion(Joueur joueur) {
    super(joueur);
    if (Affichage.COULEUR_ACTIVEE) {
      symbole = "●";
    } else {
      symbole = joueur.blanc ? "●" : "○";
    }
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupsLegaux = new ArrayList<Coup>();
    int x2;
    int y2;
    for (int i = -1; i <= 1; i += 2) {
      x2 = x + i;
      if (joueur.blanc) {
        y2 = y - 1;
      } else {
        y2 = y + 1;
      }

      if (0 < x2 && x2 < plateau.taille && 0 < y2 && y2 < plateau.taille) {
        if (plateau.getPiece(x2, y2) == null) {
          coupsLegaux.add(new Coup(x, y, x2, y2, false));
        }
      }

    }
    return coupsLegaux;
  }

}
