import java.util.ArrayList;

public class Piece {

  public int x;
  public int y;
  public Joueur joueur;
  protected String symbole;

  public Piece(Joueur joueur) {
    this.joueur = joueur;
    joueur.listePieces.add(this);
  }

  public String toString() {
    return joueur.couleur + symbole + Affichage.ANSI_RESET;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    return new ArrayList<Coup>();
  }

}
