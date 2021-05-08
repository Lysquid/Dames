public class Pion extends Piece {

  public Pion(Joueur joueur) {
    super(joueur);
    if (Affichage.COULEUR_ACTIVEE) {
      symbole = "●";
    } else {
      symbole = (joueur.id == 1) ? "●" : "○";
    }
  }

}
