import java.util.ArrayList;

public class Dame extends Piece {

  public Dame(Joueur joueur) {
    super(joueur);
    this.symbole = joueur.symbole_dame;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    return new ArrayList<Coup>();
  }

}
