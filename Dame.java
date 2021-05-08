public class Dame extends Piece {

  public Dame(Joueur joueur) {
    super(joueur);
    if (Affichage.COULEUR_ACTIVEE) {
      symbole = "♠";
    } else {
      symbole = (joueur.id == 1) ? "♠" : "♣";
    }

  }

}
