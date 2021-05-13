import java.util.ArrayList;

abstract public class Piece {

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

  /**
   * Methode abstraite calculant la liste de coups légaux, devant être implémentée
   * spécifiquement pour chaque type de pièce (pion et dame)
   */
  public abstract ArrayList<Coup> calculerCoupsLegaux(Plateau plateau);

}
