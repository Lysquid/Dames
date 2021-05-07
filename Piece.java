class Piece {

  int x;
  int y;
  String symbol;
  Joueur joueur;

  public Piece(Joueur joueur, int x, int y) {
    this.x = x;
    this.y = y;
    this.joueur = joueur;
  }

  public String toString() {
    return joueur.couleur + symbol;
  }

}

